package com.org.froom.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FroomZIP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uuid;
    private Integer zipCode;
    private String zipDetails;
    @Lob
    private String stores;
    private String isActive;
    private String createdBy;
    @JsonFormat(pattern = "yyyy/MM/dd HH:MM:SS")
    private LocalDateTime createdDate;
    private String updatedBy;
    @JsonFormat(pattern = "yyyy/MM/dd HH:MM:SS")
    private LocalDateTime updatedDate;
    // longtext - storeDescription.
    // longtext - Address Line1:
    // longtext - Address Line2:
    // Varchar(30) - City:
    // Varchar(2) - State
    // Varchar(5) - Zip:

}
