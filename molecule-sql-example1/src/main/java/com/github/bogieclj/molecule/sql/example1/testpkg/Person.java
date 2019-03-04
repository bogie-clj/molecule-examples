package com.github.bogieclj.molecule.sql.example1.testpkg;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class Person {

    private String name;
    private String sex;
    private Integer age;

    @JsonCreator
    public static Person createPerson(@JsonProperty("name") String name, @JsonProperty("sex") String sex, @JsonProperty("age") Integer age){
        return new Person(name,sex,age);
    }

}
