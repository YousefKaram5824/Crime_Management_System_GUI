package com.example.crime_management_system_gui;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static final String USER_FILE_NAME = "files/users.txt";
    private static final String REPORT_FILE_NAME = "files/reports.txt";
    private static final String DEPARTMENT_FILE = "files/departments.txt";
    private static final String CRIMINAL_FILE = "files/criminals.txt";
    private static final String ASSIGNED_CASES_FILE = "files/assigned_cases.txt";
    private static final String CRIMINAL_CASES_FILE = "files/criminal_cases.txt";

    private final List<String> userData = new ArrayList<>();
    private final List<String> cases = new ArrayList<>();
    private final List<String> departments = new ArrayList<>();
    private final List<String> criminals = new ArrayList<>();
    private final List<String> assignedCases = new ArrayList<>();
    private final List<String> criminalCases = new ArrayList<>();

    public void loadCriminalCasesData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CRIMINAL_CASES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                criminalCases.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading user data: " + e.getMessage());
        }
    }

    public void saveCriminalCasesData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CRIMINAL_CASES_FILE))) {
            for (String data : criminalCases) {
                writer.write(data);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing user data: " + e.getMessage());
        }
    }

    public boolean isCaseAssignedToCriminal(String caseId, String criminalId) {
        for (String data : criminalCases) {
            String[] criminalCasesDetails = data.split(",");
            if (criminalCasesDetails[0].equals(caseId) && criminalCasesDetails[1].equals(criminalId)) {
                return false;
            }
        }
        return true;
    }

    public List<String> getCasesIdsByCriminalId(String id) {
        List<String> caseIds = new ArrayList<>();
        for (String criminalCases : getCriminalCasesData()) {
            String[] casesDetails = criminalCases.split(",");
            if (casesDetails[1].equals(id)) {
                caseIds.add(casesDetails[0]);
            }
        }
        return caseIds;
    }

    public List<String> getCriminalCasesData() {
        return criminalCases;
    }

    public void loadDepartmentData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DEPARTMENT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                departments.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading department data: " + e.getMessage());
        }
    }

    public void saveDepartmentData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DEPARTMENT_FILE))) {
            for (String data : departments) {
                writer.write(data);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing department data: " + e.getMessage());
        }
    }

    public boolean isDepartmentIdUnique(String departmentId) {
        for (String data : departments) {
            String[] departmentDetails = data.split(",");
            if (departmentDetails[1].equals(departmentId)) {
                return false;
            }
        }
        return true;
    }

    public boolean isDepartmentNameUnique(String departmentName) {
        for (String data : departments) {
            String[] departmentDetails = data.split(",");
            if (departmentDetails[0].equals(departmentName)) {
                return false;
            }
        }
        return true;
    }

    public String getDepartmentDataByName(String departmentName) {
        for (String data : departments) {
            String[] departmentDetails = data.split(",");
            if (departmentDetails[1].equals(departmentName)) {
                return data;
            }
        }
        return null;
    }

    public List<String> getUserIdsByDepartmentName(String departmentName) {
        List<String> userIds = new ArrayList<>();
        for (String userData : getUserData()) {
            String[] userDetails = userData.split(",");
            if (userDetails[6].equals(departmentName)) {
                userIds.add(userDetails[1]);
            }
        }
        return userIds;
    }

    public List<String> getCasesIdsByDepartmentName(String departmentName) {
        List<String> caseIds = new ArrayList<>();
        for (String reportData : getCases()) {
            String[] reportDetails = reportData.split(",");
            if (reportDetails[3].equals(departmentName)) {
                caseIds.add(reportDetails[0]);
            }
        }
        return caseIds;
    }

    public List<String> getCasesIdsByOfficerId(String id) {
        List<String> caseIds = new ArrayList<>();
        for (String AssignedCases : getAssignedCases()) {
            String[] casesDetails = AssignedCases.split(",");
            if (casesDetails[1].equals(id)) {
                caseIds.add(casesDetails[0]);
            }
        }
        return caseIds;
    }

    public List<String> getDepartmentsData() {
        return departments;
    }

    public void loadCriminalData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CRIMINAL_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                criminals.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading criminal data: " + e.getMessage());
        }
    }

    public void saveCriminalData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CRIMINAL_FILE))) {
            for (String data : criminals) {
                writer.write(data);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing criminal data: " + e.getMessage());
        }
    }

    public boolean isCriminalIdUnique(String criminalId) {
        for (String data : criminals) {
            String[] criminalDetails = data.split(",");
            if (criminalDetails[1].equals(criminalId)) {
                return false;
            }
        }
        return true;
    }

    public String getCriminalDataById(String criminalId) {
        for (String data : criminals) {
            String[] criminalDetails = data.split(",");
            if (criminalDetails[1].equals(criminalId)) {
                return data;
            }
        }
        return null;
    }

    public List<String> getCriminalsData() {
        return criminals;
    }

    public void loadUserData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                userData.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading user data: " + e.getMessage());
        }
    }

    public void saveUserData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE_NAME))) {
            for (String data : userData) {
                writer.write(data);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing user data: " + e.getMessage());
        }
    }

    public boolean isUserIdUnique(String userId) {
        for (String data : userData) {
            String[] userDetails = data.split(",");
            if (userDetails[1].equals(userId)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkCredentials(String id, String password) {
        for (String data : getUserData()) {
            String[] userData = data.split(",");
            if (userData[1].equals(id) && userData[5].equals(password)) {
                return true;
            }
        }
        return false;
    }

    public String getUserDataById(String userId) {
        for (String data : getUserData()) {
            String[] userDetails = data.split(",");
            if (userDetails[1].equals(userId)) {
                return data;
            }
        }
        return null;
    }

    public void updateUserData(int index, String newData) {
        userData.set(index, newData);
    }

    public List<String> getUserData() {
        return userData;
    }

    public void loadReports() {
        try (BufferedReader reader = new BufferedReader(new FileReader(REPORT_FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                cases.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading reports: " + e.getMessage());
        }
    }

    public void saveReport() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(REPORT_FILE_NAME))) {
            for (String data : cases) {
                writer.write(data);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing reports: " + e.getMessage());
        }
    }

    public List<String> getCases() {
        return cases;
    }

    public String getCaseDataById(String caseId) {
        for (String data : cases) {
            String[] caseDetails = data.split(",");
            if (caseDetails[0].equals(caseId)) {
                return data;
            }
        }
        return null;
    }

    public boolean isCaseAssignedToOfficer(String caseId, String userId) {
        for (String data : assignedCases) {
            String[] assignedCasesDetails = data.split(",");
            if (assignedCasesDetails[0].equals(caseId) && assignedCasesDetails[1].equals(userId)) {
                return false;
            }
        }
        return true;
    }

    public void loadAssignedCases() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ASSIGNED_CASES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                assignedCases.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading assigned cases data: " + e.getMessage());
        }
    }

    public void saveAssignedCases() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ASSIGNED_CASES_FILE))) {
            for (String data : assignedCases) {
                writer.write(data);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing assigned cases data: " + e.getMessage());
        }
    }

    public List<String> getAssignedCases() {
        return assignedCases;
    }
}