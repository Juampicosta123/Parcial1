package com.example.apirest.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString

public class DNAShort {
    private Boolean isMutant;
    private String description;
}
