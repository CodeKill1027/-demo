package com.example.Springcloud.entity;

import com.example.Springcloud.annotation.Zhujie.MyAnno;

import java.io.Serializable;

/**
 * @author yzj
 */
@MyAnno("对象")
public class Student  implements Serializable {

        private String name;
       private Integer age;
        @MyAnno(value = "属性")
        private String sex;
        public Student(String name, int age, String sex){
            this.name=name;
            this.age=age;
            this.sex=sex;
        }
        public Student(){ }

    @MyAnno(value = "男")
    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public String getName() {
        return name; }

    public int getAge() {
        return age; }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

}



