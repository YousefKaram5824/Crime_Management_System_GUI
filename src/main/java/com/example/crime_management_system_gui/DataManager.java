package com.example.crime_management_system_gui;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static final String USER_FILE_NAME = "files/users.txt";
    private static final String REPORT_FILE_NAME = "files/reports.txt";
    private static final String DEPARTMENT_FILE = "files/departments.txt";
    private static final String CRIMINAL_FILE = "files/criminals.txt";
    private static final String ASSIGNED_CASES_FILE = "files/assigned_cases.txt"; // New file

    private final List<String> userData = new ArrayList<>();
    private final List<String> reports = new ArrayList<>();
    private final List<String> departments = new ArrayList<>();
    private final List<String> criminals = new ArrayList<>();
    private final List<String> assignedCases = new ArrayList<>();

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
            if (userDetails[5].equals(departmentName)) {
                userIds.add(userDetails[1]);
            }
        }
        return userIds;
    }

    public List<String> getCasesIdsByDepartmentName(String departmentName) {
        List<String> caseIds = new ArrayList<>();
        for (String reportData : getReports()) {
            String[] reportDetails = reportData.split(",");
            if (reportDetails[4].equals(departmentName)) {
                caseIds.add(reportDetails[0]);
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
                reports.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading reports: " + e.getMessage());
        }
    }

    public void saveReport() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(REPORT_FILE_NAME))) {
            for (String data : reports) {
                writer.write(data);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing reports: " + e.getMessage());
        }
    }

    public List<String> getReports() {
        return reports;
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