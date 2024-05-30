package com.voiz94.antcolonyoptimizationknapsackproblem.Controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/aco")
public class ACOController {
    private IACOService service;

    @PostMapping("/solve")
    public ACOResponse solve(ACORequest request){

    }
}
