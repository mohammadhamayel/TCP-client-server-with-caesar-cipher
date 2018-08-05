package tcpnets;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JOptionPane;

public class TCPClient {
	public static void main(String[] args) throws Exception{
		while(true) {
			Socket clientSocket = new Socket("localhost", 9999);//connect with server
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());//send to server
			//System.out.println("Enter Your Message: ");
			//BufferedReader inputFromUser = new BufferedReader(new InputStreamReader(System.in));// get user message in console
			//String clientSentence = inputFromUser.readLine(); //read from console
			
			String clientSentence = JOptionPane.showInputDialog( //show dialog to get input from user
		            "Enter Your Message:");
					
			outToServer.writeBytes(clientSentence +"\n"); //write client message on serverSocket
			
			BufferedReader inputFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			String answer = inputFromServer.readLine(); //get message from server
			//System.out.println("Client from server: "+answer); // print in consle
			JOptionPane.showMessageDialog(null, "Response From Server: "+answer);
			clientSocket.close();
			String checkend = JOptionPane.showInputDialog( //show dialog to get input from user
		            "Do you want to continue ?? Y|N");
			if(checkend.toLowerCase().equals("n")) {
				break;
			}
		}
		
	}
}
