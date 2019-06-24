import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.io.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.Scrollbar;
import java.awt.Color;

public class clientN extends Thread {

	private JFrame frame;
	static JTextField MessageText;

	
	static Socket s;
	static  DataInputStream din;
	static DataOutputStream  dout;
	static JTextArea MessageArea1;
	static JScrollPane scrollPane;
	static String Client1,sname,PN;
	static int PortNum;
	private JButton NameButton;
	static int i=0,k=0;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client1=JOptionPane.showInputDialog("Enter Your  name : ");
					sname=Client1; 
					clientN window = new clientN();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		String msgin="";		
		
		
		try {
			
			
			
			
			
			PN=JOptionPane.showInputDialog("Enter the IP address number : ");
			//PortNum=Integer.parseInt(PN);
			s= new Socket(PN,12001); 
			JOptionPane.showMessageDialog(null," Connected to the server ");
			din= new DataInputStream(s.getInputStream());
			dout=new DataOutputStream(s.getOutputStream());
			
			while(!msgin.equals("exit")) {
				msgin=din.readUTF();
				if (msgin.endsWith(".")&&k==0) {
					Client1=msgin;
					k++;
					continue;
				}
				MessageArea1.setText(MessageArea1.getText().trim()+ "\n"+ Client1+" :  "+msgin); // Displaying the message  from the client 2
				
				 
			}
		}catch(IOException e) {
			JOptionPane.showMessageDialog(null,e+"Error! No connection found!");
			System.exit(0);
		}

	}

	
	public clientN() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame(Client1);
		frame.setBounds(100, 100, 635, 414);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		MessageText = new JTextField();
		MessageText.setFont(new Font("Tahoma", Font.PLAIN, 17));
		MessageText.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		MessageText.setBounds(10, 308, 500, 64);
		frame.getContentPane().add(MessageText);
		MessageText.setColumns(10);
		
		
		//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
		

		JButton NameButton = new JButton("NAME");
		NameButton.setBackground(new Color(204, 204, 204));
		frame.getContentPane().add(NameButton);
		NameButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		NameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String msgout="";
					
					if(i==0)
					{
						sname+=".";
						dout.writeUTF(sname);
						NameButton.setVisible(false);
						i++;
						
					}
					}
					catch(IOException e) {
						JOptionPane.showMessageDialog(null,e+"Error! The connection has lost!");

					}
			}
		});
		NameButton.setBounds(520, 311, 100, 56);
		
		

		//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
		JButton SendButton = new JButton("SEND");
		SendButton.setBackground(new Color(204, 204, 204));
		SendButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		SendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String msgout="";
					if(i>0) {
				msgout=	MessageText.getText().trim();   
				dout.writeUTF(msgout);
				
				MessageArea1.setText(MessageArea1.getText()+"\n"+" Me"+" : "+msgout);
				
				
				MessageText.setText("");
					}
					else 
					{
						sname+=".";
						dout.writeUTF(sname);
						i++;
						
					}
					}
					catch(IOException e) {
						JOptionPane.showMessageDialog(null,e+"Error! The connection has lost!");

					}
			}
		});
		SendButton.setBounds(520, 311, 100, 56);
		frame.getContentPane().add(SendButton);
		
		MessageArea1 = new JTextArea();
		MessageArea1.setLocation(1, 2);
		MessageArea1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		//MessageArea1.setBounds(10, 11, 602, 289);
		frame.getContentPane().add(MessageArea1);
		

		scrollPane = new JScrollPane(MessageArea1);
		scrollPane.setBounds(10, 11, 602, 289);
		frame.getContentPane().add(scrollPane);
		
	
	
		
		
	}
}
