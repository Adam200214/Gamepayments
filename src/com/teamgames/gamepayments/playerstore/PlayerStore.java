package com.teamgames.gamepayments.playerstore;

import com.teamgames.GamePayments;
import com.teamgames.gamepayments.Configurations;
import org.google.gson.Gson;

import java.util.LinkedHashMap;
import java.util.Map;

public class PlayerStore {

	public PlayerStore(GamePayments gamePayments) {
		this.gamePayments = gamePayments;
	}
	private GamePayments gamePayments;
	public PlayerStoreResponse confirmUsername(String username, String verificationKey) {
		Map<String, Object> params = new LinkedHashMap<>();

		params.put("username", username);
		params.put("verificationKey", verificationKey);

		final String ADDRESS = Configurations.isLocal ? Configurations.LOCAL_ADDRESS
				: Configurations.GAMEPAYMENTS_ADDRESS;

		final String serverResponse = gamePayments.connection.sendPostParams(params,
				ADDRESS + "/api/v1/client/global/confirm-player-seller");

		Gson gson = new Gson();

		PlayerStoreResponse apiResponse = gson.fromJson(serverResponse, PlayerStoreResponse.class);

		return apiResponse;
	}

	public PlayerStoreResponse sellProduct(String username, int productId, String productName, double price, int quantity) {

		Map<String, Object> params = new LinkedHashMap<>();

		params.put("username", username);
		params.put("productId", productId);
		params.put("productName", productName);
		params.put("price", price);
		params.put("quantity", quantity);

		final String ADDRESS = Configurations.isLocal ? Configurations.LOCAL_ADDRESS
				: Configurations.GAMEPAYMENTS_ADDRESS;

		final String serverResponse = gamePayments.connection.sendPostParams(params,
				ADDRESS + "/api/v1/client/global/sell-player-product");

		Gson gson = new Gson();

		PlayerStoreResponse apiResponse = gson.fromJson(serverResponse, PlayerStoreResponse.class);

		return apiResponse;

	}
}
