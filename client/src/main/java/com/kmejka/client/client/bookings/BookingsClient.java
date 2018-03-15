package com.kmejka.client.client.bookings;

import feign.hystrix.FallbackFactory;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(name = "${service.bookings-api.service-name}",
             url = "${service.bookings-api.service-address}",
             fallbackFactory = BookingsClientFallbackFactory.class)
public interface BookingsClient {
    @RequestMapping(method = GET, value = "/{id}")
    Booking getById(@PathVariable("id") @NonNull final String id);
}

@Slf4j
@Component
final class BookingsClientFallbackFactory implements FallbackFactory<BookingsClient> {

    @Override
    public BookingsClient create(Throwable cause) {
        return (id) -> {
            log.debug("Fallback for request to get booking by id '{}', triggered because of following reason: '{}'",
                    id, cause.getMessage());
            log.error("Exception occurred while getting all bookings", cause);
            return null;
        };
    }
}
