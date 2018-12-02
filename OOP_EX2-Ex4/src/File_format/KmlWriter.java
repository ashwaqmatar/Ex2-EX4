package File_format;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;



import GIS.GIS_element;
import GIS.GIS_layer;
import GIS.GISproject;
import GIS.myGIS_element;

/**
 * this class gets a GIS Project and write it to a KML file.
 *
 */
public class KmlWriter {

	private final BufferedWriter writer;

	/**
	 * constructor, must run it first in order for the writer to have a destination file.
	 * @param output
	 * @throws IOException
	 */
	public KmlWriter(String output)throws IOException
	{
		writer = new BufferedWriter(new FileWriter(output));
	}

	/**
	 * this function write the Project to KML, it does double iteration, one on the layer, and one on the element
	 * and writes each element at a time to the kml file.
	 * the constructor must run before this!
	 * @param project
	 */
	public void writeStringToKML(GISproject project)
	{
		if(this.writer == null)
		{
			throw new RuntimeException("contractor must be run first!");
		}
		
		Iterator<GIS_layer> iter_layer = project.iterator();
	
		try
		{
			writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			writer.write("<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"blue\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/blue-dot.png</href></Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style><Folder><name>Wifi Networks</name>\n");
			while(iter_layer.hasNext()){
				GIS_layer layer_temp = (GIS_layer)iter_layer.next();
				Iterator<GIS_element> iter_element = layer_temp.iterator();
				while(iter_element.hasNext())
				{
					myGIS_element elm = (myGIS_element)iter_element.next();
					writer.write("<Placemark>\n");
					writer.write("<name>"+"<![CDATA["+elm.getMd().getSSID()+"]]>"  +"</name>\n");
					writer.write("<description>"+"<![CDATA[BSSID: <b>"+elm.getMd().getMAC()+"</b><br/>Capabilities: <b>"+elm.getMd().getAuthMode()+"</b><br/>Timestamp: <b>"+elm.getMd().getdata()+"</b><br/>Channel: <b>"+elm.getMd().getChannel()+"</b><br/>RSSI: <b>"+elm.getMd().getRSSI()+"</b><br/>AltitudeMeters: <b>"+elm.getP().z()+"</b><br/>AccuracyMeters: <b>"+elm.getMd().getAccuracyMeters()+"</b><br/>Type: <b>"+elm.getMd().getType()+"</b><br/>Date: <b>"+elm.getMd().getFirstSeen()+"</b>]]>"+"</description><styleUrl>#blue</styleUrl>\n");
					writer.write("<Point>\n");
					writer.write("<coordinates>"+elm.getP().y()+","+elm.getP().x()+"</coordinates></Point>\n");
					writer.write("</Placemark>\n");

				}
			}
			writer.write("</Folder>\n");
			writer.write("</Document></kml>");
			
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
		finally
		{
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	

}