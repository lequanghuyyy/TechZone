package com.springboot.shopbubu.repository.Impl;

import com.springboot.shopbubu.dto.ProductSummaryDto;
import com.springboot.shopbubu.entity.CategoryEntity;
import com.springboot.shopbubu.entity.ProductDetailEntity;
import com.springboot.shopbubu.entity.ProductEntity;

import com.springboot.shopbubu.repository.ProductCustomRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.apache.commons.lang3.StringUtils;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Repository("productNativeQueryCustomRepository")
public class ProductNativeQueryProductRepository implements ProductCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<ProductEntity> listProducts(String keySearch, int page, int pageSize, BigDecimal minPrice, BigDecimal maxPrice) {
        StringBuilder sql = new StringBuilder("select products.product_code, products.image, products.number_purchase, products.price, products.product_name, products.id, products.created_at, products.updated_at, products.category_id, " +
                                                      "product_details.id, product_details.description, product_details.is_featured, product_details.is_new, product_details.stock_quantity " +
                                                      "from products, product_details where products.id = product_details.product_id ");
        List<Object> params = new ArrayList<>();
        if(StringUtils.isNotBlank(keySearch)) {
            sql.append("and ( product_name like ? ) ");
            params.add("%" + keySearch + "%");
        }
        if (minPrice != null){
            sql.append("and (products.price >= ? ) ");
            params.add(minPrice);
        }
        if (maxPrice != null){
            sql.append("and (products.price <= ? ) ");
            params.add(maxPrice);
        }


        sql.append("LIMIT ? OFFSET ?");
        params.add(pageSize);
        params.add((page-1)*pageSize);

        Query query = entityManager.createNativeQuery(sql.toString());
        for (int i = 0; i < params.size(); i++) {
            query.setParameter(i + 1, params.get(i));
        }
        return convertToProductList(query.getResultList());
    }

    @Override
    public long countProducts(String keySearch, BigDecimal minPrice, BigDecimal maxPrice) {
        StringBuilder sql = new StringBuilder("select count(*) from products where 1=1");
        List<Object> params = new ArrayList<>();
        if(StringUtils.isNotBlank(keySearch)) {
            sql.append(" and product_name like ? ");
            params.add("%" + keySearch + "%");
        }
        if (minPrice != null) {
            sql.append(" and price >= ? ");
            params.add(minPrice);
        }
        if (maxPrice != null) {
            sql.append(" and price <= ? ");
            params.add(maxPrice);
        }
        Query query = entityManager.createNativeQuery(sql.toString());
        for (int i = 0; i < params.size(); i++) {
            query.setParameter(i + 1, params.get(i));
        }
        return ((Number) query.getSingleResult()).longValue();
    }
    public static List<ProductEntity> convertToProductList(List<Object[]> products) {
        List<ProductEntity> productEntities = new ArrayList<>();
        for (Object[] product: products) {
            ProductEntity productEntity = new ProductEntity();
            ProductDetailEntity productDetailEntity = new ProductDetailEntity();
            productEntity.setProductCode((String) product[0]);
            productEntity.setImage((String) product[1]);
            productEntity.setNumberPurchase((Integer) product[2]);
            productEntity.setPrice((BigDecimal) product[3]);
            productEntity.setProductName((String) product[4]);
            productEntity.setId((Long) product[5]);
            productEntity.setCreatedAt((Date) product[6]);
            productEntity.setUpdatedAt((Date) product[7]);
            productEntity.setCategory(new CategoryEntity((Long) product[8]));
            productDetailEntity.setId((Long) product[9]);
            productDetailEntity.setDescription((String) product[10]);
            productDetailEntity.setIsFeatured((Boolean) product[11]);
            productDetailEntity.setIsNew((Boolean) product[12]);
            productDetailEntity.setStockQuantity((Integer) product[13]);
            productEntity.setProductDetail(productDetailEntity);
            productEntities.add(productEntity);
        }
        return productEntities;
    }
}
