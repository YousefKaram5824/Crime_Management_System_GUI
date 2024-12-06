package com.example.crime_management_system_gui;

import java.time.LocalDate;
import java.util.List;

public class DepartmentChief extends PoliceOfficer {
    private String departmentName;
    private List<PoliceOfficer> departmentOfficers;

    public DepartmentChief(String id, String name, LocalDate birthDate, Gender gender, String address, String phoneNumber, String ssn, double salary, Department department, int Rate, String departmentName, List<PoliceOfficer> departmentOfficers) {
        super(id, name, birthDate, gender, address, phoneNumber, ssn, salary, department, Rate);
        this.departmentName = departmentName;
        this.departmentOfficers = departmentOfficers;
    }

    public DepartmentChief(String id, double salary, String password, String departmentName, List<PoliceOfficer> departmentOfficers) {
        super(id, salary, password);
        this.departmentName = departmentName;
        this.departmentOfficers = departmentOfficers;
    }

    //----------------------------------------------------------------
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<PoliceOfficer> getDepartmentOfficers() {
        return departmentOfficers;
    }

    public void setDepartmentOfficers(List<PoliceOfficer> departmentOfficers) {
        this.departmentOfficers = departmentOfficers;
    }
    //----------------------------------------------------------------
    //----------------------------------------------------------------


}
