package com.voiz94.antcolonyoptimizationknapsackproblem.Util;

import com.voiz94.antcolonyoptimizationknapsackproblem.Model.ACOResponse;
import com.voiz94.antcolonyoptimizationknapsackproblem.Util.Model.Ant;
import com.voiz94.antcolonyoptimizationknapsackproblem.Util.Model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AntColonyOptimization {
    private static final double ALPHA = 1.0; // коэффициент влияния феромона
    private static final double BETA = 3.0; // коэффициент влияния стоимости
    private static final double EVAPORATION = 0.5;
    private static final double Q = 500.0; // количество феромона, добавляемого каждой муравьиной колонией
    public static final int NUMBER_OF_ANTS = 5000;
    public static final int NUMBER_OF_ITERATIONS = 300;

    private final int maxWeight;
    private final List<Item> items;
    private final double[][] pheromoneLevels;
    private final Random random;

    public AntColonyOptimization(int maxWeight, List<Item> items) {
        this.maxWeight = maxWeight;
        this.items = items;
        this.pheromoneLevels = new double[items.size()][2];
        for (int i = 0; i < items.size(); i++) {
            pheromoneLevels[i][0] = 1.0;
            pheromoneLevels[i][1] = 1.0;
        }
        this.random = new Random();
    }

    private void updatePheromones(List<Ant> ants) {
        for (int i = 0; i < items.size(); i++) {
            pheromoneLevels[i][0] *= EVAPORATION;
            pheromoneLevels[i][1] *= EVAPORATION;
        }
        for (Ant ant : ants) {
            for (int i = 0; i < ant.getSolution().size(); i++) {
                if (ant.getSolution().get(i)) {
                    pheromoneLevels[i][1] += Q / ant.getTotalValue();
                } else {
                    pheromoneLevels[i][0] += Q / ant.getTotalValue();
                }
            }
        }
    }

    private List<Ant> createAnts() {
        List<Ant> ants = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_ANTS; i++) {
            ants.add(new Ant(items.size()));
        }
        return ants;
    }

    private void constructSolutions(List<Ant> ants) {
        for (Ant ant : ants) {
            for (int i = 0; i < items.size(); i++) {
                double probabilityOfTakingItem = calculateProbability(i, true);
                double probabilityOfNotTakingItem = calculateProbability(i, false);
                double sum = probabilityOfTakingItem + probabilityOfNotTakingItem;
                probabilityOfTakingItem /= sum;

                if (ant.getTotalWeight() + items.get(i).getWeight() <= maxWeight && random.nextDouble() < probabilityOfTakingItem) {
                    ant.addItem(i, items.get(i));
                }
            }
        }
    }

    private double calculateProbability(int itemIndex, boolean takeItem) {
        int index = takeItem ? 1 : 0;
        return Math.pow(pheromoneLevels[itemIndex][index], ALPHA) * Math.pow(items.get(itemIndex).getValue(), BETA);
    }

    private Ant findBestAnt(List<Ant> ants) {
        Ant bestAnt = ants.getFirst();
        for (Ant ant : ants) {
            if (ant.getTotalValue() > bestAnt.getTotalValue()) {
                bestAnt = ant;
            }
        }
        return bestAnt;
    }

    public ACOResponse solve() {
        Ant bestAntEver = null;

        for (int iteration = 0; iteration < NUMBER_OF_ITERATIONS; iteration++) {
            List<Ant> ants = createAnts();
            constructSolutions(ants);
            updatePheromones(ants);
            Ant bestAnt = findBestAnt(ants);

            if (bestAntEver == null || bestAnt.getTotalValue() > bestAntEver.getTotalValue()) {
                bestAntEver = bestAnt;
            }
        }

        return new ACOResponse(bestAntEver.getTotalValue(), bestAntEver.getTotalWeight());
    }
}
