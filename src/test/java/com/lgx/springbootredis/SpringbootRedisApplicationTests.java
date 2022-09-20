package com.lgx.springbootredis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lgx.springbootredis.pojo.Employee;
import com.lgx.springbootredis.pojo.Person;
import com.lgx.springbootredis.pojo.User;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


@SpringBootTest
class SpringbootRedisApplicationTests {
    private static final Logger log = LoggerFactory.getLogger(SpringbootRedisApplicationTests.class);
    @Resource
    private RedisTemplate redisTemplate;

    @Test
    void logTest() {
        System.out.println(new Date());
        //日志级别：trace<debug<info<warn<error
        //springboot默认日志级别info
        log.trace("this is trace");
        log.debug("this is debug");
        log.info("this is info");
        log.warn("this is warn");
        log.error("this is error");
    }

    @Test
    void GsonTest() {
        //java对象转json
        Employee employee = new Employee(1001, "李", "序", "1059648185@qq.com");
        Gson gson = new Gson();
        String s = gson.toJson(employee);
        System.out.println("java对象转json======>" + s);
        //json字符串转java对象
        String jsonString = "{'id':1002, 'firstName':'Lokesh', 'lastName':'Gupta', 'email':'howtodoinjava@gmail.com'}";
        Employee employee1 = gson.fromJson(jsonString, Employee.class);
        System.out.println("json字符串转java对象===>" + employee1);
        Gson gson1 = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        Employee employeeObj = new Employee(1, "Lokesh", "Gupta", "howtogoinjava@gmail.com");
        System.out.println(gson1.toJson(employeeObj));
       //json数组转Java对象
        String jsonStr = "[{'id':1002, 'firstName':'Lokesh', 'lastName':'Gupta', 'email':'howtodoinjava@gmail.com'},"+
                "{'id':1002, 'firstName':'Lokesh', 'lastName':'Gupta', 'email':'howtodoinjava@gmail.com'},"+
                "{'id':1002, 'firstName':'Lokesh', 'lastName':'Gupta', 'email':'howtodoinjava@gmail.com'}]";
        Employee[] employees = gson.fromJson(jsonStr, Employee[].class);
        System.out.println("json数组转Java对象数组"+Arrays.toString(employees));
        //json数组转Java对象集合
        String jsonStr2 = "[{'id':1002, 'firstName':'Lokesh', 'lastName':'Gupta', 'email':'howtodoinjava@gmail.com'},"+
                "{'id':1002, 'firstName':'Lokesh', 'lastName':'Gupta', 'email':'howtodoinjava@gmail.com'},"+
                "{'id':1002, 'firstName':'Lokesh', 'lastName':'Gupta', 'email':'howtodoinjava@gmail.com'}]";
        Type type = new TypeToken<ArrayList<Employee>>() {
        }.getType();
        ArrayList<Employee> empList = gson.fromJson(jsonStr2, type);
        System.out.println("json数组转Java对象集合"+empList);
       //对象内部含有List集合
        String jsonstring2= "{'id' : 1, "
                + "'name': 'HR',"
                + "'hobbies' : ["
                + "{'name': 'Alex','id': 1}, "
                + "{'name': 'Brian','id':2}, "
                + "{'name': 'Charles','id': 3}]}";
        Person person = gson.fromJson(jsonstring2, Person.class);
        System.out.println(person);
    }
}
