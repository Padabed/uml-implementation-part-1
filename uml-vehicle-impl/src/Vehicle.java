import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Vehicle implements Serializable {
    private String manufacturer;
    private Double pricePerDay; // optional

    private static int VEHICLES_IN_GARAGE;

    private FuelConsumption fuelConsumption; // complex
    private double maxLoad;
    private double currentLoad;
    private Set<String> drivers = new HashSet<>();

    private static Set<Vehicle> extent = new HashSet<>();

    public Vehicle(String manufacturer, Double pricePerDay, FuelConsumption fuelConsumption,
                   double maxLoad, double currentLoad, Set<String> drivers) {
        setManufacturer(manufacturer);
        setPricePerDay(pricePerDay);
        setMaxLoad(maxLoad);
        setCurrentLoad(currentLoad);
        setFuelConsumption(fuelConsumption);
        addDriver(drivers);
        updateGarageWithNewVehicle();
        extent.add(this);
    }

    public Vehicle(String manufacturer, FuelConsumption fuelConsumption,
                   double maxLoad, double currentLoad, Set<String> drivers) {
        this(manufacturer, null, fuelConsumption, maxLoad, currentLoad, drivers);
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        if (manufacturer == null || manufacturer.isBlank()) {
            throw new IllegalArgumentException("Manufacturer field is required");
        }
        this.manufacturer = manufacturer;
    }

    public FuelConsumption getFuelConsumption() {
        return fuelConsumption;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Double pricePerDay) {
        if (pricePerDay != null && pricePerDay < 0) {
            throw new IllegalArgumentException("Price per day could not be less than 0");
        }
        this.pricePerDay = pricePerDay;
    }
    private void updateGarageWithNewVehicle() {
        Vehicle.VEHICLES_IN_GARAGE++;
    }

    public static int getNumberOfVehiclesInGarage() {
        return Vehicle.VEHICLES_IN_GARAGE;
    }

    public double getMaxLoad() {
        return maxLoad;
    }

    public void setMaxLoad(double maxLoad) {
        if (maxLoad < 0) {
            throw new IllegalArgumentException("Max Vehicle load could not be less than 0");
        }
        this.maxLoad = maxLoad;
    }

    public double getCurrentLoad() {
        return currentLoad;
    }

    public void setCurrentLoad(double currentLoad) {
        if (currentLoad < 0) {
            throw new IllegalArgumentException("Current load cannot be less than 0");
        }
        if (currentLoad > maxLoad) {
            throw new IllegalArgumentException("Current load cannot exceed the max load");
        }
        this.currentLoad = currentLoad;
    }

    public void setFuelConsumption(FuelConsumption fuelConsumption) {
        if (fuelConsumption == null) {
            throw new IllegalArgumentException("Fuel consumption field cannot be null");
        }
        this.fuelConsumption = fuelConsumption;
    }

    private void addDriver(String driver) {
        if (driver == null || driver.isBlank()){
            throw new IllegalArgumentException();
        }
        drivers.add(driver);
    }

    private void addDriver(Set<String> drivers) {

        int errorNameCounter = 0;

        for (String driver : drivers) {
            try {
                addDriver(driver);
            } catch (IllegalArgumentException e) {
                errorNameCounter++;
            }
        }

        if (drivers.size() - errorNameCounter < 1) {
            throw new IllegalArgumentException("No drivers was added due to incorrect input");
        } else {
            System.out.println("\nVehicle drivers entered: " + drivers.size() +
                    ", \nVehicle drivers added: " + (drivers.size() - errorNameCounter));
        }
    }

    public Set getDrivers() {
        return Collections.unmodifiableSet(drivers);
    }

    public static void saveExtent() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.dat"))) {
            oos.writeObject(extent);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadExtent() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.dat"))) {
            extent = (Set<Vehicle>) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Set getExtent() {
        return Collections.unmodifiableSet(extent);
    }

    public static void clearExtent() {
        extent.clear();
    }

    public boolean isAvailable() {
        return (maxLoad - currentLoad) > 0;
    }

    public static Set<Vehicle> getAvailableVehicles() {
        Set<Vehicle> extent = (Set<Vehicle>) getExtent();
        return extent.stream()
                .filter(v -> v.isAvailable())
                .collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "manufacturer='" + manufacturer + '\'' +
                ", pricePerDay=" + (pricePerDay != null ? pricePerDay : "not stated") +
                ", fuelConsumption=" + fuelConsumption +
                ", maxLoad=" + maxLoad +
                ", currentLoad=" + currentLoad +
                ", available=" + isAvailable() +
                ", drivers=" + drivers +
                "}\n";
    }
}
