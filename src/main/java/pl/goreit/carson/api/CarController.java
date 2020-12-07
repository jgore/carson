package pl.goreit.carson.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.goreit.api.generated.car.CarRequest;
import pl.goreit.api.generated.car.CarResponse;
import pl.goreit.api.generated.car.RouteRequest;
import pl.goreit.carson.domain.service.CarService;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping
    public CarResponse create(@RequestBody  CarRequest carRequest) {
        return carService.create(carRequest);
    }

    @GetMapping
    public List<CarResponse> getByOwner() throws DomainException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        return carService.findByOwner(name);
    }

    @PostMapping("/route")
    public CarResponse addRoute(@RequestBody  RouteRequest routeRequest) {
        return carService.addRoute(routeRequest);
    }

    @PostMapping("/sell")
    public CarResponse sell(@RequestParam String No) throws DomainException {
        return carService.sell(No);
    }
}
