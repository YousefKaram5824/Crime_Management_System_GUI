package com.example.crime_management_system_gui.entities;

import java.time.LocalDate;

enum Gender {Male, Female}

public abstract class Person {
    protected String name;
    protected LocalDate birthDate;
    protected Gender gender;
    protected String address;
    protected String phoneNumber;

    public Person() {
    }

    public Person(String name, LocalDate birthDate, Gender gender, String address, String phoneNumber) {
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public abstract void Display_Info();
}
