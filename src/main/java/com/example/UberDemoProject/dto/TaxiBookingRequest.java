package com.example.UberDemoProject.dto;

import com.example.UberDemoProject.enums.TaxiType;
import lombok.Data;

import java.time.LocalDateTime;

public record  TaxiBookingRequest(Long userId,Long taxiId,TaxiType taxiType, LocalDateTime bookingTime,double distance){

}
