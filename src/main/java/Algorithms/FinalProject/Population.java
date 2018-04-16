/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms.FinalProject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;



public class Population {

    private List<Genotype> gtList;
    private double cutoff;
    private List<City> firstOrder;

    public Comparator<Genotype> genoTypeComparator
            = new Comparator<Genotype>() {

        public int compare(Genotype gA, Genotype gB) {

            double fitnessA = gA.getPhenotype().getFitnessScore();
            double fitnessB = gB.getPhenotype().getFitnessScore();
            return gB.compareTo(gA);
        }};

    public Population(double cutoff,List<City> baseOrder) 
    {
        this.gtList = new ArrayList<>();
        this.cutoff = cutoff;
        this.firstOrder = baseOrder;

    }

    public List<Genotype> getGtList() {
        return gtList;
    }

    public void setGtList(List<Genotype> gtList) {
        this.gtList = gtList;
    }

    public void initPopulation(int populationSize, int genotypeLength,int phenoTypeLength) {

        for (int i = 0; i < populationSize; i++) 
        {
            Genotype g = new Genotype(phenoTypeLength, i);
            this.gtList.add(g);
        }

        String[] geneBaseOrder = new String[phenoTypeLength];

        for (int i = 0; i < firstOrder.size(); i++)
        {

            String p = String.format("%4s", Integer.toBinaryString(i)).replace(' ', '0');
            geneBaseOrder[i] = p;
        }

        for (int i = 0; i < gtList.size(); i++)
        {
            Genotype gt = gtList.get(i);
            String[] arr = new String[geneBaseOrder.length];
            arr = Arrays.copyOf(geneBaseOrder, geneBaseOrder.length);
            Collections.shuffle(Arrays.asList(arr));

            gt.setRepresentation(arr);
            gt.generatePhenotype(gt, firstOrder);

        }

    }

    public void sortPopulation() {
        Collections.sort(this.gtList, this.genoTypeComparator);
    }

    public void regenerationAndCulling() {
        Random r = new Random();

        int upperbound = (int) ((1 - this.cutoff) * this.gtList.size());
        List<Genotype> newGeneration = new ArrayList<Genotype>();
        for (int i = 0; i < this.gtList.size(); i++)
        {
            int firstParent = getRandomParentIndex(upperbound, r);

            int secondParent = getRandomParentIndex(upperbound, r);

            while (firstParent == secondParent) 
            {
                secondParent = getRandomParentIndex(upperbound, r);
            }
            Genotype child = crossover(firstParent, secondParent, i);
            newGeneration.add(child);
        }

        this.gtList = newGeneration;

    }
    

    private Genotype crossover(int firstParent, int secondParent, int newMemberId) {
        Genotype genoFirst = this.gtList.get(firstParent);
        Genotype genoSecond = this.gtList.get(secondParent);
        int i = 0;
        String[] childRepresentation = new String[genoFirst.getRepresentation().length];
        String[] tempRepresentation = new String[childRepresentation.length / 2];
        for (i = 0; i < childRepresentation.length / 2; i++) {

            if (i < childRepresentation.length / 2) {

                childRepresentation[i] = genoFirst.getRepresentation()[i];
                tempRepresentation[i] = genoFirst.getRepresentation()[i];
            } else {

            }

        }
        int temp = 0;

        for (int j = 0; j < genoSecond.getRepresentation().length; j++) {
            temp = 0;
            for (int k = 0; k < tempRepresentation.length; k++) {
                if (genoSecond.getRepresentation()[j].equals(tempRepresentation[k])) {
                    temp = 1;
                    break;
                }

            }
            if (temp == 0) {
                childRepresentation[i] = genoSecond.getRepresentation()[j];
                i++;
            }
        }


        Genotype child = new Genotype(newMemberId);
        child.setRepresentation(childRepresentation);
        child.generatePhenotype(child, firstOrder);
        return child;
    }

    private int getRandomParentIndex(int upperbound, Random r) {

        int randInt = r.nextInt((upperbound));
        return randInt;
    }
}
