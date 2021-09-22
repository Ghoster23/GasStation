import java.util.Collection;
import java.util.ArrayList;
import java.util.HashMap;

import net.bigpoint.assessment.gasstation.GasPump;
import net.bigpoint.assessment.gasstation.GasStation;
import net.bigpoint.assessment.gasstation.GasType;
import net.bigpoint.assessment.gasstation.exceptions.GasTooExpensiveException;
import net.bigpoint.assessment.gasstation.exceptions.NotEnoughGasException;

public class RealGasStation implements GasStation {
	private ArrayList<GasPump> gasPumps;
	
	private double reveneu = 0;
	private int numSales = 0;
	
	private int cancelNGas = 0;
	private int cancelTooMuch = 0;
	
	private HashMap<GasType,Double> priceTable;
	
	public RealGasStation() {
		gasPumps = new ArrayList<GasPump>();
		priceTable = new HashMap<GasType,Double>();
	}
	
	/**
	 * Add a gas pump to this station.
	 * This is used to set up this station.
	 * 
	 * @param pump
	 *            the gas pump
	 */
	@Override
	public void addGasPump(GasPump pump) {
		gasPumps.add(pump);
	}

	/**
	 * Get all gas pumps that are currently associated with this gas station.
	 * 
	 * Modifying the resulting collection should not affect this gas station.
	 * 
	 * @return A collection of all gas pumps.
	 */
	@Override
	public Collection<GasPump> getGasPumps() {
		return gasPumps;
	}
	
	/**
	 * Simulates a customer wanting to buy a specific amount of gas.
	 * 
	 * @param type
	 *            The type of gas the customer wants to buy
	 * @param amountInLiters
	 *            The amount of gas the customer wants to buy. Nothing less than this amount is acceptable!
	 * @param maxPricePerLiter
	 *            The maximum price the customer is willing to pay per liter
	 * @return the price the customer has to pay for this transaction
	 * @throws NotEnoughGasException
	 *             Should be thrown in case not enough gas of this type can be provided
	 *             by any single {@link GasPump}.
	 * @throws GasTooExpensiveException
	 *             Should be thrown if gas is not sold at the requested price (or any lower price)
	 */
	@Override
	public double buyGas(GasType type, double amountInLiters, double maxPricePerLiter)
			throws NotEnoughGasException, GasTooExpensiveException {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @return the total revenue generated
	 */
	@Override
	public double getRevenue() {
		return reveneu;
	}

	/**
	 * Returns the number of successful sales. This should not include cancelled sales.
	 * 
	 * @return the number of sales that were successful
	 */
	@Override
	public int getNumberOfSales() {
		return numSales;
	}

	/**
	 * @return the number of cancelled transactions due to not enough gas being available
	 */
	@Override
	public int getNumberOfCancellationsNoGas() {
		return cancelNGas;
	}

	/**
	 * Returns the number of cancelled transactions due to the gas being more expensive than what the customer wanted to pay
	 * 
	 * @return the number of cancelled transactions
	 */
	@Override
	public int getNumberOfCancellationsTooExpensive() {
		return cancelTooMuch;
	}

	/**
	 * Get the price for a specific type of gas
	 * 
	 * @param type
	 *            the type of gas
	 * @return the price per liter for this type of gas
	 */
	@Override
	public double getPrice(GasType type) {
		return priceTable.getOrDefault(type, 0d);
	}

	/**
	 * Set a new price for a specific type of gas
	 * 
	 * @param type
	 *            the type of gas
	 * @param price
	 *            the new price per liter for this type of gas
	 */
	@Override
	public void setPrice(GasType type, double price) {
		priceTable.replace(type, price);
	}

}
