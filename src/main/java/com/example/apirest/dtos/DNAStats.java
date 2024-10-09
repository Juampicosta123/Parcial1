package com.example.apirest.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString

public class DNAStats {
    private int count_mutant_dna;
    private int count_human_dna;
    private float ratio;
}
