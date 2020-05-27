package com.teamgames.gamepayments.checkout;

import com.teamgames.GamePayments;
import com.teamgames.gamepayments.Configurations;
import org.google.gson.Gson;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CheckOut {
    public CheckOut(GamePayments gamePayments) {
        this.gamePayments = gamePayments;
    }
    private GamePayments gamePayments;
    public boolean completedCheckout;
    public String redirect;
    public CheckoutResponce completeCheckout(String username, List<CartItem> cartItems) {
        Gson gson = new Gson();
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("username", username);
        System.out.println(gson.toJson(cartItems).toString());
        params.put("cartItems", gson.toJson(cartItems).toString());
        final String ADDRESS = Configurations.isLocal ? Configurations.LOCAL_ADDRESS : Configurations.GAMEPAYMENTS_ADDRESS;
        final String serverResponse = gamePayments.connection.sendPostParams(params, ADDRESS + "/api/v2/client/global/checkout/complete");
        CheckoutResponce apiResponse = gson.fromJson(serverResponse, CheckoutResponce.class);
        return apiResponse;
    }
}
