package com.example.Springcloud.entity;

import com.google.common.net.InternetDomainName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Iterator;

/**
 * @author yzj
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sentinelpojo {
    private Integer code;
    private String message;
}
