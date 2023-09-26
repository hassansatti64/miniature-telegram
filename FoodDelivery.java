package com.food_delivery;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class FoodDelivery {

    //Initializing static variables and arrayLists to store the data
    static List<String> singleOrder = new ArrayList<>();
    static List<String> siOrder=new ArrayList<>();
    static List<String> totalItemP=new ArrayList<>();
    static double bill = 0;

    static String order = " ";
    static String selection;

    static Scanner input = new Scanner(System.in);
    static List<String> orderIds = new ArrayList<>();
    static List<String> selRest = new ArrayList<>();
    static List<String> phones = new ArrayList<>();
    static List<String> restaurants = new ArrayList<>();

    static List<String> menu = new ArrayList<>();
    static List<String> deals = new ArrayList<>();

    static List<Double> price = new ArrayList<>();
    static List<Double> dealsprice = new ArrayList<>();

    static List<String> restId = new ArrayList<>();

    static List<String> locations = new ArrayList<>();

    static List<String> costumerNames = new ArrayList<>();

    static List<String> orders = new ArrayList<>();

    static List<String> bills = new ArrayList<>();

    //Methods for file handling
    public static void deleteFile(String name) {
        File myFile = new File(name);
        myFile.delete();
    }

    public static void readFile(String name) throws RuntimeException {
        Scanner reader = null;
        try {
            reader = new Scanner((new File(name)));
            while (reader.hasNext()) {
                System.out.println(reader.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createFile(String name) {
        File myFile = new File(name);

        //Checks if the file with given name already exists or not
        if (myFile.exists())
            System.out.print("");
        else {
            try {

                //creates file if it doesnot already exist
                myFile.createNewFile();

            } catch (IOException e) {

                //Throws excpetion if file is not created
                System.out.println("Unable to create file");
                throw new RuntimeException(e);
            }
        }
    }

    //Reads input from the file
    public static void scanner(String fileName) {
        Scanner list = null;

        try {
            list = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (list.hasNext()) {

            // Split the line by double tab
            String[] splitted = list.nextLine().split("\t\t");

            if (!(menu.contains(splitted[0]))) {
                // Add the item at zero index after splitting in arrayList named menu
                menu.add(splitted[0]);
            }

            if (!(price.contains(splitted[1]))) {
                // Add the item at index 1 after splitting in arrayList named price
                price.add(Double.valueOf(splitted[1]));
            }
        }

    }

    public static void dealscanner(String fileName) {
        Scanner list = null;

        try {
            list = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (list.hasNext()) {

            // Split the line by double tab
            String[] splitted = list.nextLine().split("\t\t");

            if (!(deals.contains(splitted[0]))) {
                // Add the item at zero index after splitting in arrayList named menu
                deals.add(splitted[0]);
            }

            if (!(dealsprice.contains(splitted[1]))) {
                // Add the item at index 1 after splitting in arrayList named price
                dealsprice.add(Double.valueOf(splitted[1]));
            }
        }

    }

    //Method to read file having the elements separated by double tab
    public static void readOrderDetails(String fileName) {
        Scanner list = null;

        try {
            list = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (list.hasNext()) {

            // Split the line by double tab
            String[] splitted = list.nextLine().split("\t\t");

            // Add the items to the corresponding lists
            orderIds.add(splitted[0]);
            costumerNames.add(splitted[1]);
            locations.add(splitted[2]);
            bills.add((splitted[3]));
            orders.add(splitted[5]);
            phones.add(splitted[4]);
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

    //Method to write elements of different arraylists separated by double tab in a text file
    public static void writeOrderDetails(String name, ArrayList<String> list1, ArrayList<String> list2,
                                         ArrayList<String> list3, ArrayList<String> list4, ArrayList<String> list5,
                                         ArrayList<String> list6, List<String> list8) {
        try {
            PrintWriter fileWriter = new PrintWriter(name);

            //Write the elements of different array lists separated by double tab in the same file
            int i;
            for (i = 0; i < list1.size(); i++) {
                fileWriter.println(list6.get(i) + "\t\t" + list1.get(i) + "\t\t" + list4.get(i) + "\t\t" + list3.get(i)
                        + "\t\t" + list8.get(i) + "\t\t" + list2.get(i));
            }
            if (singleOrder.size() > 1) {
                for (int k = 1; k < list5.size(); k++) {
                    fileWriter.println("    \t\t" + "    \t\t" + "    \t\t" + "    \t\t" + "    \t\t" +
                            list5.get(k));
                }
            }


            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void writeFileMenu(String name, ArrayList<String> list1, ArrayList<Double> list2) {
        try {
            FileWriter fileWriter = new FileWriter(name);

            for (int i = 0; i < list1.size(); i++) {
                fileWriter.write(list1.get(i) + "\t\t" + list2.get(i) + "\n");
                System.out.println();
            }

            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static void writeFileIds(String name, ArrayList<String> list1, ArrayList<String> list2) {
        try {
            FileWriter fileWriter = new FileWriter(name);

            for (int i = 0; i < list1.size(); i++) {
                fileWriter.write(list1.get(i) + "\t\t" + list2.get(i) + "\n");
                System.out.println();
            }

            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    public static void admin() {
        createFile("restaurants.txt");
        createFile("restIDs.txt");

        //Array to store admin ids
        String[] admins = {"saram", "hassan", "fazeela"};

        //Array to store admin id passwords
        String[] passes = {"1234", "5678", "9852"};

        boolean incorrectIdPass = true;
        do {
            System.out.print("Enter ID: ");
            String id = input.next().toLowerCase();
            System.out.print("Enter Password: ");
            String pass = input.next().toLowerCase();

            // Condition to check if correct id and password is entered
            if (id.equals(admins[0]) && pass.equals(passes[0])) {
                System.out.println("\nWelcome to the ADMIN Console, SARAM");
                incorrectIdPass = false;
            } else if (id.equals(admins[1]) && pass.equals(passes[1])) {
                System.out.println("\nWelcome to the ADMIN Console, HASSAN.");
                incorrectIdPass = false;
            } else if (id.equals(admins[2]) && pass.equals(passes[2])) {
                System.out.println("\nWelcome to the ADMIN Console, FAZEELA.");
                incorrectIdPass = false;
            } else
                System.out.println("Incorrect id or password, please try again.");
        } while (incorrectIdPass);
        boolean more =false;

        while (!more) {

            System.out.println("Press 1 to view orders.");
            System.out.println("Press 2 to add a restaurant.");
            System.out.println("Press 3 to delete a restaurant.");
            System.out.println("Press 4 to view restaurants.");
            try {
                //take input from user
                int choice = input.nextInt();
                if (choice > 0 && choice <= 4) {
                    if (choice == 1) {
                        if (orderIds.isEmpty()) {
                            System.out.println("No orders yet.");
                        } else {
                            System.out.println("ALL ORDERS DETAILS");
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
                            System.out.println("Order Id      Costumer Name               Address                                            Total Bill     QTY  Order Detail                            Contact");
                            for (int i = 0; i < costumerNames.size(); i++) {
                                System.out.printf("%-14s%-28s%-51s%-15s%-45s%-15s\n", orderIds.get(i),
                                        costumerNames.get(i), locations.get(i), bills.get(i), orders.get(i),
                                        phones.get(i));
                            }
                            System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------");
                        }
                    }

                    if (choice == 2) {
                        while (true) {
                            System.out.println("\t\t\t\tRestaurants");
                            for (int i = 0; i < restaurants.size(); i++) {
                                System.out.println((i + 1) + ". " + restaurants.get(i));
                            }
                            System.out.println("----------------------------------------------");
                            System.out.println("\nEnter the name of restaurant you want to add: ");
                            input.nextLine().toUpperCase();
                            String restName = input.nextLine().toUpperCase();

                            //Add the new restaurant name in the array list named restaurants
                            restaurants.add(restName);

                            //Generate random ids for the new restaurants added
                            String id = "" + (char) (65 + Math.random() * 25) + (char) (65 + Math.random() * 25) +
                                    (int) (Math.random() * 100);

                            //Store the ids in arraylist named restId
                            restId.add(id);

                            //Writes the new restaurants in the file "restaurants.txt"
                            writeFile("restaurants.txt", (ArrayList<String>) restaurants);

                            //Writes the new restaurant ids in the file "restIDs.txt"
                            writeFileIds("restIDs.txt", (ArrayList<String>) restaurants,
                                    (ArrayList<String>) restId);

                            //Create the file to store the menu of the restaurant
                            createFile(("menu" + restName + ".txt"));
                            createFile(("deals" + restName + ".txt"));

                            System.out.println("\nDo you want to add more restaurants? Y or N");
                            //takes the input and convert it to the uppercase
                            String moreRest = input.next().toUpperCase();

                            //Loop until user enters the valid input
                            while (!(moreRest.equals("Y") || moreRest.equals("N"))) {

                                System.out.println("Invalid input. Pls Enter \"Y\" or \"N\"");
                                moreRest = input.next().toUpperCase();

                            }
                            if (moreRest.equals("Y"))
                                continue;

                            else if (moreRest.equals("N"))
                                break;
                        }

                    }

                    if (choice == 3) {
                        if (restaurants.isEmpty()) {
                            System.out.println("No restaurants added yet by the admin");
                        } else {
                            while (true) {
                                System.out.println("\t\t\t\tRestaurants");
                                for (int i = 0; i < restaurants.size(); i++) {
                                    System.out.println((i + 1) + ". " + restaurants.get(i));
                                }
                                System.out.println("----------------------------------------------");
                                System.out.println("\nEnter the restaurant you want to remove: ");


                                while (true) {
                                    try {
                                        int delRest = input.nextInt();

                                        //Take the index of the restaurant entered

                                        if (delRest > 0 && delRest <= restaurants.size()) {

                                            //Delete the menu file of the restaurant
                                            deleteFile(("menu" + restaurants.get(delRest - 1) + ".txt"));
                                            deleteFile(("deals" + restaurants.get(delRest - 1) + ".txt"));

                                            //Remove the restaurant entered from the arraylist
                                            restaurants.remove(restaurants.get(delRest - 1));

                                            //Remove the Id of the restaurant deleted present on
                                            // the same index as the restaurant in different arraylist
                                            restId.remove(restId.get(delRest - 1));


                                            break;
                                        } else

                                            //If restaurant is not added continue the loop and take input again
                                            System.out.println("Restaurant doesn't exist. Please try again");
                                        continue;

                                    } catch (InputMismatchException e) {
                                        System.out.println("Invalid input.Please try again");
                                        input.nextLine();
                                    }
                                }

                                //Now write the new array list items after removing restaurant in file which stores
                                // restaurant names
                                writeFile("restaurants.txt", (ArrayList<String>) restaurants);

                                // write the new array list items after removing id in file which stores restaurant ids
                                writeFileIds("restIDs.txt", (ArrayList<String>) restaurants,
                                        (ArrayList<String>) restId);

                                System.out.println("\nDo you want to delete more restaurants? Y or N");
                                String moreRest = input.next().toUpperCase();

                                //Loop until user enters the valid input
                                while (!(moreRest.equals("Y") || moreRest.equals("N"))) {

                                    System.out.println("Invalid input. Pls Enter \"Y\" or \"N\"");
                                    moreRest = input.next().toUpperCase();

                                }
                                if (moreRest.equals("Y"))
                                    continue;
                                else if (moreRest.equals("N"))
                                    break;
                            }
                        }
                    }

                    if (choice == 4) {
                        if (restaurants.isEmpty()) {
                            System.out.println("No restaurants added yet by the admin.");
                        } else {
                            System.out.println("----------------------------");

                            //Display restaurant names and their Ids
                            System.out.println("Name                    ID");
                            for (int i = 0; i < restaurants.size(); i++) {
                                System.out.printf((i + 1) + ". %-20s %s\n", restaurants.get(i), restId.get(i));
                            }
                        }
                    }
                    boolean validInput = false;
                    while (!validInput) {
                        System.out.println("\nPress 1 to return to Admin menu.");
                        System.out.println("Press 0 to exit Admin.");
                        input.nextLine();
                        String in = input.next();
                        if (in.equals("1")) {
                            validInput = true;
                            continue;

                        } else if (in.equals("0")) {
                            validInput = true;
                            more = true;

                        } else
                            System.out.println("Invalid input! please try again.");
                        continue;
                    }


                }
                else{
                    System.out.println("Invalid input! please try again.");
                }

            }catch(InputMismatchException e){
                System.out.println("Invalid input! please try again.");
                input.nextLine();
            }
        }
    }




    public static void rest() throws FileNotFoundException {

        boolean incorRest = true;
        boolean incorId = true;
        menu.clear();
        deals.clear();
        price.clear();
        dealsprice.clear();

        if (restaurants.isEmpty()){
            incorRest = false;
            System.out.println("No restaurants added yet by admin.");
        }

        //Loop continues until user enters the correct restaurant
        while (incorRest) {
            System.out.println("Enter your restaurant: ");
            String restName = input.nextLine().toUpperCase();
            if (restaurants.contains(restName)) {

                //loop continues until user enters correct restaurant id
                while (incorId) {
                    System.out.println("Enter the restaurant id: ");
                    String id = input.next().toUpperCase();
                    if (restId.indexOf(id) == restaurants.indexOf(restName)) {
                        incorId = false;


                        boolean more  =false;
                        while (!more) {
                            while (true) {

                                //Reads the menu of the restaurant from file
                                scanner("menu" + restName + ".txt");
                                dealscanner("deals" + restName + ".txt");

                                System.out.println("\nPress 1 to view menu");
                                System.out.println("Press 2 to add an item to the menu");
                                System.out.println("Press 3 to remove an item from the menu");
                                System.out.println("Press 4 to update the price of an item");


                                try {

                                    int choice = input.nextInt();
                                    if (choice == 1) {

                                        if (menu.isEmpty()){
                                            System.out.println("No single items added yet in the menu");
                                        }
                                        else {
                                            System.out.printf("\n%s MENU\n", restName);
                                            System.out.println("------------------------------------------");

                                            //Display menu items with their price
                                            System.out.println("Sr. Item                          Price");
                                            for (int i = 0; i < menu.size(); i++) {
                                                System.out.printf((i + 1) + ".  %-30sRs %.2f\n", menu.get(i), price.get(i));
                                            }
                                            System.out.println("------------------------------------------");
                                        }

                                        if (deals.isEmpty()){
                                            System.out.println("\nNo deals added yet in the menu");
                                        }
                                        else {

                                            System.out.printf("\n%s DEALS\n", restName);
                                            System.out.println("------------------------------------------");

                                            //Display menu items with their price
                                            System.out.println("Sr. Deals                          Price");
                                            for (int i = 0; i < deals.size(); i++) {
                                                System.out.printf((i + 1) + ".  %-31sRs %.2f\n", deals.get(i),
                                                        dealsprice.get(i));
                                            }
                                            System.out.println("------------------------------------------");
                                        }
                                        break;

                                    } else if (choice == 2) {

                                        System.out.println("Press 1 to add an single item to the menu");
                                        System.out.println("Press 2 to add a deal to the menu");
                                        while (true) {
                                            String choiceN = input.next();

                                            if (choiceN.equals("1")) {

                                                boolean validInput = false;
                                                while (!validInput) {
                                                    System.out.println("Enter the item name: ");
                                                    input.nextLine();
                                                    String item = input.nextLine();

                                                    //Add the new item in the menu arraylist
                                                    menu.add(item);
                                                    while (true) {
                                                        try {
                                                            System.out.println("Enter price: ");
                                                            //Add the price of the item in price array list at
                                                            // the same index as the item stored in menu arraylist
                                                            double itemPrice = input.nextDouble();
                                                            price.add(itemPrice);
                                                            break;
                                                        } catch (InputMismatchException e) {
                                                            System.out.println("Price must be a number!");
                                                            input.nextLine();
                                                        }
                                                    }

                                                    System.out.println("Do you want to enter more items? Y or N");
                                                    String moreItems = input.next().toUpperCase();

                                                    //Loop until user enters the valid input
                                                    while (!(moreItems.equals("Y") || moreItems.equals("N"))) {

                                                        System.out.println("Invalid input. Pls Enter \"Y\" or \"N\"");
                                                        moreItems = input.next().toUpperCase();

                                                    }
                                                    if (moreItems.equals("Y")) {
                                                        continue;
                                                    } else {

                                                        break;

                                                    }

                                                }
                                                writeFileMenu("menu" + restName + ".txt", (ArrayList<String>) menu,
                                                        (ArrayList<Double>) price);
                                                menu.clear();
                                                price.clear();
                                                break;
                                            } else if (choiceN.equals("2")) {
                                                boolean validInput = false;
                                                while (!validInput) {
                                                    System.out.println("Enter the deal: ");
                                                    input.nextLine();
                                                    String item = input.nextLine();

                                                    //Add the new item in the menu arraylist
                                                    deals.add(item);
                                                    while (true) {
                                                        try {
                                                            System.out.println("Enter deal price: ");
                                                            //Add the price of the item in price array list at the same
                                                            // index as the item stored in menu arraylist
                                                            double itemPrice = input.nextDouble();
                                                            dealsprice.add(itemPrice);
                                                            break;
                                                        } catch (InputMismatchException e) {
                                                            System.out.println("Price must be a number!");
                                                            input.nextLine();
                                                        }
                                                    }

                                                    System.out.println("Do you want to enter more deals? Y or N");
                                                    String moreItems = input.next().toUpperCase();

                                                    //Loop until user enters the valid input
                                                    while (!(moreItems.equals("Y") || moreItems.equals("N"))) {

                                                        System.out.println("Invalid input. Pls Enter \"Y\" or \"N\"");
                                                        moreItems = input.next().toUpperCase();

                                                    }
                                                    if (moreItems.equals("Y")) {
                                                        continue;
                                                    } else {

                                                        break;

                                                    }

                                                }
                                                writeFileMenu("deals" + restName + ".txt", (ArrayList<String>) deals,
                                                        (ArrayList<Double>) dealsprice);
                                                deals.clear();
                                                dealsprice.clear();
                                                break;
                                            }
                                            else
                                                System.out.println("Inalid input. Please Try Again.");
                                        }
                                        break;
                                    }
                                    if (choice == 3) {

                                        System.out.println("Press 1 to remove a single item from the menu");
                                        System.out.println("Press 2 to remove a deal from the menu");

                                        while (true){
                                            String choiceN = input.next();

                                            if (choiceN.equals("1")){
                                                if (menu.isEmpty()){
                                                    System.out.println("No single item added yet to the menu.");
                                                }
                                                else {
                                                    System.out.printf("\n%s MENU\n", restName);
                                                    System.out.println("-----------------------------------------");

                                                    //Display the menu items and their price
                                                    System.out.println("Sr. Item                          Price");
                                                    for (int i = 0; i < menu.size(); i++) {
                                                        System.out.printf((i + 1) + ".  %-30sRs %.2f\n", menu.get(i), price.get(i));
                                                    }
                                                    System.out.println("\nEnter the Sr. of the item you want to remove ");
                                                    while (true) {
                                                        try {
                                                            int item = input.nextInt();

                                                            //Check if the user entered the valid input
                                                            if (item <= menu.size() && item > 0) {

                                                                //remove the price and menu item from their respective arraylists
                                                                price.remove(item - 1);
                                                                menu.remove(item - 1);
                                                                break;
                                                            } else
                                                                System.out.println("Incorrect Choice. Please Try again.");
                                                            continue;
                                                        } catch (InputMismatchException e) {
                                                            System.out.println("Please enter a valid input!");
                                                            input.nextLine();
                                                        }
                                                    }

                                                    //write the modified menu and price arraylist in the file
                                                    writeFileMenu("menu" + restName + ".txt", (ArrayList<String>) menu,
                                                            (ArrayList<Double>) price);
                                                }
                                                break;

                                            } else if (choiceN.equals("2")) {
                                                if (deals.isEmpty()){
                                                    System.out.println("No deals added yet to the menu.");
                                                }
                                                else {
                                                    System.out.printf("\n%s DEALS\n", restName);
                                                    System.out.println("-----------------------------------------");

                                                    //Display the menu items and their price
                                                    System.out.println("Sr. Deals                          Price");
                                                    for (int i = 0; i < deals.size(); i++) {
                                                        System.out.printf((i + 1) + ".  %-31sRs %.2f\n", deals.get(i),
                                                                dealsprice.get(i));
                                                    }
                                                    System.out.println("\nEnter the Sr. of the deal you want to remove ");
                                                    while (true) {
                                                        try {
                                                            int item = input.nextInt();

                                                            //Check if the user entered the valid input
                                                            if (item <= deals.size() && item > 0) {

                                                                //remove the price and menu item from their respective arraylists
                                                                dealsprice.remove(item - 1);
                                                                deals.remove(item - 1);
                                                                break;
                                                            } else
                                                                System.out.println("Incorrect Choice. Please Try again.");
                                                            continue;
                                                        } catch (InputMismatchException e) {
                                                            System.out.println("Please enter a valid input!");
                                                            input.nextLine();
                                                        }
                                                    }

                                                    //write the modified menu and price arraylist in the file
                                                    writeFileMenu("deals" + restName + ".txt", (ArrayList<String>) deals,
                                                            (ArrayList<Double>) dealsprice);
                                                }
                                                break;
                                            }else
                                                System.out.println("Invalid input. Please Try again.");
                                        }
                                        break;

                                    } else if (choice == 4) {
                                        System.out.println("Press 1 to update the price of a single item");
                                        System.out.println("Press 2 to update the price of a deal");

                                        while (true){
                                            String choiceN = input.next();

                                            if (choiceN.equals("1")){
                                                if (menu.isEmpty()){
                                                    System.out.println("No single items added yet to the menu.");
                                                }
                                                else {
                                                    System.out.printf("\n%s MENU\n", restName);
                                                    System.out.println("-----------------------------------------");

                                                    //Display the menu items with their price
                                                    System.out.println("Sr. Item                          Price");
                                                    for (int i = 0; i < menu.size(); i++) {
                                                        System.out.printf((i + 1) + ".  %-31sRs %.2f\n", menu.get(i), price.get(i));
                                                    }
                                                    boolean validInput = false;
                                                    while (!validInput) {
                                                        try {

                                                            System.out.println("\nEnter the Sr. of the item to change its price: ");
                                                            int item = input.nextInt();

                                                            //checks if the user entered the valid input
                                                            if (item > 0 && item <= menu.size()) {
                                                                while (true) {
                                                                    try {

                                                                        System.out.println("Enter the new price: ");
                                                                        double newPrice = input.nextDouble();

                                                                        //set the new price
                                                                        price.set(item - 1, newPrice);
                                                                        break;
                                                                    } catch (InputMismatchException e) {
                                                                        System.out.println("Price must be  a number!Try again.");
                                                                        input.nextLine();
                                                                    }

                                                                }
                                                            } else {
                                                                System.out.println("Invalid input! Please try again.");
                                                                continue;
                                                            }
                                                            break;
                                                        } catch (InputMismatchException e) {
                                                            System.out.println("Invalid input! Please try again.");
                                                            input.nextLine();
                                                        }
                                                    }
                                                    // write the menu and new price in the file
                                                    writeFileMenu("menu" + restName + ".txt", (ArrayList<String>) menu,
                                                            (ArrayList<Double>) price);
                                                }
                                                break;
                                            } else if (choiceN.equals("2")) {
                                                if (deals.isEmpty()){
                                                    System.out.println("No deals added yet in the menu");
                                                }
                                                else {
                                                    System.out.printf("\n%s DEALS\n", restName);
                                                    System.out.println("-----------------------------------------");

                                                    //Display the menu items with their price
                                                    System.out.println("Sr. Deals                          Price");
                                                    for (int i = 0; i < deals.size(); i++) {
                                                        System.out.printf((i + 1) + ".  %-31sRs %.2f\n", deals.get(i),
                                                                dealsprice.get(i));
                                                    }
                                                    boolean validInput = false;
                                                    while (!validInput) {
                                                        try {

                                                            System.out.println("\nEnter the Sr. of the deal to change its price: ");
                                                            int item = input.nextInt();

                                                            //checks if the user entered the valid input
                                                            if (item > 0 && item <= deals.size()) {
                                                                while (true) {
                                                                    try {

                                                                        System.out.println("Enter the new price: ");
                                                                        double newPrice = input.nextDouble();

                                                                        //set the new price
                                                                        dealsprice.set(item - 1, newPrice);
                                                                        break;
                                                                    } catch (InputMismatchException e) {
                                                                        System.out.println("Price must be  a number!Try again.");
                                                                        input.nextLine();
                                                                    }

                                                                }
                                                            } else {
                                                                System.out.println("Invalid input! Please try again.");
                                                                continue;
                                                            }
                                                            break;
                                                        } catch (InputMismatchException e) {
                                                            System.out.println("Invalid input! Please try again.");
                                                            input.nextLine();
                                                        }
                                                    }
                                                    // write the menu and new price in the file
                                                    writeFileMenu("deals" + restName + ".txt", (ArrayList<String>) deals,
                                                            (ArrayList<Double>) dealsprice);
                                                }
                                                break;

                                            }else
                                                System.out.println("Invalid input. Please Try again.");
                                        }
                                        break;

                                    }else
                                        System.out.println("Incorrect choice. Please Try again.");

                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input! please try again");
                                    input.nextLine();
                                }
                            }
                            boolean validInput = false;
                            while (!validInput) {
                                System.out.println("\nPress 1 to return to restaurant menu.");
                                System.out.println("Press 0 to exit restaurant management.");
                                String in = input.next();
                                if (in.equals("1")) {
                                    validInput = true;
                                    continue;

                                } else if (in.equals("0")) {
                                    validInput = true;
                                    more = true;

                                } else
                                    System.out.println("Invalid input! please try again.");
                            }
                        }

                        //loop continues and takes input again if user enters incorrect id
                    } else
                        System.out.println("Incorrect id! Please Try again.");
                }

                //loop continues and Takes input again if restaurant doesn't exist in the list
            } else
                System.out.println("Restaurant not added by admin! Please 1 to try again. 0 to exit restaurant management");

            while (true){
                String tryAgain = input.next();
                if (tryAgain.equals("1")){
                    incorRest = true;
                    input.nextLine();
                    break;
                }
                else if (tryAgain.equals("0")){
                    incorRest = false;
                    break;
                }
                else
                    System.out.println("Invalid choice. Please Try again");

            }



        }
    }

    public static void selMenu() throws FileNotFoundException{

        System.out.println();
        boolean validQuantity = false;
        boolean cont = true;
        int item = 0;
        // print the menu items and their price
        System.out.printf("\n%s MENU", selection);
        System.out.println("\n-----------------------------------------");
        System.out.println("Sr. Item                           Price");
        for (int i = 0; i < menu.size(); i++) {
            System.out.printf((i + 1) + ".  %-30s %.2f\n", menu.get(i), price.get(i));
        }
        System.out.println("-----------------------------------------");

        System.out.println("\nPress C to continue with single items.");
        System.out.println("Press D to see deals.");
        System.out.println("Press R to change restaurant.");

        while (true) {

            String k = input.next().toLowerCase();

            if (k.equals("d")) {
                selDeal();
                cont = false;
                break;
            } else if (k.equals("r")) {
                order();
                cont = false;
                break;
            } else if (k.equals("c")) {
                break;
            }else
                System.out.println("Invalid Input. Please Try Again.");
        }

        if (cont) {
            while (true) {
                try {
                    System.out.println("Select item using Sr. Np. ");
                    item = input.nextInt();

                    if (item <= 0 || item > menu.size()) {
                        System.out.println("Invalid input! Please try again\n");
                        continue;
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please try again\n");
                    input.nextLine();
                }
            }
        }
        if (cont) {
            while (!validQuantity) {
                try {
                    System.out.println("Enter the quantity: ");
                    int quantity = input.nextInt();
                    validQuantity = true;

                    //store the order in a variable
                    orders.add(quantity + "    " + menu.get(item - 1) + "[" + selection + "]");
                    singleOrder.add(quantity + "    " + menu.get(item - 1) + "[" + selection + "]");
                    siOrder.add(quantity + "      " + menu.get(item - 1) + "[" + selection + "]" + " --- Rs " + price.get(item - 1) + "/-");
                    totalItemP.add("Rs " + quantity * price.get(item - 1) + "/-");

                    //calculate and store the bill
                    bill += price.get(item - 1) * quantity;
                    break;

                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please try again");
                    input.nextLine();
                }
            }
        }
    }
    public static void selDeal() throws FileNotFoundException{
        System.out.println();
        boolean validQuantity = false;
        boolean cont = true;
        int item = 0;

        // print the menu items and their price

        System.out.printf("\n%s MENU", selection);
        System.out.println("\n-----------------------------------------");
        System.out.println("Sr. Deals                           Price");
        for (int i = 0; i < deals.size(); i++) {
            System.out.printf((i + 1) + ".  %-31s %.2f\n", deals.get(i), dealsprice.get(i));
        }
        System.out.println("-----------------------------------------");

        System.out.println("\nPress C to continue with deals.");
        System.out.println("Press S to see single items.");
        System.out.println("Press R to change restaurant.");

        while (true) {

            String k = input.next().toLowerCase();

            if (k.equals("s")) {
                selMenu();
                cont = false;
                break;
            } else if (k.equals("r")) {
                order();
                cont = false;
                break;
            } else if (k.equals("c")) {
                break;
            }else
                System.out.println("Invalid Input. Please Try Again.");
        }
        if (cont){
            while (true) {
                try {
                    System.out.println("Select deal using Sr. No. ");
                    item = input.nextInt();

                    if (item <= 0 || item > deals.size()) {
                        System.out.println("Invalid input! Please try again\n");
                        continue;
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please try again\n");
                    input.nextLine();
                }
            }
        }
        if (cont) {
            while (!validQuantity) {
                try {
                    System.out.println("Enter the quantity: ");
                    int quantity = input.nextInt();
                    validQuantity = true;

                    //store the order in a variable
                    orders.add(quantity + "    " + deals.get(item - 1) + "[" + selection + "]");
                    singleOrder.add(quantity + "    " + deals.get(item - 1) + "[" + selection + "]");
                    siOrder.add(quantity + "      " + deals.get(item - 1) + "[" + selection + "]" + " --- Rs "
                            + dealsprice.get(item - 1)
                            + "/-");
                    totalItemP.add("Rs " + quantity * dealsprice.get(item - 1) + "/-");

                    //calculate and store the bill
                    bill += dealsprice.get(item - 1) * quantity;
                    break;

                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please try again");
                    input.nextLine();
                }
            }
        }

    }

    public static void order() throws FileNotFoundException {


        menu.clear();
        deals.clear();
        price.clear();
        dealsprice.clear();

        if (restaurants.isEmpty()) {
            System.out.println("No restaurants yet. Try Again later.");
            System.exit(0);
        } else {

            System.out.println("Select the restaurant");

            //Display the restaurant names
            for (int i = 0; i < restaurants.size(); i++) {
                System.out.println((i + 1) + ". " + restaurants.get(i));
            }
            boolean validChoice = false;
            while (!validChoice) {
                try {

                    int choice = input.nextInt();
                    if (choice <= 0 || choice > restaurants.size()) {
                        System.out.println("Invalid input! Enter again");
                        continue;
                    } else {
                        validChoice = true;
                    }


                    selection = restaurants.get(choice - 1);
                    selRest.add(selection);
                    System.out.println("WELCOME TO " + selection);
                    System.out.println("--------------------------------");

                    //Read the menu file of the restaurant selected and store the inputs in their respective arraylists
                    scanner("menu" + restaurants.get(choice - 1) + ".txt");
                    dealscanner("deals" + restaurants.get(choice - 1) + ".txt");

                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Enter again.");
                    input.nextLine();
                }
            }

            while (true) {

                System.out.println("\nPress 1 for single items.");
                System.out.println("Press 2 for deals.");
                System.out.println("Press 3 to change restaurant.");
                while (true) {
                    String choice = input.next();

                    if (choice.equals("1")) {
                        if (menu.isEmpty()){
                            System.out.println("No single items added yet to the menu. Press 2 for deals or 3 to change" +
                                    " restaurant or 4 to exit");
                        }
                        else {
                            selMenu();
                            break;
                        }
                    } else if (choice.equals("2")) {
                        if (deals.isEmpty()){
                            System.out.println("No deals items added yet to the menu. Press 1 for Single Items or 3 to" +
                                    " change restaurant or 4 to exit");
                        }
                        else {
                            selDeal();
                            break;
                        }
                    } else if (choice.equals("3")) {
                        order();
                        System.exit(0);
                        break;
                    } else if (choice.equals("4")) {
                        System.exit(0);

                    } else
                        System.out.println("Invalid choice. PLease Try again.");
                }

                System.out.println("Do you want to order more? [Y or N]");
                String moreOrder = input.next().toUpperCase();

                //Loop to check if user entered the valid input
                while (!(moreOrder.equals("Y") || moreOrder.equals("N"))) {

                    System.out.println("Invalid input. Pls Enter \"Y\" or \"N\"");
                    moreOrder = input.next().toUpperCase();

                }
                if (moreOrder.equals("Y"))
                    continue;
                else if (moreOrder.equals("N"))
                    break;

            }

            //Add the bill in the arraylist that stores bills
            String DeBill = ("Rs " + bill + "/-");
            bills.add(DeBill);

            //Take details from the user
            System.out.println("\nCostumer Details");
            System.out.println("---------------------------");
            System.out.println("Enter your name: ");
            input.nextLine();
            String name = input.nextLine();

            //Add the customer's name in the array list that stores customer names
            costumerNames.add(name);

            System.out.println("Enter your address: ");
            String location = input.nextLine();

            ////Add the customer's address in the array list that stores customer addresses
            locations.add(location);

            System.out.println("Enter phone number: ");
            String phoneNumber = "";

            //Take input from the user until user enters the phone number
            while (true) {
                try {
                    phoneNumber = input.next();

                    //check the length of the number
                    if (phoneNumber.length() != 11) {
                        System.out.println("Invalid phone number!\nPlease Enter valid number!");
                        continue;
                    }
                    boolean validNumber = true;

                    //Loop to validate if each character entered is a digit
                    for (int i = 0; i < phoneNumber.length(); i++) {
                        if (!(Character.isDigit(phoneNumber.charAt(i))))
                            validNumber = false;
                    }
                    if (!(validNumber)) {
                        System.out.println("Invalid phone number!\nPlease Enter valid number!");
                        continue;

                    }
                    phones.add(phoneNumber);
                    break;

                } catch (InputMismatchException e) {

                    //Exception if the user enters any character or string
                    System.out.println("Invalid phone number! Enter valid number");
                    input.nextLine();

                }

            }
            int orderId = (int) (1000 + Math.random() * 9000);
            orderIds.add(Integer.toString(orderId));

            //Write the detail of the order in the file
            writeOrderDetails("orderDetails.txt", (ArrayList<String>) costumerNames, (ArrayList<String>) orders,
                    (ArrayList<String>) bills, (ArrayList<String>) locations, (ArrayList<String>) singleOrder,
                    (ArrayList<String>) orderIds, phones);
            String paymentStatus = "";
            //user will select the method of payment
            while (true) {
                try {

                    System.out.println("\nSelect method of payment:\n" + "1- Cash on Delivery\n" + "2- Payment by card");
                    int payment = input.nextInt();
                    if (payment != 1 && payment != 2) {
                        System.out.println("Invalid Input!Please try again.\n");
                        continue;
                    }

                    if (payment == 1) {
                        paymentStatus = "Cash on Delivery";
                    }

                    if (payment == 2) {
                        System.out.println("Enter credit card number without \"_\" and space: ");

                        //Take input from the user until user enters the credit card number
                        while (true) {
                            try {
                                String creditCard = input.next();

                                //check the length of the number
                                if (creditCard.length() != 16) {
                                    System.out.println("Invalid credit card number!\n Please Enter valid number!");
                                    continue;
                                }
                                boolean validNumber = true;

                                //Loop to validate if each character entered is a digit
                                for (int i = 0; i < creditCard.length(); i++) {
                                    if (!(Character.isDigit(creditCard.charAt(i))))
                                        validNumber = false;
                                }
                                if (!(validNumber)) {
                                    System.out.println("Invalid credit card number!\n Please Enter valid number!");
                                    continue;

                                }
                                break;

                            } catch (InputMismatchException e) {

                                //Exception if the user enters any character or string
                                System.out.println("Invalid credit card number! Enter valid number");
                                input.nextLine();

                            }

                        }
                        paymentStatus = "Through Debit Card";
                    }
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid Input!Please try again.\n");
                    input.nextLine();
                }
            }


            //print the receipt
            System.out.println("\nORDER RECEIPT");
            System.out.println("-----------------------------");
            System.out.println("\nOrder ID: " + orderId);
            System.out.println("Costumer Name: " + name);
            System.out.println("Costumer Address: " + location);
            System.out.println("Costumer Phone Nummber: " + phoneNumber);
            System.out.println("\nORDER DETAILS");
            System.out.println("-----------------------");
            System.out.println("Sr.  QTY    ITEM DESC. WITH NET PRICE                            TOTAL");
            for (int i = 0; i < siOrder.size(); i++) {
                System.out.printf((i + 1) + ".   %-60s", siOrder.get(i));
                while (i < totalItemP.size()) {
                    System.out.printf("%-10s\n", totalItemP.get(i));
                    break;
                }
            }
            System.out.println("\nTotal Bill: Rs " + bill + "/-");
            System.out.println("Payment Method: " + paymentStatus);

            System.out.println("\nThank You!");
            System.out.println("Your order is on its way. ");
        }
    }





    public static void main(String[] args) throws FileNotFoundException {

        createFile("orderDetails.txt");
        createFile("restIds.txt");
        createFile("restaurants.txt");


        //Read the order details from file
        readOrderDetails("orderDetails.txt");
        Scanner list = null;

        try {
            //Read the restaurant ids
            list = new Scanner(new File("restIDs.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (list.hasNext()) {
            //split the line by double tab and stores the value at index 0 in arraylist that stores restaurant ids
            String[] splitted = list.nextLine().split("\t\t");

            restId.add(splitted[1]);
        }


        try {

            //Read the restaurant names from file
            list = new Scanner(new File("restaurants.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (list.hasNext()) {

            //split the line by double tab
            String[] splitted = list.nextLine().split("\t\t");
            restaurants.add(splitted[0]);

        }

        Scanner input = new Scanner(System.in);
        boolean a = true;
        System.out.println("Welcome to Panda Food Delivery Service.\n");

        //Loop to take input until user enters the valid input
        while (a) {
            System.out.println("Press 1 for ADMIN.");
            System.out.println("Press 2 for Restaurant Management.");
            System.out.println("Press 3 for Ordering Food.");

            //Take input from the user
            String choice = input.next();


            switch (choice) {
                case "1" -> {
                    admin();
                    System.out.println("\nPress 5 to return to main menu.");
                    System.out.println("Press 6 to exit.");
                    String exit = input.next();

                    //Takes input until user enters the valid number
                    while (!(exit.equals("5") || exit.equals("6"))) {

                        System.out.println("Invalid input. Pls Enter valid input");
                        exit = input.next();
                    }
                    if (exit.equals("5"))
                        a = true;

                    else if (exit.equals("6")) {
                        a = false;
                    }

                }
                case "2" -> {
                    rest();
                    System.out.println("\nPress 5 to return to main menu.");
                    System.out.println("Press 6 to exit.");
                    String exit = input.next();

                    //Takes input until user enters the valid number
                    while (!(exit.equals("5") || exit.equals("6"))) {

                        System.out.println("Invalid input. Pls Enter valid input");
                        exit = input.next();
                    }
                    if (exit.equals("5"))
                        a = true;

                    else if (exit.equals("6")) {
                        a = false;
                    }

                }
                case "3" -> {
                    order();
                    System.out.println("\nPress 5 to return to main menu.");
                    System.out.println("Press 6 to exit.");
                    String exit = input.next();

                    //Takes input until user enters the valid number
                    while (!(exit.equals("5") || exit.equals("6"))) {

                        System.out.println("Invalid input. Pls Enter valid input");
                        exit = input.next();
                    }
                    if (exit.equals("5"))
                        a = true;

                    else if (exit.equals("6")) {
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