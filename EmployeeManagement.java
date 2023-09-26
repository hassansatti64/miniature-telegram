package com.employee_tracker;

import java.io.*;
import java.util.*;

public class EmployeeManagement {

    static List<String> employees = new ArrayList<>();
    static List<String> employeePasses = new ArrayList<>();
    static List<String> leaveRecords = new ArrayList<>();
    static List<String> leaveRequest = new ArrayList<>();
    static List<String> salary = new ArrayList<>();

    static Scanner input = new Scanner(System.in);

    public static void createFile(String name) {
        File myFile = new File(name);

        //Checks if the file with the given name exists or not
        if (myFile.exists())
            System.out.print("");
        else {
            try {

                //Creates file if it doesn't already exist
                myFile.createNewFile();
            } catch (IOException e) {

                //Throws exception if file is not created
                System.out.println("Unable to create file");
                throw new RuntimeException(e);
            }
        }
    }

    public static void writeFile(String name, ArrayList<String> list1) {
        try {
            FileWriter out = new FileWriter(name);
            for (String s : list1) {
                out.write(s + "\n");

            }
            out.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeFilePasses(String name, ArrayList<String> list1, ArrayList<String> list2) {
        try {
            FileWriter fileWriter = new FileWriter(name);

            for (int i = 0; i < list1.size(); i++) {
                fileWriter.write(list1.get(i) + "\t\t" + list2.get(i)  + "\n");
                System.out.println();
            }

            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeFileSalary(String name, ArrayList<String> list1, ArrayList<String> list2, ArrayList<String> list3) {
        try {
            FileWriter fileWriter = new FileWriter(name);

            for (int i = 0; i < list1.size(); i++) {
                fileWriter.write(list1.get(i) + "\t\t" + list2.get(i) + "\t\t" + list3.get(i) + "\n");
                System.out.println();
            }

            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static void deleteFile(String name) {
        File myFile = new File(name);
        myFile.delete();
    }

    public static void management() {
        createFile("employees.txt");
        createFile("employeePasses.txt");

        //Array to store management IDs
        String[] management = {"hassan", "aisha"};

        //Array to store management passwords
        String[] password = {"1234", "5678"};

        boolean incorrectIdPass = true;
        do {
            System.out.print("Enter ID: ");
            String id = input.next().toLowerCase();
            System.out.print("Enter password: ");
            String pass = input.next().toLowerCase();

            //Condition to check if password or ID entered is correct
            if (id.equals(management[0]) && pass.equals(password[0])) {
                System.out.println("\nWelcome to the Management Console, HASSAN");
                incorrectIdPass = false;
            } else if (id.equals(management[1]) && pass.equals(password[1])) {
                System.out.println("\nWelcome to the Management Console, AISHA");
                incorrectIdPass = false;
            } else
                System.out.println("Incorrect ID or password, please try again.");
        } while (incorrectIdPass);
        boolean more = false;

        while (!more) {

            System.out.println("Press 1 to add employee");
            System.out.println("Press 2 to manage leave requests");
            System.out.println("Press 3 to remove an employee");
            System.out.println("Press 4 to view employees");

            try {
                //Take input from user
                int choice = input.nextInt();

                if (choice == 1) {
                    while (true) {
                        System.out.println("\t\t\t\tEmployees");
                        for (int i = 0; i < employees.size(); i++) {
                            System.out.println((i + 1) + ". " + employees.get(i));
                        }
                        System.out.println("----------------------------------------------");
                        System.out.println("\nEnter name of the employee you want to add: ");
                        input.nextLine().toUpperCase();
                        String employeeName = input.nextLine().toUpperCase();

                        //Add the new employee name in the array list named employees
                        employees.add(employeeName);


                        //Generate random passwords for the employees added
                        String pass = "" + (char) (65 + Math.random() * 25) + (char) (65 + Math.random() * 25) +
                                (int) (Math.random() * 100);

                        //Store username and passwords in array list named employeePasses
                        employeePasses.add(pass);

                        //Write the employee name in the file employees.txt
                        writeFile("employees.txt", (ArrayList<String>) employees);

                        //Write the username and password in the file employeePasses.txt
                        writeFilePasses("employeePasses.txt", (ArrayList<String>) employees,
                                (ArrayList<String>) employeePasses);

                        System.out.println("\nDo you want to add more employee? Y or N");
                        String moreName = input.next().toUpperCase();

                        //Loop until user enters valid input
                        while (!(moreName.equals("Y") || moreName.equals("N"))) {

                            System.out.println("Invalid input! Please enter \"Y\" or \"N\"");
                            moreName = input.next().toUpperCase();

                        }
                        if (moreName.equals("Y"))
                            continue;
                        else if (moreName.equals("N"))
                            break;
                    }

                }

                if(choice == 2){
                    manageLeaveRequests();
                }

                if(choice == 3){
                    if (employees.isEmpty()) {
                        System.out.println("No employees added yet by the management");
                    } else {
                        while (true) {
                            System.out.println("\t\t\t\tEmployees");
                            for (int i = 0; i < employees.size(); i++) {
                                System.out.println((i + 1) + ". " + employees.get(i));
                            }
                            System.out.println("----------------------------------------------");
                            System.out.println("\nEnter the employee you want to remove: ");


                            while (true) {
                                try {
                                    int delEmployee = input.nextInt();

                                    //Take the index of the employee

                                    if (delEmployee > 0 && delEmployee <= employees.size()) {

                                        //Remove the employee entered from the arraylist
                                        employees.remove(employees.get(delEmployee - 1));

                                        //Remove the password of the employee deleted present on
                                        // the same index as the employee in different arraylist
                                        employeePasses.remove(employeePasses.get(delEmployee - 1));


                                        break;
                                    } else

                                        //If employee is not added continue the loop and take input again
                                        System.out.println("This employee doesn't exist in records. Please try again");
                                    continue;

                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input.Please try again");
                                    input.nextLine();
                                }
                            }

                            //Now write the new array list items after removing employees in file which stores
                            // employee names
                            writeFile("employees.txt", (ArrayList<String>) employees);

                            // write the new array list items after removing passes in file which stores employee passes
                            writeFilePasses("employeePasses.txt", (ArrayList<String>) employees,
                                    (ArrayList<String>) employeePasses);

                            System.out.println("\nDo you want to remove more employees? Y or N");
                            String moreName = input.next().toUpperCase();

                            //Loop until user enters the valid input
                            while (!(moreName.equals("Y") || moreName.equals("N"))) {

                                System.out.println("Invalid input. Please enter \"Y\" or \"N\"");
                                moreName = input.next().toUpperCase();

                            }
                            if (moreName.equals("Y"))
                                continue;
                            else if (moreName.equals("N"))
                                break;
                        }
                    }
                }

                if(choice == 4){
                    if (employees.isEmpty()) {
                        System.out.println("No employees added yet by the management.");
                    } else {
                        System.out.println("----------------------------");

                        //Display restaurant names and their Ids
                        System.out.println("Name                    Pass");
                        for (int i = 0; i < employees.size(); i++) {
                            System.out.printf((i + 1) + ". %-20s %s\n", employees.get(i), employeePasses.get(i));
                        }
                    }
                }


                boolean validInput = false;
                while (!validInput) {
                    System.out.println("\nPress 1 to return to Management menu.");
                    System.out.println("Press 0 to exit Management.");
                    input.nextLine();
                    String in = input.next();

                    if (in.equals("1")) {
                        validInput = true;
                        continue;

                    } else if (in.equals("0")) {
                        validInput = true;
                        more = true;

                    } else
                        System.out.println("Invalid input! Please try again.");
                    continue;


                }


            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please try again.");
                input.nextLine();
            }

        }
    }

    public static void manageLeaveRequests() {
        System.out.println("\nLeave Requests:");

        try {
            File leaveFile = new File("leaveRecords.txt");
            Scanner fileScanner = new Scanner(leaveFile);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                System.out.println(line);
            }

            fileScanner.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        boolean more = false;
        while (!more) {
            System.out.println("\nPress 1 to approve a leave request");
            System.out.println("Press 2 to reject a leave request");
            System.out.println("Press 0 to return to the Management menu");

            try {
                int choice = input.nextInt();

                if (choice == 1) {
                    approveLeaveRequest();
                } else if (choice == 2) {
                    rejectLeaveRequest();
                } else if (choice == 0) {
                    more = true;
                } else {
                    System.out.println("Invalid input! Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please try again.");
                input.nextLine();
            }
        }
    }

    public static void approveLeaveRequest() {
        System.out.println("\nEnter the employee name whose leave request you want to approve:");
        input.nextLine();
        String employeeName = input.nextLine().toUpperCase();

        try {
            File leaveFile = new File("leaveRecords.txt");
            Scanner fileScanner = new Scanner(leaveFile);
            StringBuilder fileContent = new StringBuilder();

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (line.startsWith(employeeName)) {
                    String[] parts = line.split("\t");
                    String approvedLeaveRecord = parts[0] + "\t" + parts[1] + "\tAPPROVED";
                    leaveRecords.add(approvedLeaveRecord);
                    System.out.println("Leave request approved for " + parts[0]);
                } else {
                    fileContent.append(line).append("\n");
                }
            }

            fileScanner.close();

            // Write the updated leave records to the file
            writeFile("leaveRecords.txt", (ArrayList<String>) leaveRecords);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void rejectLeaveRequest() {
        System.out.println("\nEnter the employee name whose leave request you want to reject:");
        input.nextLine();
        String employeeName = input.nextLine().toUpperCase();

        try {
            File leaveFile = new File("leaveRecords.txt");
            Scanner fileScanner = new Scanner(leaveFile);
            StringBuilder fileContent = new StringBuilder();

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (line.startsWith(employeeName)) {
                    String[] parts = line.split("\t");
                    String rejectedLeaveRecord = parts[0] + "\t" + parts[1] + "\tREJECTED";
                    leaveRecords.add(rejectedLeaveRecord);
                    System.out.println("Leave request rejected for " + parts[0]);
                } else {
                    fileContent.append(line).append("\n");
                }
            }

            fileScanner.close();

            // Write the updated leave records to the file
            writeFile("leaveRecords.txt", (ArrayList<String>) leaveRecords);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void markPresence(String username) {
        String currentDate = getCurrentDate();
        String attendanceRecord = username + "\t\t" + currentDate;

        try {
            FileWriter fileWriter = new FileWriter("attendance.txt", true);
            fileWriter.write(attendanceRecord + "\n");
            fileWriter.close();
            System.out.println("Attendance marked successfully!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void viewAttendanceReport(String username) {
        try {
            File attendanceFile = new File("attendance.txt");
            Scanner fileScanner = new Scanner(attendanceFile);

            System.out.println("Attendance Report for " + username + ":\n");

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (line.startsWith(username)) {
                    System.out.println(line);
                }
            }

            fileScanner.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void viewLeaveRecord(String username) {
        try {
            File leaveFile = new File("leaveRecords.txt");
            Scanner fileScanner = new Scanner(leaveFile);

            System.out.println("Leave Record for " + username + ":\n");

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (line.startsWith(username)) {
                    System.out.println(line);
                }
            }

            fileScanner.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getCurrentDate() {
        // Implementation of getting the current date goes here
        return "2023-06-05"; // Placeholder for simplicity
    }

    public static void employee() {
        createFile("attendance.txt");
        createFile("leaveRecords.txt");
        createFile("employeeList.txt");

        String username = " ";
        String password;

        boolean incorEmployee = true;
        boolean incorPass = true;

        if (employees.isEmpty()) {
            incorEmployee = false;
            System.out.println("No employees added yet by management.");
        }

        //Loop continues until user enters the correct employee name
        while (incorEmployee) {
            System.out.println("Enter your name: ");
            String employeeName = input.nextLine().toUpperCase();
            if (employees.contains(employeeName)) {

                //loop continues until user enters correct employee password
                while (incorPass) {
                    System.out.println("Enter the password: ");
                    String passes = input.next().toUpperCase();
                    if (employeePasses.indexOf(passes) == employees.indexOf(employeeName)) {
                        incorPass = false;


                        boolean more = false;
                        while (!more) {
                            while (true) {

                                System.out.println("\nPress 1 to mark attendance");
                                System.out.println("Press 2 to apply for leave");
                                System.out.println("Press 3 to view attendance report");
                                System.out.println("Press 4 to view leave record");
                                System.out.println("Press 0 to exit Employee Console");

                                try {
                                    int choice = input.nextInt();

                                    if (choice == 1) {
                                        markPresence(employeeName.toUpperCase());
                                    } else if (choice == 2) {
                                        applyForLeave(employeeName.toUpperCase());
                                    } else if (choice == 3) {
                                        viewAttendanceReport(employeeName.toUpperCase());
                                    } else if (choice == 4) {
                                        viewLeaveRecord(employeeName.toUpperCase());
                                    } else if (choice == 0) {
                                        more = true;
                                        break;
                                    } else {
                                        System.out.println("Invalid input! Please try again.");
                                    }
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input! Please try again.");
                                    input.nextLine();
                                }
                            }


                        }

                        //loop continues and takes input again if user enters incorrect password
                    } else
                        System.out.println("Incorrect password! Please Try again.");
                }

                //loop continues and takes input again if employee name doesn't exist in the list
            } else
                System.out.println("Name not added by management! Press 1 to try again.\nPress 0 to exit Employee Console.");

            while (true){
                String tryAgain = input.next();
                if (tryAgain.equals("1")){
                    incorEmployee = true;
                    input.nextLine();
                    break;
                }
                else if (tryAgain.equals("0")){
                    incorEmployee = false;
                    break;
                }
                else
                    System.out.println("Invalid choice. Please Try again");

            }

        }
    }





    public static void applyForLeave(String username) {
        System.out.println("\nEnter the starting date of your leave (YYYY-MM-DD):");
        String startDate = input.next();

        System.out.println("Enter the end date of your leave (YYYY-MM-DD):");
        String endDate = input.next();

        leaveRequest.add(username + "\t\t" + startDate + "\t\t" + endDate + "\tPENDING");

        writeFile("leaveRecords.txt", (ArrayList<String>) leaveRequest);
        System.out.println("Leave request submitted successfully!");
    }


    public static String generateRandomPassword() {
        // Implementation of generating a random password goes here
        return "password"; // Placeholder for simplicity
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        boolean a = true;
        System.out.println("Welcome to Employee Management System.\n");

        //Loop to take input until user enters valid input
        while (a) {
            System.out.println("Press 1 for Management Console.");
            System.out.println("Press 2 for Employee Console.");

            //Take input from user
            String choice = input.next();

            switch (choice) {
                case "1" -> {
                    management();

                    System.out.println("\nPress M to return to main menu.");
                    System.out.println("Press E to exit.");
                    String exit = input.next().toUpperCase();

                    //Takes input until user enters the valid number
                    while (!(exit.equals("M") || exit.equals("E"))) {

                        System.out.println("Invalid input! Please enter valid input");
                        exit = input.next().toUpperCase();
                    }
                    if (exit.equals("M"))
                        a = true;

                    else if (exit.equals("E")) {
                        a = false;
                    }


                }

                case "2" -> {
                    employee();

                    System.out.println("\nPress M to return to main menu.");
                    System.out.println("Press E to exit.");
                    String exit = input.next().toUpperCase();

                    //Takes input until user enters the valid number
                    while (!(exit.equals("M") || exit.equals("E"))) {

                        System.out.println("Invalid input! Please enter valid input");
                        exit = input.next().toUpperCase();
                    }
                    if (exit.equals("M"))
                        a = true;

                    else if (exit.equals("E")) {
                        a = false;
                    }
                }

                default -> {
                    System.out.println("Invalid input. Please Try again.");
                    continue;
                }


            }

        }
    }
}
