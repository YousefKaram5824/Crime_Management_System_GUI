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
    private static final String UPDATED_CRIMINAL_CASES_FILE = "files/updated_criminal_cases.txt";

    private final List<String> userData = new ArrayList<>();
    private final List<String> cases = new ArrayList<>();
    private final List<String> departments = new ArrayList<>();
    private final List<String> criminals = new ArrayList<>();
    private final List<String> assignedCases = new ArrayList<>();
    private final List<String> criminalCases = new ArrayList<>();
    private final List<String> updatedCriminalCases = new ArrayList<>();

    /*======================Start of User data====================================*/

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
        getUserData().set(index, newData);
    }

    /*======================End of User data====================================*/

    /*======================Start of Criminal data====================================*/


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
        for (String data : getCriminalsData()) {
            String[] criminalDetails = data.split(",");
            if (criminalDetails[1].equals(criminalId)) {
                return false;
            }
        }
        return true;
    }

    public String getCriminalDataById(String criminalId) {
        for (String data : getCriminalsData()) {
            String[] criminalDetails = data.split(",");
            if (criminalDetails[1].equals(criminalId)) {
                return data;
            }
        }
        return null;
    }

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
        for (String data : getUpdatedCriminalCases()) {
            String[] criminalCasesDetails = data.split(",");
            if (criminalCasesDetails[0].equals(caseId) && criminalCasesDetails[2].equals(criminalId)) {
                return true;
            }
        }
        return false;
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

    public List<String> getCriminalsIdsByCaseId(String id) {
        List<String> criminalIds = new ArrayList<>();
        for (String criminalCases : getUpdatedCriminalCases()) {
            String[] casesDetails = criminalCases.split(",");
            if (casesDetails[0].equals(id)) {
                criminalIds.add(casesDetails[2]);
            }
        }
        return criminalIds;
    }

    public int getNumberOfCasesForCriminal(String id) {
        int num = 0;
        for (String criminalCases : getCriminalCasesData()) {
            String[] casesDetails = criminalCases.split(",");
            if (casesDetails[1].equals(id)) {
                num++;
            }
        }
        return num;
    }

    public String getSolvedCaseDataById(String caseId) {
        for (String data : getCriminalCasesData()) {
            String[] caseDetails = data.split(",");
            if (caseDetails[0].equals(caseId)) {
                return data;
            }
        }
        return null;
    }

    public boolean isCaseIsSolved(String caseId) {
        for (String data : getCriminalCasesData()) {
            String[] casesDetails = data.split(",");
            if (casesDetails[0].equals(caseId) && casesDetails[3].equals("yes")) {
                return true;
            }
        }
        return false;
    }

    /*======================End of Criminal data====================================*/

    /*======================Start of Department data====================================*/

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
            for (String data : getDepartmentsData()) {
                writer.write(data);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing department data: " + e.getMessage());
        }
    }

    public boolean isDepartmentIdUnique(String departmentId) {
        for (String data : getDepartmentsData()) {
            String[] departmentDetails = data.split(",");
            if (departmentDetails[1].equals(departmentId)) {
                return false;
            }
        }
        return true;
    }

    public boolean isDepartmentNameUnique(String departmentName) {
        for (String data : getDepartmentsData()) {
            String[] departmentDetails = data.split(",");
            if (departmentDetails[0].equals(departmentName)) {
                return false;
            }
        }
        return true;
    }

    public String getDepartmentDataByName(String departmentName) {
        for (String data : getDepartmentsData()) {
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

    /*======================End of Department data====================================*/

    /*======================Start of Case data====================================*/

    public void loadCase() {
        try (BufferedReader reader = new BufferedReader(new FileReader(REPORT_FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                cases.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading reports: " + e.getMessage());
        }
    }

    public void saveCase() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(REPORT_FILE_NAME))) {
            for (String data : cases) {
                writer.write(data);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing reports: " + e.getMessage());
        }
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

    public String getCaseDataById(String caseId) {
        for (String data : getCases()) {
            String[] caseDetails = data.split(",");
            if (caseDetails[0].equals(caseId)) {
                return data;
            }
        }
        return null;
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
            for (String data : getAssignedCases()) {
                writer.write(data);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing assigned cases data: " + e.getMessage());
        }
    }

    /*======================End of Case data====================================*/

    /*======================Start of Updated Case data====================================*/

    public void loadUpdatedCriminalCases() {
        try (BufferedReader reader = new BufferedReader(new FileReader(UPDATED_CRIMINAL_CASES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                getUpdatedCriminalCases().add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading assigned cases data: " + e.getMessage());
        }
    }

    public void saveUpdatedCriminalCases() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(UPDATED_CRIMINAL_CASES_FILE))) {
            for (String data : getUpdatedCriminalCases()) {
                writer.write(data);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing assigned cases data: " + e.getMessage());
        }
    }

    public String getUpdatedCaseDataById(String caseId) {
        for (String data : getUpdatedCriminalCases()) {
            String[] caseDetails = data.split(",");
            if (caseDetails[0].equals(caseId)) {
                return data;
            }
        }
        return null;
    }

    /*======================End of Updated Case data====================================*/

    /*==========================Getters============================ */

    public List<String> getCriminalCasesData() {
        return criminalCases;
    }

    public List<String> getDepartmentsData() {
        return departments;
    }

    public List<String> getCriminalsData() {
        return criminals;
    }

    public List<String> getUserData() {
        return userData;
    }

    public List<String> getCases() {
        return cases;
    }

    public List<String> getAssignedCases() {
        return assignedCases;
    }
    public List<String> getUpdatedCriminalCases() {
        return updatedCriminalCases;
    }
}