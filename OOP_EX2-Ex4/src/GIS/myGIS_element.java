package GIS;

import Geom.Geom_element;
import Geom.Point3D;

public class myGIS_element implements GIS_element {
	
	MetaData MD ;
	 Point3D gps;
	private String MAC;
	private String SSID_name;
	private String AuthMode;
	private double Lat;
	private double Lon;
	private double AltitudeMeters;
	private int Channel;
	private int RSSI; 
	private int AccuracyMeters;
	private String type;
	
	public myGIS_element(String MAC , String SSID_name,String AuthMode,String Channel,
			String RSSI,String Lat,String Lon , String  AltitudeMeters,String AccuracyMeters, String type) {
		// TODO Auto-generated constructor stub
		
	}

	/**
	 * @return the mD
	 */
	public MetaData getMD() {
		return MD;
	}

	/**
	 * @param mD the mD to set
	 */
	public void setMD(MetaData mD) {
		MD = mD;
	}

	/**
	 * @return the gps
	 */
	public Point3D getGps() {
		return gps;
	}

	/**
	 * @param gps the gps to set
	 */
	public void setGps(Point3D gps) {
		this.gps = gps;
	}

	/**
	 * @return the mAC
	 */
	public String getMAC() {
		return MAC;
	}

	/**
	 * @param mAC the mAC to set
	 */
	public void setMAC(String mAC) {
		MAC = mAC;
	}

	/**
	 * @return the sSID_name
	 */
	public String getSSID_name() {
		return SSID_name;
	}

	/**
	 * @param sSID_name the sSID_name to set
	 */
	public void setSSID_name(String sSID_name) {
		SSID_name = sSID_name;
	}

	/**
	 * @return the authMode
	 */
	public String getAuthMode() {
		return AuthMode;
	}

	/**
	 * @param authMode the authMode to set
	 */
	public void setAuthMode(String authMode) {
		AuthMode = authMode;
	}

	/**
	 * @return the lat
	 */
	public double getLat() {
		return Lat;
	}

	/**
	 * @param lat the lat to set
	 */
	public void setLat(double lat) {
		Lat = lat;
	}

	/**
	 * @return the lon
	 */
	public double getLon() {
		return Lon;
	}

	/**
	 * @param lon the lon to set
	 */
	public void setLon(double lon) {
		Lon = lon;
	}

	/**
	 * @return the altitudeMeters
	 */
	public double getAltitudeMeters() {
		return AltitudeMeters;
	}

	/**
	 * @param altitudeMeters the altitudeMeters to set
	 */
	public void setAltitudeMeters(double altitudeMeters) {
		AltitudeMeters = altitudeMeters;
	}

	/**
	 * @return the channel
	 */
	public int getChannel() {
		return Channel;
	}

	/**
	 * @param channel the channel to set
	 */
	public void setChannel(int channel) {
		Channel = channel;
	}

	/**
	 * @return the rSSI
	 */
	public int getRSSI() {
		return RSSI;
	}

	/**
	 * @param rSSI the rSSI to set
	 */
	public void setRSSI(int rSSI) {
		RSSI = rSSI;
	}

	/**
	 * @return the accuracyMeters
	 */
	public int getAccuracyMeters() {
		return AccuracyMeters;
	}

	/**
	 * @param accuracyMeters the accuracyMeters to set
	 */
	public void setAccuracyMeters(int accuracyMeters) {
		AccuracyMeters = accuracyMeters;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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


}
