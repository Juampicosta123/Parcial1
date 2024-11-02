package com.example.apirest.dtos;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString

public class DNARequest {
    private String[] dna;
}
