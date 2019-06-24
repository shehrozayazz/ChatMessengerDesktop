
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
public class Client2  extends Client1 implements Runnable {
	
	
		
	
	
	
	Client2(){
		try {
			
		
		ss= new ServerSocket(PortNum); 
		JOptionPane.showMessageDialog(null,"  Waiting for cleint  to connect !");
				
				s1= ss.accept();
				
				JOptionPane.showMessageDialog(null," Client  is connect !");
		}catch(Exception e) {
			
		}
	}
	
	 public void run() {
	try {
		String msgin1="";
		dout=new DataOutputStream(s.getOutputStream());
		 cin= new DataInputStream(s1.getInputStream());
    		try {
    			 while(!msgin1.equals("exit")){
    		    		Thread.sleep(300);
    					msgin1=cin.readUTF();
    					if(j>0) {
    						MessageArea1.setText(MessageArea1.getText().trim()+"\n "+Clt2+" :  "+msgin1); // Displaying the message  from the client
    								
    						dout.writeUTF(msgin1);
    					
    						}
    						else if(msgin1.endsWith(".")&& i==0) {
    							
    							Clt2=msgin1;
    							j++;
    							dout.writeUTF(msgin1);
    							
    						}
    					
    				}
    			
    			
    		
		
       
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,e);
		}
	}catch(Exception e ) {
		
	}
	 }
	 }
