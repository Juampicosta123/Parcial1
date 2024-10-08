package com.example.apirest.repositories;


import com.example.apirest.entities.DNA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DNARepository extends JpaRepository<DNA, Long> {
    Optional<DNA> getBySequence(String[] sequence);
}
