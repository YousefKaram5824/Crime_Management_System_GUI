package com.example.crime_management_system_gui;

import java.util.ArrayList;
import java.util.List;

public class Department {
    private final String id;
    private final String name;
    private final String activationDate;
    private  List<PoliceOfficer> officers;
    private  List<Case> cases;

    public Department(String id, String name, String activationDate) {
        this.id = id;
        this.name = name;
        this.activationDate = activationDate;
        this.officers = new ArrayList<>();
        this.cases = new ArrayList<>();
    }

    //----------------------------------------------------------------
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getActivationDate() {
        return activationDate;
    }

    public List<Case> getCases() {return cases;}
    public void setCases(List<Case> cases) {this.cases = cases;}

    public List<PoliceOfficer> getOfficers() {return officers;}
    public void setOfficers(List<PoliceOfficer> officers) {this.officers = officers;}
    //----------------------------------------------------------------

    public void addOfficer(PoliceOfficer officer) {
        officers.add(officer);
    }

    public void addCase(Case caseFile) {
        cases.add(caseFile);
    }


    public String toString() {
        return "Department{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", activationDate='" + activationDate + '\'' + ", officers=" + officers.size() + ", cases=" + cases.size() + '}';
    }
}
