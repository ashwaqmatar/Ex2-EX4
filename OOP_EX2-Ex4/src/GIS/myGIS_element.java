package GIS;

import Geom.Geom_element;
import Geom.Point3D;

public class myGIS_element implements GIS_element {
	
	private MetaData MD ;
	private Point3D gps;
	
	
	public myGIS_element() {
		// TODO Auto-generated constructor stub
		MD=new MetaData();
		setGps(new Point3D(0,0,0));
	}
	public myGIS_element(String name, String date, String mac, String authMode,
			double lon, double lat, double alt) {
		MD=new MetaData(name ,date,mac,authMode);
		this.gps=new Point3D(lat,lon,alt);
	}
	public myGIS_element(String name, String date, String mac, String authMode,Point3D cd) {
		this.MD=new MetaData(name, date, mac, authMode);
		this.gps=new Point3D(cd);
	}
	public myGIS_element(MetaData MD ,double lon , double lat,double alt) {
	this.MD=new MetaData(MD);
	this.gps=new Point3D(lat,lon,alt);
	}

	@Override
	public Geom_element getGeom() {
		// TODO Auto-generated method stub
		return this.gps;
	}

	@Override
	public Meta_data getData() {
		// TODO Auto-generated method stub
		return this.MD;
	}

	@Override
	public void translate(Point3D vec) {
		// TODO Auto-generated method stub

	}
	public Point3D getGps() {
		return gps;
	}
	public void setGps(Point3D gps) {
		this.gps = gps;
	}

}
