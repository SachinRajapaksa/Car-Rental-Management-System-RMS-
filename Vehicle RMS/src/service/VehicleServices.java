package service;

import model.Bike;
import model.Car;
import model.Van;
import model.Vehicle;
import util.InputValidation;

import java.util.ArrayList;

public class VehicleServices {
    private final ArrayList<Vehicle> vehicles = new ArrayList<>();
    private double totalIncome = 0;

    // This code line is used to add vehicles.
    public void addVehicle(InputValidation input) {
        do {
            System.out.println("\n--------Select Vehicle Type--------");
            System.out.println("1. Car");
            System.out.println("2. Van");
            System.out.println("3. Bike");

            int type = input.rangeCheck("\nSelect option (1-3) : ", 1, 3);

            System.out.println("\n------Add Vehicle------");
            String id = input.checkUniqueId("vehicle ID: ", vehicles);
            String brand = input.readString("Vehicle brand : ");
            String model = input.readString("Vehicle model : ");
            double baseRatePerDay = input.readPositiveDouble("Rent Price/Day : ");

            //The below code uses switch function to create a specific vehicle type

            switch (type) {
                //Creates the car object
                case 1 -> {
                    int seats = input.readPositiveInt("Number of seats: ");
                    vehicles.add(new Car(id, brand, model, baseRatePerDay, true, seats));
                }
                //Creates the van object
                case 2 -> {
                    double cargoCapacity = input.readPositiveDouble("Cargo Capacity Kg: ");
                    vehicles.add(new Van(id, brand, model, baseRatePerDay, true, cargoCapacity));
                }
                //creates the bike object
                case 3 -> {
                    int engine = input.readPositiveInt("Engine Capacity: ");
                    vehicles.add(new Bike(id, brand, model, baseRatePerDay, true, engine));
                }
            }
            System.out.println("Vehicle added successfully!");
        } while (input.goForward("\nAdd another vehicle"));
    }

    // The below code displays all vehicles in the array list
    public void viewAllVehicle() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles. Add some vehicles");
            return;
        }
        System.out.println("\n----- Vehicles -----");
        for (Vehicle v : vehicles) {
            v.displayDetails();

        }
    }

    // This code is used to rent vehicles
    public void rentVehicle(InputValidation input) {
        while (true) {
            //Checks whether the array list is empty and whether the vehicles are available to rent
            if ((vehicles.isEmpty()) || !checkAvailble()) {
                if (vehicles.isEmpty()) System.out.println("No vehicles are available.");
                if (!checkAvailble()) System.out.println("All vehicles have been rented.");
                break;

            } else {
                System.out.println("\n----- Vehicle Renting -----");
                String id = input.readString("Enter Vehicle ID: ");
                Vehicle vehicle = findById(id);

                // checks whether the vehicle is null using the vehicle ID

                if (vehicle == null) {
                    System.out.println("Vehicle (" + id + ") not found.");
                } else if (!vehicle.getIsAvailable()) {
                    System.out.println("Already Rented Out.");
                } else {
                    int days = input.readPositiveInt("Number of days: ");
                    //the cost is calculated according the vehicle type and it is saved inside the variable "cost"
                    double cost = vehicle.calculateRentalCost(days);
                    //previous value inside cost is updated by adding the new cost and the total income is updated
                    totalIncome += cost;
                    System.out.println("Your rental cost: " + cost);

                    //the vehicle is searched through the vehicle ID and the availability is changed
                    vehicle.rentVehicle();

                }
            }
            if (!input.goForward("\nRent another vehicle.")) {
                return;
            }

        }
    }

    // This is used to return the rented vehicles
    public void returnVehicle(InputValidation input) {
        while (true) {
            // Checks whether the array list is empty and whether the vehicles are already rented

                if (vehicles.isEmpty()){
                    System.out.println("No vehicles are available.");
                    return;
                }
                if (!hasRentedVehicle()) {
                    System.out.println("All vehicles have been returned.");
                    return;
                }

            String id = input.readString("Enter vehicle ID: ");
            Vehicle vehicle = findById(id);

            if (vehicle == null) {
                System.out.println("Vehicle" + id + " not found");
            } else {
                //after finding the vehicle ID, the function is called
                vehicle.returnVehicle();
            }
            if (!input.goForward("\nReturn another vehicle")) {
                return;
            }
        }
    }

    //Search a vehicle by ID
    public void searchVehicle(InputValidation input) {
        while (true) {
            if (vehicles.isEmpty()) {
                System.out.println("No vehicles are available.");
                return;
            } else {
                System.out.println("\n----- Searching Vehicle -----");
                String id = input.readString("Enter Vehicle ID: ");
                Vehicle vehicle = findById(id);

                if (vehicle == null) {
                    System.out.println("Vehicle did not exist.");
                } else {
                    // the specific details of the ID is displayed
                    vehicle.displayDetails();
                }
                if (!input.goForward("\nNeed to search another vehicle.")) {
                    return;
                }

            }
        }
    }

    //Filter vehicle by brand, model or availability
    public void filterVehicle(InputValidation input) {
        while (true) {
            if (vehicles.isEmpty()) {
                System.out.println("No vehicles are available.");
                return;
            } else {
                System.out.println("\n----- Filtering Vehicles -----");
                System.out.println("1. Brand");
                System.out.println("2. Model");
                System.out.println("3. Availability");

                int value = input.rangeCheck("\nSelect option (1-3): ", 1, 3);

                switch (value) {
                    case 1 -> {
                        String brand = input.readString("Enter vehicle brand: ");
                        boolean found = false;
                        /*this for loop is used to check whether the variable "brand" type entered by the
                        user is available inside the array list*/

                        for (Vehicle v : vehicles) {
                            if (v.getBrand().equalsIgnoreCase(brand)) {
                                v.displayDetails();
                                found = true;
                            }
                        }
                        if (!found) System.out.println("No matching vehicles fo " + brand);
                    }
                    case 2 -> {
                        String model = input.readString("Enter vehicle model: ");
                        boolean found = false;
                        /*this for loop is used to check whether the variable "model" type entered by the
                        user is available inside the array list*/

                        for (Vehicle v : vehicles) {
                            if (v.getModel().equalsIgnoreCase(model)) {
                                v.displayDetails();
                                found = true;
                            }
                        }
                        if (!found) System.out.println("No matching vehicles found " + model);
                    }
                    case 3 -> {
                        System.out.println("Select Availability");
                        System.out.println("1. Is Available");
                        System.out.println("2. Not Available");
                        int choice = input.rangeCheck("Enter your choice :", 1, 2);
                        boolean isAvailable = choice == 1;
                        boolean found = false;
                        //checks the availability of the vehicles for the user to choose
                        for (Vehicle v : vehicles) {
                            if (v.getIsAvailable() == isAvailable) {
                                v.displayDetails();
                                found = true;
                            }
                        }
                        if (!found) System.out.println("No vehicles found !");
                    }
                }
            }
            if (!input.goForward("Want to filter another ")) {
                return;
            }
        }
    }

    // View total Income
    public void viewTotalIncome() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles.");
            return;
        }
        System.out.println("\n----- Total Income -----");
        System.out.println("Total income is: Rs " + totalIncome);
    }

    // this method is used to find vehicles using the vehicle ID
    private Vehicle findById(String id) {
        for (Vehicle v : vehicles) {
            if (v.getVehicleId().equalsIgnoreCase(id)) {
                return v;
            }
        }
        //if the vehicle is not found inside the array list, it returns NULL
        return null;
    }

    //this method is used to find the availability of the vehicle from the vehicle list
    private boolean checkAvailble() {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getIsAvailable()) {
                return true;
            }
        }
        return false;
    }
    //this method is used to find the rented of the vehicle from the vehicle list
    private boolean hasRentedVehicle() {
        for (Vehicle vehicle : vehicles) {
            if (!vehicle.getIsAvailable()) {
                return true;
            }
        }
        return false;
    }

}