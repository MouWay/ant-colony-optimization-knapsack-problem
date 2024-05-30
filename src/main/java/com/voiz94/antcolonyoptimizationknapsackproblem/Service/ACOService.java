package com.voiz94.antcolonyoptimizationknapsackproblem.Service;

import com.voiz94.antcolonyoptimizationknapsackproblem.Controller.IACOService;
import com.voiz94.antcolonyoptimizationknapsackproblem.Model.ACORequest;
import com.voiz94.antcolonyoptimizationknapsackproblem.Util.AntColonyOptimization;

public class ACOService implements IACOService {
    @Override
    public ACOResponse solve(ACORequest request) {
        var aco = new AntColonyOptimization(request.getMaxWeight(), request.getItems());
        aco.solve();
    }
}
