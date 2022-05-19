package com.example.Springcloud.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author yzj
 * @date 2022/05/08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role   {
    private String username;
    private String permissions1;
    private String permissions2;
    private String permissions3;
}
