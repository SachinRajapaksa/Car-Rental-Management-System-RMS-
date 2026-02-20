package model;

public class Van extends Vehicle {
    private double cargoCapacity;
    private double totalCost;
    //this is the constructor for the van class
    public Van(String vehicleId, String brand, String model, double baseRatePerDay, boolean isAvailable, double cargoCapacityKg) {
        super(vehicleId, brand, model, baseRatePerDay, isAvailable);
        this.cargoCapacity = cargoCapacityKg;

    }
    // this is the abstract override method implemented from the vehicle class
    @Override
    public double calculateRentalCost(int days){
        totalCost = getBaseRatePerDay()*days+(cargoCapacity*0.2*days);
        return totalCost;
     }

}
