package com.group5.psu.illnessvisualization;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Driver {

	public static void main(String[] args) {
		int mapWidth = 600;
		int mapHeight= 300;
		
		//String ip = "162.242.48.142";
		String ip = getExternalIP();
		
		String location = getLatLonByIP(ip);
		// TODO Auto-generated method stub
		System.out.println("IP: "+ip+"\nlocation: "+location);
		
		Image map = getMapVisualization(mapWidth,mapHeight);
	
		
		MapCanvas m = new MapCanvas(map);
		JFrame f = new JFrame();
		f.add(m);
		f.setTitle("Illness Visualization");
		f.setSize(mapWidth,mapHeight);
		f.setVisible(true);
		
	}
	
	
	
	public static Image getMapVisualization(int w, int h) {
		// TODO Auto-generated method stub
		URL getMap;
		String url= "https://maps.googleapis.com/maps/api/staticmap?"+
					"center=Brooklyn+Bridge,New+York,NY"+
					"&zoom=13&size="+w+"x"+h+
					"&maptype=roadmap"+
					"&markers=color:blue%7Clabel:C%7C40.702147,-74.015794"+
					"&markers=color:green%7Clabel:G%7C40.711614,-74.012318"+
					"&markers=color:red%7Clabel:F%7C40.718217,-73.998284"+
					"&key=AIzaSyBAZVe92yfAucFt2DiqqvsiTEJaBfVYcX4";
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
	
	
	public static String getExternalIP() {
		// TODO Auto-generated method stub
		URL findIP;
		String ip = null;
		try {
			findIP = new URL("http://checkip.amazonaws.com");
			BufferedReader in = new BufferedReader(new InputStreamReader(
	                findIP.openStream()));

			ip = in.readLine(); //you get the IP as a String	
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ip;
	}

	public static String getLatLonByIP(String ip) {
		String address = "http://freegeoip.net/json/"+ip;
		String loc[] = null;
		
		
		try {
			
			URL url = new URL(address);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			
			
			while((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			
			in.close();
			con.disconnect();
			loc = content.toString().split(",");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return loc[8]+","+loc[9];		
	}

}
