package com.datamirage.providers;

import com.datamirage.util.RandomService;
import com.datamirage.locale.DataMirageLocale;
import com.datamirage.util.DataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.*;

class VehicleProviderTest {
    private VehicleProvider vehicleProvider;

    @BeforeEach
    void setUp() {
        DataLoader.setLocale(DataMirageLocale.TR_TR);
        vehicleProvider = new VehicleProvider(new RandomService());
    }

    @RepeatedTest(20)
    void vehicle_ShouldReturnValidVehicle() {
        String vehicle = vehicleProvider.vehicle();
        assertNotNull(vehicle);
        assertFalse(vehicle.isEmpty());
    }

    @RepeatedTest(20)
    void manufacturer_ShouldReturnValidManufacturer() {
        String manufacturer = vehicleProvider.manufacturer();
        assertNotNull(manufacturer);
        assertFalse(manufacturer.isEmpty());
    }

    @RepeatedTest(20)
    void model_ShouldReturnValidModel() {
        String model = vehicleProvider.model();
        assertNotNull(model);
        assertFalse(model.isEmpty());
    }

    @RepeatedTest(20)
    void type_ShouldReturnValidType() {
        String type = vehicleProvider.type();
        assertNotNull(type);
        assertFalse(type.isEmpty());
    }

    @RepeatedTest(20)
    void fuel_ShouldReturnValidFuel() {
        String fuel = vehicleProvider.fuel();
        assertNotNull(fuel);
        assertFalse(fuel.isEmpty());
    }

    @RepeatedTest(20)
    void color_ShouldReturnValidColor() {
        String color = vehicleProvider.color();
        assertNotNull(color);
        assertFalse(color.isEmpty());
    }

    @RepeatedTest(20)
    void vin_ShouldReturnValidVin() {
        String vin = vehicleProvider.vin();
        assertNotNull(vin);
        assertFalse(vin.isEmpty());
        assertTrue(vin.matches("^[A-HJ-NPR-Z0-9]{17}$"));
    }

    @RepeatedTest(20)
    void licensePlate_ShouldReturnValidFormat() {
        String licensePlate = vehicleProvider.licensePlate();
        assertNotNull(licensePlate);
        assertFalse(licensePlate.isEmpty());
    }
} 