
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hamid
 */
public class GraphColoringProblem implements GeneticProblem {

    private int[][] graphMatrix;
    private int maxColors, graphSize;

    public GraphColoringProblem() {
        Scanner input = null;
        try {
            input = new Scanner(new File("1.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        maxColors = input.nextInt();
        graphSize = input.nextInt();
        graphMatrix = new int[graphSize][graphSize];
        for (int i = 0; i < graphSize; i++) {
            for (int j = 0; j < graphSize; j++) {
                graphMatrix[i][j] = input.nextInt();
            }
        }
    }

    @Override
    public ArrayList<State> initialPopulation(int populationSize) {
        ArrayList<State> result = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < populationSize; i++) {
            GraphState graphState = new GraphState();
            for (int j = 0; j < 4; j++) {
                graphState.colors[j] = rnd.nextInt(maxColors);
            }
            result.add(graphState);
        }
        return result;
    }

    @Override
    public double fitness(State state) {
        int[] colorArray = ((GraphState) state).colors;
        int sameColors = 0;
        for (int i = 0; i < graphSize; i++) {
            for (int j = 0; j < graphSize; j++) {
                if (graphMatrix[i][j] == 1 && colorArray[i] == colorArray[j]) {
                    sameColors++;
                }
            }
        }

        return sameColors / 2;
    }

    @Override
    public State crossover(State s1, State s2) {
        GraphState graphState1 = (GraphState) s1;
        GraphState graphState2 = (GraphState) s2;
        GraphState result = new GraphState();

        Random rnd = new Random();
        int crossoverIndex = rnd.nextInt(graphSize - 1) + 1;

        for (int i = 0; i < crossoverIndex; i++) {
            result.colors[i] = graphState1.colors[i];
        }
        for (int i = crossoverIndex; i < graphSize; i++) {
            result.colors[i] = graphState2.colors[i];
        }

        return result;
    }

    @Override
    public State mutate(State s) {
        GraphState graphState = (GraphState) s;
        GraphState result = new GraphState();
        Random rnd = new Random();
        int mutateIndex = rnd.nextInt(graphSize);

        for (int i = 0; i < graphSize; i++) {
            if (i != mutateIndex) {
                result.colors[i] = graphState.colors[i];
            } else {
                result.colors[i] = rnd.nextInt(maxColors);
            }
        }

        return result;
    }

    class GraphState implements State {

        private int[] colors;

        public GraphState() {
            colors = new int[graphSize];
        }

        @Override
        public boolean isEquals(State s) { // return false
            GraphState graphState = (GraphState) s;
            for (int i = 0; i < colors.length; i++) {
                if (colors[i] != graphState.colors[i]) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public String toString() {
            String s = "";
            for (int i = 0; i < graphSize; i++) {
                s += "Node " + i + " -> color " + colors[i] + "\n";
            }
            return s;
        }
    }
}
