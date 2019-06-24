import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Client extends Thread {

	private JFrame frame;
	static JTextField MessageArea;
	static JTextField MessageSend;

	
	static Socket s;
	static  DataInputStream din;
	static DataOutputStream  dout;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client window = new Client();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		String msgin="";		
		
		
		try {
	
			s= new Socket("127.0.0.1",1201); 
			din= new DataInputStream(s.getInputStream());
			dout=new DataOutputStream(s.getOutputStream());
			while(!msgin.equals("exit")) {
				msgin=din.readUTF();
				MessageArea.setText(MessageArea.getText().trim()+"\n"+msgin); // Displaying the message  from the client
				
			}
		}catch(Exception e) {
			
		}

	}

	
	public Client() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("   Client  ");
		frame.setBounds(100, 100, 557, 376);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		MessageArea = new JTextField();
		MessageArea.setBounds(10, 11, 521, 240);
		frame.getContentPane().add(MessageArea);
		MessageArea.setColumns(10);
		
		MessageSend = new JTextField();
		MessageSend.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		MessageSend.setBounds(10, 262, 419, 64);
		frame.getContentPane().add(MessageSend);
		MessageSend.setColumns(10);
		
		JButton SendButton = new JButton("S.E.N.D");
		SendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String msgout="";
					
				msgout=	MessageArea.getText().trim();
				dout.writeUTF(msgout);
					}
					catch(Exception e) {
						JOptionPane.showMessageDialog(null,"Error! Something went wrong!");
					}
			}
		});
		SendButton.setBounds(439, 262, 89, 49);
		frame.getContentPane().add(SendButton);
	}
}
