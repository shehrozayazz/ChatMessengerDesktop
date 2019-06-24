
package Messenger;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
public class Client1 implements Runnable {
	
	
		
	static ServerSocket ss;
	static Socket s,s1;
	static  DataInputStream din,cin;
	static DataOutputStream  dout,cout;
	static JTextArea MessageArea1;
	
	public static int PortNum=12001;
	public static String Clt1,Clt2;
	//public static String Client2;
	static int i=0,j=0;
	
	
	Client1(){
		try {
			
		
		ss= new ServerSocket(PortNum); 
		JOptionPane.showMessageDialog(null,"  Waiting for cleint  to connect !");
				
				s= ss.accept();
				
				JOptionPane.showMessageDialog(null," Client  is connect !");
		}catch(Exception e) {
			
		}
	}
	
	 public void run() {
	try {
		String msgin="";
		
		
	
        din= new DataInputStream(s.getInputStream());
        cout=new DataOutputStream(s1.getOutputStream());
    	
       
       
    		try {
    			 while(!msgin.equals("exit")){
    		    		Thread.sleep(300);
    					msgin=din.readUTF();
    					if(i>0) {
    						MessageArea1.setText(MessageArea1.getText().trim()+"\n "+Clt1+" :  "+msgin); // Displaying the message  from the client
    								
    						cout.writeUTF(msgin);
    					
    						}
    						else if(msgin.endsWith(".")&& i==0) {
    							
    							Clt1=msgin;
    							i++;
    							cout.writeUTF(msgin);
    							
    						}
    					
    				}
    			
    			
    		
		
       
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,e);
		}
	}catch(Exception e ) {
		
	}
	 }
	 }
