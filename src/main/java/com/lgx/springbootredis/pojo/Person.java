package com.lgx.springbootredis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author lgx
 * @version 1.0
 * @date 2022/9/18 17:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {//gson测试用
    private int id;
    private String name;
    private List<Hobby> hobbies;
}
@Data
@AllArgsConstructor
@NoArgsConstructor
class Hobby{
    private String id;
    private String name;
}
