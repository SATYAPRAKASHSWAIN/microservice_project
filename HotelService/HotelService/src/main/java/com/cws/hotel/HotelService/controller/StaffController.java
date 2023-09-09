package com.cws.hotel.HotelService.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/staffs")
public class StaffController {

    @GetMapping
    public ResponseEntity<List<String>> getStaff(){

        List<String> list = Arrays.asList("Satya", "Prakash", "Swain", "Chiku", "Pranab");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
