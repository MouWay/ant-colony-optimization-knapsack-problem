package com.voiz94.antcolonyoptimizationknapsackproblem.Util;

import com.voiz94.antcolonyoptimizationknapsackproblem.Util.Model.Ant;
import com.voiz94.antcolonyoptimizationknapsackproblem.Util.Model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AntColonyOptimization {
    private static final double ALPHA = 1.0; // коэффициент влияния феромона
    private static final double BETA = 2.0; // коэффициент влияния дистанции
    private static final double EVAPORATION = 0.5;
    private static final double Q = 500.0; // количество феромона, добавляемого каждой муравьиной колонией
    private static final int NUMBER_OF_ANTS = 50;
    private static final int NUMBER_OF_ITERATIONS = 100;

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
                probabilityOfNotTakingItem /= sum;

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
        Ant bestAnt = ants.get(0);
        for (Ant ant : ants) {
            if (ant.getTotalValue() > bestAnt.getTotalValue()) {
                bestAnt = ant;
            }
        }
        return bestAnt;
    }

    public void solve() {
        Ant bestAntEver = null;

        for (int iteration = 0; iteration < NUMBER_OF_ITERATIONS; iteration++) {
            List<Ant> ants = createAnts();
            constructSolutions(ants);
            updatePheromones(ants);
            Ant bestAnt = findBestAnt(ants);

            if (bestAntEver == null || bestAnt.getTotalValue() > bestAntEver.getTotalValue()) {
                bestAntEver = bestAnt;
            }

            System.out.println("Iteration " + iteration + ": Best value = " + bestAntEver.getTotalValue());
        }

        System.out.println("Best solution found has value " + bestAntEver.getTotalValue() + " with weight " + bestAntEver.getTotalWeight());
    }

    public static void main(String[] args) {
        List<Item> items = new ArrayList<>();
        items.add(new Item(10, 60));
        items.add(new Item(20, 100));
        items.add(new Item(30, 120));

        AntColonyOptimization aco = new AntColonyOptimization(items);
        aco.solve();
    }
}
