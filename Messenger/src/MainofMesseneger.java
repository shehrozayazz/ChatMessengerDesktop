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
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.Scrollbar;

public class MainofMesseneger extends Thread{

	private JFrame frame;
	static JTextField MessageText;

	static ServerSocket ss;
	static Socket s;
	static  DataInputStream din;
	static DataOutputStream  dout;
	static JTextArea MessageArea1;
	static Scrollbar scrollbar;
	
	public static void main(String[] args) {
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainofMesseneger window = new MainofMesseneger();
					window.frame.setVisible(true);
				
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	
		
		
		try {
				
		
			
			String msgin="";
			ss= new ServerSocket(12001); 
			s= ss.accept();
			
		
			
            	
			din= new DataInputStream(s.getInputStream());
			
			dout=new DataOutputStream(s.getOutputStream());
		
			while(!msgin.equals("exit")){
			
				msgin=din.readUTF();
				MessageArea1.setText(MessageArea1.getText().trim()+"\n Client  :  "+msgin); // Displaying the message  from the client
				
						
				
		}
		
	}catch(Exception e) {
		JOptionPane.showMessageDialog(null,e+"Error! Something went wrong, or the server isn't working!");
		
		}
		
		
	}
		
		
	
	public MainofMesseneger() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("   Server  ");
		frame.setBounds(100, 100, 663, 425);
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
					catch(Exception e) {
						JOptionPane.showMessageDialog(null,e+"  Error! Something went wrong, or the client isn't connected!");
					}
			}
		});
		SendButton.setBounds(520, 311, 100, 56);
		frame.getContentPane().add(SendButton);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(111, 122, 5, 22);
		frame.getContentPane().add(textArea);
		
		MessageArea1 = new JTextArea();
		
		MessageArea1.setFont(new Font("Tahoma", Font.PLAIN, 17));
		MessageArea1.setBounds(10, 11, 303, 289);
		frame.getContentPane().add(MessageArea1);
		
		scrollbar = new Scrollbar();
		scrollbar.setBounds(626, 23, 17, 289);
		frame.getContentPane().add(scrollbar);
		
	}
}
