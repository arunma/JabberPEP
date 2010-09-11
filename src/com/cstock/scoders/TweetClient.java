package com.cstock.scoders;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.PEPManager;

import com.cstock.scoders.utils.ConnectionManager;

public class TweetClient extends JFrame {

	
	JTextField usernameField=null;
	JPasswordField passwordField=null;
	JTextField serverField=null;
	JTextArea messageArea=null;
	XMPPConnection connection=null;
	
	public TweetClient() {
		/*SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	initializeGUI();
            }
        });*/
		initializeGUI();
	}
	
	public static void main(String[] args) {
		TweetClient client=new TweetClient();
		client.pack();
		client.setVisible(true);
		client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	public void initializeGUI(){
		
		final JPanel corePanel=new JPanel();
		corePanel.setLayout(new CardLayout());
		JPanel firstPagePanel=new JPanel();
		JPanel secondPagePanel=new JPanel();
		firstPagePanel.setLayout(new GridLayout(3, 2));
		
		JPanel jPanel1=new JPanel();
		JPanel jPanel2=new JPanel();
		JLabel userLabel=new JLabel("Username : ");
		JLabel passwordLabel=new JLabel("Password : ");
		JLabel serverLabel=new JLabel("Server : ");
		JLabel messageLabel=new JLabel("Message : ");
		final JLabel statusLabel=new JLabel("Status : ");
		
		usernameField=new JTextField(20);
		passwordField=new JPasswordField(20);
		serverField=new JTextField(20);
		
		usernameField.setText("arun");
		passwordField.setText("arun");
		//serverField.setText("arun-laptop");
		serverField.setText("Aruns-MacBook-Pro.local");
		
		
		jPanel1.add(userLabel);
		jPanel1.add(usernameField);
		jPanel1.add(passwordLabel);
		jPanel1.add(passwordField);
		jPanel1.add(serverLabel);
		jPanel1.add(serverField);
		
		
		JButton loginButton=new JButton(new AbstractAction("Login") {
	
			public void actionPerformed(ActionEvent arg0) {

				try {
					connection=ConnectionManager.getConnection(usernameField.getText(), passwordField.getText(), serverField.getText());
					statusLabel.setText("Status : Connected");
					CardLayout layout=(CardLayout)corePanel.getLayout();
					layout.show(corePanel, "second");
					
				} catch (XMPPException e) {
					e.printStackTrace();
					statusLabel.setText("Status : Connection Error. Login failed with "+e.getMessage());
				}
				
			}
		});

		jPanel1.add(loginButton);
		
		jPanel2.add(statusLabel);
		firstPagePanel.add(jPanel1);
		firstPagePanel.add(jPanel2);
		
		
		JLabel enterTweetLabel=new JLabel("Enter tweet : ");
		messageArea=new JTextArea(20,30);
		secondPagePanel.add(enterTweetLabel);
		secondPagePanel.add(messageArea);
		secondPagePanel.add(statusLabel);
		
		final Publisher publisher =new Publisher();
		
		JButton tweetButton=new JButton(new AbstractAction("Tweet") {
			
			public void actionPerformed(ActionEvent arg0) {

				try {
					publisher.publish(connection, messageArea.getText());
					statusLabel.setText("Status : Tweet Successful");
					
				} catch (XMPPException e) {
					e.printStackTrace();
					statusLabel.setText("Status : Connection Error. Login failed with "+e.getMessage());
				}
				
			}
		});
		secondPagePanel.add(tweetButton);
		
		corePanel.add("first",firstPagePanel);
		corePanel.add("second",secondPagePanel);
		this.add(corePanel);
		
		
		/*jPanel.add(messageLabel);
		jPanel.add(messageArea);*/
	}
}
