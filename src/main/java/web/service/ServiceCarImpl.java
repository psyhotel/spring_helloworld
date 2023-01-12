package web.service;

import org.springframework.stereotype.Component;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServiceCarImpl implements ServiceCar {
    private List<Car> cars;

    {
        cars = new ArrayList<>();
        cars.add(new Car(1, "Lada", "Black"));
        cars.add(new Car(2, "Lamborghini", "Red"));
        cars.add(new Car(3, "Porsche", "White"));
        cars.add(new Car(4, "Audi", "Blue"));
        cars.add(new Car(5, "Suzuki", "Green"));

    }

    public List<Car> allCars() {
        return cars;
    }

    public List<Car> getCars(Integer count) {
        if (count != null && count >= 1 && count <= 5) {
            List<Car> carList = (List<Car>) cars.stream().limit(count).collect(Collectors.toList());
            return carList;
        } else {
            return cars;
        }
    }
}