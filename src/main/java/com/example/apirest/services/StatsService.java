package com.example.apirest.services;

import com.example.apirest.dtos.DNAStats;
import com.example.apirest.repositories.DNARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatsService {

    @Autowired
    private DNARepository DNARepository;

    public DNAStats getStats() throws Exception {
        try {
            int mutantQuantity = DNARepository.countByIsMutant(true);
            int humansQuantity = DNARepository.countByIsMutant(false);
            float ratio = humansQuantity > 0 ? (float) mutantQuantity / humansQuantity : 0;

            DNAStats dnaStatsDto = new DNAStats();
            dnaStatsDto.setCountMutantDna(mutantQuantity);
            dnaStatsDto.setCountHumanDna(humansQuantity);
            dnaStatsDto.setRatio(ratio);
            return dnaStatsDto;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
}
