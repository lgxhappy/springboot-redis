package com.lgx.springbootredis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lgx
 * @version 1.0
 * @date 2022/9/18 16:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {//gson测试用
    private int id;
    private String firstName;
    private String lastName;
    private String email;
}
