import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.Scrollbar;

public class Client2 extends Thread {

	private JFrame frame;
	static JTextField MessageText1;

	
	static Socket s1;
	static  DataInputStream cin;
	static DataOutputStream  cout;
	static JTextArea MessageArea2;
	static String Client1,sname,PN;
	static int PortNum;
	private JScrollPane scrollPane;
	static int i=0,k=0;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client1=JOptionPane.showInputDialog("Enter Your  name : ");
					sname=Client1;
					Client2 window = new Client2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	
		String msgin1="";		
		
		
		try {
			PN=JOptionPane.showInputDialog("Enter the servers' port number : ");
			PortNum=Integer.parseInt(PN);
			s1= new Socket("127.0.0.1",PortNum); 
			JOptionPane.showMessageDialog(null," Connected to the server ");
	
			
			cin= new DataInputStream(s1.getInputStream());
			cout=new DataOutputStream(s1.getOutputStream());
			while(!msgin1.equals("exit")) {
				msgin1=cin.readUTF();
				if (msgin1.endsWith(".")&&k==0) {
					Client1=msgin1;
					k++;
					continue;
				}
				MessageArea2.setText(MessageArea2.getText().trim()+ "\n"+ Client1+" :  "+msgin1); // Displaying the message  from the Client1
				
							}
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,e);
			System.exit(0);

		}

	}

	
	public Client2() {
		initialize();
	}

	
	private void initialize() {
		frame = new JFrame(Client1);
		frame.setFont(new Font("Tahoma", Font.BOLD, 13));
		frame.setBounds(100, 100, 546, 369);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		MessageText1 = new JTextField();
		MessageText1.setFont(new Font("Tahoma", Font.BOLD, 17));
		MessageText1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		MessageText1.setBounds(10, 262, 419, 64);
		frame.getContentPane().add(MessageText1);
		MessageText1.setColumns(10);
		 		
		MessageArea2 = new JTextArea();
		MessageArea2.setFont(new Font("Tahoma", Font.BOLD, 15));
		//MessageArea2.setBounds(10, 11, 518, 244);
		frame.getContentPane().add(MessageArea2);
		

		scrollPane = new JScrollPane(MessageArea2);
		scrollPane.setBounds(10, 11, 518, 244);
		frame.getContentPane().add(scrollPane);
		
		
		
		JButton SendButton = new JButton("SEND");
		SendButton.setBackground(new Color(204, 204, 204));
		SendButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		SendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String msgout="";
					if(i>0) {
				msgout=	MessageText1.getText().trim();   
				cout.writeUTF(msgout);
				
				MessageArea2.setText(MessageArea2.getText()+"\n"+" Me"+" : "+msgout);
				
				
				MessageText1.setText("");
					}
					else 
					{
						sname+=".";
						cout.writeUTF(sname);
						i++;
						
					}
					}
					catch(Exception e) {
						JOptionPane.showMessageDialog(null,"Error! The connection has lost!");

					}
			}
		});
		SendButton.setBounds(430, 263, 100, 56);
		frame.getContentPane().add(SendButton);
		

		
	}
}
