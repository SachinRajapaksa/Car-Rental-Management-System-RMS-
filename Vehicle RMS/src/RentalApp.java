import service.VehicleServices;
import util.InputValidation;
import java.util.Scanner;


public class RentalApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        InputValidation input = new InputValidation(sc);
        VehicleServices services = new VehicleServices();
        //this code is used to display the menu until the user makes a choice or exits
        while (true) {
            System.out.println("\n----------------- Menu -----------------");
            System.out.println("1. Add a Vehicle");
            System.out.println("2. View All Vehicles");
            System.out.println("3. Rent a Vehicle");
            System.out.println("4. Return a Vehicle");
            System.out.println("5. Search Vehicle by ID");
            System.out.println("6. Filter Vehicles");
            System.out.println("7. View Total Rental Income");
            System.out.println("8. Exit");
            System.out.println("\n==========================================");

            int inputNO = input.rangeCheck("Enter your choice: ", 1, 8);
            //switch changes the user's path according to the variable "inputNO"
            switch (inputNO) {
                case 1 -> services.addVehicle(input);
                case 2 -> services.viewAllVehicle();
                case 3 -> services.rentVehicle(input);
                case 4 -> services.returnVehicle(input);
                case 5 -> services.searchVehicle(input);
                case 6 -> services.filterVehicle(input);
                case 7 -> services.viewTotalIncome();
                case 8 -> {
                    System.out.println("Good bye!");
                    sc.close();
                    return;
                }
            }


        }
    }
}

