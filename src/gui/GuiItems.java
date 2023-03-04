package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import models.BulletList;
import models.Item;
import models.Section;

public class GuiItems extends JFrame {

	private JPanel contentPane;
	private Section section;
	private JLabel sectionNameLabel;
	private JList list;
	private JLabel lblNewItem;
	private JTextField textFieldName;
	private JButton btnNewButton;
	private JButton btnDelete;
	private JLabel lblDate;
	private JTextField textFieldDate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiItems frame = new GuiItems();
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
	public GuiItems() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 687, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		sectionNameLabel = new JLabel("Section");
		sectionNameLabel.setBounds(12, 0, 424, 15);
		contentPane.add(sectionNameLabel);
		
		list = new JList();
		list.setBounds(12, 26, 352, 235);
		contentPane.add(list);
		
		lblNewItem = new JLabel("Title");
		lblNewItem.setBounds(382, 27, 137, 15);
		contentPane.add(lblNewItem);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(376, 44, 297, 19);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = textFieldName.getText();
				String d = textFieldDate.getText();
				
				addItem(text, d);
				
				
				
			}
		});
		btnNewButton.setBounds(376, 120, 297, 25);
		contentPane.add(btnNewButton);
		
		btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteSelected();
			}

			
		});
		btnDelete.setBounds(376, 236, 297, 25);
		contentPane.add(btnDelete);
		
		lblDate = new JLabel("Date (2015-12-31)");
		lblDate.setBounds(382, 73, 287, 15);
		contentPane.add(lblDate);
		
		textFieldDate = new JTextField();
		textFieldDate.setBounds(376, 89, 297, 19);
		contentPane.add(textFieldDate);
		textFieldDate.setColumns(10);
		
		list.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {
		            int index = list.locationToIndex(evt.getPoint());
		            
		            GuiBulletsWithItem g = new GuiBulletsWithItem();
		            g.setItem((BulletList)section.getItems().get(index));
		            g.show();
		            
		        } 
		    }
		});
	}
	
	
	
	protected void addItem(String text, String d) {
		if(d == null || d.equals("")){
			BulletList b = new BulletList(text);
			section.addItem(b);
			updateList();
		}else{
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date date = format.parse(d);
				
				if(section.getItems().size() == 0){
					BulletList b = new BulletList(text,date);
					section.addItem(b);
					updateList();
				}else{
					Date lastDate = section.getItems().get(section.getItems().size()-1).getDate();
					if(lastDate == null || date.after(lastDate)){
						BulletList b = new BulletList(text,date);
						section.addItem(b);
						updateList();
					}else{
						JOptionPane.showMessageDialog(null, "Wrong date order");
					}
				}
				
				
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(null, "Wrong date format");
			}
		}
		
	}

	public void setSection(Section section) {
		this.section = section;
		updateLabel();
		updateList();
	}

	private void updateLabel() {
		sectionNameLabel.setText(section.getTitle());
	}

	private void updateList() {
		DefaultListModel<Item> model = new DefaultListModel<>();

		for ( Item item: section.getItems()){
			model.addElement( item );
		}
		list.setModel(model);

		
	}
	
	private void deleteSelected() {
		int i = list.getSelectedIndex();
		if(i>=0){
			section.removeItem(i);
			updateList();
		}
		
	}
}
