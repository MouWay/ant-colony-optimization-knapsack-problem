package com.voiz94.antcolonyoptimizationknapsackproblem.Util.Model;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class Ant {
    private List<Boolean> solution;
    private int totalWeight;
    private int totalValue;

    public Ant(int numberOfItems) {
        solution = new ArrayList<>(Collections.nCopies(numberOfItems, false));
        totalWeight = 0;
        totalValue = 0;
    }

    public void addItem(int index, Item item) {
        solution.set(index, true);
        totalWeight += item.weight;
        totalValue += item.value;
    }
}