/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms.FinalProject;

import java.util.ArrayList;
import java.util.List;


public class Genotype implements Comparable<Genotype>{

        private String[] representation;
        private TravelPath phenotype;


        public Genotype(int memberId) {
            this.phenotype = new TravelPath(memberId);
        }
        public Genotype(int genotypeLength, int memberId) {
            this.representation = new String[genotypeLength];
            this.phenotype = new TravelPath(memberId);
        }

        public TravelPath getPhenotype() {
            return phenotype;
        }

        public void setPhenotype(TravelPath phenotype) {
            this.phenotype = phenotype;
        }

        public String[] getRepresentation() {
            return representation;
        }

        public void setRepresentation(String[] representation) {
            this.representation = representation;
        }

        public void generatePhenotype(Genotype gt, List<City> baseOrder) 
        {

            List<City>newOrder= new ArrayList<City>();
              for(String s:gt.getRepresentation())
              {
                 City e= baseOrder.get(Integer.parseInt(s,2));
                 newOrder.add(e);
              }

               gt.getPhenotype().setTraversalPath(newOrder);
               gt.getPhenotype().fitnessScore();
          }


    
    //compare Genotype Fitness and sort
    public int compareTo(Genotype gt) {
        
        if(gt.getPhenotype().getFitnessScore() < this.phenotype.getFitnessScore())return 1;
        else if(gt.getPhenotype().getFitnessScore() == this.phenotype.getFitnessScore())return 0;
        else return -1;
    }
    
}
