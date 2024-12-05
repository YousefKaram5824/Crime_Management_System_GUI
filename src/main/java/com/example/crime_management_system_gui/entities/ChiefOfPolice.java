package com.example.crime_management_system_gui.entities;

import java.time.LocalDate;
import java.util.List;

public class ChiefOfPolice extends DepartmentChief {
    private int yearsOfExperience;

    public ChiefOfPolice(String id, double salary, String password, String departmentName, List<PoliceOfficer> departmentOfficers, int yearsOfExperience) {
        super(id, salary, password, departmentName, departmentOfficers);
        this.yearsOfExperience = yearsOfExperience;
    }

    public ChiefOfPolice(String id, String name, LocalDate birthDate, Gender gender, String address, String phoneNumber, String ssn, double salary, Department department, int Rate, String departmentName, List<PoliceOfficer> departmentOfficers, int yearsOfExperience) {
        super(id, name, birthDate, gender, address, phoneNumber, ssn, salary, department, Rate, departmentName, departmentOfficers);
        this.yearsOfExperience = yearsOfExperience;
    }

    //-----------------------------------------------------------------------------------
    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }
    //-----------------------------------------------------------------------------------


    public void editCaseDescription(Case caseToEdit, String newDescription) {
        caseToEdit.setDescription(newDescription);
        caseToEdit.updateLastUpdated(LocalDate.now().toString());
        System.out.println("Case description updated by Chief of Police: " + getName());
    }

    //---------------------------------
    public void addOfficerToCase(Case caseToEdit, PoliceOfficer officer) {
        caseToEdit.assignOfficer(officer);
        caseToEdit.updateLastUpdated(LocalDate.now().toString());
        System.out.println("Officer added to case by Chief of Police: " + getName());
    }

    //---------------------------------
    public void addCriminalToCase(Case caseToEdit, Criminal criminal) {
        caseToEdit.addCriminal(criminal);
        caseToEdit.updateLastUpdated(LocalDate.now().toString());
        System.out.println("Criminal added to case by Chief of Police: " + getName());
    }

    //---------------------------------
    public void removeCriminalFromCase(Case caseToEdit, Criminal criminal) {
        if (caseToEdit.involvedCriminals.contains(criminal)) {
            caseToEdit.involvedCriminals.remove(criminal);
            caseToEdit.updateLastUpdated(LocalDate.now().toString());
            System.out.println("Criminal removed from case by Chief of Police: " + getName());
        } else {
            System.out.println("Criminal not found in this case.");
        }
    }
    // This would ideally get all cases from the system
//    public List<Case> getAllCasesFromSystem() {
//        // This is just a placeholder. You need to implement how you get all cases.
//        return new ArrayList<>(); // Replace with actual list of all cases.
//    }
}
