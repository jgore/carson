package pl.goreit.carson.api.converter;

import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.goreit.api.generated.car.CarResponse;
import pl.goreit.api.generated.car.RouteResponse;
import pl.goreit.carson.domain.model.Car;

import java.util.stream.Collectors;

@Component
public class CarToCarResponseConverter implements Converter<Car, CarResponse> {

    private ConversionService carsonConversionService;

    @Lazy
    public CarToCarResponseConverter(ConversionService carsonConversionService) {
        this.carsonConversionService = carsonConversionService;
    }

    @Override
    public CarResponse convert(Car car) {
        return new CarResponse(car.getNo(), car.getMake(), car.getModel(), car.getYear(), car.getCourse(),
                car.getRouteList().stream()
                        .map(r -> carsonConversionService.convert(r, RouteResponse.class))
                        .collect(Collectors.toList()),
                car.getCarStatus().name()
        );
    }
}
