package crapPack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadFromConsole {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader cons = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("Enter some shit: ");
			String capture = cons.readLine();
			System.out.println("Copy of your shit: " + capture);
		} catch (IOException e) {
			System.err.println("Poor reader.");
			e.printStackTrace();
		}

	}

}
