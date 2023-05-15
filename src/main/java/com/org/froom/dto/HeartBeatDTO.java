package com.org.froom.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class HeartBeatDTO implements Serializable {
    private String message;
    private String status;
    private String comments;

}
