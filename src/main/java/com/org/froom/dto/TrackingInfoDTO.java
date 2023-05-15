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
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
//@JsonInclude(JsonInclude.Include.NON_NULL)
//public class TrackingInfoDTO implements Serializable {
//    private String transporterName;
//    private long trackingId;
//    @JsonFormat(pattern = "yyyy/MM/dd HH:MM:SS")
//    private LocalDateTime shippedDate;
//    private String comments;
//    @JsonFormat(pattern = "yyyy/MM/dd HH:MM:SS")
//    private LocalDateTime expectedDateOfShipping;
//    private String shipmentStatus;
//
//}
