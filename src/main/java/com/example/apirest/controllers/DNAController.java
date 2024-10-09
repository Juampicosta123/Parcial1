package com.example.apirest.controllers;

import com.example.apirest.services.DNAService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/mutant")
public class DNAController {
    @Autowired
    private DNAService dnaService;

    @PostMapping("/")
    public ResponseEntity<?> checkIsMutant(@RequestBody String[] sequence){

        try{
            return ResponseEntity.status(HttpStatus.OK).body(dnaService.checkIsMutant(sequence).toString());
        } catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{\"error\":\"Error. Por favor intente más tarde.\"}");

        }
    }
}
