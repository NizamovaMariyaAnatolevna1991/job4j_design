package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ParkingTest {

    @Test
    void whenParkCarThenSuccess() {
        Parking parking = new SimpleParking(3, 0);
        Vehicle car = new Car("A123BC");
        boolean parked = parking.park(car);

        assertThat(parked).isTrue();
        assertThat(parking.getAvailablePlaces()).isEqualTo(2);
        assertThat(parking.getVehicles().contains(car));
    }

    @Test
    void whenParkTruckOnTruckSpotThenSuccess() {
        Parking parking = new SimpleParking(0, 1);
        Vehicle truck = new Truck("T999XX", 3);
        boolean parked = parking.park(truck);
        assertThat(parked).isTrue();
    }

    @Test
    void whenParkTruckInFreeCarSpotsThenSuccess() {
        Parking parking = new SimpleParking(5, 0);
        Vehicle truck = new Truck("T999XX", 3);
        boolean parked = parking.park(truck);

        assertThat(parking.getAvailablePlaces()).isEqualTo(2);
    }

    @Test
    void whenNoPlaceThenFail() {
        Parking parking = new SimpleParking(1, 0);
        Vehicle car1 = new Car("C001");
        Vehicle car2 = new Car("C002");

        parking.park(car1);
        boolean result = parking.park(car2);

        assertThat(result).isFalse();
    }

    @Test
    void whenVehicleLeavesThenPlaceFreed() {
        Parking parking = new SimpleParking(5, 0);

        Vehicle car = new Car("A123BC");
        Vehicle truck = new Truck("T999XX", 3);

        parking.park(car);
        parking.park(truck);

        assertThat(parking.getAvailablePlaces()).isEqualTo(1);

        boolean result = parking.leave("A123BC");

        assertThat(result).isTrue(); // успешно уехала

        assertThat(parking.getAvailablePlaces()).isEqualTo(2);
        assertThat(parking.getVehicles()).doesNotContain(car);
    }

    @Test
    void whenTruckLeavesThenMultiplePlacesFreed() {
        Parking parking = new SimpleParking(5, 0);
        Vehicle truck = new Truck("T999XX", 4);
        parking.park(truck);

        assertThat(parking.getAvailablePlaces()).isEqualTo(1);

        boolean result = parking.leave("T999XX");

        assertThat(result).isTrue();
        assertThat(parking.getAvailablePlaces()).isEqualTo(5); // все места свободны
        assertThat(parking.getVehicles()).isEmpty();
    }

    @Test
    void whenParkTruckThenOnlyAdjacentSpots() {
        Parking parking = new SimpleParking(5, 0);

        Vehicle car1 = new Car("C001");
        Vehicle car2 = new Car("C002");

        parking.park(car1);
        parking.park(car2);

        Vehicle truck = new Truck("T999", 2); // хочет 2 места

        // when
        boolean parked = parking.park(truck);

        // then
        assertThat(parked).isTrue(); // может встать на [4][5]
        assertThat(parking.getAvailablePlaces()).isEqualTo(1);
    }

    @Test
    void whenNoAdjacentSpotsThenTruckCannotPark() {
        Parking parking = new SimpleParking(5, 0);
        parking.park(new Car("C001")); // место 1
        parking.park(new Car("C002")); // место 3


        Vehicle bigTruck = new Truck("T999", 3);
        assertThat(parking.park(bigTruck)).isTrue();

        parking.leave("C002");

        Vehicle smallTruck = new Truck("T888", 2);

        boolean canPark = parking.park(smallTruck);

        assertThat(canPark).isFalse();
    }
}