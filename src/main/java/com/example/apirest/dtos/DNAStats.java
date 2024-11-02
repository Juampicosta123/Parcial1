package com.example.apirest.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString

public class DNAStats {
    private int countMutantDna;
    private int countHumanDna;
    private float ratio;
}
