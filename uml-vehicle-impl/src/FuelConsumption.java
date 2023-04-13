import java.io.Serializable;

public class FuelConsumption implements Serializable {
    private double fuelRate, fuelVolume;

    public FuelConsumption(double fuelRate, double fuelVolume) {
        setFuelRate(fuelRate);
        setFuelVolume(fuelVolume);
    }

    public double getFuelRate() {
        return fuelRate;
    }

    public void setFuelRate(double fuelRate) {
        if (fuelRate < 1) {
            throw new IllegalArgumentException("Fuel rate cannot be less than 1");
        }
        this.fuelRate = fuelRate;
    }

    public double getFuelVolume() {
        return fuelVolume;
    }

    public void setFuelVolume(double fuelVolume) {
        if (fuelVolume < 1) {
            throw new IllegalArgumentException("Fuel Volume cannot be less than 1");
        }
        this.fuelVolume = fuelVolume;
    }

    @Override
    public String toString() {
        return "FuelConsumption{" +
                "fuelRate=" + fuelRate +
                ", fuelVolume=" + fuelVolume +
                '}';
    }
}
