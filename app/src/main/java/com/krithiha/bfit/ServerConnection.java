package com.krithiha.bfit;

import android.content.Context;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerConnection {
	Socket s;
	BufferedReader in;
	PrintWriter out;
	Context con;

	public ServerConnection(String ipaddredss,Context con) {

		this.con=con;
		try {
			s = new Socket(ipaddredss, 55);
			in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			out = new PrintWriter(s.getOutputStream(), true);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void sendDate(String data) {
		
		try {
			out.println(data);
		} catch (Exception e) {
			//out.println("-0.1155,5.1244,1.2554");
			
			Toast.makeText(this.con,"\nError in Sending Data\n", Toast.LENGTH_SHORT).show();
		}
		
	}
}
