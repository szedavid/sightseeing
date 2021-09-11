package com.szedavid.sightseeing.client;

import com.szedavid.sightseeing.client.dto.PocketguideDataDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

// In a real microservice environment I would recommend Eureka for load balancing (then "url" is not required)
@FeignClient(value = "jplaceholder",
        url = "${application.rest.pocketguide}",
        configuration = ClientConfiguration.class)
public interface PocketguideClient {

    @GetMapping(value = "/pocketguide/_test/store_en.v2.gz", produces = "application/json")
    PocketguideDataDTO getTours();
}