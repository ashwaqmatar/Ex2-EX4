package File_format;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import GIS.GIS_project;
import GIS.GISproject;

/**
 * this function gets to paths, one for in and one for out, and convert one CSV file to one kml file
 *
 */
public class Csv2Kml {
	
	private final BufferedWriter writer;
	private final BufferedReader reader;
	private String headers[]=null;

	/**
	 * constructor, firs must get the CSV path and the second String is the destenation Path.
	 * @param input
	 * @param output
	 * @throws IOException
	 */
	public Csv2Kml(String input, String output)throws IOException
	{
		reader = new BufferedReader(new FileReader(input));
		writer = new BufferedWriter(new FileWriter(output));
	}
	
	/**
	 * returns the "headres" row of the CSV file.
	 * @return
	 */
	public String[] getHeaders(){
		if(headers==null)
		{
			throw new RuntimeException("must excute \"run\" first!!");
		}
		return headers;
	}
  
	/**
	 * this function activated the creation of the kml file from the csv file
	 * @throws IOException
	 */
	public void run() throws IOException {
		try {
			reader.readLine(); // skip 1st line
			String str = reader.readLine();
			String[] headers = str.split(",");
			this.headers=headers;
			writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			writer.write("<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style><Folder><name>Wifi Networks</name>\n");
			while ((str = reader.readLine()) != null)
			{
				String[] current_line = str.split(",");
				String MAC = current_line[0];
				String ssid = current_line[1];
				String AuthMode = current_line[2];
				String FirstSeen = current_line[3];
				String Channel = current_line[4];
				String RSSI = current_line[5];
				String CurrentLatitude = current_line[6];
				String CurrentLongitude = current_line[7];
				String AltitudeMeters = current_line[8];
				String AccuracyMeters = current_line[9];
				String Type = current_line[10];
				writer.write("<Placemark>\n");
				writer.write("<name>"+"<![CDATA["+ssid+"]]>"  +"</name>\n");
				writer.write("<description>"+"<![CDATA[BSSID: <b>"+MAC+"</b><br/>Capabilities: <b>"+AuthMode+"</b><br/>Channel: <b>"+Channel+"</b><br/>RSSI: <b>"+RSSI+"</b><br/>AltitudeMeters: <b>"+AltitudeMeters+"</b><br/>AccuracyMeters: <b>"+AccuracyMeters+"</b><br/>Type: <b>"+Type+"</b><br/>Date: <b>"+FirstSeen+"</b>]]>"+"</description><styleUrl>#red</styleUrl>\n");
				writer.write("<Point>\n");
				writer.write("<coordinates>"+CurrentLongitude+","+CurrentLatitude+"</coordinates></Point>\n");
				writer.write("</Placemark>\n");
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
			if (reader != null)
			{
				try
				{
					reader.close();
				} finally
				{
					if (writer != null)
					{
						writer.close();
					}
				}
			} else
			{
				if (writer != null)
				{
					writer.close();
				}
			}
		}
	}
}
