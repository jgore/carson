package pl.goreit.carson.api.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import pl.goreit.api.generated.car.RouteRequest;
import pl.goreit.carson.domain.model.Route;

@Component
public class RouteRequestToRouteConverter implements Converter<RouteRequest, Route> {

    @Override
    public Route convert(RouteRequest routeRequest) {
        return new Route(routeRequest.getFrom(), routeRequest.getTo(), routeRequest.getDistance());
    }
}
