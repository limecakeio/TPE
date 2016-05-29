package iFileEncryptor;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Richard Vladimirskij
 * @author Konstantin Pelevin
 */

public class IFileMenu {
	public static final String lineDiv = "\n------------------------------------------------------------------------------------------------------------------------------------\n";
	public static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {	
		boolean exit = false;
		int sel = 0;
		int key = 0;
		String directory = "";
		IFileEncryptor crypter = new CaesarFileEncryptor(0);
		
		System.out.println("Welcome to the Caesar File Encryptor!");
		while(!exit) {
			boolean success = false;
			boolean keySet = false;
			boolean directorySet = false;
			System.out.print(lineDiv + "Please enter: [1] To create a new encrypted directory 	[2] To create a new decrypted directory 	[3] To exit the programm" + lineDiv + "Selection: ");
			sel = in.nextInt();
			
			//Loop through the options in order to catch certain exceptions which we can fix using a dialog.
			while(!success) {
				try {
					//Both encrypt and decrypt share most of the same set-up
					if(sel == 1 || sel == 2) {
						if(!keySet) {
							key = getKeyFromUser();
							crypter = new CaesarFileEncryptor(key);
							keySet = true;
						}
						if(!directorySet) {
							directory = getDirectoryFromUser();
						}
						
						if(sel == 1) {
							File eResult = crypter.encrypt(new File(directory));
							directorySet = true;
							encryptorSuccess(eResult);
						}
						else {
							File dResult = crypter.decrypt(new File(directory));
							directorySet = true;
							encryptorSuccess(dResult);
						}
					}
					else if (sel == 3) {
						System.out.println("Thank you for using the Caesar File Encryptor, Goodbye!");
						exit = true;		
					}
					else {
						throw new InputMismatchException();
					}
					
					success = true;
				
				}
				catch(InputMismatchException e) {
					in.nextLine();
					System.err.println("Please enter a number between 1 and 3 to continue.");
				} 
				catch (InvalidDirectoryException e) {
					System.err.println("The path you have entered is not a valid directory. Please try again.");
				} 
				catch (IOException e) {
					System.err.println("An error has occured while trying to read/write the file. ");
					exit = true;
					success = true;
					e.printStackTrace();
				}
			}
		}
	}
	
	private static String getDirectoryFromUser() {
		String dir = "";
		System.out.print(lineDiv + "Please enter the path to the directory you would like to decrypt/encrypt. "
				+ lineDiv + "Path: ");
		dir = in.next();
		return dir;
	}
	
	private static int getKeyFromUser() {
		int key = 0;
		boolean success = false;
		while (!success) {
			try {
				System.out.print(lineDiv + "Please enter the encryption key for the directory you'd like to decrypt/encrypt. "
						+ lineDiv + "Key: ");
				key = in.nextInt();
				success = true;
			}
			catch(InputMismatchException e) {
				System.err.println("Only numbers are allowed for keys (negative and positive), please try again. ");
				in.nextLine();
			}
		}
		return key;
	}

	private static void encryptorSuccess(File f) {
		System.out.println("Caesar File Encryptor SUCCESS! \n"
				+ "Successfully created the directory: " + f.getName() + "\n"
				+ "The directory can be found by navigating to: " + f.getAbsolutePath());
	}
}