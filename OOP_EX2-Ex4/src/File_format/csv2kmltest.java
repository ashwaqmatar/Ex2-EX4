package File_format;

import java.util.Arrays;

public class csv2kmltest {

	public static void main(String[] args) {
		String source_file ="C:\\Users\\user\\Desktop\\data\\CSV456.csv";
		String output_file ="C:\\Users\\user\\Desktop\\data\\CSV123.kml";
		try{
		Csv2Kml c2k = new Csv2Kml(source_file,output_file);
		c2k.run();
		String[] headres = c2k.getHeaders();
		System.out.println("headers are:"+Arrays.deepToString(headres));
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
