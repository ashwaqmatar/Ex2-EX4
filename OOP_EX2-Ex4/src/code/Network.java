package code;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class Network {
	private Hotspots[] line;
	private int real_size;
	private final int INIT_SIZE=10,RESIZE=10;

	public Network()
	{
		line=new Hotspots[INIT_SIZE];
		real_size=0;
	}

	public void add(WIFI dot)//adding spot to the array of arrays
	{
		if(real_size==line.length)
		{
			resize();
		}
		if(checkFS(dot))//if last firstseen== new firstseen put it into one array
		{
			line[real_size-1].add(dot);
		}
		else
			line[real_size++]=new Hotspots(dot);
	}

	private void resize()
	{
		Hotspots temp[]=new Hotspots[line.length+RESIZE];
		for(int i=0;i<line.length;i++)
		{
			temp[i]=line[i];
		}
		line=temp;

	}

	private boolean checkFS(WIFI dot)//check date
	{
		if(line[0]==null) return false;
		else if(line[real_size-1].getLine()[0].getFirtseen().equals(dot.getFirtseen())) return true;
		return false;
	}

	public void toCSV(String folder) throws FileNotFoundException, IOException//print scv
, ParserConfigurationException
	{
		if(real_size!=0) {
			PrintWriter pw = new PrintWriter(new File(folder));
			StringBuilder sb = new StringBuilder();
			sb.append("Time,ID,LAT,LON,ALT,#WiFi networks,");
			try 
			{
				for(int i=1;i<=10;i++)
				{
					sb.append("SSID"+ i +",Mac"+ i +",Frequncy"+ i +",Signal"+ i +"," );
				}
				sb.append("\n");
				int max=real_size;
				WIFI[] temp=new WIFI[10];
				int j=0;
				for(int i=0;i<max;i++)
				{
					j=0;
					if(line[i]!=null)
					{
						temp=line[i].getLine();
						if(temp[j]!=null) {
							sb.append(temp[j].getFirtseen()+","+temp[j].getid()+","+temp[j].getLat()+","+
									temp[j].getLot()+","+temp[j].getAlt()+","+temp.length+",");
							for(;j<temp.length&&temp[j]!=null;j++)
							{
								if(temp[j]!=null) 
								{
									String freq;
									if(temp[j].getChannel()==0)
									{
										freq="gsm";
									}
									else if(temp[j].getChannel()>35)
										freq= "5 GHZ";
									else freq= "2.4 GHZ";
									sb.append(temp[j].getSsid()+","+temp[j].getMac()+","+freq+","+temp[j].getRssi()+",");
								}
							}
						}
						sb.append("\n");
					}
				}
			}
			catch (Exception e) {
				System.out.println(e);
			}
			finally {
				System.out.println("Write file complete");
			}
			pw.append(sb.toString());
			pw.close();
		}
		else System.out.println("error!");
	}

	public boolean writeFileKML(String output) throws IOException {//write Kml 
		//from https://www.mkyong.com/java/how-to-create-xml-file-in-java-dom/
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document document = docBuilder.newDocument();
			Element root = document.createElement("kml");
			Attr attr = document.createAttribute("xmlns");
			attr.setValue("http://www.opengis.net/kml/2.2");
			document.appendChild(root);
			root.setAttributeNode(attr);
			Element Document = document.createElement("Document");
			root.appendChild(Document);
			Element name = document.createElement("name");
			name.appendChild(document.createTextNode("my"));
			Document.appendChild(name);
			Element Folder = document.createElement("Folder");
			Document.appendChild(Folder);
			Element fname = document.createElement("name");
			fname.appendChild(document.createTextNode("WiFi Networks"));
			Folder.appendChild(fname);
			int count = 0;
			WIFI[] temp=new WIFI[10];
			for (int i = 0 ; i < real_size; i++) 
			{
				if(line[i]!=null) {
					temp=line[i].getLine();
					for(int j=0;j<temp.length;j++)
					{
						if(temp[j]!=null) {
							Element placemark = document.createElement("Placemark");
							Attr at = document.createAttribute("id");
							at.setValue(count++ + "A");
							placemark.setAttributeNode(at);
							Folder.appendChild(placemark);
							Element tname = document.createElement("name");
							tname.appendChild(document.createTextNode(temp[j].getSsid()));
							placemark.appendChild(tname);
							Element description = document.createElement("description");
							String desc = temp[j].toString();
							description.appendChild(document.createTextNode(desc)); 
							placemark.appendChild(description);
							Element point = document.createElement("Point");
							Element coordinates = document.createElement("coordinates");
							coordinates.appendChild(document.createTextNode(Double.toString(temp[j].getLot())+","
									+Double.toString(temp[j].getLat())));
							point.appendChild(coordinates);
							placemark.appendChild(point);
						}
					}
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource domSource = new DOMSource(document);
					StreamResult streamResult = new StreamResult(new File(output));
					transformer.transform(domSource, streamResult);
				} 
			}
		}
		catch (ParserConfigurationException pce) {
			pce.printStackTrace();
			return false;
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
			return false;
		} catch (Exception e) {
			System.out.println(e);
			return false;

		}
		finally
		{
			System.out.println("Done creating XML File");
		}
		return false;
	}

	public void setReal_size(int real_size) {
		this.real_size = real_size;
	}

	public Hotspots[] getLine() {
		return line;
	}
	public void setLine(Hotspots[] line) {
		this.line = line;
	}

	public int getReal_size() {
		return real_size;
	}


}
