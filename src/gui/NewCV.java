package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import models.CV;
import models.ChronologicalCV;
import models.CombinedCV;
import models.FunctionalCV;

public class NewCV extends JFrame {
	
	
	private ButtonGroup group;
	private JRadioButton radioFunctional;
	private JRadioButton radioChrono;
	private JRadioButton radioCombined;
	private JLabel lblName;
	private JTextField textField;
	
	private Listener listener = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewCV frame = new NewCV();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NewCV() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Επιλέξτε ένα τύπο");
		label.setBounds(4, 12, 205, 15);
		getContentPane().add(label);
		
		radioFunctional = new JRadioButton("Functional CV");
		radioFunctional.setBounds(22, 35, 149, 23);
		getContentPane().add(radioFunctional);
		
		radioChrono = new JRadioButton("Chronological CV");
		radioChrono.setBounds(22, 73, 149, 23);
		getContentPane().add(radioChrono);
		
		radioCombined = new JRadioButton("Combined CV");
		radioCombined.setBounds(22, 112, 149, 23);
		getContentPane().add(radioCombined);
		
		group = new ButtonGroup();
		group.add(radioFunctional);
		group.add(radioChrono);
		group.add(radioCombined);
		
		
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				next();
			}

			
		});
		btnNext.setBounds(311, 233, 117, 25);
		getContentPane().add(btnNext);
		
		lblName = new JLabel("Όνομα");
		lblName.setBounds(311, 10, 70, 15);
		getContentPane().add(lblName);
		
		textField = new JTextField();
		textField.setBounds(311, 37, 125, 19);
		getContentPane().add(textField);
		textField.setColumns(10);
	}
	
	
	
	
	private void next() {
		String name = textField.getText();
		if(!radioFunctional.isSelected() && !radioChrono.isSelected() && !radioCombined.isSelected()){
			JOptionPane.showMessageDialog(null, "Please select on of the options");
		}else{
			CV cv = null;
			if(radioFunctional.isSelected()){
				cv = new FunctionalCV(name);
				
			}else if(radioChrono.isSelected()){
				cv = new ChronologicalCV(name);
			}else if(radioCombined.isSelected()){
				cv = new CombinedCV(name);
			}
			
			if(listener!=null){
				listener.cvready(cv);
			}
			
			Sections sec = new Sections();
			sec.setCV(cv);
			sec.show();
		}
	}
	
	public void setListener(Listener listener) {
		this.listener = listener;
	}
}
