package com.group5.psu.illnessvisualization;

import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class IllnessVisApp extends JFrame {
	int width, height;
	static ArrayList<Marker>markers= new ArrayList<Marker>();
	
	public IllnessVisApp(int width, int height) {
		this.width=width;
		this.height=height;
		
		String markerQuery= getMarkersQuery("cough","blue","C");// compound different queries by concatenating to this string
		markerQuery+=getMarkersQuery("fever","red","F");
		
		Image mapImage= getMapVisualization(this.width,this.height, markerQuery);
		MapCanvas m = new MapCanvas(mapImage);

		add(m);
		setTitle("Illness Visualization");
		setSize(this.width,this.height);
		setVisible(true);
		setResizable(false);	
	}
	public static String getMarkersQuery(String symptom, String color, String label) {
		String res ="";
		String url = "markers.csv";
		File file= new File(url);
		
		try {
			Scanner inputStream = new Scanner (file);
			
			while(inputStream.hasNext()) {
				String []data= inputStream.nextLine().split(",");
				ArrayList<String>symptoms= new ArrayList<String>();
				
				for(int x = 2;x<data.length;x++) {
					symptoms.add(data[x].replaceAll(" ",""));
				}
				Marker newMarker = new Marker(Float.parseFloat(data[0]),Float.parseFloat(data[1]),symptoms);			
				markers.add(newMarker);	
				System.out.println(newMarker.toString());
				
			}
			
			inputStream.close();
			
		}catch(FileNotFoundException e) {
			e.printStackTrace();
			
		}
		
		for (Marker marker: markers) {
			if(marker.symptoms.contains(symptom)) {
				res = res.concat("&markers=color:"+color+"%7Clabel:"+label+"C%7C"+marker.lat+","+marker.lon);
			}
			

		}
				return res;
	}
	public static Image getMapVisualization(int w, int h, String markerQuery) {
		// TODO Auto-generated method stub
		URL getMap;
		String url= "https://maps.googleapis.com/maps/api/staticmap?"+
					"center=Brooklyn+Bridge,New+York,NY"+
					"&zoom=13&size="+w+"x"+h+
					"&maptype=roadmap"+markerQuery;
		Image map=null;
		try {
			getMap = new URL(url);
			map = ImageIO.read(getMap);
		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
		return map;
	}
}
