package com.voiz94.antcolonyoptimizationknapsackproblem.Service;

import com.voiz94.antcolonyoptimizationknapsackproblem.Model.ACOLog;
import com.voiz94.antcolonyoptimizationknapsackproblem.Model.ACOResponse;
import com.voiz94.antcolonyoptimizationknapsackproblem.Repository.Interface.IACORepository;
import com.voiz94.antcolonyoptimizationknapsackproblem.Service.Interface.IACOService;
import com.voiz94.antcolonyoptimizationknapsackproblem.Model.ACORequest;
import com.voiz94.antcolonyoptimizationknapsackproblem.Util.AntColonyOptimization;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ACOService implements IACOService {
    private IACORepository repository;

    @Override
    public ACOResponse solve(ACORequest request) {
        var aco = new AntColonyOptimization(request.getMaxWeight(), request.getItems());
        var result = aco.solve();
        saveResult(result);
        return result;
    }

    private void saveResult(ACOResponse response){
        var result = new ACOLog();
        result.setTotalValue(response.getTotalValue());
        result.setTotalWeight(response.getTotalWeight());
        result.setAntsAmount(AntColonyOptimization.NUMBER_OF_ANTS);
        result.setIterations(AntColonyOptimization.NUMBER_OF_ITERATIONS);
        repository.save(result);
    }
}
