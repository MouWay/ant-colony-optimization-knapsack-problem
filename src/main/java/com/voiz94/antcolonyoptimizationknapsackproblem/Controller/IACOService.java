package com.voiz94.antcolonyoptimizationknapsackproblem.Controller;

import com.voiz94.antcolonyoptimizationknapsackproblem.Model.ACORequest;

public interface IACOService {
    ACOResponse solve(ACORequest request);
}
