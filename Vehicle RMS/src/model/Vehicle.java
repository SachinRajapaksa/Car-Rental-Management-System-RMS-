package model;

public abstract class Vehicle {
    private String vehicleId;
    private String brand;
    private String model;
    private double baseRatePerDay;
    private boolean isAvailable;
    //this is the constructor of the superclass
    public Vehicle(String vehicleId, String brand,String model,double baseRatePerDay, boolean isAvailable){
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.baseRatePerDay = baseRatePerDay;
        this.isAvailable = isAvailable;
    }
    //---------------Getters-----------------
    public String getVehicleId(){
        return vehicleId;
    }
    public String getBrand(){
        return brand;
    }
    public String getModel(){
        return model;
    }
    public double getBaseRatePerDay(){
        return baseRatePerDay;
    }
    public boolean getIsAvailable(){
        return isAvailable;
    }

    //-----------------Setters------------------
    public void setVehicleId(String vehicleId){
        this.vehicleId = vehicleId;
    }
    public void setBrand(String brand){
        this.brand = brand;
    }
    public void setModel(String model){
        this.model = model;
    }
    public void setBaseRatePerDay(double baseRatePerDay){
        this.baseRatePerDay = baseRatePerDay;
    }
    public void setIsAvailable(boolean isAvailable){
        this.isAvailable = isAvailable;
    }

    // display vehicle details
    public void displayDetails(){
        System.out.println("\n---- Details ----");
        System.out.println("Vehicle ID : "+vehicleId);
        System.out.println("Brand : "+brand);
        System.out.println("Model : "+model);
        System.out.println("Rate Per Day : "+baseRatePerDay);
        if (isAvailable){
            System.out.println("Availability : Available");
        }
        else System.out.println("Availability : Not Available");
    }

    public void rentVehicle(){
        if(!isAvailable){
            System.out.println("This Vehicle ("+vehicleId+") Not Available");
        }
        else {
            isAvailable = false;
            System.out.println("Vehicle ("+vehicleId+") Rent Successful");
        }

    }
    public void returnVehicle(){
        if(!isAvailable){
            System.out.println("Vehicle ("+vehicleId+") Returned Successfully");
            isAvailable = true;
        } else {
            System.out.println("Vehicle("+vehicleId+") Already Returned");
        }
    }
    public abstract double calculateRentalCost(int days);
}
