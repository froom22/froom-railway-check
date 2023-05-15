//package com.org.froom.dto;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import lombok.*;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@ToString
//@JsonInclude(JsonInclude.Include.NON_NULL)
//public class FroomOrdersDTO {
//    private Long uuID;
//
//    private String transactionID;
//    private String comments;
//
//    private FroomOrderDetailsDTO froomOrderDetailsDTO;
//
//
//    private Long froomZipId;
//    private Long merchantId;
//    private Long customerId;
//
//    private String isActive;
//    private String froomOrderStatus;
//
//    private String createdBy;
//    @JsonFormat(pattern = "yyyy/MM/dd HH:MM:SS")
//    private LocalDateTime createdDate;
//    private String updatedBy;
//    @JsonFormat(pattern = "yyyy/MM/dd HH:MM:SS")
//    private LocalDateTime updatedDate;
//    private String trackingID;
//    @Lob
//    private TrackingInfoDTO trackingInfo;
//}
