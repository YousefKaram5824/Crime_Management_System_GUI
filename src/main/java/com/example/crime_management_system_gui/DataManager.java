package com.example.crime_management_system_gui;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static final String USER_FILE_NAME = "users.txt";
    private static final String REPORT_FILE_NAME = "reports.txt";
    private static final String LAST_REPORT_ID_FILE = "lastReportId.txt";
    private static final String DEPARTMENT_FILE = "department.txt";
    private final List<String> userData = new ArrayList<>();
    private final List<String> reports = new ArrayList<>();
    private final List<String> departments = new ArrayList<>();
    private int lastReportId;


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
            if (departmentDetails[0].equals(departmentId)) {
                return false;
            }
        }
        return true;
    }

    public boolean isDepartmentNameUnique(String departmentName) {
        for (String data : departments) {
            String[] departmentDetails = data.split(",");
            if (departmentDetails[1].equals(departmentName)) {
                return false;
            }
        }
        return true;
    }

    public List<String> getDepartmentsData() {
        return departments;
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

    public List<String> getUserData() {
        return userData;
    }

    public void updateUserData(int index, String newData) {
        userData.set(index, newData);
    }

    public boolean isUserIdUnique(String userId) {
        for (String data : userData) {
            String[] userDetails = data.split(",");
            if (userDetails[0].equals(userId)) {
                return false;
            }
        }
        return true;
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

    public void saveReport(String reportData) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(REPORT_FILE_NAME, true))) {
            writer.write(reportData);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error saving report: " + e.getMessage());
        }
    }

    public void loadLastReportId() {
        lastReportId = 1;
        try (BufferedReader reader = new BufferedReader(new FileReader(LAST_REPORT_ID_FILE))) {
            String line = reader.readLine();
            if (line != null) {
                lastReportId = Integer.parseInt(line);
            }
        } catch (IOException | NumberFormatException e) {
            lastReportId = 0;
        }
    }

    public void saveLastReportId() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LAST_REPORT_ID_FILE))) {
            writer.write(String.valueOf(lastReportId));
        } catch (IOException e) {
            System.err.println("Error saving last report ID: " + e.getMessage());
        }
    }

    public int getNextReportId() {
        return lastReportId++;
    }

    public void incrementLastReportId() {
        lastReportId++;
    }

    public List<String> getReports() {
        return reports;
    }
}