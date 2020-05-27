package com.teamgames.gamepayments.product;


public class ProductResponse {

	String message;
	String extendedMessage;
	Product[] products;



	public Product[] getProducts() {

		return products;

	}
	
	public String getMessage() {
		return message;
	}
	
	public String getExtendedMessage() {
		return extendedMessage;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(message).append("\n").append(extendedMessage).append("\n");
		for(Product product : getProducts()) {
			builder.append(product.toString()).append("\n");
		}
		return builder.toString();
	}
}