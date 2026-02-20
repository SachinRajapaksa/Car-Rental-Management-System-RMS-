package util;

import model.Vehicle;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputValidation {
    private final Scanner sc;

    public InputValidation(Scanner sc) {
        this.sc = sc;
    }

    // Reads integers using try catch
    public int readInt(String text) {
        while (true) {
            System.out.print(text);
            try {
                int value = sc.nextInt();
                sc.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input please try a whole number");
                sc.nextLine();
            }
        }
    }

    // Reads doubles using try catch
    public double readDouble(String text) {
        while (true) {
            System.out.print(text);
            try {
                double value = sc.nextDouble();
                sc.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please try a decimal number.");
                sc.nextLine();
            }
        }
    }

    // Check if the number entered is positive
    public int readPositiveInt(String text) {
        while (true) {
            int value = readInt(text);
            if (value > 0) {
                return value;
            }
            System.out.println("Value needs to be greater than 0");
        }
    }

    // this method checks whether the number is in range
    public int rangeCheck(String text, int min, int max) {
        while (true) {
            int value = readInt(text);
            if (value >= min && value <= max) {
                return value;
            } else {
                System.out.println("Enter a number between " + min + " and " + max);
            }
        }
    }

    // this method checks until the user enters a positive double
    public double readPositiveDouble(String text) {
        while (true) {
            double value = readDouble(text);
            if (value > 0) {
                return value;
            }
            System.out.println("Enter a positive decimal number");
        }
    }

    // Error handling when getting strings
    public String readString(String text) {
        while (true) {
            System.out.print(text);
            try {
                String value = sc.nextLine();
                if (!value.isEmpty()) {
                    return value;
                }
                System.out.println("Input cannot be empty.");
            } catch (Exception e) {
                System.out.println("Error please try again.");
            }
        }
    }

    // Check for repetition in ID
    public String checkUniqueId(String text, ArrayList<Vehicle> vehiclelist) {
        while (true) {
            String id = readString(text);
            boolean found = false;
            for (Vehicle v : vehiclelist) {
                if (v.getVehicleId().equalsIgnoreCase(id)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return id;
            }
            System.out.println("Vehicle ID: " + id + " already exist. Try a different ID.");
        }
    }

    // this code asks the user whether the user wants to move forward
    public boolean goForward(String text) {
        System.out.print(text + " (Y/N) : ");
        while (true) {
            try {
                String value = sc.nextLine();
                if (value.equalsIgnoreCase("Y")) return true;
                if (value.equalsIgnoreCase("N")) return false;
                System.out.print("Please enter Y or N.");
            } catch (Exception e) {
                System.out.println("Error! Please try again");
            }
        }
    }
}
