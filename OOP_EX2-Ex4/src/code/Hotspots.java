package code;

public class Hotspots {
	public void setLine(WIFI[] line) {
		this.line = line;
	}
	private WIFI [] line;
	private int real_size;
	private final int INIT_SIZE=10;

	public Hotspots()
	{
		line=new WIFI[INIT_SIZE];
		real_size=0;
	}
	
	public Hotspots(WIFI dot)
	{
		if(line==null) line=new WIFI[INIT_SIZE];
		if(real_size==line.length)
		{
			int i=checkSignal(dot);
			if(i!=-1)
			{
				line[i]=new WIFI(dot);	
			}
		}
		line[real_size++]=new WIFI(dot);
	}

	public void add(WIFI dot)
	{
		if(line==null) line=new WIFI[INIT_SIZE];
		if(real_size==line.length)
		{
			int i=checkSignal(dot);
			if(i!=-1)
			{
				line[i]=new WIFI(dot);	
			}
		}
		else line[real_size++]=new WIFI(dot);
	}
	
	public int getReal_size() {
		return real_size;
	}
	
	public WIFI[] getLine() {
		return line;
	}
	/*Check signal before import
	 * if its lower dont input
	 * 
	 */
	private int checkSignal(WIFI dot)
	{
		int max=line[0].getRssi();
		int index=0;
		for(int i=1;i<real_size;i++)
		{
			if(line[i].getRssi()>max)
			{
				max=line[i].getRssi();
				index=i;
			}
		}
		if(max>dot.getRssi()) return index;
		else return -1;
	}

}
