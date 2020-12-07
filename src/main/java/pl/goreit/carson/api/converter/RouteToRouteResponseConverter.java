package pl.goreit.carson.api.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.goreit.api.generated.car.RouteResponse;
import pl.goreit.carson.domain.model.Route;

@Component
public class RouteToRouteResponseConverter implements Converter<Route, RouteResponse> {

    @Override
    public RouteResponse convert(Route route) {
        return new RouteResponse(route.getFrom(), route.getTo(), route.getDistance());
    }
}
