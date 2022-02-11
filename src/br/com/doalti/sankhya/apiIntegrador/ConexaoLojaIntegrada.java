package br.com.doalti.sankhya.apiIntegrador;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class ConexaoLojaIntegrada {
	
	String url = "https://api.awsli.com.br/v1";
	Gson GSON = (new GsonBuilder()).serializeNulls().create();
	String url1 = "";
	String metodo = "";

	public static void main(String[] args) {

	}

	public String getToken(String login, String senha) throws Exception {
		url1 = "/autenticacao/login";

		Map<String, Object> obj = new HashMap<>();
		obj.put("login", login);
		obj.put("senha", senha);

		metodo = "POST";
		String postBody = GSON.toJson(obj, Map.class);
		System.out.println(postBody);

		String token = ChamaApi(url1, postBody, metodo);

		return token;

	}

	public String ChamaApi(String url1, String body, String metodo) throws Exception {
		byte[] postData = body.getBytes(StandardCharsets.UTF_8);
		HttpURLConnection con = getConnection(url + url1, metodo);
		System.out.println(url + url1);
		con.setRequestProperty("Content-Type", "application/json");
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.write(postData);
		StringBuffer response = new StringBuffer();
		BufferedReader br = null;
		int statusCode = con.getResponseCode();
		String line;
		br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		if (statusCode >= 200 && statusCode < 400) {
			while ((line = br.readLine()) != null)
				response.append(line);
		} else {

			while ((line = br.readLine()) != null)
				response.append(line);
		}

		return response.toString();
	}

	public String ChamaApi1(String url2, String metodo) throws Exception {
		
		HttpURLConnection con = getConnection(url + url2, metodo);
		System.out.println(url + url2);
		con.setRequestProperty("Content-Type", "application/json");
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		
		//wr.write(postData);
		StringBuffer response = new StringBuffer();
		BufferedReader br = null;
		int statusCode = con.getResponseCode();
		String line;
		br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		if (statusCode >= 200 && statusCode < 400) {
			while ((line = br.readLine()) != null)
				response.append(line);
		} else {

			while ((line = br.readLine()) != null)
				response.append(line);
		}

		return response.toString();
	}
	

	public String ChamaApi2(String url2, String metodo, String chaveAPI , String chaveAplicacao) throws Exception {
		
		System.out.println(url + url2);
		HttpURLConnection con = getConnection(url + url2, metodo);
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Authorization", "chave_api " + chaveAPI  + " aplicacao " + chaveAplicacao);
		StringBuffer response1 = new StringBuffer();
		BufferedReader br = null;
		int statusCode = con.getResponseCode();
		String line;
		br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		if (statusCode >= 200 && statusCode < 400) {
			while ((line = br.readLine()) != null)
				response1.append(line);
		} else {

			while ((line = br.readLine()) != null)
				response1.append(line);
		}

		return response1.toString();
	}

	public HttpURLConnection getConnection(String urlStr, String metodo) throws Exception {
		URL url = new URL(urlStr);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setConnectTimeout(450000);
		con.setRequestMethod(metodo);
		con.setUseCaches(true);
		con.setDoInput(true);
		con.setDoOutput(true);
		return con;
	}

}
