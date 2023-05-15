//package com.org.froom.entity;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
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
//public class FroomOrderDetails {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long uuid;
//    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "froomOrder_Id", referencedColumnName = "uuID")
//    @JsonBackReference
//    private FroomOrders froomOrders;
//
//    private Long customerId;
//    private Long merchantId;
//    private String isActive;
//
//    @Lob
//    private String productDetails;
//
//    private String createdBy;
//    @JsonFormat(pattern = "yyyy/MM/dd HH:MM:SS")
//    private LocalDateTime createdDate;
//    private String updatedBy;
//    @JsonFormat(pattern = "yyyy/MM/dd HH:MM:SS")
//    private LocalDateTime updatedDate;
//
//
//}
