package com.voiz94.antcolonyoptimizationknapsackproblem.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class ACOLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int antsAmount;
    private int iterations;
    private int totalWeight;
    private int totalValue;
}
