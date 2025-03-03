package com.dci.exercises.mvcAnnotations.domain;

public final class Employee {

    private static int count = 0;
    private int id;
    private final String firstName;
    private final String lastName;
    private final Integer age;
    private final String position;


    public Employee(String firstName, String lastName, Integer age, String position) {
        this.id = id + 1;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.position = position;
        setId(++count);
    }

    public Integer getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", position='" + position + '\'' +
                '}';
    }
}
