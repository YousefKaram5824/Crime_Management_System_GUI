package com.example.crime_management_system_gui.entities;

import java.time.LocalDate;

public class Citizen extends Person {
    private String ssn;  // Social Security Number (or equivalent)

    public Citizen(String name, LocalDate birthDate, Gender gender, String address, String phoneNumber, String ssn) {
        super(name, birthDate, gender, address, phoneNumber);
        this.ssn = ssn;
    }

    public Citizen() {

    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public void Display_Info() {
        System.out.println("Display Citizen : " + getName() + " Information");
        System.out.println(" ================================================================");
        System.out.println("Name: " + getName());
        System.out.println("Birth Date: " + getBirthDate());
        System.out.println("Gender: " + getGender());
        System.out.println("Address: " + getAddress());
        System.out.println("Phone Number: " + getPhoneNumber());
        System.out.println("SSN: " + getSsn());
    }

}
