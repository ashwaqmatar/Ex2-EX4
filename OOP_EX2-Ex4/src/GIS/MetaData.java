package GIS;

import Geom.Point3D;

public class MetaData implements Meta_data {

	private long data;
	Point3D Orientation;


	public MetaData(long data,Point3D orientation) {
		this.data = data;
		this.Orientation=orientation;

	}
	/**
	 * returns the Universal Time Clock associated with this data;
	 */
	@Override
	public long getUTC() {
		// TODO Auto-generated method stub
		return this.data;
	}
	

	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return null ;
	}
	@Override
	public String toString() {
		 return "" + getUTC();

	}

}
