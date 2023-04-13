import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        FuelConsumption fuelConsumption = new FuelConsumption(1, 1);

        Set drivers = new HashSet();
        drivers.add("Andrei");
        drivers.add("Mark");

        Set drivers1 = new HashSet();
        drivers1.add("");
        drivers1.add("K");

        System.out.println("Number of vehicles before creation: " +
                Vehicle.getNumberOfVehiclesInGarage());

        Vehicle vehicle = new Vehicle("TrackRS", 1.0, fuelConsumption,
                8, 5, drivers);

        Vehicle vehicle1 = new Vehicle("TrainRS", 1.0, fuelConsumption,
                   9, 9, drivers1);

        System.out.println("\nNumber of vehicles after creation: " +
                Vehicle.getNumberOfVehiclesInGarage());

        try {
            System.out.println("\nExtent before saving: \n");
            System.out.println(Vehicle.getExtent());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Vehicle vehicle2 = new Vehicle("PlaneRS", 1.0, fuelConsumption,
                9, 9, drivers1);

        try {
            System.out.println("\nExtent after saving: \n");
            Vehicle.saveExtent();
            Vehicle.loadExtent();
            System.out.println(Vehicle.getExtent());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\n Avaliable: \n" + Vehicle.getAvailableVehicles());

    }

}
