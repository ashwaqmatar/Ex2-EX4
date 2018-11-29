package Coords;

import Geom.Point3D;

public class MyCooeds implements coords_converter {
	public MyCooeds() {}
	private final  long  earthR = 6371*1000; //Radios of earth in meter

	private final  double  PI= Math.PI;
	public double Lon_Norm (double x ) {

		double Lon_Norm = Math.cos(Point3D.r2d(x));
		return Lon_Norm;}
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
		double raded_lon = Math.asin((local_vector_in_meter.y() / (earthR * Lon_Norm(gps.x()))));
		double lonDifference = Point3D.r2d(raded_lon);
		double dest_lonvalue = gps.y() + lonDifference;
		double dest_altvalue = local_vector_in_meter.z() + gps.z();
		return new Point3D(dest_latvalue, dest_lonvalue, dest_altvalue);
	}


	/** computes the 3D distance (in meters) between the two gps like points */
	@Override
	public double distance3d(Point3D gps0, Point3D gps1)
	{
		double Dis_x = Math.sin((gps1.x()-gps0.x())*(PI/180))*earthR;
		double Dis_y = Math.sin((gps1.y()-gps0.y())*(PI/180))*Lon_Norm(gps0.x())*earthR;
		double Dis_z = gps1.z()-gps0.z();
		double distance = Math.sqrt((Dis_x*Dis_x) + (Dis_y*Dis_y));
		return distance;

	}





	/** computes the 3D vector (in meters) between two gps like points */
	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1) {

		double diff_lat = gps1.x()-gps0.x();
		double diff_lon = gps1.y()-gps0.y();
		double diff_alt = gps1.z()-gps0.z();


		double diff_lat_rad =Math.toRadians(diff_lat);
		double diff_lon_rad =Math.toRadians(diff_lon);

		double lat_meter = Math.sin(diff_lat_rad)*earthR;
		double lon_meter = Math.sin(diff_lon_rad)*earthR*Lon_Norm(gps0.x());

		Point3D point = new Point3D(lat_meter,lon_meter,diff_alt);


		return point;
	}
	/** computes the polar representation of the 3D vector be gps0-->gps1 
	 * Note: this method should return an azimuth (aka yaw), elevation (pitch), and distance*/
	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		double[] ans = new double[3];

		//calculate azimuth
		double x0 = gps0.x()*PI/180;
		double x1 = gps1.x()*PI/180;
		double dY=gps1.y()- gps0.y();
		double delta = (dY*PI)/180;

		double x = Math.sin(delta) * Math.cos(x1);
		double y = Math.cos(x0) * Math.sin(x1) - Math.sin(x0)*Math.cos(x1)*Math.cos(delta);
		double azimuth = Math.atan2(x,y);

		if(Math.toDegrees(azimuth)<0)
			azimuth = 360+Math.toDegrees(azimuth);
		else
			azimuth = Math.toDegrees(azimuth);

		//calculate distance
		double distance =distance3d(gps0, gps1);


		//calculate elevation
		double elevation= Math.toDegrees(Math.acos( (gps1.z() - gps0.z())/distance));

		ans[0]=azimuth;ans[1]=elevation;ans[2]=distance;

		return ans;
	}
	/**
	 * return true iff this point is a valid lat, lon , lat coordinate: [-180,+180],[-90,+90],[-450, +inf]
	 * @param p
	 * @return
	 */
	@Override
	public boolean isValid_GPS_Point(Point3D p) {
		boolean   isValid = true;
		if (p.x()<-180 && p.x() >180 && p.y() <-90 && p.y() >90 && p.z() <-450) {
			return isValid;
		}

		return isValid=false  ;

	}


}
