package com.example.apirest.controllers;

import com.example.apirest.services.DNAService;
import com.example.apirest.services.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/stats")
public class StatsController {
    @Autowired
    private StatsService statsService;

    @GetMapping("/")
    public ResponseEntity<?> getStats(){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(statsService.getStats().toString());
        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{\"error\":\"Error. Por favor intente más tarde.\"}");

        }
    }
}
