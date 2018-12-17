package File_format;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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
			writer.write("<kml xmlns=\"http://www.opengis.net/kml/2.2\">"
					+ "<Document><Style id=\"red\"><IconStyle><Icon><href>"
					+ "http://maps.google.com/mapfiles/ms/icons/red-dot.png</href>"
					+ "</Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle>"
					+ "<Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png"
					+ "</href></Icon></IconStyle></Style><Style id=\"green\"><IconStyle>"
					+ "<Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png"
					+ "</href></Icon></IconStyle></Style><Folder><name>Wifi Networks</name>\n");
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
	static void to_KML_Packman(List<String[]> a, String output) {


		ArrayList<String> content = new ArrayList<String>();
		String kmlstart = 
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
						"<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"red\">\r\n" + 
						"<IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle>\r\n" + 
						"</Style><Style id=\"Packman\"><IconStyle><Icon><href>http://www.iconhot.com/icon/png/quiet/256/pac-man.png</href></Icon></IconStyle>\r\n" + 
						"</Style><Style id=\"Fruit\"><IconStyle><Icon><href>http://www.stickpng.com/assets/images/580b57fcd9996e24bc43c316.png</href></Icon></IconStyle></Style>\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"<Folder><name>Wifi Networks</name>\n\n";
		content.add(kmlstart);
		String[] nameData = a.get(1);
		String kmlend = "</Folder>\n" + 
				"</Document>\n</kml>";
		try{
			FileWriter fw = new FileWriter(output);
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i = 2; i < a.size(); i++) {
				String[] s = a.get(i);

				String kmlelement ="<Placemark>\n" +
						"<name><![CDATA["+s[1]+"]]></name>\n" +
						"<description>"+
						"<![CDATA[B"
						+nameData[0]+": <b>"+s[0]+" </b><br/>"
						+nameData[2]+": <b>"+s[2]+" </b><br/>"
						+nameData[3]+": <b>"+s[3]+" </b><br/>" // time and date
						+nameData[4]+": <b>"+s[4]+" </b><br/>"
						+nameData[5]+": <b>"+s[5]+" </b><br/>" // rssi
						+nameData[6]+": <b>"+s[6]+" </b><br/>" // latitauo
						+nameData[7]+": <b>"+s[7]+" </b><br/>" // logntiue
						+nameData[8]+": <b>"+s[8]+" </b><br/>" // altito to meter
						+nameData[9]+": <b>"+s[9]+" </b><br/>" //accaryto meter
						+nameData[10]+": <b>"+s[10]+" </b><br/>" //type wifi

						+"]]></description>\n" +"<styleUrl>#Fruit</styleUrl>"+
						"<Point>\n" +
						"<coordinates>"+s[7]+","+s[6]+"</coordinates>" +
						"</Point>\n" +
						"</Placemark>\n";


				content.add(kmlelement);


			}
			content.add(kmlend);
			bw.write(String.join("\n", content));
			System.out.println("Operation Complete");
			bw.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
