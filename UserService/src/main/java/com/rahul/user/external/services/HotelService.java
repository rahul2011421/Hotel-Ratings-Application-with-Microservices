package com.rahul.user.external.services;

import com.rahul.user.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {


    //this method is known as declarative approach(this will get called UserServiceImpl we have done @Autowired and it call this method and spring will provide the implementation automatically
    @GetMapping("/hotels/{hotelId}")
    Hotel getHotel(@PathVariable String hotelId);
}
