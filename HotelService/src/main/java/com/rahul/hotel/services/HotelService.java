package com.rahul.hotel.services;

import com.rahul.hotel.entities.Hotel;

import java.util.List;

public interface HotelService {
    Hotel create(Hotel hotel);
    Hotel get(String id);
    List<Hotel> getAll();
}
