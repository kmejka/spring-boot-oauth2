package com.kmejka.bookings.client.flights;

import java.util.Collection;

import feign.hystrix.FallbackFactory;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(name = "${service.flights-api.service-name}",
             url = "${service.flights-api.service-address}",
             fallbackFactory = FlightsClientFallbackFactory.class)
public interface FlightsClient {
    @RequestMapping(method = GET, value = "/")
    Collection<Flight> getAll();

    @RequestMapping(method = GET, value = "/{id}")
    Flight getById(@PathVariable("id") @NonNull final String id);
}

@Slf4j
@Component
final class FlightsClientFallbackFactory implements FallbackFactory<FlightsClient> {
    @Override
    public FlightsClient create(Throwable cause) {
        return new FlightsClient() {
            @Override
            public Collection<Flight> getAll() {
                log.debug("Fallback for request to get all flights, triggered because of following reason: '{}'",
                        cause.getMessage());
                log.error("Exception occurred while getting all flights", cause);
                return null;
            }

            @Override
            public Flight getById(final String id) {
                log.debug("Fallback for request to get flight by id: '{}', triggered because of following reason: '{}'",
                        id, cause.getMessage());
                log.error("Exception occurred while getting flight", cause);
                return null;
            }
        };
    }
}
