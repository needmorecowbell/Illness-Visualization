package com.group5.psu.illnessvisualization;

import java.util.ArrayList;

public class Marker {
	float lat, lon;
	ArrayList<String>symptoms = new ArrayList<String>();
	
	public Marker(float lat, float lon, ArrayList<String>syms) {
		this.lat=lat;
		this.lon=lon;
		this.symptoms=syms;
	}

	public float getLat() {
		return lat;
	}

	public void setLat(float lat) {
		this.lat = lat;
	}

	public float getLon() {
		return lon;
	}

	public void setLon(float lon) {
		this.lon = lon;
	}

	public ArrayList<String> getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(ArrayList<String> symptoms) {
		this.symptoms = symptoms;
	}
	public String toString() {
		return "Lat: "+this.lat+"\tLon: "+this.lon+"\tSymptoms:\n"+this.symptoms.toString()+"\n";
		}
	
}
