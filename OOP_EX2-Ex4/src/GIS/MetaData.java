package GIS;
import java.util.Date;
import Geom.Point3D;

public class MetaData implements Meta_data {





	public MetaData() {	}
	private String Type;
	Point3D Orientation;
	private String MAC;
	private String SSID;
	private String RSSI;
	private String Channel;
	private String FirstSeen;
	private long data;
	private String color;
	private String AccuracyMeters;
	private String AuthMode;


	public long getdata() {
		return data;
	}

	public void setdata(long data) {
		this.data = data;
	}

	public String getMAC() {
		return MAC;
	}

	public void setMAC(String mAC) {
		MAC = mAC;
	}

	public String getSSID() {
		return SSID;
	}

	public void setSSID(String sSID) {
		SSID = sSID;
	}

	public String getAuthMode() {
		return AuthMode;
	}

	public String getChannel() {
		return Channel;
	}
	public void setAuthMode(String authMode) {
		AuthMode = authMode;
	}



	public void setFirstSeen(String firstSeen) {
		FirstSeen = firstSeen;
	}


	public String getFirstSeen() {
		return FirstSeen;
	}
	public void setRSSI(String rSSI) {
		RSSI = rSSI;
	}
	public String getRSSI() {
		return RSSI;
	}


	public String getType() {
		return Type;
	}

	public void setChannel(String channel) {
		Channel = channel;
	}
	public void setAccuracyMeters(String accuracyMeters) {
		AccuracyMeters = accuracyMeters;
	}
	public String getAccuracyMeters() {
		return AccuracyMeters;
	}

	public void setType(String type) {
		Type = type;
	}




	/**
	 * Constracor, must get all data from CSV file row
	 * @param m
	 * @param s
	 * @param a2
	 * @param f
	 * @param c
	 * @param r
	 * @param a3
	 * @param t
	 */
	public MetaData(String mac,String ssid,String authmode,String firstseen,String channel,String rssi, String accuracymeters, String type)
	{
		MAC=mac;
		SSID=ssid;
		FirstSeen=firstseen;
		AuthMode=authmode;
		AccuracyMeters=accuracymeters;
		Channel=channel;
		RSSI=rssi;
		Type=type;
		data=this.getUTC();

	}
	/**
	 * returns the Universal Time Clock associated with this data;
	 */
	@Override
	public long getUTC() {
		// TODO Auto-generated method stub
		Long data = new Date().getTime();
		return data;	}
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
