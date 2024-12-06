package com.example.crime_management_system_gui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

enum Danger {Low, Moderate, High, Very_High}

public class Criminal extends Citizen {
    private final String id;
    private String currentLocation;
    private Danger dangerLevel; // "Low", "Moderate", "High", "Very High"
    private List<Case> crimesCommitted;

    // Constructor
    public Criminal(String id, String name, LocalDate birthDate, Gender gender, String address, String phoneNumber, String ssn, String currentLocation, Danger dangerLevel) {
        super(name, birthDate, gender, address, phoneNumber, ssn); // Call Citizen's constructor
        this.id = id;
        this.currentLocation = currentLocation;
        this.dangerLevel = dangerLevel;
        this.crimesCommitted = new ArrayList<>();
    }

    public Criminal(String id, String currentLocation, Danger dangerLevel, List<Case> crimesCommitted) {
        this.id = id;
        this.currentLocation = currentLocation;
        this.dangerLevel = dangerLevel;
        this.crimesCommitted = crimesCommitted;
    }

    //----------------------------------------------------------------
    public Danger getDangerLevel() {
        return dangerLevel;
    }

    public void setDangerLevel(Danger dangerLevel) {
        this.dangerLevel = dangerLevel;
    }

    public String getId() {
        return id;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public List<Case> getCrimesCommitted() {
        return crimesCommitted;
    }

    public void setCrimesCommitted(List<Case> crimesCommitted) {
        this.crimesCommitted = crimesCommitted;
    }
    //----------------------------------------------------------------


    // Add a crime to the list
    public void addCrime(Case caseFile) {
        crimesCommitted.add(caseFile);
    }

    public void Display_Info() {
        System.out.println("Display Criminal : " + getName() + " Informations");
        System.out.println("================================================================");
        System.out.println("Name:  " + getName());
        System.out.println("criminal ID : " + getId());
        System.out.println("Danger Level: " + getDangerLevel());
        System.out.println("Birth Date: " + getBirthDate());
        System.out.println("Gender: " + getGender());
        System.out.println("Address: " + getAddress());
        System.out.println("Phone Number: " + getPhoneNumber());
        System.out.println("SSN: " + getSsn());
        System.out.println("Current Location: " + getCurrentLocation());
        System.out.println("Cases Committed: ");
        // need to implement display cases function

    }

}
