package saulomedeiros.service;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class HttpService {

	private final static String APPLICATION_JSON = "application/json";

	/*
	 * Método faz requisição HTTP GET para uma url e cujo resultado da requisição é
	 * um JSON
	 */
	public static String get(String url, String token) throws Exception {
		URL requestUrl = new URL(url + token);

		HttpURLConnection myUrlConnection = (HttpURLConnection) requestUrl.openConnection();
		myUrlConnection.setRequestMethod("GET");
		myUrlConnection.connect();

		Scanner scanner = new Scanner(myUrlConnection.getInputStream());
		String result = scanner.nextLine();
		scanner.close();

		System.out.println("HTTP GET - Response from the server: " + result);

		myUrlConnection.disconnect();

		return result;
	}

	/*
	 * Método submete um arquivo via POST para uma url
	 */
	public static void sendFile(String url, String token, String key, File value, String filename) throws IOException {

		if(!token.isEmpty())
			url += token;
		
		OkHttpClient client = new OkHttpClient();


		RequestBody body = new MultipartBuilder()
				.type(MultipartBuilder.FORM)
				.addFormDataPart(key, filename, RequestBody.create(MediaType.parse(APPLICATION_JSON), value)).build();

		Request request = new Request.Builder().url(url).post(body)
				.build();

		Response response = client.newCall(request).execute();

		if (response.isSuccessful()) {
			System.out.println("URL = " +url);
			System.out.println("Sucess! File posted !");
			System.out.println("HTTP STATUS = " + response.code());
			System.out.println("HTTP POST (Send File) - Response from the server: " + response.body().string());
		}else {
			System.out.println("URL = " +url);
			System.out.println("Failed! File not posted !");
			System.out.println("HTTP STATUS = " + response.code());
			System.out.println("HTTP POST (Send File) - Response from the server: " + response.body().string());
		}

	}
}
