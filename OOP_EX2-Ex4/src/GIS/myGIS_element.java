package GIS;

import Coords.MyCoords;
import Geom.Geom_element;
import Geom.Point3D;

public class myGIS_element implements GIS_element {
	MetaData MD ;
	Point3D gps;

	private String SSID_name;
	private String AuthMode;
	private double Lat;
	private double Lon;
	private double AltitudeMeters;
	private int Channel;
	private int RSSI; 
	private int AccuracyMeters;
	private String type;
	private String MAC;


	/**
	 * this Constractor include all the information foreach element.
	 * all the elements that the constartor get he is String type cuse we read it from csv file.
	 * 
	 * @param MAC
	 * @param SSID
	 * @param AuthMode
	 * @param FirstSeen
	 * @param Channel
	 * @param RSSI
	 * @param Lat
	 * @param Lon
	 * @param AltitudeMeters
	 * @param AccuracyMeters
	 * @param type
	 */
	public myGIS_element(String MAC , String SSID_name,String AuthMode,String Channel,
			String RSSI,String Lat,String Lon , String  AltitudeMeters,String AccuracyMeters, String type) {
		// TODO Auto-generated constructor stub
		setgps(Lat,Lon,AltitudeMeters);
		MD=new MetaData();

		setAuthMode(AuthMode); 
		setRSSI(RSSI); 
		setType(type);; 
		setAccuracyMeters(AccuracyMeters);		
		setChannel(Channel); 
	}

	/// Getters ////

	public String getMAC() {
		return MAC;
	}

	public String getSSID_name() {
		return SSID_name;
	}

	public String getAuthMode() {
		return AuthMode;
	}

	public double getLat() {
		return Lat;
	}

	public double getLon() {
		return Lon;
	}

	public double getAltitudeMeters() {
		return AltitudeMeters;
	}

	public int getChannel() {
		return Channel;
	}

	public int getRSSI() {
		return RSSI;
	}

	public double getAccuracyMeters() {
		return AccuracyMeters;
	}

	public String getType() {
		return type;
	}
	/// end Getters //

	/// Setters ///

	public void setChannel(String Channel) {
		int theChannel = Integer.parseInt(Channel);
		this.Channel = theChannel;
	}
	public void setRSSI(String RSSI) {
		int theRSSI = Integer.parseInt(RSSI);
		this.RSSI = theRSSI;
	}
	public void setAccuracyMeters(String AccuracyMeters) {

		double theAccuracyMeters = Double.parseDouble(AccuracyMeters);
		this.AccuracyMeters =(int) theAccuracyMeters;
	}

	public void setgps(String lat, String lon , String AltitudeMeters) {
		gps = new Point3D(String_2_Point3D(lat,lon,AltitudeMeters));
	}	

	public void setType(String type) {
		this.type = type;
	}

	public void setAuthMode(String AuthMode) {
		this.AuthMode = AuthMode;
	}
	public void setSSID_name(String name) {
		this.SSID_name = name;
	}
	public void setMAC(String MAC) {
		this.MAC = MAC;
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
		MyCoords m = new MyCoords();
		Point3D ans = m.add(this.gps, vec);
		gps = new Point3D(ans.x(),ans.y(),ans.z());

	}
	private Point3D String_2_Point3D(String lat , String lon , String AltitudeMeters) {

		Double x = Double.parseDouble(lat);
		Double y = Double.parseDouble(lon);
		Double z = Double.parseDouble(AltitudeMeters);

		return new Point3D(x,y,z);
	}



}
