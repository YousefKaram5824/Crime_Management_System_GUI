package com.example.crime_management_system_gui.entities;

import java.util.ArrayList;
import java.util.List;


public class Case {
    protected final Department department;
    private final String id;
    private final String startDate;
    private final String crimeType;
    protected List<PoliceOfficer> assignedOfficers;
    protected List<Criminal> involvedCriminals;
    private String description;
    private String lastUpdated;

    public Case(String id, String description, String startDate, String crimeType, Department department) {
        this.id = id;
        this.description = description;
        this.startDate = startDate;
        this.lastUpdated = startDate; // Initialize as start date
        this.crimeType = crimeType;
        this.department = department;
        this.assignedOfficers = new ArrayList<>();
        this.involvedCriminals = new ArrayList<>();
    }

    //--------------------------------------------------------------------------------------
    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void updateLastUpdated(String date) {
        this.lastUpdated = date;
    }

    public void assignOfficer(PoliceOfficer officer) {
        assignedOfficers.add(officer);
    }

    public void addCriminal(Criminal criminal) {
        involvedCriminals.add(criminal);
    }

    public void removeCriminal(Criminal criminal) {
        involvedCriminals.remove(criminal);
    }

    //--------------------------------------------------------------------------------------
    public boolean isOfficerAssigned(PoliceOfficer officer) {
        return assignedOfficers.contains(officer);
    }

    public boolean isCriminalInvolved(Criminal criminal) {
        return involvedCriminals.contains(criminal);
    }

    //--------------------------------------------------------------------------------------
    public String toString() {
        return "\nCase{" + "id='" + id + '\'' + ", description='" + description + '\'' + ", startDate='" + startDate + '\'' + ", lastUpdated='" + lastUpdated + '\'' + ", crimeType='" + crimeType + '\'' + '}' + '\n';
    }
}
