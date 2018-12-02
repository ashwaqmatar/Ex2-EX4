package code;

import java.util.Date;

public class WIFI {
	/*Creating WIfi spot with all data for copmare
	 * 
	 * 
	 */
	
	private Date firtseen;
	private String mac,ssid,authmode,type,id;
	private int channel,rssi;
	private double lat,lot,alt,accm;

	public WIFI() {
		super();
	}
	public WIFI(String mac,String ssid,String 
			authmode,Date firtseen,int channel,int rssi,double lat,
			double lot,double alt,double accm,String type) {
		this.firtseen=firtseen;
		this.mac=mac;
		this.ssid=ssid;
		this.authmode=authmode;
		this.type=type;
		this.channel=channel;
		this.rssi=rssi;
		this.lat=lat;
		this.lot=lot;
		this.alt=alt;
		this.accm=accm;
	}
	
	public WIFI(WIFI a) {
		this.firtseen=a.getFirtseen();
		this.mac=a.getMac();
		this.ssid=a.getSsid();
		this.authmode=a.getAuthmode();
		this.type=a.getType();
		this.channel=a.getChannel();
		this.rssi=a.getRssi();
		this.lat=a.getLat();
		this.lot=a.getLot();
		this.alt=a.getAlt();
		this.accm=a.getAccm();
		this.id=a.getid();
	}
	public String getid() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getSsid() {
		return ssid;
	}
	public void setSsid(String ssid) {
		this.ssid = ssid;
	}
	public String getAuthmode() {
		return authmode;
	}
	public void setAuthmode(String authmode) {
		this.authmode = authmode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getFirtseen() {
		return firtseen;
	}
	public void setFirtseen(Date firtseen) {
		this.firtseen = firtseen;
	}
	public int getChannel() {
		return channel;
	}
	public void setChannel(int channel) {
		this.channel = channel;
	}
	public int getRssi() {
		return rssi;
	}
	public void setRssi(int rssi) {
		this.rssi = rssi;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLot() {
		return lot;
	}
	public void setLot(double lot) {
		this.lot = lot;
	}
	public double getAlt() {
		return alt;
	}
	public void setAlt(double alt) {
		this.alt = alt;
	}
	public double getAccm() {
		return accm;
	}
	public void setAccm(double accm) {
		this.accm = accm;
	}
	public String toString() {
		return   "mac:"+mac +"\nssid:"+ ssid +"\nProtection:"+ authmode + "\nDate:"+firtseen + "\nFrequency:"+
				channel + "\nPower:"+ rssi +"\nType:"+type+"\nID:"+id ;
	}
	
}
