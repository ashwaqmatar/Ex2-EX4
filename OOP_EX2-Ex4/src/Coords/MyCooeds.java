package Coords;

import Geom.Point3D;

public class MyCooeds implements coords_converter {
	public MyCooeds() {}
	private long earthR = 6371*1000; //Radios of earth in meter
	
	

	/** computes a new point which is the gps point transformed by a 3D vector (in meters)*/
	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		boolean Isgood =isValid_GPS_Point(gps);
		if (!Isgood) {
			System.out.println("Invalid coordinates");
		}
		double R_lat = Math.asin(local_vector_in_meter.x()/earthR);
		double latDifference =Point3D.r2d(R_lat);
		double dest_latvalue=gps.x()+latDifference;
		double lonNorm =Math.cos(Point3D.r2d(gps.x()));
          double raded_lon = Math.asin((local_vector_in_meter.y() / (earthR * lonNorm)));
          double lonDifference = Point3D.r2d(raded_lon);
          double dest_lonvalue = gps.y() + lonDifference;
          double dest_altvalue = local_vector_in_meter.z() + gps.z();
          return new Point3D(dest_latvalue, dest_lonvalue, dest_altvalue);
	}
	 
	/** computes the 3D distance (in meters) between the two gps like points */
	@Override
	public double distance3d(Point3D gps0, Point3D gps1)
 {
		double lonNorm = Math.cos(Point3D.r2d(gps0.x()));;
		double Dis_x = Math.sin((gps1.x()-gps0.x())*(Math.PI/180))*earthR;
		double Dis_y = Math.sin((gps1.y()-gps0.y())*(Math.PI/180))*lonNorm*earthR;
		double Dis_z = gps1.z()-gps0.z();
		double distance = Math.sqrt((Dis_x*Dis_x) + (Dis_y*Dis_y));
		return distance;

	}
	/** computes the 3D vector (in meters) between two gps like points */
	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1) {

	}
	/** computes the polar representation of the 3D vector be gps0-->gps1 
	 * Note: this method should return an azimuth (aka yaw), elevation (pitch), and distance*/
	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {

	}
	/**
	 * return true iff this point is a valid lat, lon , lat coordinate: [-180,+180],[-90,+90],[-450, +inf]
	 * @param p
	 * @return
	 */
	@Override
	public boolean isValid_GPS_Point(Point3D p) {

	}


}
