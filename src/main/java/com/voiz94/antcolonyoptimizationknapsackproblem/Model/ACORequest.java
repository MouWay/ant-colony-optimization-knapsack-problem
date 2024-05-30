package com.voiz94.antcolonyoptimizationknapsackproblem.Model;

import com.voiz94.antcolonyoptimizationknapsackproblem.Util.Model.Item;
import lombok.Data;

import java.util.List;

@Data
public class ACORequest {
    private String name;
    private int maxWeight;
    private List<Item> items;
}