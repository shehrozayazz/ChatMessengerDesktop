package Messenger;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.io.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.Scrollbar;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;


public class server{

	private JFrame frame;
	static JTextField MessageText;

	static ServerSocket ss;
	static Socket s,s1;
	static  DataInputStream din,cin;
	static DataOutputStream  dout,cout;
	static JTextArea MessageArea1;
	private JScrollPane scrollPane;
	private static int PortNum=12001;
	public static String Client1;
	public static String Client2;
	static int i=0,j=0;
	
	public static void main(String[] args) {
	
		 
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					server window = new server();
					window.frame.setVisible(true);
					
					
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,e);
				}
			}         
		});
		
	
		
	try {
		String msgin="";
		ss= new ServerSocket(PortNum); 
		JOptionPane.showMessageDialog(null,"  Waiting for cleint 1 to connect !");
		
		s= ss.accept();
		
		JOptionPane.showMessageDialog(null," Client1  is connect !");
		
		String msgin1="";
		JOptionPane.showMessageDialog(null,"  Waiting for cleint 2 to connect !");
		s1= ss.accept();
		JOptionPane.showMessageDialog(null," Client2  is connect !");
		        	
		din= new DataInputStream(s.getInputStream());
		
		dout=new DataOutputStream(s.getOutputStream());
	
        cin= new DataInputStream(s1.getInputStream());
		
		cout=new DataOutputStream(s1.getOutputStream());
		// TIC TAC TOE 
		
		
		while(!msgin.equals("exit")){
			
			msgin=din.readUTF();
			cout.writeUTF(msgin);
			msgin1=cin.readUTF();
			dout.writeUTF(msgin1);
		}
		// TIC TAC TOE ENDS 
	
		/*while(!msgin.equals("exit")){
		
			msgin=din.readUTF();
			
			if(i>0) {
			MessageArea1.setText(MessageArea1.getText().trim()+"\n "+Client1+" :  "+msgin); // Displaying the message  from the client
					
			cout.writeUTF(msgin);
		
			}
			else if(msgin.endsWith(".")&& i==0) {
				
				Client1=msgin;
				i++;
				cout.writeUTF(msgin);
				
			}
			msgin1=cin.readUTF();
			if(j>0) {
				MessageArea1.setText(MessageArea1.getText().trim()+"\n "+Client2+" :  "+msgin1); // Displaying the message  from the client
						
				dout.writeUTF(msgin1);
			
				}
				else if(msgin1.endsWith(".")&& j==0) {
					
					Client2=msgin1;
					j++;
					dout.writeUTF(msgin1);
					
				}
			
		}*/
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,e);
		}
		
		
		
	}
		
		
	
	public server() {
		
		initialize();
				
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("   Server for 2 Clients ");
		frame.setBounds(100, 100, 634, 425);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		MessageText = new JTextField();
		MessageText.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
		MessageText.setBounds(10, 311, 500, 64);
		frame.getContentPane().add(MessageText);
		MessageText.setColumns(10);
		
		JButton SendButton = new JButton("SEND");
		SendButton.setBackground(new Color(255, 228, 181));
		SendButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		SendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String msgout="";
					
					
				msgout=	MessageText.getText().trim();
				
				dout.writeUTF(msgout);
				MessageArea1.setText(MessageArea1.getText()+"\n"+" Me"+" : "+msgout);
				
				
				MessageText.setText("");
					}
					catch(IOException e) {
						JOptionPane.showMessageDialog(null,e+"  Error! Something went wrong, or the client isn't connected!");
					}
			}
		});
		SendButton.setBounds(515, 311, 100, 56);
		frame.getContentPane().add(SendButton);
		
		
		
		MessageArea1 = new JTextArea();
		
		MessageArea1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		//MessageArea1.setBounds(10, 11, 600, 289);
		frame.getContentPane().add(MessageArea1);
		
		scrollPane = new JScrollPane(MessageArea1);
		scrollPane.setBounds(10, 11, 600, 289);
		frame.getContentPane().add(scrollPane);
		
	}
}
