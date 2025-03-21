package com.springboot.shopbubu.dto.response;



import com.springboot.shopbubu.constant.AppConstant;
import org.springframework.http.ResponseEntity;

public class ResponseFactory {
    private ResponseFactory() {}

    public static <T> ResponseEntity<BaseResponse<T>> ok(T data) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setData(data);
        response.setStatus(AppConstant.SUCCESS_CODE);
        response.setDescription("success");
        return ResponseEntity.ok(response);
    }
    public static ResponseEntity<BaseResponse<String>> error(String m) {
        BaseResponse<String> response = new BaseResponse<>();
        response.setData(m);
        response.setStatus(AppConstant.Fail_CODE);
        response.setDescription("fail");
        return ResponseEntity.badRequest().body(response);
    }
}
