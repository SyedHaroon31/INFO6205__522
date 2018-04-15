package Algorithms.FinalProject;



import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Driver {

        public static void main(String[] args) {
            int phenoTypeLength = 8;
            int populationSize = 500;
            int genoTypeLength = 32;


            List<City> baseOrder = getBaseOrder(phenoTypeLength);
            Population population = new Population(0.1, baseOrder);
            population.initPopulation(populationSize,genoTypeLength,phenoTypeLength);
            population.sortPopulation();
            System.out.println("Generation: 0"  + " fitness score :" + population.getGtList().get(0).getPhenotype().toString());
            IntStream.range(1,20).forEach(generationNo -> {
                        population.regenerationAndCulling();
                        population.sortPopulation();
                        System.out.println("Generation: " +generationNo  + " fitness score :" + population.getGtList().get(0).getPhenotype().toString());
                    });

        }

        public static List<City> getBaseOrder(int phenoTypeLength) 
        {
  
        		double min = -100;
            double max = 100;
            return IntStream.range(0, phenoTypeLength)
                    .mapToObj(index -> {
                        double a = ThreadLocalRandom.current().nextDouble(min, max + 1);
                        double b = ThreadLocalRandom.current().nextDouble(min, max + 1);

                        City city = new City(a, b, "City"+index , index);
                        return city;
                    }).collect(Collectors.toList());

        }

}
