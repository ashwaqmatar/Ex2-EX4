package code;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;

public class test {

	public static void main(String[] args) throws NumberFormatException, IOException, ParseException, ParserConfigurationException {
		// TODO Auto-generated method stub
		Network nt=dbase.readCSV("C:\\Users\\user\\Desktop\\Ex2\\data");
		Network nts;
		nts=CSVFilter.FilterId("model=ONEPLUS A3003",nt);
		nt.toCSV("C:\\Users\\user\\Desktop\\Ex2\\data");
		nt.writeFileKML("C:\\Users\\user\\Desktop\\Ex2\\data");
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1=format.parse("2018-12-02 16:14:00");
		Date d2=format.parse("2018-12-07 16:18:00");
		nts=CSVFilter.FilterByDate(d1,d2, nt);//(("C:/gmon/c.kml"), nt);
		nt.writeFileKML("c:/gmon/c.kml");
		double a=0.005;
		double lat=32.16876425;
		double lot=34.81321225;
		nts=CSVFilter.FilterByRadius(a,lat,lot,nt);
		nts.toCSV("C:/gmon/d.csv");
		System.out.println();
	}

}
