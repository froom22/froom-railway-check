//package com.org.froom.dto;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import lombok.*;
//
//import java.io.Serializable;
//import java.time.LocalDateTime;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@ToString
//@JsonInclude(JsonInclude.Include.NON_NULL)
//public class FroomZIPDTO implements Serializable {
//    private Long uuid;
//    private Integer zipCode;
//    private String zipDetails;
//    private FroomZIPStoreDetailsDTO stores;
//    private String isActive;
//    private String createdBy;
//    @JsonFormat(pattern = "yyyy/MM/dd HH:MM:SS")
//    private LocalDateTime createdDate;
//    private String updatedBy;
//    @JsonFormat(pattern = "yyyy/MM/dd HH:MM:SS")
//    private LocalDateTime updatedDate;
//}
