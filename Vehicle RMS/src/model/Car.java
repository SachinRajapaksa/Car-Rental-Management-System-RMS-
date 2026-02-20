package model;

public class Car extends Vehicle{
    private int numberOfSeats;
    private double totalCost;
    //this is the constructor of the car class
    public Car(String vehicleId, String brand, String model, double baseRatePerDay, boolean isAvailable, int numberOfSeats){
        super(vehicleId,brand,model,baseRatePerDay,isAvailable);
        this.numberOfSeats = numberOfSeats;

    }

    // this is the abstract override method implemented from the vehicle class
   @Override
   public double calculateRentalCost(int days){
        totalCost = getBaseRatePerDay()*days+(numberOfSeats*200*days);
        return totalCost;

   }

}
