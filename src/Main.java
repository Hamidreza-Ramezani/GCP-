/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hamid
 */
public class Main {
    public static void main(String[] args) {
        
        GraphColoringProblem graphColoringProblem = new GraphColoringProblem();
        Genetic genetic = new Genetic(20,0.2,0.1);
        genetic.solve(graphColoringProblem,10000);
        System.out.println(genetic.finalState.toString());
        System.out.println("Fitness : " + graphColoringProblem.fitness(genetic.finalState));
        genetic.showSolution(graphColoringProblem, 10000);
        
    }
    
}
