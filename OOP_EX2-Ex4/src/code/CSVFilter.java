package code;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CSVFilter 
{
	/*
	 * Filtering by id and erase from Network if its true
	 * 
	 */
	
	public static Network FilterId(String name,Network nt)
	{
		WIFI[] temp=new WIFI[10];
		int count=0;
		for(int i=0;i<nt.getReal_size();i++)
		{
			count=0;
			temp=nt.getLine()[i].getLine();
			for(int j=0;j<10;j++)
				if(temp[j]!=null)
				{
					if(temp[j].getid().equals(name))
					{
						temp[j]=null;
					}
				}
			for(int q=0;q<10;q++)
			{
				if(temp[q]==null) count++;
			}
			if(count==10) 
			{
				nt.getLine()[i]=null;
			}
		}
		return nt;
	}
	/*
	 * Filter by date to date with erasing if true
	 * 
	 */
	public static Network FilterByDate(Date x,Date y,Network nt)
	{
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		WIFI[] temp=new WIFI[10];
		int count=0;
		for(int i=0;i<nt.getReal_size();i++)
		{
			count=0;
			temp=nt.getLine()[i].getLine();
			for(int j=0;j<10;j++)
				if(temp[j]!=null)
				{
					if(temp[j].getFirtseen().after(y)||temp[j].getFirtseen().before(x))
					{
						temp[j]=null;
					}
				}
			for(int q=0;q<10;q++)
			{
				if(temp[q]==null) count++;
			}
			if(count==10) 
			{
				nt.getLine()[i]=null;
			}
		}
		System.out.println();

		return nt;
	}
	/*
	 * Filter by place and eracing if true
	 * 
	 */

	public static Network FilterByRadius(double radius,double lat,double lot,Network nt)
	{
		WIFI[] temp=new WIFI[10];
		int count=0;
		for(int i=0;i<nt.getReal_size();i++)
		{
			count=0;
			temp=nt.getLine()[i].getLine();
			for(int j=0;j<10;j++)
				if(temp[j]!=null)
				{
					if((temp[j].getLot()>lot+radius||temp[j].getLot()<lot-radius)||
					   (temp[j].getLat()>lat+radius||temp[j].getLat()<lat-radius))
					{
						temp[j]=null;
					}
				}
			for(int q=0;q<10;q++)
			{
				if(temp[q]==null) count++;
			}
			if(count==10) 
			{
				nt.getLine()[i]=null;
			}
		}
		System.out.println();

		return nt;
	}

}

