package web.service;

import web.model.Car;

import java.util.List;

public interface ServiceCar {
    public List<Car> allCars();

    public List<Car> getCars(Integer count);

}
