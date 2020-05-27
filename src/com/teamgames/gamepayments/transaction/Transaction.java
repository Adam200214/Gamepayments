package com.teamgames.gamepayments.transaction;

import com.teamgames.GamePayments;
import com.teamgames.gamepayments.Configurations;
import org.google.gson.Gson;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Nelson Sanchez
 */

public class Transaction {


	public Transaction(GamePayments gamePayments) {
		this.gamePayments = gamePayments;
	}
	private GamePayments gamePayments;

	/**
	 * These variables represent the JSON response that is sent from Gamepayments
	 */

	public String productId;
	public String name;
	public float price;
	public float priceWithDiscount;
	public int quantity;
	public int allowReclaim;
	public int gameServer;
	public String username;
	public String paymentType;
	public float tax;
	public String invoice;
	public String paymentStatus;
	public String deliveryStatus;

	public static ArrayList<String> orders = new ArrayList<String>();

	public TransactionResponse getResponse(String username)  {
		Map<String, Object> params = new LinkedHashMap<>();
		params.put("username", username);
		final String ADDRESS = Configurations.isLocal ? Configurations.LOCAL_ADDRESS
				: Configurations.GAMEPAYMENTS_ADDRESS;
		final String serverResponse;
			serverResponse = gamePayments.connection.sendPostParams(params,
					ADDRESS + "/api/v1/client/global/claim-purchase");

		Gson gson = new Gson();
		TransactionResponse apiResponse = gson.fromJson(serverResponse, TransactionResponse.class);
		if (!apiResponse.getMessage().equalsIgnoreCase("")) {
			if (apiResponse.getTransactions().length > 0) {
				final String invoice = apiResponse.getTransactions()[0].invoice;
				if (!orders.isEmpty()) {
					if (orders.contains(invoice)) {
						apiResponse.message = "ALREADY_PROCESSED";
						apiResponse.extendedMessage = "Transaction has already been processed.";
					}
				}
				orders.add(invoice);
			}
		}
		return apiResponse;
	}
}
