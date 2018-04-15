/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms.FinalProject;

public class City {

    		//Co-ordinates for the City
        private double latitude;
        private double longitude;
        private String name;
        private int id;

        public City(double latitude, double longitude, String name, int id) {
            this.latitude = latitude;
            this.longitude = longitude;
            this.name = name;
            this.id = id;
        }

        public double getLatitude() {
			return latitude;
		}

		public void setLatitude(double latitude) {
			this.latitude = latitude;
		}

		public double getLongitude() {
			return longitude;
		}

		public void setLongitude(double longitude) {
			this.longitude = longitude;
		}

		public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "City{" +
                    "name='" + name + '\'' + '}';
        }
}
