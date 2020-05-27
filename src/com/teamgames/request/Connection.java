package com.teamgames.request;

import com.teamgames.GamePayments;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.nio.reactor.ConnectingIOReactor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.Future;

public class Connection {
	private GamePayments gamePayments;

	public Connection(GamePayments gamePayments) {
		this.gamePayments = gamePayments;
	}

	public String sendPostParams(Map<String, Object> params, String targetURL) {
		try {
			while (!GamePayments.requestRunning) {
				GamePayments.requestRunning = true;
				ConnectingIOReactor ioReactor = new DefaultConnectingIOReactor();
				PoolingNHttpClientConnectionManager cm =
						new PoolingNHttpClientConnectionManager(ioReactor);
				CloseableHttpAsyncClient client =
						HttpAsyncClients.custom().disableCookieManagement().setConnectionManager(cm).build();
				URIBuilder builder = new URIBuilder(targetURL);
				params.forEach((key, value) -> {
					builder.setParameter(key, String.valueOf(value));
				});
				HttpPost httpPost = new HttpPost(builder.build());
				httpPost.addHeader("Authorization", new String(Base64.getEncoder().encode(gamePayments.getSecret_key().getBytes())));
				httpPost.addHeader("User-Agent", "GamepaymentsJavaClientAPI/1.1");
				httpPost.addHeader("Accept", "*/*");
				httpPost.addHeader("Accept-Language", "en-US,en;q=0.5");
				httpPost.addHeader("X-Requested-With", "XMLHttpRequest");
				httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
				httpPost.addHeader("Content-Language", "en-US");
				client.start();
				Future<HttpResponse> future = client.execute(httpPost, HttpClientContext.create(), null);
				HttpResponse response = future.get();
				BufferedReader rd = new BufferedReader(
						new InputStreamReader(response.getEntity().getContent()));
				StringBuffer result = new StringBuffer();
				String line = "";
				while ((line = rd.readLine()) != null) {
					result.append(line);
				}
				rd.close();
				GamePayments.requestRunning = false;
				return result.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

