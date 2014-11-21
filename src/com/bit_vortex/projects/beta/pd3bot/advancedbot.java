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

import org.json.JSONObject;

public class AdvancedBot {
	
	public File dir = new File((System.getProperty("user.home") + File.separator + ".pd3bot" + File.separator + "advanced" + File.separator + "bot"));
	Main m = new Main();
	@SuppressWarnings("static-access")
	public File mainDir = new File(m.getDir());
	public File tokenFile = new File(mainDir + File.separator + "token.txt");
	public File botNameFile = new File(dir + File.separator + "botname.txt");
	public File onLoseFile = new File(dir + File.separator + "onlose.txt");
	public File onWinFile = new File(dir + File.separator + "onwin.txt");
	public File loseIncFile = new File(dir + File.separator + "loseinc.txt");
	public File winIncFile = new File(dir + File.separator + "wininc.txt");
	public File alternateFile = new File(dir + File.separator + "alternate.txt");
	public File enableBetIncFile = new File(dir + File.separator + "enablebetinc.txt");
	public File betIncFile = new File(dir + File.separator + "betinc.txt");
	public File betIncIntervalFile = new File(dir + File.separator + "betincinterval.txt");
	public File betAmountFile = new File(mainDir + File.separator + "betamount.txt");
	public File betTargetFile = new File(mainDir + File.separator + "bettarget.txt");
	public File betConditionFile = new File(mainDir + File.separator + "betcondition.txt");
	public File sessProfitFile = new File(dir + File.separator + "sessprofit.txt");
	public File logFile = new File(System.getProperty("user.dir") + File.separator + "log.txt");
	
	
	
	
	public void bot() throws IOException{
		System.out.println("Loading...");
		new Thread(new Runnable(){

			@Override
			public void run() {
				try {
					if(!checkFiles()){
						makeFiles();
						makeSettings(false);
					} else {
						askChoice();
					}
				} catch (IOException e) {
					
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}).run();
		
		sessProfitFile.delete();
		sessProfitFile.createNewFile();
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(sessProfitFile));
		bw.write("0.0");
		bw.close();
		
	}
	
	@SuppressWarnings("static-access")
	public void makeSettings(boolean delete) throws Exception{
		Scanner in = new Scanner(System.in);
		
		if(delete){
			botNameFile.delete();
			onLoseFile.delete();
			onWinFile.delete();
			loseIncFile.delete();
			winIncFile.delete();
			alternateFile.delete();
			enableBetIncFile.delete();
			betIncFile.delete();
			betIncIntervalFile.delete();
			makeFiles();
		}
		
		
		
		print("Enter your desired bot name! With the Advanced Bot, you can name your bot!");
		String botName = in.nextLine();
		BufferedWriter botNameOutput = new BufferedWriter(new FileWriter(botNameFile));
		botNameOutput.write(botName);
		botNameOutput.close();
		
		print("Do you want to increase bet on lose or keep the bet the same? Enter \"increase\" or \"keep\".");
		BufferedWriter onLoseOutput = new BufferedWriter(new FileWriter(onLoseFile));
		String onLose = in.next();
		if(!onLose.equalsIgnoreCase("increase")){
			if(!onLose.equalsIgnoreCase("keep")){
				print("[Error] You entered something different! Bot restarting...");
				onLoseOutput.close();
				Main m = new Main();
				m.main(null);
			} else {
				onLoseOutput.write("keep");
				onLoseOutput.close();
			}
		} else {
			userWantsLoseInc();
			onLoseOutput.write("increase");
			onLoseOutput.close();
		}
		
		print("Do you want to increase bet on lose or keep the bet the same? Enter \"increase\" or \"keep\".");
		BufferedWriter onWinOutput = new BufferedWriter(new FileWriter(onWinFile));
		String onWin = in.next();
		if(!onWin.equalsIgnoreCase("increase")){
			if(!onWin.equalsIgnoreCase("keep")){
				print("[Error] You entered something different! Bot restarting...");
				onWinOutput.close();
				Main m = new Main();
				m.main(null);
			} else {
				onWinOutput.write("keep");
				onWinOutput.close();
				continueSettings();
			}
		} else {
			userWantsWinInc();
			onWinOutput.write("increase");
			onWinOutput.close();
			continueSettings();
		}
		in.close();
	}
	
	@SuppressWarnings("static-access")
	public void continueSettings() throws Exception{
		Scanner in = new Scanner(System.in);
		
		print("Do you want to increase the bet after a certain amount of bets? (y/n)");
		String yesno1 = in.nextLine();
		if(yesno1.equalsIgnoreCase("y")){
			userWantsToIncBet();
			continueSettings1();
		} else if(yesno1.equalsIgnoreCase("n")){
			continueSettings1();
		} else {
			print("[Error] You entered something different! Bot restarting...");
			Main m = new Main();
			m.main(null);
		}
		in.close();
	}
	
	@SuppressWarnings("static-access")
	public void continueSettings1() throws Exception{
		Scanner in = new Scanner(System.in);
		
		BufferedWriter ynOutput = new BufferedWriter(new FileWriter(alternateFile));
		
		print("=====================\nWARNING\n====================\n\nDo you want to alternate betting HI/LO? (y/n)");
		String yesno = in.nextLine();
		if(yesno.equalsIgnoreCase("y")){
			ynOutput.write("y");
			ynOutput.close();
			print("[Info] Settings finished.");
			askChoice();
		} else if(yesno.equalsIgnoreCase("n")){
			ynOutput.write("n");
			ynOutput.close();
			print("[Info] Settings finished.");
			askChoice();
		} else {
			print("[Error] You entered something different! Bot restarting...");
			Main m = new Main();
			m.main(null);
		}
		
		in.close();
	}
	
	public void askChoice() throws Exception{
		Scanner in = new Scanner(System.in);
		
		print("Enter: start/settings");
		
		String choice = in.nextLine();
		
		if(choice.equalsIgnoreCase("start")){
			startBot();
		} else if(choice.equalsIgnoreCase("settings")){
			makeSettings(true);
			askChoice();
		}
		in.close();
	}
	
	public void startBot(){
		new Thread(new Runnable(){

			@Override
			public void run() {
				while(true){
					try {
						bet();
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
			
		}).run();
	}
	public void bet() throws IOException{
		BufferedWriter log = new BufferedWriter(new FileWriter(logFile));
		Scanner tokenScanner = new Scanner(tokenFile, "UTF-8");
		Scanner betAmountScanner = new Scanner(betAmountFile, "UTF-8");
		Scanner betTargetScanner = new Scanner(betTargetFile, "UTF-8");
		Scanner betConditionScanner = new Scanner(betConditionFile, "UTF-8");
		
		String token = tokenScanner.nextLine();
		URL url = new URL("https://api.primedice.com/api/bet?access_token=" + token);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		String urlParams = "amount=" + betAmountScanner.nextLine() + "&target=" + betTargetScanner.next() + "&condition=" + betConditionScanner.next();
		
		betAmountScanner.close();
		betTargetScanner.close();
		betConditionScanner.close();
		tokenScanner.close();
		
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParams);
		wr.flush();
		wr.close();
		
		int resCode = con.getResponseCode();
		if(resCode == 200){
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			String result = response.toString();
			
			Scanner botNameScanner = new Scanner(botNameFile, "UTF-8");
			
			String name = botNameScanner.nextLine();
			
			JSONObject j = new JSONObject(result);
			Scanner sessProfitScanner = new Scanner(sessProfitFile, "UTF-8");
			double sessProfit = Double.parseDouble(sessProfitScanner.nextLine());
			sessProfitScanner.close();
			
			boolean win = j.getJSONObject("bet").getBoolean("win");
			if(win){
				log.append("[" + name + ":Info] Bet " + (j.getJSONObject("bet").getInt("amount")*0.01) + " μBTC on " + j.getJSONObject("bet").getString("condition") + " " + j.getJSONObject("bet").getInt("target") + ". Rolled " + j.getJSONObject("bet").getDouble("roll") + " and won " + (j.getJSONObject("bet").getDouble("profit")*0.01) + " μBTC.");
				System.out.println("[" + name + ":Info] Bet " + (j.getJSONObject("bet").getInt("amount")*0.01) + " μBTC on " + j.getJSONObject("bet").getString("condition") + " " + j.getJSONObject("bet").getInt("target") + ". Rolled " + j.getJSONObject("bet").getDouble("roll") + " and won " + (j.getJSONObject("bet").getDouble("profit")*0.01) + " μBTC.");
				sessProfit = sessProfit + j.getJSONObject("bet").getDouble("profit")*0.01;
				BufferedWriter sessProfitOutput = new BufferedWriter(new FileWriter(sessProfitFile));
				sessProfitFile.delete();
				sessProfitFile.createNewFile();
				sessProfitOutput.write(String.valueOf(sessProfit));
				sessProfitOutput.close();
				System.out.println("[" + name + ":Info] Session profit: " + sessProfit + " μBTC");
			} else {
				log.append("[" + name + ":Info] Bet " + (j.getJSONObject("bet").getInt("amount")*0.01) + " μBTC on " + j.getJSONObject("bet").getString("condition") + " " + j.getJSONObject("bet").getInt("target") + ". Rolled " + j.getJSONObject("bet").getDouble("roll") + " and lost " + (j.getJSONObject("bet").getDouble("profit")*(-0.01)) + " μBTC.");
				System.out.println("[" + name + ":Info] Bet " + (j.getJSONObject("bet").getInt("amount")*0.01) + " μBTC on " + j.getJSONObject("bet").getString("condition") + " " + j.getJSONObject("bet").getInt("target") + ". Rolled " + j.getJSONObject("bet").getDouble("roll") + " and lost " + (j.getJSONObject("bet").getDouble("profit")*(-0.01)) + " μBTC.");
				sessProfit = sessProfit + j.getJSONObject("bet").getDouble("profit")*-0.01;
				BufferedWriter sessProfitOutput = new BufferedWriter(new FileWriter(sessProfitFile));
				sessProfitFile.delete();
				sessProfitFile.createNewFile();
				sessProfitOutput.write(String.valueOf(sessProfit));
				sessProfitOutput.close();
				System.out.println("[" + name + "] Session profit: " + sessProfit + " μBTC");
			}
			botNameScanner.close();
		} else {
			Scanner botNameScanner = new Scanner(botNameFile, "UTF-8");
			String name = botNameScanner.nextLine();
			log.append("[" + name + ":Error] Tried to bet and was unable to bet. Insufficient funds or no internet connection.");
			System.out.println("[" + name + ":Error] Insufficient funds or internet fail.");
			botNameScanner.close();
		}
		log.close();
	}
	
	public void userWantsToIncBet() throws IOException{
		Scanner in = new Scanner(System.in);
		
		print("By how many satoshis do you want to increase the bet?");
		int satoshis = in.nextInt();
		print("After how many bets should we increase the bet by " + satoshis + " satoshis?");
		int bets = in.nextInt();
		
		BufferedWriter betIncOutput = new BufferedWriter(new FileWriter(betIncFile));
		BufferedWriter betIncIntervalOutput = new BufferedWriter(new FileWriter(betIncIntervalFile));
		betIncOutput.write(satoshis);
		betIncIntervalOutput.write(bets);
		betIncOutput.close();
		betIncIntervalOutput.close();
		
		in.close();
	}
	
	public void userWantsLoseInc() throws IOException{
		Scanner in = new Scanner(System.in);
		print("By how much percentage should we increase the bet by? Enter the number without the % sign.");
		double pcent = in.nextDouble();
		
		if(pcent > 0.0){
			BufferedWriter loseIncOutput = new BufferedWriter(new FileWriter(loseIncFile));
			loseIncOutput.write(String.valueOf(pcent));
			loseIncOutput.close();
		}
		
		in.close();
	}
	
	public void userWantsWinInc() throws IOException{
		Scanner in = new Scanner(System.in);
		print("By how much percentage should we increase the bet by? Enter the number without the % sign.");
		double pcent = in.nextDouble();
		
		if(pcent > 0.0){
			BufferedWriter winIncOutput = new BufferedWriter(new FileWriter(winIncFile));
			winIncOutput.write(String.valueOf(pcent));
			winIncOutput.close();
		}
		
		in.close();
	}
	
	public void print(String s){
		System.out.println(s);
	}
	
	public void makeFiles() throws IOException{
		if(!dir.exists()){
			dir.mkdirs();
		}
		if(!onLoseFile.exists()){
			onLoseFile.createNewFile();
		}
		if(!onWinFile.exists()){
			onWinFile.createNewFile();
		}
		if(!loseIncFile.exists()){
			loseIncFile.createNewFile();
		}
		if(!winIncFile.exists()){
			winIncFile.createNewFile();
		}
		if(!alternateFile.exists()){
			alternateFile.createNewFile();
		}
		if(!enableBetIncFile.exists()){
			enableBetIncFile.createNewFile();
		}
		if(!betIncFile.exists()){
			betIncFile.createNewFile();
		}
		if(!betIncIntervalFile.exists()){
			betIncIntervalFile.createNewFile();
		}
		if(!botNameFile.exists()){
			botNameFile.createNewFile();
		}
		if(!logFile.exists()){logFile.createNewFile();}
		
		if(!sessProfitFile.exists()){sessProfitFile.createNewFile();}
	}
	
	public boolean checkFiles() throws IOException{
		if(dir.exists()){
			return true;
		}
		return false;
	}
}
