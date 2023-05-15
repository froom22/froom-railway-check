//package com.org.froom.entity;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class FroomOrders {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long uuID;
//
//    private String transactionID;
//    private String comments;
//
//    @OneToOne(mappedBy = "froomOrders", fetch = FetchType.EAGER)
//    @JsonManagedReference
//    private FroomOrderDetails froomOrderDetails;
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
//    private String trackingInfo;
//}
