package com.cws.hotel.HotelService.repository;

import com.cws.hotel.HotelService.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepostiory extends JpaRepository<Hotel,String> {

}
