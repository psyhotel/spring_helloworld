package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.service.ServiceCar;

@Controller
public class CarController {

    private final ServiceCar serviceCar;

    @Autowired
    public CarController(ServiceCar serviceCar) {
        this.serviceCar = serviceCar;
    }

    @GetMapping(value = "/cars")
    public String allCars(@RequestParam(value = "count", required = false) Integer count, Model model) {
        model.addAttribute("Count", count);
        model.addAttribute("allCars", serviceCar.getCars(count));
        return "/cars";
    }

}