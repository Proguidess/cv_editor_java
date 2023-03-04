package gui;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.CV;
import models.Section;

public class Sections extends JFrame {

	private JPanel contentPane;
	private JList list;
	
	private CV cv;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sections frame = new Sections();
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
	public Sections() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 194, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SECTIONS");
		lblNewLabel.setBounds(12, 0, 144, 15);
		contentPane.add(lblNewLabel);
		
		
	
		list = new JList();
		
		list.setBounds(12, 27, 154, 231);
		contentPane.add(list);
	}
	
	public void setCV(CV cv){
		this.cv = cv;
		updateList();
	}
	
	private void updateList(){
		
		DefaultListModel<Section> model = new DefaultListModel<>();

		for ( Section s: cv.getSections()){
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
		            gi.setSection(cv.getSections().get(index));
		            gi.show();
		            
		        } 
		    }
		});
	}
}
