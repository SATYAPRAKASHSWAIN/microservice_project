package com.cws.user.service.external.externalService;

import com.cws.user.service.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="HOTEL-SERVICE")
public interface HotelService {
    @GetMapping("/hotel/{hotelId}")
    Hotel getHotel(@PathVariable String hotelId);



}
