package macawsProject;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;


public abstract class GUIDB extends JFrame implements ActionListener {

	
	
public static void gui() {	
	JFrame frame =new JFrame();
	ImageIcon image = new ImageIcon("macawsProject\\icon.jpg");//creates an image Icon
	JLabel label= new JLabel();
	ImageIcon Mimage = new ImageIcon("macawsProject\\menu.png");
	label.setIcon(Mimage);
	frame.setLayout(new FlowLayout());
	
	JButton button = new JButton("Submit");
	//button.addActionListener((ActionListener) frame);
	
	
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
	
		
}


}

	
	
	
	
	
	
	

