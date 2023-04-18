import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class VehicleTest {

    private FuelConsumption fuelConsumption;
    private Set<String> drivers;
    private Vehicle ship;
    private Vehicle track;

    @BeforeEach
    void setUp() {
        try {
            fuelConsumption = new FuelConsumption(1,1);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void firstScenario() {
        try {
            Vehicle.clearExtent();

            drivers = new HashSet<>();
            drivers.add("Mark");
            drivers.add("John");

            ship = new Vehicle("Laguna", 1.0, fuelConsumption,
                    10, 8, drivers);

            track = new Vehicle("Laguna", 1.0, fuelConsumption,
                    10, 9, drivers);

            assertEquals(2, Vehicle.getAvailableVehicles().size());

        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void secondScenario() {
        try {
            Vehicle.clearExtent();

            drivers = new HashSet<>();
            drivers.add("");
            drivers.add("");

            ship = new Vehicle("Laguna", 1.0, fuelConsumption,
                    10, 8, drivers);

            track = new Vehicle("Laguna", 1.0, fuelConsumption,
                    10, 9, drivers);

            if (Vehicle.getAvailableVehicles().size() > 0) {
                fail();
            }

            assertEquals(0, Vehicle.getAvailableVehicles().size());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void thirdScenario() {
        try {
            Vehicle.clearExtent();

            drivers = new HashSet<>();
            drivers.add("BigO");
            drivers.add("");

            ship = new Vehicle("Laguna", null, fuelConsumption,
                    10, 8, drivers);

            track = new Vehicle("Laguna", fuelConsumption,
                    10, 9, drivers);

            assertEquals(2, Vehicle.getAvailableVehicles().size());

        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    void staticAttributeTest() {
        try {
            Vehicle.clearExtent();

            drivers = new HashSet<>();
            drivers.add("BigO");
            drivers.add("");

            ship = new Vehicle("Laguna", null, fuelConsumption,
                    10, 8, drivers);

            track = new Vehicle("Laguna", fuelConsumption,
                    10, 9, drivers);

            assertEquals("Nikita Padabed", Vehicle.getOwner());
            assertEquals("Nikita Padabed", Vehicle.getOwner());
            System.out.println(Vehicle.getOwner());

            Vehicle.saveExtent();
            Vehicle.loadExtent();

            Vehicle.setOwner("Nikita");

            Vehicle.saveExtent();
            Vehicle.loadExtent();
            assertEquals("Nikita", Vehicle.getOwner());
            assertEquals("Nikita", Vehicle.getOwner());
            System.out.println(Vehicle.getOwner());

        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

}
