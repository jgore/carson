package pl.goreit.carson.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.goreit.api.generated.car.CarRequest;
import pl.goreit.api.generated.car.CarResponse;
import pl.goreit.api.generated.car.RouteRequest;
import pl.goreit.carson.api.DomainException;
import pl.goreit.carson.api.ExceptionCode;
import pl.goreit.carson.domain.model.Car;
import pl.goreit.carson.domain.model.Route;
import pl.goreit.carson.infrastructure.mongo.CarRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService implements CrudService<Car, CarRequest, CarResponse> {

    @Autowired
    private CarRepo carRepo;

    @Autowired
    private ConversionService carsonConversionService;

    @Override
    public CarResponse create(CarRequest carRequest) {
        Car car = carsonConversionService.convert(carRequest, Car.class);
        assert car != null;
        Car saved = carRepo.save(car);

        return carsonConversionService.convert(saved, CarResponse.class);
    }

    @Override
    public List<CarResponse> findAll() {
        return carRepo.findAll()
                .stream()
                .map(car -> carsonConversionService.convert(car, CarResponse.class))
                .collect(Collectors.toList());
    }

    public List<CarResponse> findByOwner(String owner) throws DomainException {
        List<Car> cars = carRepo.findByOwner(owner);

        return cars.stream()
                .map(car -> carsonConversionService.convert(car, CarResponse.class))
                .collect(Collectors.toList());

    }

    @Override
    public CarResponse save(Car car) {
        Car saved = carRepo.save(car);
        return carsonConversionService.convert(saved, CarResponse.class);
    }

    @Override
    public void delete(String id) {
        carRepo.deleteById(id);
    }

    public CarResponse sell(String No) throws DomainException {
        Car car = carRepo.findByNo(No).orElseThrow(() -> new DomainException(ExceptionCode.CAR_NOT_EXIST));
        car.markForSell();
        return carsonConversionService.convert(car, CarResponse.class);
    }

    public CarResponse addRoute(RouteRequest routeRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();

        List<Car> cars = carRepo.findByOwner(name);
        Car car = cars.get(0);

        Route route = carsonConversionService.convert(routeRequest, Route.class);

        car.addRoute(route);
        carRepo.save(car);

        return carsonConversionService.convert(car, CarResponse.class);
    }
}
