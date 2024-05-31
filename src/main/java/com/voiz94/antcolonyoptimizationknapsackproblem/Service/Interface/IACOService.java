package com.voiz94.antcolonyoptimizationknapsackproblem.Service.Interface;

import com.voiz94.antcolonyoptimizationknapsackproblem.Model.ACORequest;
import com.voiz94.antcolonyoptimizationknapsackproblem.Model.ACOResponse;

public interface IACOService {
    ACOResponse solve(ACORequest request);
}
