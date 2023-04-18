# Project Description

## Vehicle Class

![VehicleModel](https://user-images.githubusercontent.com/77849594/232226695-faec9ba6-9c83-410e-9f1f-edd571132616.png)

### Attributes

- `maxLoad` (mandatory): maximum weight the vehicle can carry
- `currentLoad` (mandatory): current weight of the vehicle's load
- `pricePerDay` (optional): rental price per day
- `VEHICLES_IN_GARAGE` (class): a number of all the vehicles created
- `FuelConsumption` (complex):
    - `fileRate`: how many kilometers the vehicle can travel with 1 liter of fuel
    - `fuelVolume`: the volume of the vehicle's fuel tank
- `drivers` (multi-value): a collection of drivers of the vehicle
- `freeSpace` (derived): defference between maxLoad and currentLoad (returned from the method isAvaliable())

### Methods

- `isAvailable()`: returns true if the vehicle is available (i.e., currentLoad < maxLoad), false otherwise
- `addDriver(driver)`: adds a new driver to the drivers collection
- `removeDriver(driver)`: removes the driver from the drivers collection
- `getAvailableVehicles()`: returns the set of currently available vehicles in the garage

### Class Extent

- A set of all correctly created objects of the Vehicle class
- Each newly created object is automatically placed in the extent collection
- The extent collection is common to all instances of the Vehicle class (static)
- The extent collection is secured with an appropriate access modifier
- There are no public methods to modify the extent collection
- The `getExtent()` method returns a copy of the extent collection, preventing its modification

### Class Extent - Persistence

- The Vehicle class implements the `java.io.Serializable` interface
- All class attributes implement the `java.io.Serializable` interface
- Static methods to write to and read from the file are created
- These methods handle exceptions properly using the try-with-resources construct
- The persistence is obtained by object serialization and writing (reading) to (from) the file

### Complex Attribute

- The `FuelConsumption` class is created to represent the FuelConsumption complex attribute
- The `FuelConsumption` class members are:
    - `fuelRate`
    - `fuelVolume`
- The `FuelConsumption` class implements the `java.io.Serializable` interface
- Privacy of the `FuelConsumption` class fields is ensured
- The correct get and set methods are implemented
- A constructor that initializes the required components of the object is implemented

### Optional Attribute

- The `pricePerDay` attribute is optional and can be set to null
- The setter method accepts a null value as a parameter
- An appropriate check is made when setting a new value and, if necessary, an exception signaling a validation error is thrown

### Mandatory Attribute

- The `maxLoad` and `currentLoad` attributes are mandatory
- A simple type (i.e., `int`) is used to prevent null values from being set for numeric types
- An appropriate validation of the value in the setter method is made, and if necessary, an exception is thrown in the case of setting an incorrect value
- A constructor that sets all the mandatory attributes is created
- If the conditions for validating the attributes are not met, the object cannot be placed in the class extent

### Multi-value Attribute

- The `drivers` attribute is a multi-value attribute and is implemented with a collection
- The `drivers` attribute is mandatory and should always have at least one value
- Methods to add and remove the value of the attribute are implemented
