package com.example.crime_management_system_gui;
import java.time.LocalDate;
import java.util.List;

public class ChiefOfPolice extends DepartmentChief {
    private int yearsOfExperience;

    public ChiefOfPolice(String id, double salary,String password, String departmentName, List<PoliceOfficer> departmentOfficers, int yearsOfExperience) {
        super(id, salary,password, departmentName, departmentOfficers);
        this.yearsOfExperience = yearsOfExperience;
    }

    public ChiefOfPolice(String id, String name, LocalDate birthDate, Gender gender, String address, String phoneNumber, String ssn, double salary, Department department,int Rate, String departmentName, List<PoliceOfficer> departmentOfficers, int yearsOfExperience) {
        super(id, name, birthDate, gender, address, phoneNumber, ssn, salary, department,Rate, departmentName, departmentOfficers);
        this.yearsOfExperience = yearsOfExperience;
    }
    //----------------------------------------------------------------
    public int getYearsOfExperience() {
        return yearsOfExperience;}


    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;}
    //----------------------------------------------------------------

}
