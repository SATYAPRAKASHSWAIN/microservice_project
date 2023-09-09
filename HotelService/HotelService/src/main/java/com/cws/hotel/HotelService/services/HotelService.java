package com.cws.hotel.HotelService.services;

import com.cws.hotel.HotelService.entities.Hotel;

import java.util.List;

public interface HotelService {


    //create
    Hotel create(Hotel hotel);
    //getAll
    List<Hotel> getAll();

    //get single
    Hotel get(String id);
}
