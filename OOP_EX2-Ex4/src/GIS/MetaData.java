package GIS;

import Geom.Point3D;

public class MetaData implements Meta_data {

	private long data;
	Point3D Orientation;
	private String color;

public MetaData() {	}
	public MetaData(long data,Point3D orientation) {
		this.data = data;
		this.Orientation=orientation;
        this.color=color;
	}
	
	/**
	 * returns the Universal Time Clock associated with this data;
	 */
	@Override
	public long getUTC() {
		// TODO Auto-generated method stub
		return this.data;
	}
    /**
     * @return the orientation: yaw, pitch and roll associated with this data;
     */
	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return Orientation ;
	}
	public String getColor() {
		return this.color;
	}
	
	public void setColor(String Color) {
		this.color = Color;
	}
	/**
     * return a String representing this data
     */
	@Override
	public String toString() {
		return ""+data+ "," + Orientation + "," + color+"";

	}

}
