package com.dci.exercises.mvcAnnotations.dto;

public class EmployeeReqDto {


    private final String firstName;
    private final String lastName;
    private final Integer age;
    private final String position;

    public EmployeeReqDto(String firstName, String lastName, Integer age, String position) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.position = position;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public String getPosition() {
        return position;
    }

}
