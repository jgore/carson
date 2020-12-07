package pl.goreit.carson.api.converter;

import com.google.common.collect.Lists;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pl.goreit.api.generated.car.CarRequest;
import pl.goreit.carson.domain.model.Car;

import java.util.Random;

@Component
public class CarRequestToCarConverter implements Converter<CarRequest, Car> {

    @Override
    public Car convert(CarRequest carRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();


        //@FIXME No need to be calculated
        return new Car(name,
                new Random().nextInt(100),
                carRequest.getMake(),
                carRequest.getModel(),
                carRequest.getYear(),
                carRequest.getCourse(),
                Lists.newArrayList());
    }
}
