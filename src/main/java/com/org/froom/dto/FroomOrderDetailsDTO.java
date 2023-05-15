//package com.org.froom.dto;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import lombok.*;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@ToString
//@JsonInclude(JsonInclude.Include.NON_NULL)
//public class FroomOrderDetailsDTO {
//    private Long uuid;
//
//    private Long customerId;
//    private Long merchantId;
//    private String isActive;
//
//
//    private List<ProductDetails> productDetails;
//
//    private String createdBy;
////    @JsonFormat(pattern = "yyyy/MM/dd HH:MM:SS")
//    private LocalDateTime createdDate;
//    private String updatedBy;
////    @JsonFormat(pattern = "yyyy/MM/dd HH:MM:SS")
//    private LocalDateTime updatedDate;
//
//    public List<ProductDetails> getProductDetails() {
//        if(productDetails == null) {
//            return new ArrayList<>();
//        }
//        return productDetails;
//    }
//
//    public void setProductDetails(List<ProductDetails> productDetails) {
//        this.productDetails = productDetails;
//    }
//}
