package tcpnets;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
	public static void main(String[] args) throws Exception {
		ServerSocket serversocket = new ServerSocket(9999); //define server with port # 9999
		System.out.println("welcome, Server is ready to receive message");
		try {
			while(true)	{
				Socket connectionSocket = serversocket.accept(); // accept connection with client
				 BufferedReader massageFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
				 String clientMassage = massageFromClient.readLine();// get connection input and and read Client message
				 
				 System.out.println("Receive: "+clientMassage);
				 
				 PrintWriter out = new PrintWriter(connectionSocket.getOutputStream(), true); //modify on client message 
	                 out.println(encrypt(clientMassage, 3)); // call encryption function and send it to client 
	                 connectionSocket.close();
	                 
			}
			
		} finally {
			serversocket.close();
		}
		
	}
	public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";//all characters in alphabet
	//any other characters replaced by c here or depends on shift from zero to reach the shift number
	 
    public static String encrypt(String plainText, int shiftKey) // encryption function to Caesar Cipher
    {
        plainText = plainText.toLowerCase();
        String cipherText = "";
        for (int i = 0; i < plainText.length(); i++)
        {
            int charPosition = ALPHABET.indexOf(plainText.charAt(i));
            int keyVal = (shiftKey + charPosition) % 26; // take MOD(%) to restart from 0 when shift value grater than 26 like x,y and z
            char replaceVal = ALPHABET.charAt(keyVal);//start do encryption 
            cipherText += replaceVal; // cipher text
        }
        return cipherText;
    }

}
