package com.rahul.hotel.services.impl;

import com.rahul.hotel.entities.Hotel;
import com.rahul.hotel.exception.ResourceNotFoundException;
import com.rahul.hotel.repositories.HotelRepository;
import com.rahul.hotel.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;
    @Override
    public Hotel create(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setHotelId(hotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public Hotel get(String id) {
        return hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel with given Id not found!!"));
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }
}
