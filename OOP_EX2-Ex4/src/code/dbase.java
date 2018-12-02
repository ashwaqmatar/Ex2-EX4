package code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class dbase {
	/*
	 * Read and sort Csv file
	 * 
	 * 
	 */
	public static Network readCSV(String folder) throws IOException, NumberFormatException, ParseException {

		File dir = new File(folder);
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".csv");
			}
		};
		Network nt=new Network();
		try {
			for (File file : dir.listFiles(filter)) {
				check(file,nt);
			}
		}
		finally {
			System.out.println("Read complete");
		}
		return nt;
	}
	/*Adding data from wifi hotspot into object
	 * 
	 * 
	 */
	private static WIFI add(String []power) throws NumberFormatException, ParseException
	{
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		WIFI temp= new WIFI(power[0],power[1],power[2],format.parse(power[3]),Integer.parseInt(power[4]),
				Integer.parseInt(power[5]),Double.parseDouble(power[6]),
				Double.parseDouble(power[7]),Double.parseDouble(power[8]),
				Double.parseDouble(power[9]),power[10]);
		return temp;
	}
	/*
	 * Reading csv file and placing data into Wifi class and Network
	 * 
	 * 
	 */
	private static void check(File file,Network nt) throws NumberFormatException, ParseException, IOException
	{
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		String csvFile = file.toString();
		br = new BufferedReader(new FileReader(csvFile));
		line = br.readLine();
		try
		{
			if(line!=null) {
				String[] power=line.split(cvsSplitBy);
				String id="";
				if(power.length==8)
				{
					id=power[2];
					line = br.readLine();
					line = br.readLine();
					power=line.split(cvsSplitBy);
					
					WIFI temp=add(power);
					temp.setId(id);
					nt.add(temp);

				}
				if(power.length>10)
				{	
					while ((line = br.readLine()) != null) {
						power = line.split(cvsSplitBy);
						WIFI temp=add(power);
						temp.setId(id);
						nt.add(temp);
					}
				}
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}
		catch (Exception e) {
			System.out.println(e);
		}
		finally {

			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}		
	}

}