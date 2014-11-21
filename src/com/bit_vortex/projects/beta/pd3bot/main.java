package com.bit_vortex.projects.beta.pd3bot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.UUID;

import org.json.JSONObject;




public class Main{
	
	
	
	static double sessProfit = 0.0;
	
	static File dir = new File(getDir());
	static File userFile = new File(dir + File.separator + "username.txt");
	static File passFile = new File(dir + File.separator + "password.txt");
	static File betAmountFile = new File(dir + File.separator + "betamount.txt");
	static File betTargetFile = new File(dir + File.separator + "bettarget.txt");
	static File betConditionFile = new File(dir + File.separator + "betcondition.txt");
	static File keyFile = new File(dir + File.separator + "key.txt");
	static File waitFile = new File(dir + File.separator + "wait.txt");
	static File balFile = new File(dir + File.separator + "bal.txt");
	static File tokenFile = new File(dir + File.separator + "token.txt");
	
	
	public static void main(String[] args) throws Exception{
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Welcome to PD3Bot by Vortex20000.\nThis bot is freemium, meaning basic functions are available for free but more complex functions must be paid for.\nEnjoy betting using my bot.");
		if(!dir.exists()){
			dir.mkdir();
		}
		if(!userFile.exists()){
			System.out.println("[Error] Settings files have not been created. Attempting to create settings file now.");
			userFile.createNewFile();
			betAmountFile.createNewFile();
			betTargetFile.createNewFile();
			betConditionFile.createNewFile();
			keyFile.createNewFile();
			waitFile.createNewFile();
			balFile.createNewFile();

			
			String key = UUID.randomUUID().toString();
			
			BufferedWriter outputKey = new BufferedWriter(new FileWriter(keyFile));
	        outputKey.write(key);
	        outputKey.close();
	        
	        System.out.println("[Resolved] Files created.\n[Error] No settings. Please input settings.\nInput username: ");
	        BufferedWriter outputUser1 = new BufferedWriter(new FileWriter(userFile));
	        if(!userFile.exists()){
	        	userFile.createNewFile();
	        }
	        String user = in.next();
	        outputUser1.write(user);
	        outputUser1.close();
	        System.out.println("Accepted. Input password.");
	        BufferedWriter outputPass1 = new BufferedWriter(new FileWriter(passFile));
	        outputPass1.write(in.next());
	        outputPass1.close();
	        System.out.println("Accepted. Input bet amount in satoshis: ");
	        BufferedWriter outputBetAmount1 = new BufferedWriter(new FileWriter(betAmountFile));
	        outputBetAmount1.write(in.next());
	        outputBetAmount1.close();
	        System.out.println("Accepted. Input bet target (under so-and-so or over-so-and-so; enter the number only.): "); 
	        BufferedWriter outputBetTarget1 = new BufferedWriter(new FileWriter(betTargetFile));
	        outputBetTarget1.write(in.next());
	        outputBetTarget1.close();
	        System.out.println("Accepted. Bet on less than or more than target? Input < or >.");
	        BufferedWriter outputBetCondition1 = new BufferedWriter(new FileWriter(betConditionFile));
	        outputBetCondition1.write(in.next());
	        outputBetCondition1.close();
	        System.out.println("Accepted. Input wait time between bets in milliseconds.");
	        BufferedWriter outputWaitTime1 = new BufferedWriter(new FileWriter(waitFile));
	        String z = in.next();
	        
	        outputWaitTime1.write(z);
	        outputWaitTime1.close();
	        
	        System.out.println("Loading...");
	        
	        getToken();
			
			Scanner tokenScanner1 = new Scanner(tokenFile, "UTF-8");
			String token1 = tokenScanner1.next();
			tokenScanner1.close();
			URL user1 = new URL("https://api.primedice.com/api/users/1?access_token=" + token1);
			HttpURLConnection conn = (HttpURLConnection) user1.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			
			BufferedReader in1 = new BufferedReader(
			        new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in1.readLine()) != null) {
				response.append(inputLine);
			}
			in1.close();
			
			String result = response.toString();
			
			JSONObject j = new JSONObject(result).getJSONObject("user");
			
			Scanner userScanner = new Scanner(userFile, "UTF-8");
			Scanner tokenScanner2 = new Scanner(tokenFile, "UTF-8");
			Scanner keyScanner = new Scanner(keyFile, "UTF-8");
			
			System.out.println("\n====================\nUsernane: " + userScanner.nextLine() + "\nToken: " + tokenScanner2.nextLine() + "\nBalance: " + (j.getDouble("balance")*0.01) + " μBTC\nKey: " + keyScanner.nextLine() + "\n====================");
			
			keyScanner.close();
			userScanner.close();
			tokenScanner2.close();
	       
	        System.out.println("Accepted. Settings complete. \nEnter: start/settings/features/upgrade/advancedbot");
		} else {
			
			System.out.println("Loading...");
			
			getToken();
			
			Scanner tokenScanner1 = new Scanner(tokenFile, "UTF-8");
			String token1 = tokenScanner1.next();
			tokenScanner1.close();
			URL user1 = new URL("https://api.primedice.com/api/users/1?access_token=" + token1);
			HttpURLConnection conn = (HttpURLConnection) user1.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			
			BufferedReader in1 = new BufferedReader(
			        new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in1.readLine()) != null) {
				response.append(inputLine);
			}
			in1.close();
			
			String result = response.toString();
			
			JSONObject j = new JSONObject(result).getJSONObject("user");
			
			Scanner userScanner = new Scanner(userFile, "UTF-8");
			Scanner tokenScanner2 = new Scanner(tokenFile, "UTF-8");
			Scanner keyScanner = new Scanner(keyFile, "UTF-8");
			
			System.out.println("\n====================\nUsername: " + userScanner.nextLine() + "\nToken: " + tokenScanner2.nextLine() + "\nBalance: " + (j.getDouble("balance")*0.01) + " μBTC\nKey: " + keyScanner.nextLine() + "\n====================");
			
			keyScanner.close();
			userScanner.close();
			tokenScanner2.close();
			
			System.out.println("Enter: start/features/settings/upgrade/advancedbot");
		}
		askChoice();
		in.close();
	}
	public static void askChoice() throws IOException{
		Scanner in = new Scanner(System.in);
		Scanner tokenScanner1 = new Scanner(tokenFile, "UTF-8");
		String token1 = tokenScanner1.next();
		tokenScanner1.close();
		URL user1 = new URL("https://api.primedice.com/api/users/1?access_token=" + token1);
		HttpURLConnection conn = (HttpURLConnection) user1.openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		
		BufferedReader in1 = new BufferedReader(
		        new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in1.readLine()) != null) {
			response.append(inputLine);
		}
		in1.close();
		
		String result = response.toString();
		
		JSONObject j = new JSONObject(result).getJSONObject("user");
		
		String choice = in.next();
		if(choice.equalsIgnoreCase("start")){
			System.out.println("Attempting to start betting bot.");
			Scanner tokenScanner = new Scanner(tokenFile, "UTF-8");
			Scanner betAmountScanner = new Scanner(betAmountFile, "UTF-8");
			Scanner betTargetScanner = new Scanner(betTargetFile, "UTF-8");
			Scanner betConditionScanner = new Scanner(betConditionFile, "UTF-8");
			Scanner waitScanner = new Scanner(waitFile, "UTF-8");
			
			String url = "https://api.primedice.com/api/bet?access_token=" + tokenScanner.next();
			startBot(url, betAmountScanner.next(), betTargetScanner.next(), betConditionScanner.next(), waitScanner.nextInt());
			
			tokenScanner.close();
			betAmountScanner.close();
			betTargetScanner.close();
			betConditionScanner.close();
			waitScanner.close();
		} else if(choice.equalsIgnoreCase("features")){
			System.out.println("[Info] Please view our BitcoinTalk.org thread for more info.");
			askChoice();
		} else if(choice.equalsIgnoreCase("settings")){
			System.out.println("Resetting settings.\nEnter token: ");
			
			BufferedWriter outputToken1 = new BufferedWriter(new FileWriter(tokenFile));
	        String token = in.next();
	        tokenFile.delete();
	        tokenFile.createNewFile();
	        outputToken1.write(token);
	        outputToken1.close();
	        System.out.println("Accepted. Input bet amount: ");
	        BufferedWriter outputBetAmount1 = new BufferedWriter(new FileWriter(betAmountFile));
	        betAmountFile.delete();
	        betAmountFile.createNewFile();
	        outputBetAmount1.write(in.next());
	        outputBetAmount1.close();
	        System.out.println("Accepted. Input bet target (under so-and-so or over-so-and-so; enter the number only.): "); 
	        BufferedWriter outputBetTarget1 = new BufferedWriter(new FileWriter(betTargetFile));
	        betTargetFile.delete();
	        betTargetFile.createNewFile();
	        outputBetTarget1.write(in.next());
	        outputBetTarget1.close();
	        System.out.println("Accepted. Bet on less than or more than target? Input < or >. ");
	        BufferedWriter outputBetCondition1 = new BufferedWriter(new FileWriter(betConditionFile));
	        betConditionFile.delete();
	        betConditionFile.createNewFile();
	        outputBetCondition1.write(in.next());
	        outputBetCondition1.close();
	        System.out.println("Accepted. Input wait time between bets in milliseconds.");
	        BufferedWriter outputWaitTime1 = new BufferedWriter(new FileWriter(waitFile));
	        String z = in.next();
	        
	        outputWaitTime1.write(z);
	        outputWaitTime1.close();
	        System.out.println("Accepted. Settings reset complete and settings have been saved.");
	        askChoice();
		} else if(choice.equalsIgnoreCase("upgrade")){
			if(j.getDouble("balance") >= 50001){
				tip("VortexiaBank", 50001);
				System.out.println("[Info] Successfully upgraded. Now using PD3BOT-CmdLine+.");
				System.out.println("Email your key to **chicken.jockey@aim.com**. We will process your request and will add your key to the authorized keys list within 24 hours.");
				askChoice();
			} else {
				System.out.println("[Error] Not enough money. Upgrading costs 0.0005 BTC.");
				askChoice();
			}
		} else if(choice.equalsIgnoreCase("advancedbot")){
			Scanner keyScanner = new Scanner(keyFile, "UTF-8");
			if(userIsPro(keyScanner.nextLine())){
				
				AdvancedBot bot = new AdvancedBot();
				bot.bot();
			} else {
				System.out.println("[Error] You must upgrade to use the advanced bot! Enter \"upgrade\".");
				askChoice();
			}
			keyScanner.close();
		}
		in.close();
	}
	public static boolean userIsPro(String key) throws IOException{
		URL url = new URL("https://dl.dropboxusercontent.com/sh/q99d72aiqa4nbpk/AADDJMiJx8HnMGeRvI0PMonqa/keys.txt");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.connect();
		
		BufferedReader reader = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine1;
		StringBuffer response1 = new StringBuffer();
 
		while ((inputLine1 = reader.readLine()) != null) {
			response1.append(inputLine1);
		}
		
		String result1 = response1.toString();
		String[] authedKeys = new String[] {result1};
		
		for(String key1 : authedKeys){
			if(key1.equals(key)){
				return true;
			}
		}
		return false;
	}
	public static void getToken() throws IOException{
		if(tokenFile.exists()){
			tokenFile.delete();
			tokenFile.createNewFile();
		}
		URL url = new URL("https://api.primedice.com/api/login");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestProperty("User-Agent", "PD3BOT-CmdLine by Vortex20000");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		
		Scanner userScanner = new Scanner(userFile, "UTF-8");
		Scanner passScanner = new Scanner(passFile, "UTF-8");
 
		String urlParameters = "username=" + userScanner.next() + "&password=" + passScanner.next();
		
		userScanner.close();
		passScanner.close();
		
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
		
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		
		String result = response.toString();
		
		int x = con.getResponseCode();
		
		if(!(x == 200)){
			System.out.println("[Error] Could not fetch token!");
		}
		
		JSONObject j = new JSONObject(result);
		String token = j.getString("access_token");
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(tokenFile));
		bw.write(token);
		bw.close();
		
		
	}
	public static void tip(String s, int tip) throws IOException{
		Scanner tokenScanner = new Scanner(tokenFile, "UTF-8");
		String token = tokenScanner.nextLine();
		URL x = new URL("https://api.primedice.com/api/tip?access_token=" + token);
		tokenScanner.close();
		HttpURLConnection con = (HttpURLConnection) x.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "PD3BOT-CmdLine by Vortex20000");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
 
		String urlParameters = "username=" + s + "&amount=" + tip;
 
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
		
		int responseCode = con.getResponseCode();
		
		if(responseCode == 200){
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			
		} else {
			System.out.println("[Error] Insufficient funds (you neen 0.0005 BTC in your account) or error occured.");
			System.out.println("Server response: " + responseCode);
			
		}
	}
	
	
	public static void startBot(String url, String betAmount, String betTarget, String betCondition, int waitTime){
		new Thread(new Runnable(){

			@Override
			public void run() {
				while(true) {
					try {
						sendPost(url, betAmount, betTarget, betCondition);
					} catch (Exception e) {
						System.out.println("[Error] Unexpected error. Please report this error to the author of this application." );
						e.printStackTrace();
					}
					try {
						Thread.sleep((long) waitTime);
					} catch (InterruptedException e) {
						System.out.println("[Error] Unexpected error. Please report this error to the author of this application." );
						e.printStackTrace();
					}
					
				}
				
			}
			
		}).run();
	}
	
	
	public static String getDir(){
		return (System.getProperty("user.home") + File.separator + ".pd3bot" + File.separator);
	}
	
	public static void sendPost(String url, String betAmount, String betTarget, String betCondition) throws Exception {
		 
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", "PD3BOT-CmdLine by Vortex20000");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
 
		String urlParameters = "amount=" + betAmount + "&target=" + betTarget + "&condition=" + betCondition;
 
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
 
		int responseCode = con.getResponseCode();
		
		if(responseCode == 200){
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			String result = response.toString();
			
			JSONObject object = new JSONObject(result);
			
			if(object.getJSONObject("bet").getBoolean("win")){
				sessProfit = sessProfit + object.getJSONObject("bet").getDouble("profit");
				
				System.out.println("W | Balance: " + (object.getJSONObject("user").getDouble("balance")*0.01) + " μBTC | Session Profit: " + sessProfit*0.01 + " μBTC");
			} else {
				sessProfit = sessProfit + object.getJSONObject("bet").getDouble("profit");
				
				System.out.println("L | Balance: " + (object.getJSONObject("user").getDouble("balance")*0.01) + " μBTC | Session Profit: " + sessProfit*0.01 + " μBTC");
			}
		} else if(responseCode == 400){
			System.out.println("Insufficient funds!");
		}
	}
}
