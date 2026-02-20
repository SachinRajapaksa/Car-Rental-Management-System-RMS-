package model;

public class Bike extends Vehicle {
    private int engineCapacityCC;
    private double totalCost;
    //this is the constructor of the bike class
    public Bike(String vehicleID, String brand, String model, double baseRatePerDay,boolean isAvailable, int engineCapacityCC){
        super(vehicleID,brand,model,baseRatePerDay,isAvailable);
        this.engineCapacityCC = engineCapacityCC;
    }
    // this is the abstract override method implemented from the vehicle class
    @Override
    public double calculateRentalCost(int days){
        totalCost = getBaseRatePerDay()*days+(engineCapacityCC*0.5*days);
        return totalCost;
    }

}
