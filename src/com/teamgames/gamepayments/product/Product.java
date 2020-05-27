package com.teamgames.gamepayments.product;

import com.teamgames.GamePayments;
import com.teamgames.gamepayments.Configurations;
import org.google.gson.Gson;

import java.util.LinkedHashMap;
import java.util.Map;

public class Product {

	public Product(GamePayments gamePayments) {
		this.gamePayments = gamePayments;
	}
	private GamePayments gamePayments;
	public int id;

	public int getId() {
		return id;
	}

	public int getProductId() {
		return productId;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getDescription() {
		return description;
	}

	public String getImage() {
		return image;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public Sale[] getSales() {
		return sales;
	}

	public int productId;
	public String name;
	public double price;
	public int quantity;
	public String description;
	public String image;
	public boolean disabled;
	public Sale sales[];


	public ProductResponse fetch() {
		Map<String, Object> params = new LinkedHashMap<>();
		final String ADDRESS = Configurations.isLocal ? Configurations.LOCAL_ADDRESS : Configurations.GAMEPAYMENTS_ADDRESS;
		final String serverResponse = gamePayments.connection.sendPostParams(params, ADDRESS + "/api/v2/client/global/products");
		Gson gson = new Gson();
		ProductResponse apiResponse = gson.fromJson(serverResponse, ProductResponse.class);
		return apiResponse;
	}

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append("Product ID: " + getProductId() + "\n").append("Item ID: " + getId() + "\n").append("Name: " + getName() + "\n")
				.append("Price: " + getPrice() + "\n").append("Quantity: " + getQuantity() + "\n")
				.append("Description: " + getDescription() + "\n").append("image: " + getImage() + "\n").append("disabled: " + isDisabled() + "\n");
		return b.toString();
	}
}