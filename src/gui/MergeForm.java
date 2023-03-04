package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.CV;
import models.Section;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MergeForm extends JFrame {

	private JPanel contentPane;
	private JList list;
	
	private CV differences;
	private CV similarities;
	private Listener listener;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MergeForm frame = new MergeForm();
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
	public MergeForm() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 249, 359);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Differences");
		lblNewLabel.setBounds(12, 0, 144, 15);
		contentPane.add(lblNewLabel);
		
		
	
		list = new JList();
		
		list.setBounds(12, 27, 215, 249);
		contentPane.add(list);
		
		btnNewButton = new JButton("Merge");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listener != null){
					listener.cvready(CV.merge(similarities, differences));
				}
			}
		});
		btnNewButton.setBounds(12, 295, 215, 25);
		contentPane.add(btnNewButton);
	}
	
	public void setDifferences(CV differences) {
		this.differences = differences;
	}
	
	public void setSimilarities(CV similarities) {
		this.similarities = similarities;
	}

	public void setListener(Listener listener) {
		this.listener = listener;
		updateList();
	}
	
	
private void updateList(){
		
		DefaultListModel<Section> model = new DefaultListModel<>();

		for ( Section s: differences.getSections()){
			model.addElement( s );
			System.out.println(s);
		}
		list.setModel(model);
		
		
		list.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {
		            int index = list.locationToIndex(evt.getPoint());
		            
		            GuiItems gi = new GuiItems();
		            gi.setSection(differences.getSections().get(index));
		            gi.show();
		            
		        } 
		    }
		});
	}
}
