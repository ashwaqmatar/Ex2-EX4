package Coords;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import Geom.Point3D;

class MyCoordsTest {

	private Point3D p1;
	private Point3D p2;
	private Point3D local_vector_in_meter; // Set Coordinates as you want.

	MyCoords mc = new MyCoords();

	//constructor
	public MyCoordsTest(Point3D p1,
	 Point3D p2,
	Point3D local_vector_in_meter) {
		this.p1=p1;
		this.p2=p2;
		this.local_vector_in_meter=local_vector_in_meter;
	}
	@Before
	public void setup() {
		// Coordinates copied from the examples in the attached Exel file.
		p1 = new Point3D(32.103315, 35.209039, 670);
		p2 = new Point3D(32.106352, 35.205225, 650);
		local_vector_in_meter = new Point3D(337.6989920612881, -359.24920693881893, -20.0);
	}
	/**
	 * Test method for {@link Coords.MyCoords#add(Geom.Point3D, Geom.Point3D)}.
	 */
	@Test
	void testAdd() {
		Point3D pTemp;
		pTemp = mc.add(p1, local_vector_in_meter);
		assertEquals(p2.x(), pTemp.x(),0.001,"The Point Latitude is wrong");
		assertEquals(p2.y(), pTemp.y(),0.001,"The Point longtidude is wrong");
		assertEquals(p2.z(), pTemp.z(),0.001,"The Point atitude is wrong");
	}
	/**
	 * Test method for {@link Coords.MyCoords#distance3d(Geom.Point3D, Geom.Point3D)}.
	 */
	@Test
	void testDistance3d() {
		double distanceExpected = 493.0523318;
		double distanceActual = mc.distance3d(p1, p2);
		assertEquals(distanceExpected, distanceActual, 0.001, "The distance calculated is wrong" );
	}

	@Test
	void testVector3D() {
		Point3D pTemp;
		pTemp = mc.vector3D(p1, p2);
		assertEquals(local_vector_in_meter.x(), pTemp.x(),0.001,"The Vector Latitude is wrong");
		assertEquals(local_vector_in_meter.y(), pTemp.y(),0.001,"The Vector longtidude is wrong");
		assertEquals(local_vector_in_meter.z(), pTemp.z(),0.001,"The Vector atitude is wrong");	
	}

	@Test
	void testAzimuth_elevation_dist() {
		double [] aziExpected= {313.2304, -2.3247, 493.0523};
		double [] aziActual = mc.azimuth_elevation_dist(p1, p2);
		assertEquals(aziExpected[0],aziActual[0],0.001, "The Azimuth is wrong");
		assertEquals(aziExpected[1],aziActual[1],0.001,"The Elevation is wrong");
		assertEquals(aziExpected[2],aziActual[2],0.001,"The Distance is wrong");
	}

	@Test
	void testIsValid_GPS_Point() {
		Point3D invalidP = new Point3D(190, 30, 200);		
		assertEquals(false, mc.isValid_GPS_Point(invalidP),"The function for Valid GPS Point is wrong");
		assertEquals(true, mc.isValid_GPS_Point(p1),"The function for Valid GPS Point is wrong");

	}
}
