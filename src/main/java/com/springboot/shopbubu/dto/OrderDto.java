    package com.springboot.shopbubu.dto;


    import com.springboot.shopbubu.constant.OrderStatus;

    import lombok.*;

    import java.math.BigDecimal;
    import java.util.Date;
    import java.util.List;


    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor


    public class OrderDto {
        private Long id;
        private Integer customerId;
        private Date orderDate;
        private Date shippedDate;
        private String shipName;
        private String shipAddress;
        private String shipCity;
        private BigDecimal shippingFee = BigDecimal.valueOf(20);
        private OrderStatus orderStatus;
        private BigDecimal sumPrice;
        private OrderDetailDto orderDetails;
        private List<OrderItemDto> orderItems;
    }
