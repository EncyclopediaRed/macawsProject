package macawsProject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyFrame extends JFrame implements ActionListener {

	JTextField txt;
	
	
	JButton btn;
	
	MyFrame() throws IOException {    	
		
		
		JPanel imgpan=new JPanel();  
		imgpan.setBounds(0,0,800,500);
		BufferedImage myPicture = ImageIO.read(new File("macawsProject\\menu.png"));
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		imgpan.add(picLabel);
		
		JPanel secondPan=new JPanel();
		txt =new JTextField();
		txt.setPreferredSize(new Dimension(140,30));
		secondPan.add(txt);
		secondPan.setBounds(950,0,140,30);
		
		JPanel thirdPan=new JPanel();
		btn=new JButton("submit");
		thirdPan.add(btn);
		btn.addActionListener(this);
		thirdPan.setBounds(1100,0,140,30);
		
		 JPanel tpanel = new JPanel();
		 tpanel.add(new JButton("Click"));
		 tpanel.add(new JTextField(20));
		 tpanel.add(new JLabel("Label"));
		 JOptionPane.showMessageDialog(null,tpanel,"Information",JOptionPane.INFORMATION_MESSAGE);
		

		
		this.setLayout(null);
		this.setSize(1240,1240);//sets demnesions   
		this.getContentPane().setBackground(Color.gray);
		this.setVisible(true); 
		this.add(imgpan);
		this.add(secondPan);
		this.add(thirdPan);
		this.add(tpanel);
		ImageIcon image = new ImageIcon("macawsProject\\icon.jpg");//creates an image Icon
		this.setIconImage(image.getImage());//sets the plain as the icon
    	this.setTitle("Java Airlines: Ticket Management System");//title of program
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//actually closes the jframe
		
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btn) {
			String get =txt.getText().toString();
			int choice = Integer.parseInt(get);
			 while (choice != 12) { // while choice is not 12, keep looping
		            // call menu method and assign choice to the returned value
		            if (choice == 0) {
		            	AirlineDriverDB.createConnection();
		            } else if (choice == 1) { 
		                AirlineDriverDB.printSeatMap(null);
		            } else if (choice == 2) {
		                AirlineDriverDB.printFlightInfo();
		            } else if (choice == 3) {
		                AirlineDriverDB.printPilots();
		            } else if (choice == 4) {
		                AirlineDriverDB.printReservation();
		            } else if (choice == 5) {
		                AirlineDriverDB.printCustomerByNum();
		            } else if (choice == 6) {
		                AirlineDriverDB.bookReservation();
		            } else if (choice == 7) {
		                AirlineDriverDB.cancelReservation();
		            } else if (choice == 8) {
		                AirlineDriverDB.printGrossIncome();
		            } else if (choice == 9) {
		                AirlineDriverDB.searchReservation();
		            } else if (choice == 10) {
		                AirlineDriverDB.searchDeleted();
		            } else if (choice == 11) {
		                AirlineDriverDB.addCustomer();
		            } else if (choice == 12) {
		                AirlineDriverDB.closeConnection();
		                System.out.println("Thank you for choosing Java Airlines!\nHave a good day :)");
		                System.exit(0);
		            }
		        }

		    }
		}
}	
	
	
	/*    public static void gui() {	
    	JFrame frame =new JFrame();
    	ImageIcon image = new ImageIcon("macawsProject\\icon.jpg");//creates an image Icon
    	JLabel label= new JLabel();
    	ImageIcon Mimage = new ImageIcon("macawsProject\\menu.png");
    	label.setIcon(Mimage);
    	frame.setLayout(new FlowLayout());
    	
    	JButton button = new JButton("Submit");
    	button.addActionListener(b);
    	
    	
    	JTextField textField = new JTextField();
    	textField.setPreferredSize(new Dimension(250,40));

    	
    	
    	frame.setIconImage(image.getImage());//sets the plain as the icon
    	frame.setTitle("Java Airlines: Ticket Management System");//title of program
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//actually closes the jframe
    	frame.setSize(1240,1240);//sets demnesions
    	frame.setVisible(true);//sets frame to visable
    	frame.getContentPane().setBackground(Color.gray);
    	frame.add(label);
    	frame.add(button);
    	frame.add(textField);
    	    		
    }*/
	//btn.addActionListener(this);
	//f.add(btn);
	
	
	
	//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//this.setLayout(new FlowLayout());
	//txt.setPreferredSize(new Dimension(250,40));
	//add(txt);
	//btn.addActionListener(this);
	//add(btn);

	//JFrame frame =new JFrame();
	//ImageIcon image = new ImageIcon("macawsProject\\icon.jpg");//creates an image Icon

	//frame.add(label);
	//txt = new JTextField("");
	//txt.setPreferredSize(new Dimension(250,40));
	//frame.add(txt);
	//btn=new JButton ("Submit");
	//frame.add(btn);
	
	//frame.setIconImage(image.getImage());//sets the plain as the icon
	//frame.setTitle("Java Airlines: Ticket Management System");//title of program
	
	//frame.setVisible(true);//sets frame to visable
	
	//JFrame frame = new JFrame();
	//JLabel label= new JLabel();
	
	//ImageIcon Mimage = new ImageIcon("macawsProject\\menu.png");
	
	//JPanel funPan =new JPanel();
	
	
	
	
	
	
	//this.setSize(1240,1240);//sets demnesions
	
	//label.setHorizontalAlignment(JLabel.CENTER);
	//label.setVerticalAlignment(JLabel.CENTER);
	//this.getContentPane().setBackground(Color.gray);
	//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//actually closes the jframe
	
	//this.add(label);
	 
	
	//txt.setHorizontalAlignment(JLabel.RIGHT);
	//btn.setHorizontalAlignment(JLabel.BOTTOM);
	//this.add(txt);
	//this.add(btn);
	
	//this.setVisible(true);


