package com.voiz94.antcolonyoptimizationknapsackproblem.Controller;


import com.voiz94.antcolonyoptimizationknapsackproblem.Model.ACORequest;
import com.voiz94.antcolonyoptimizationknapsackproblem.Model.ACOResponse;
import com.voiz94.antcolonyoptimizationknapsackproblem.Service.Interface.IACOService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/aco")
public class ACOController {
    private IACOService service;

    @PostMapping("/solve")
    public ACOResponse solve(@RequestBody ACORequest request){
        return service.solve(request);
    }
}
