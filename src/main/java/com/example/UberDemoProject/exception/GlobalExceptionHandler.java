package com.example.UberDemoProject.exception;

import com.example.UberDemoProject.dto.PaymentResponse;
import com.example.UberDemoProject.dto.TaxiBookingResponse;
import com.example.UberDemoProject.exception.exceptions.BookingIdNotFoundException;
import com.example.UberDemoProject.exception.exceptions.TaxiNotFoundException;
import com.example.UberDemoProject.exception.exceptions.TaxiUnavailableException;
import com.example.UberDemoProject.exception.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaxiNotFoundException.class)
    public ResponseEntity<TaxiBookingResponse> handleTaxiNotFound(TaxiNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body( new TaxiBookingResponse(null,"NOT_FOUND", ex.getMessage()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<TaxiBookingResponse> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new TaxiBookingResponse(null,"NOT_FOUND", ex.getMessage()));
    }

    @ExceptionHandler(BookingIdNotFoundException.class)
    public ResponseEntity<PaymentResponse> handleBookingNotFound( BookingIdNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new PaymentResponse(null,"Failure", "FAILED", ex.getMessage()));
    }

    @ExceptionHandler(TaxiUnavailableException.class)
    public ResponseEntity<TaxiBookingResponse> handleTaxiUnavailable(TaxiUnavailableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new TaxiBookingResponse(null,"BAD_REQUEST", ex.getMessage()));
    }
}
