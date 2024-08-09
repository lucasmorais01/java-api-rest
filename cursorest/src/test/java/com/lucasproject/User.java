package com.lucasproject;

import javafx.beans.binding.IntegerExpression;

public class User {

    private Long    id;
    private String name;
    private Integer age;
    private Double  salary;


    public User(String string, int i) {
        //TODO Auto-generated constructor stub
    }
    public String getName(){
        return name;
        
    }
    public void setName(String name){
        this.name = name;
    }

    public Integer getAge(){
        return age;
    }
    public void setAge(Integer age) {

        this.age = age;
    }
    public Double getSalary(){
        return salary;
    }
    public void setSalary(Double salary){
        this.salary = salary;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", age=" + age + ", salary=" + salary + "]";
    }
    

}
