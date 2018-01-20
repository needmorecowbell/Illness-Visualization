package com.group5.psu.illnessvisualization;
import javax.swing.JFrame;
import java.awt.*;

public class MapCanvas extends Canvas{
	Image map;
	public MapCanvas(Image map) {
		this.map= map;
	}
	public void paint(Graphics g) {
		g.drawImage(map, 0, 0, this);
	}
}
