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
		
		IllnessVisApp app = new IllnessVisApp(mapWidth,mapHeight);

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
