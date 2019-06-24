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



public class MultiServer extends Client1 {

	private JFrame frame;
	static JTextField MessageText;

	
	//static JTextArea MessageArea1;
	private JScrollPane scrollPane;
	public static int PortNum=12001;
	
	
	
	public static void main(String[] args) {
	/*Client1 c1=new Client1();
	Client1 c2=new Client1();*/
		Thread c1=new Thread(new Client1());
		Thread c2=new Thread(new Client2());
	
c1.start();
c2.start();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MultiServer window = new MultiServer();
					window.frame.setVisible(true);
					
					
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null,e);
				}
			}         
		});
		
	
	
	}
		
		
	
	public MultiServer() {
		
		initialize();
				
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("   MultiServer for 2 Clients ");
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
