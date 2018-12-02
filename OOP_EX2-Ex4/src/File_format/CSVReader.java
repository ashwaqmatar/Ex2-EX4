package File_format;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import GIS.GIS_layer;
import GIS.myGIS_element;

/**
 * this function read for a single CSV file, and build a layer from it, and returns it so it can be added to a project.
 *
 */
public class CSVReader {
	
	private BufferedReader reader=null;

	/**
	 * constructor, must be run first in order to have a file to open.
	 * @param input
	 */
	public CSVReader(String input)
	{
		try{
		reader = new BufferedReader(new FileReader(input));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * this class read from the CSV file, and enters each row as an element and add the new element into a layer.
	 * this layer will finally be sent to a project.
	 * @return
	 * @throws IOException
	 */
	public ArrayList<myGIS_element> readFromCSV() throws IOException {
		try {
			reader.readLine(); // skip 1st line
			reader.readLine(); // skip 2nd line
			String str1; 
			ArrayList<myGIS_element> arr = new ArrayList<>();
			myGIS_element gis;
			while ((str1 = reader.readLine()) != null)
			{
				gis = new myGIS_element(str1);
				arr.add(gis);
			}
			return arr;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static GIS_layer CSVReader(String absolutePath) {
		// TODO Auto-generated method stub
		return null;
	}
}