package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import models.Bullet;
import models.BulletList;

public class GuiBulletsWithBullet extends JFrame {

	private JPanel contentPane;
	private JLabel itemNameLabel;
	private JList list;
	private JLabel lblNewItem;
	private JTextField textFieldName;
	
	private Bullet bullet;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiBulletsWithBullet frame = new GuiBulletsWithBullet();
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
	public GuiBulletsWithBullet() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 622, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		itemNameLabel = new JLabel("Item");
		itemNameLabel.setBounds(12, 0, 424, 15);
		contentPane.add(itemNameLabel);
		
		list = new JList();
		list.setBounds(12, 26, 352, 235);
		contentPane.add(list);
		
		
		lblNewItem = new JLabel("Title");
		lblNewItem.setBounds(382, 27, 137, 15);
		contentPane.add(lblNewItem);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(376, 44, 226, 19);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteSelected();
			}
		});
		
		btnDelete.setBounds(376, 236, 226, 25);
		contentPane.add(btnDelete);
		
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = textFieldName.getText();
				Bullet b = new Bullet(text);
				bullet.addSubBullet(b);
				updateList();
			}
		});
		btnNewButton.setBounds(376, 75, 229, 25);
		contentPane.add(btnNewButton);
		
		
		list.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {
		        	int index = list.locationToIndex(evt.getPoint());
		            
		            GuiBulletsWithBullet g = new GuiBulletsWithBullet();
		            g.setBullet(bullet.getSubBullets().get(index));
		            g.show();
		            
		        } 
		    }
		});
		
	}

	protected void deleteSelected() {
		int i = list.getSelectedIndex();
		if(i>=0){
			bullet.removeBullet(i);
			updateList();
		}
		
	}
	
	public void setBullet(Bullet bullet) {
		this.bullet = bullet;
		
		updateLabel();
		updateList();
	}
	
	private void updateLabel() {
		itemNameLabel.setText(bullet.getText());
		
	}
	
	private void updateList() {
		DefaultListModel<Bullet> model = new DefaultListModel<>();

		for ( Bullet bullet: bullet.getSubBullets()){
			model.addElement( bullet );
		}
		list.setModel(model);
		
		
		
	}

}
