import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SelfOptimizationGeneticAlgorithm {
    private static final int POPULATION_SIZE = 1000;
    private static final double MUTATION_RATE = 0.01;
    private static final int TOURNAMENT_SELECTION_SIZE = 5;
    private static final int NUM_GENERATIONS = 1000;

    private List<Solution> population;

    public static void main(String[] args) {
        SelfOptimizationGeneticAlgorithm ga = new SelfOptimizationGeneticAlgorithm();
        ga.runGeneticAlgorithm();
    }

    public void runGeneticAlgorithm() {
        population = initializePopulation(POPULATION_SIZE);

        for (int generation = 1; generation <= NUM_GENERATIONS; generation++) {
            System.out.println("Generation: " + generation);

            population = crossoverPopulation(population);
            population = mutatePopulation(population);

            double averageFitness = calculateAverageFitness(population);
            System.out.println("Average Fitness: " + averageFitness);

            Solution bestSolution = getBestSolution(population);
            System.out.println("Best Solution: " + bestSolution);

            System.out.println("----------------------------------");
        }

        Solution finalBestSolution = getBestSolution(population);
        System.out.println("Final Best Solution: " + finalBestSolution);
    }

    private List<Solution> initializePopulation(int populationSize) {
        List<Solution> population = new ArrayList<>();

        for (int i = 0; i < populationSize; i++) {
            Solution solution = new Solution();
            population.add(solution);
        }

        return population;
    }

    private List<Solution> crossoverPopulation(List<Solution> population) {
        List<Solution> crossoverPopulation = new ArrayList<>();

        for (int i = 0; i < POPULATION_SIZE; i++) {
            Solution parent1 = selectTournamentPopulation(population);
            Solution parent2 = selectTournamentPopulation(population);

            Solution offspring = crossoverSolution(parent1, parent2);
            crossoverPopulation.add(offspring);
        }

        return crossoverPopulation;
    }

    private Solution crossoverSolution(Solution parent1, Solution parent2) {
        Solution offspring = new Solution();

        for (int i = 0; i < parent1.getGenes().size(); i++) {
            if (Math.random() < 0.5) {
                offspring.getGenes().set(i, parent1.getGenes().get(i));
            } else {
                offspring.getGenes().set(i, parent2.getGenes().get(i));
            }
        }

        return offspring;
    }

    private List<Solution> mutatePopulation(List<Solution> population) {
        List<Solution> mutatedPopulation = new ArrayList<>();

        for (Solution solution : population) {
            Solution mutatedSolution = mutateSolution(solution);
            mutatedPopulation.add(mutatedSolution);
        }

        return mutatedPopulation;
    }

    private Solution mutateSolution(Solution solution) {
        Solution mutatedSolution = new Solution();

        for (int i = 0; i < solution.getGenes().size(); i++) {
            if (Math.random() < MUTATION_RATE) {
                mutatedSolution.getGenes().set(i, generateRandomGene());
            } else {
                mutatedSolution.getGenes().set(i, solution.getGenes().get(i));
            }
        }

        return mutatedSolution;
    }

    private Solution selectTournamentPopulation(List<Solution> population) {
        List<Solution> tournamentPopulation = new ArrayList<>();

        for (int i = 0; i < TOURNAMENT_SELECTION_SIZE; i++) {
            Solution randomSolution = population.get(new Random().nextInt(population.size()));
            tournamentPopulation.add(randomSolution);
        }

        return getBestSolution(tournamentPopulation);
    }

    private Solution getBestSolution(List<Solution> population) {
        Solution bestSolution = population.get(0);

        for (int i = 1; i < population.size(); i++) {
            if (population.get(i).getFitness() > bestSolution.getFitness()) {
                bestSolution = population.get(i);
            }
        }

        return bestSolution;
    }

    private double calculateAverageFitness(List<Solution> population) {
        double totalFitness = 0;

        for (Solution solution : population) {
            totalFitness += solution.getFitness();
        }

        return totalFitness / population.size();
    }

    private static int generateRandomGene() {
        // Generate a random gene value according to the problem's constraints
        return new Random().nextInt(100);
    }

    private static class Solution {
        private List<Integer> genes;
        private double fitness;

        public Solution() {
            genes = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                genes.add(generateRandomGene());
            }

            fitness = calculateFitness();
        }

        public List<Integer> getGenes() {
            return genes;
        }

        public double getFitness() {
            return fitness;
        }

        private double calculateFitness() {
            // Calculate the fitness of the solution based on the problem's objective function
            // The higher the fitness, the better the solution
            return genes.stream().mapToInt(Integer::intValue).sum();
        }

        @Override
        public String toString() {
            return "Genes: " + genes + ", Fitness: " + fitness;
        }
    }
}
