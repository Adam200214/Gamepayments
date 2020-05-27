package com.teamgames;


import com.teamgames.gamepayments.checkout.CheckOut;
import com.teamgames.gamepayments.playerstore.PlayerStore;
import com.teamgames.gamepayments.product.Product;
import com.teamgames.gamepayments.transaction.Transaction;
import com.teamgames.request.Connection;

public class GamePayments {
    public static boolean requestRunning = false;

    public GamePayments(String secret_key) {
        this.secret_key = secret_key;
    }
    private String secret_key;

    public String getSecret_key() {
        return secret_key;
    }

    public Transaction getTransations() {
        return transations;
    }

    public Product getProducts() {
        return products;
    }

    public CheckOut getCheckOut() {
        return checkOut;
    }

    public PlayerStore getPlayerStore() {
        return playerStore;
    }

    private Transaction transations = new Transaction(this);
    private Product products = new Product(this);
    private CheckOut checkOut = new CheckOut(this);
    private PlayerStore playerStore = new PlayerStore(this);
    public Connection connection = new Connection(this);
}
