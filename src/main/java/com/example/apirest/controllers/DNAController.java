package com.example.apirest.controllers;

import com.example.apirest.dtos.DNARequest;
import com.example.apirest.dtos.DNAShort;
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
    public ResponseEntity<?> checkIsMutant(@RequestBody DNARequest request) {
        try {
            DNAShort dnaShort = dnaService.checkIsMutant(request.getDna().toArray(new String[0]));
            return ResponseEntity.status(HttpStatus.OK).body(dnaShort.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}
