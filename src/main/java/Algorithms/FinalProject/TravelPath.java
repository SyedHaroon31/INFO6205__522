/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms.FinalProject;

import java.util.ArrayList;
import java.util.List;


public class TravelPath {

    private int memberId;
    private List<City> travelPath;
    private double distance;
    private double fitnessScore;

    public TravelPath() {
    	
    }
    public TravelPath(int memberId) {
        this.memberId = memberId;
        this.travelPath = new ArrayList<>();
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public TravelPath(List<City> traversalOrder) {
        this.travelPath = traversalOrder;
    }

    public List<City> getTraversalPath() {
        return travelPath;
    }

    public void setTraversalPath(List<City> traversalPath) {
        this.travelPath = traversalPath;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getFitnessScore() {
        return fitnessScore;
    }

    public void setFitnessScore(double fitnessScore) {
        this.fitnessScore = fitnessScore;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("<Order " + this.memberId + " : ");

        for (int i = 0; i < this.travelPath.size(); i++) {
            builder.append(this.travelPath.get(i).toString());
            if (i != this.travelPath.size() - 1) {
                builder.append("->");
            }
        }

        builder.append(">");
        builder.append("< distance : " + this.distance + ">");
        builder.append("< fitness score : " + this.fitnessScore + ">");

        return builder.toString();
    }

    public double fitnessScore() 
    {

        double sum = 0.0;
        for (int i = 0; i <= travelPath.size()-1 ; i++) 
        {
            this.distance = totalDistance(i);
            sum += distance;
        }
        this.fitnessScore = 1 / sum;
        return fitnessScore;
    }

    public double totalDistance(int i) {

        City first = this.travelPath.get(i);
        
        City second = null;
        if (i != this.travelPath.size() - 1) {

            second = this.travelPath.get(i + 1);

        } else {
            second = this.travelPath.get(0);
        }
        double distance = distanceBetweenCities(first, second);
        return distance;
    }

    public double distanceBetweenCities(City first, City second) {
        return Math.sqrt(Math.pow(first.getLatitude() - second.getLatitude(), 2) + Math.pow(first.getLongitude() - second.getLongitude(), 2));
    }

}
