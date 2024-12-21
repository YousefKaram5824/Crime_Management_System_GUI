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

    /*======================Start of Officer data====================================*/

    public void loadOfficerData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                getOfficerData().add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading user data: " + e.getMessage());
        }
    }

    public void saveOfficerData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE_NAME))) {
            for (String data : getOfficerData()) {
                writer.write(data);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing user data: " + e.getMessage());
        }
    }

    public boolean isOfficerIdUnique(String userId) {
        for (String data : getOfficerData()) {
            String[] userDetails = data.split(",");
            if (userDetails[1].equals(userId)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkCredentials(String id, String password) {
        for (String data : getOfficerData()) {
            String[] userData = data.split(",");
            if (userData[1].equals(id) && userData[5].equals(password)) {
                return true;
            }
        }
        return false;
    }

    public String getOfficerDataById(String userId) {
        for (String data : getOfficerData()) {
            String[] userDetails = data.split(",");
            if (userDetails[1].equals(userId)) {
                return data;
            }
        }
        return null;
    }

    public List<String> getOfficersIdsByDepartmentName(String departmentName) {
        List<String> userIds = new ArrayList<>();
        for (String userData : getOfficerData()) {
            String[] userDetails = userData.split(",");
            if (userDetails[6].equals(departmentName)) {
                userIds.add(userDetails[1]);
            }
        }
        return userIds;
    }

    public void updateOfficerData(int index, String newData) {
        getOfficerData().set(index, newData);
    }

    /*======================End of Officer data====================================*/

    /*======================Start of Criminal data====================================*/


    public void loadCriminalData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CRIMINAL_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                getCriminalData().add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading criminal data: " + e.getMessage());
        }
    }

    public void saveCriminalData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CRIMINAL_FILE))) {
            for (String data : getCriminalData()) {
                writer.write(data);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing criminal data: " + e.getMessage());
        }
    }

    public boolean isCriminalUnique(String criminalId) {
        for (String data : getCriminalData()) {
            String[] criminalDetails = data.split(",");
            if (criminalDetails[1].equals(criminalId)) {
                return true;
            }
        }
        return false;
    }

    public boolean isCriminalUnique(String criminalId, String criminalName) {
        for (String data : getCriminalData()) {
            String[] criminalDetails = data.split(",");
            if (!criminalDetails[1].equals(criminalId)) {
                if(criminalDetails[0].equals(criminalName)){
                    return true;
                }
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
                getDepartmentData().add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading department data: " + e.getMessage());
        }
    }

    public void saveDepartmentData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DEPARTMENT_FILE))) {
            for (String data : getDepartmentData()) {
                writer.write(data);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing department data: " + e.getMessage());
        }
    }

    public boolean isDepartmentIdUnique(String departmentId) {
        for (String data : getDepartmentData()) {
            String[] departmentDetails = data.split(",");
            if (departmentDetails[1].equals(departmentId)) {
                return true;
            }
        }
        return false;
    }

    public boolean isDepartmentNameUnique(String departmentName) {
        for (String data : getDepartmentData()) {
            String[] departmentDetails = data.split(",");
            if (departmentDetails[0].equals(departmentName)) {
                return true;
            }
        }
        return false;
    }

    public String getDepartmentDataById(String departmentId) {
        for (String data : getDepartmentData()) {
            String[] departmentDetails = data.split(",");
            if (departmentDetails[1].equals(departmentId)) {
                return data;
            }
        }
        return null;
    }

    /*======================End of Department data====================================*/

    /*======================Start of Case data====================================*/

    public void loadCaseData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(REPORT_FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                getCaseData().add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading reports: " + e.getMessage());
        }
    }

    public void saveCaseData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(REPORT_FILE_NAME))) {
            for (String data : getCaseData()) {
                writer.write(data);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing reports: " + e.getMessage());
        }
    }

    public List<String> getCasesIdsByDepartmentName(String departmentName) {
        List<String> caseIds = new ArrayList<>();
        for (String reportData : getCaseData()) {
            String[] reportDetails = reportData.split(",");
            if (reportDetails[3].equals(departmentName)) {
                caseIds.add(reportDetails[0]);
            }
        }
        return caseIds;
    }

    public String getCaseDataById(String caseId) {
        for (String data : getCaseData()) {
            String[] caseDetails = data.split(",");
            if (caseDetails[0].equals(caseId)) {
                return data;
            }
        }
        return null;
    }

    /*======================End of Case data====================================*/

    /*======================Start of Criminal Cases data====================================*/

    public void loadCriminalCasesData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CRIMINAL_CASES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                getCriminalCasesData().add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading criminal cases data: " + e.getMessage());
        }
    }

    public void saveCriminalCasesData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CRIMINAL_CASES_FILE))) {
            for (String data : getCriminalCasesData()) {
                writer.write(data);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing criminal cases data: " + e.getMessage());
        }
    }

    public String getCriminalDataById(String criminalId) {
        for (String data : getCriminalData()) {
            String[] criminalDetails = data.split(",");
            if (criminalDetails[1].equals(criminalId)) {
                return data;
            }
        }
        return null;
    }

    public void updateCriminalData(int index, String newData) {
        getCriminalData().set(index, newData);
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

    /*======================End of Criminal Cases data====================================*/

    /*======================Start of Assigned Case data====================================*/

    public void loadAssignedCases() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ASSIGNED_CASES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                getAssignedCaseData().add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading assigned cases data: " + e.getMessage());
        }
    }

    public void saveAssignedCases() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ASSIGNED_CASES_FILE))) {
            for (String data : getAssignedCaseData()) {
                writer.write(data);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing assigned cases data: " + e.getMessage());
        }
    }

    public List<String> getCasesIdsByOfficerId(String id) {
        List<String> caseIds = new ArrayList<>();
        for (String AssignedCases : getAssignedCaseData()) {
            String[] casesDetails = AssignedCases.split(",");
            if (casesDetails[1].equals(id)) {
                caseIds.add(casesDetails[0]);
            }
        }
        return caseIds;
    }

    /*======================End of Assigned Case data====================================*/

    /*======================Start of Updated Case data====================================*/

    public void loadUpdatedCriminalCases() {
        try (BufferedReader reader = new BufferedReader(new FileReader(UPDATED_CRIMINAL_CASES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                getUpdatedCriminalCaseData().add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading assigned cases data: " + e.getMessage());
        }
    }

    public void saveUpdatedCriminalCases() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(UPDATED_CRIMINAL_CASES_FILE))) {
            for (String data : getUpdatedCriminalCaseData()) {
                writer.write(data);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing assigned cases data: " + e.getMessage());
        }
    }

    public String getUpdatedCaseDataById(String caseId) {
        for (String data : getUpdatedCriminalCaseData()) {
            String[] caseDetails = data.split(",");
            if (caseDetails[0].equals(caseId)) {
                return data;
            }
        }
        return null;
    }

    public void updateUpdatedCriminalCasesData(int index, String newData) {
        getUpdatedCriminalCaseData().set(index, newData);
    }

    public boolean isCaseAssignedToCriminal(String caseId, String criminalId) {
        for (String data : getUpdatedCriminalCaseData()) {
            String[] criminalCasesDetails = data.split(",");
            if (criminalCasesDetails[0].equals(caseId) && criminalCasesDetails[2].equals(criminalId)) {
                return true;
            }
        }
        return false;
    }

    public List<String> getCasesIdsByCriminalId(String id) {
        List<String> caseIds = new ArrayList<>();
        for (String criminalCases : getUpdatedCriminalCaseData()) {
            String[] casesDetails = criminalCases.split(",");
            if (casesDetails[2].equals(id)) {
                caseIds.add(casesDetails[0]);
            }
        }
        return caseIds;
    }

    public List<String> getCriminalsIdsByCaseId(String id) {
        List<String> criminalIds = new ArrayList<>();
        for (String criminalCases : getUpdatedCriminalCaseData()) {
            String[] casesDetails = criminalCases.split(",");
            if (casesDetails[0].equals(id)) {
                criminalIds.add(casesDetails[2]);
            }
        }
        return criminalIds;
    }

    public int getNumberOfCasesForCriminal(String id) {
        int num = 0;
        for (String criminalCases : getUpdatedCriminalCaseData()) {
            String[] casesDetails = criminalCases.split(",");
            if (casesDetails[2].equals(id)) {
                num++;
            }
        }
        return num;
    }

    /*======================End of Updated Case data====================================*/

    /*==========================Getters============================ */

    public List<String> getCriminalCasesData() {
        return criminalCases;
    }

    public List<String> getDepartmentData() {
        return departments;
    }

    public List<String> getCriminalData() {
        return criminals;
    }

    public List<String> getOfficerData() {
        return userData;
    }

    public List<String> getCaseData() {
        return cases;
    }

    public List<String> getAssignedCaseData() {
        return assignedCases;
    }
    public List<String> getUpdatedCriminalCaseData() {
        return updatedCriminalCases;
    }
}