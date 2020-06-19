package module6;

import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import processing.core.PConstants;
import processing.core.PGraphics;

/** Implements a visual marker for cities on an earthquake map
 * 
 * @author UC San Diego Intermediate Software Development MOOC team
 * 
 */
public class CityMarker extends CommonMarker {
	
	public static int TRI_SIZE = 5;  // The size of the triangle marker
	private static int NUM_EARTHQUAKES = 0;
	private static float MEAN_MAG = 0.0f;
	private static EarthquakeMarker latest = null;
	public CityMarker(Location location) {
		super(location);
	}
	
	
	public CityMarker(Feature city) {
		super(((PointFeature)city).getLocation(), city.getProperties());
		// Cities have properties: "name" (city name), "country" (country name)
		// and "population" (population, in millions)
	}
	
	
	// pg is the graphics object on which you call the graphics
	// methods.  e.g. pg.fill(255, 0, 0) will set the color to red
	// x and y are the center of the object to draw. 
	// They will be used to calculate the coordinates to pass
	// into any shape drawing methods.  
	// e.g. pg.rect(x, y, 10, 10) will draw a 10x10 square
	// whose upper left corner is at position x, y
	/**
	 * Implementation of method to draw marker on the map.
	 */
	public void drawMarker(PGraphics pg, float x, float y) {
		//System.out.println("Drawing a city");
		// Save previous drawing style
		pg.pushStyle();
		
		// IMPLEMENT: drawing triangle for each city
		pg.fill(150, 30, 30);
		pg.triangle(x, y-TRI_SIZE, x-TRI_SIZE, y+TRI_SIZE, x+TRI_SIZE, y+TRI_SIZE);
		
		// Restore previous drawing style
		pg.popStyle();
	}
	
	/** Show the title of the city if this marker is selected */
	public void showTitle(PGraphics pg, float x, float y)
	{
		String name = getCity() + " " + getCountry() + " ";
		String pop = "Pop: " + getPopulation() + " Million";
		
		pg.pushStyle();
		
		pg.fill(255, 255, 255);
		pg.textSize(12);
		pg.rectMode(PConstants.CORNER);
		pg.rect(x, y-TRI_SIZE-39, Math.max(pg.textWidth(name), pg.textWidth(pop)) + 6, 39);
		pg.fill(0, 0, 0);
		pg.textAlign(PConstants.LEFT, PConstants.TOP);
		pg.text(name, x+3, y-TRI_SIZE-33);
		pg.text(pop, x+3, y - TRI_SIZE -18);
		
		pg.popStyle();
	}
	
	@Override
	public void draw(PGraphics pg, float x, float y) {
		// For starter code just drawMaker(...)
		if (!hidden) {
			drawMarker(pg, x, y);
			if (selected) {
				showTitle(pg, x, y);
			}
		}
		if (clicked) {
			showEarthquake(pg);
		}
	}
	private void showEarthquake(PGraphics pg) {
		pg.fill(255, 250, 240);
		
		int xbase = 0;
		int ybase = 450;
		
		pg.rect(xbase, ybase, 150, 150);
		
		pg.fill(0);
		pg.textAlign(pg.LEFT, pg.CENTER);
		pg.textSize(10);
		pg.text("Selected city:" + getCity(), xbase, ybase+25);
		pg.text("Num. of nearby quakes:" + Integer.toString(NUM_EARTHQUAKES), xbase, ybase+50);
		
		if (NUM_EARTHQUAKES>0) {
			
			pg.text("Avg mag of nearby quakes:" , xbase, ybase+75);
			pg.text(Float.toString(MEAN_MAG), xbase, ybase+88);
		}
		
		pg.text("Latest nearby quakes:", xbase, ybase+100);
		pg.text(latest.getTitle(), xbase, ybase+113);
		
		return;
	}
	
	public void setNumQuake(int x) {
		NUM_EARTHQUAKES = x;
	}
	public void setMag(float x) {
		MEAN_MAG = x;
	}
	public void setQuake(EarthquakeMarker m) {
		latest = m;
	}
	private String getCity()
	{
		return getStringProperty("name");
	}
	
	private String getCountry()
	{
		return getStringProperty("country");
	}
	
	private float getPopulation()
	{
		return Float.parseFloat(getStringProperty("population"));
	}
}
