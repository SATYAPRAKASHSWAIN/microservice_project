package com.cws.hotel.HotelService.services.impl;

import com.cws.hotel.HotelService.entities.Hotel;
import com.cws.hotel.HotelService.exception.ResourceNotFoundException;
import com.cws.hotel.HotelService.repository.HotelRepostiory;
import com.cws.hotel.HotelService.services.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepostiory hotelRepostiory;
    @Override
    public Hotel create(Hotel hotel) {
        String hotelid = UUID.randomUUID().toString();
        hotel.setId(hotelid);
        return hotelRepostiory.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepostiory.findAll();
    }

    @Override
    public Hotel get(String id) {
        return hotelRepostiory.findById(id).orElseThrow(

                ()-> new ResourceNotFoundException("hotel with given id not found")
        );
    }
}
