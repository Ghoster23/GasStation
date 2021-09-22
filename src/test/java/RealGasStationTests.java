import java.util.Collection;
import java.util.Vector;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.bigpoint.assessment.gasstation.*;
import net.bigpoint.assessment.gasstation.exceptions.GasTooExpensiveException;
import net.bigpoint.assessment.gasstation.exceptions.NotEnoughGasException;

/**
 * 
 */

/**
 * @author Manel
 *
 */
public class RealGasStationTests {
	private static GasPump regularPump;
	
	private static RealGasStation station;

	@BeforeClass
	public static void setUpBeforeClass() {
		regularPump = new GasPump(GasType.REGULAR, 100d);
	}
	
	
	@AfterClass
	public static void tearDownAfterClass() {
		regularPump = null;
		
		station = null;
	}

	@Before
	public void setUp() {
		station = new RealGasStation();
		
		station.addGasPump(regularPump);
		
		station.setPrice(GasType.REGULAR, 1d);
	}
	

	/**
	 * Test method for {@link RealGasStation#addGasPump(net.bigpoint.assessment.gasstation.GasPump)}.
	 */
	@Test
	public final void testAddGasPump() {
		GasPump newPump = new GasPump(GasType.DIESEL, 66d);
		
		station.addGasPump(newPump);
		
		Vector<GasPump> pumps = new Vector<GasPump>(station.getGasPumps());
		
		assertTrue(pumps.size() == 2);
		
		for(int ind = 0; ind < pumps.size(); ind++) {
			GasPump pump = pumps.get(ind);
			
			if(pump.getRemainingAmount() == newPump.getRemainingAmount() &&
				pump.getGasType() == newPump.getGasType()) {
				return;
			}
		}
		
		fail("Pump not in station");
	}
	
	/**
	 * Test method for {@link RealGasStation#addGasPump(net.bigpoint.assessment.gasstation.GasPump)}.
	 */
	@Test
	public final void testAddNullGasPump() {		
		try {
			station.addGasPump(null);
		}
		catch(NullPointerException e) {
			fail("NullPointerException thrown");
		}
		
		assertTrue(station.getGasPumps().size() == 1);
	}
	
	/**
	 * Test method for {@link RealGasStation#addGasPump(net.bigpoint.assessment.gasstation.GasPump)}.
	 */
	@Test
	public final void testAddEmptyGasPump() {
		GasPump newPump = new GasPump(GasType.DIESEL, 0d);
		
		station.addGasPump(newPump);
		
		assertTrue(station.getGasPumps().size() == 1);
	}
	
	/**
	 * Test method for {@link RealGasStation#addGasPump(net.bigpoint.assessment.gasstation.GasPump)}.
	 */
	@Test
	public final void testAddNegativeGasPump() {
		GasPump newPump = new GasPump(GasType.DIESEL, -100d);
		
		station.addGasPump(newPump);
		
		assertTrue(station.getGasPumps().size() == 1);
	}

	
	/**
	 * Test method for {@link RealGasStation#getGasPumps()}.
	 */
	@Test
	public final void testGetGasPumps() {
		station = new RealGasStation();
		
		Collection<GasPump> pumps = station.getGasPumps();
		
		assertTrue(pumps != null);
	}
	
	/**
	 * Test method for {@link RealGasStation#getGasPumps()}.
	 */
	@Test
	public final void testGetGasPumpsHasPumps() {
		Collection<GasPump> pumps = station.getGasPumps();
		
		assertTrue(pumps.size() > 0 && pumps.iterator().next() instanceof GasPump);
	}

	
	/**
	 * Test method for {@link RealGasStation#buyGas(net.bigpoint.assessment.gasstation.GasType, double, double)}.
	 */
	@Test
	public final void testBuyGas() {
		try {
			station.buyGas(GasType.REGULAR, 1d, 1d);
		}
		catch(Exception e) {
			fail("Valid Purchase threw an Exception -> " + e);
		}
	}
	
	/**
	 * Test method for {@link RealGasStation#buyGas(net.bigpoint.assessment.gasstation.GasType, double, double)}.
	 */
	@Test
	public final void testBuyGasNoneOfType() {
		try {
			station.buyGas(GasType.DIESEL, 1d, 1d);
		}
		catch(NotEnoughGasException e) {
			return;
		}
		catch(Exception e) {
			fail("Wrong Exception thrown -> " + e);
		}
	}
	
	/**
	 * Test method for {@link RealGasStation#buyGas(net.bigpoint.assessment.gasstation.GasType, double, double)}.
	 */
	@Test
	public final void testBuyGasNotEnoughOfType() {
		try {
			station.buyGas(GasType.REGULAR, 200d, 1d);
		}
		catch(NotEnoughGasException e) {
			return;
		}
		catch(Exception e) {
			fail("Wrong Exception thrown -> " + e);
		}
	}
	
	/**
	 * Test method for {@link RealGasStation#buyGas(net.bigpoint.assessment.gasstation.GasType, double, double)}.
	 */
	@Test
	public final void testBuyGasTooExpensive() {
		try {
			station.buyGas(GasType.REGULAR, 1d, 0.1d);
		}
		catch(GasTooExpensiveException e) {
			return;
		}
		catch(Exception e) {
			fail("Wrong Exception thrown -> " + e);
		}
	}

	
	/**
	 * Test method for {@link RealGasStation#getRevenue()}.
	 */
	@Test
	public final void testGetRevenue0() {
		assertTrue(station.getRevenue() == 0);
	}
	
	/**
	 * Test method for {@link RealGasStation#getRevenue()}.
	 */
	@Test
	public final void testGetRevenue() {
		try {
			station.buyGas(GasType.REGULAR, 1d, 1d);
		}
		catch(Exception e) {
			fail("Valid purchase threw an Exception -> " + e);
		}
		
		assertTrue(station.getRevenue() == 1d);
	}

	
	/**
	 * Test method for {@link RealGasStation#getNumberOfSales()}.
	 */
	@Test
	public final void testGetNumberOfSales0() {
		assertTrue(station.getNumberOfSales() == 0);
	}
	
	/**
	 * Test method for {@link RealGasStation#getNumberOfSales()}.
	 */
	@Test
	public final void testGetNumberOfSales() {
		try {
			station.buyGas(GasType.REGULAR, 1d, 1d);
		}
		catch(Exception e) {
			fail("Valid purchase threw an Exception -> " + e);
		}
		
		assertTrue(station.getNumberOfSales() == 1);
	}

	
	/**
	 * Test method for {@link RealGasStation#getNumberOfCancellationsNoGas()}.
	 */
	@Test
	public final void testGetNumberOfCancellationsNoGas0() {
		assertTrue(station.getNumberOfCancellationsNoGas() == 0);
	}
	
	/**
	 * Test method for {@link RealGasStation#getNumberOfCancellationsNoGas()}.
	 */
	@Test
	public final void testGetNumberOfCancellationsNoGasOfType() {
		try {
			station.buyGas(GasType.DIESEL, 1, 1);
		}
		catch(NotEnoughGasException e) {
			assertTrue(station.getNumberOfCancellationsNoGas() == 1);
			return;
		}
		catch(Exception e) {
			fail("Wrong exception thrown -> " + e);
		}
		
		fail("NotEnoughGasException not thrown");
	}
	
	/**
	 * Test method for {@link RealGasStation#getNumberOfCancellationsNoGas()}.
	 */
	@Test
	public final void testGetNumberOfCancellationsNoGasQuantity() {
		try {
			station.buyGas(GasType.REGULAR, 200d, 1d);
		}
		catch(NotEnoughGasException e) {
			assertTrue(station.getNumberOfCancellationsNoGas() == 1);
			return;
		}
		catch(Exception e) {
			fail("Wrong exception thrown -> " + e);
		}
		
		fail("NotEnoughGasException not thrown");
	}

	
	/**
	 * Test method for {@link RealGasStation#getNumberOfCancellationsTooExpensive()}.
	 */
	@Test
	public final void testGetNumberOfCancellationsTooExpensive0() {
		assertTrue(station.getNumberOfCancellationsTooExpensive() == 0);
	}
	
	/**
	 * Test method for {@link RealGasStation#getNumberOfCancellationsTooExpensive()}.
	 */
	@Test
	public final void testGetNumberOfCancellationsTooExpensive() {
		try {
			station.buyGas(GasType.REGULAR, 1, 0.1d);
		}
		catch(GasTooExpensiveException e) {
			assertTrue(station.getNumberOfCancellationsTooExpensive() == 1);

			return;
		}
		catch(Exception e) {
			fail("Wrong exception thrown -> " + e);
		}
		
		fail("GasTooExpensiveException not thrown");
	}

	
	/**
	 * Test method for {@link RealGasStation#getPrice(net.bigpoint.assessment.gasstation.GasType)}.
	 */
	@Test
	public final void testGetPrice() {
		assertTrue(station.getPrice(GasType.REGULAR) == 1d);
	}
	
	/**
	 * Test method for {@link RealGasStation#getPrice(net.bigpoint.assessment.gasstation.GasType)}.
	 */
	@Test
	public final void testGetPriceMissing() {
		assertTrue(station.getPrice(GasType.DIESEL) == 0);
	}

	
	/**
	 * Test method for {@link RealGasStation#setPrice(net.bigpoint.assessment.gasstation.GasType, double)}.
	 */
	@Test
	public final void testSetPrice() {
		station.setPrice(GasType.DIESEL, 5d);
		
		assertTrue(station.getPrice(GasType.DIESEL) == 5d);
	}
	
	/**
	 * Test method for {@link RealGasStation#setPrice(net.bigpoint.assessment.gasstation.GasType, double)}.
	 */
	@Test
	public final void testSetPriceExisting() {
		station.setPrice(GasType.REGULAR, 5d);
		
		assertTrue(station.getPrice(GasType.REGULAR) == 5d);
	}
}
