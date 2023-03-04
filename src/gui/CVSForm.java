package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.BulletList;
import models.CV;

public class CVSForm extends JFrame {

	private JPanel contentPane;
	private ArrayList<CV> cvs = new ArrayList<CV>();
	private JList list;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CVSForm frame = new CVSForm();
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
	public CVSForm() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		list = new JList();
		list.setBounds(12, 33, 252, 217);
		contentPane.add(list);
		
		JLabel lblLoadedCvs = new JLabel("Loaded cvs");
		lblLoadedCvs.setBounds(12, 6, 146, 15);
		contentPane.add(lblLoadedCvs);
		
		JButton btnNewCv = new JButton("New cv");
		btnNewCv.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newCV();
			}
		});
		btnNewCv.setBounds(276, 29, 160, 25);
		contentPane.add(btnNewCv);
		
		JButton btnImport = new JButton("Import");
		btnImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				importcv();
			}
		});
		btnImport.setBounds(276, 66, 160, 25);
		contentPane.add(btnImport);
		
		JButton btnMerge = new JButton("Merge");
		btnMerge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				merge();
			}
		});
		btnMerge.setBounds(276, 171, 163, 25);
		contentPane.add(btnMerge);
		
		JButton btnExport = new JButton("Export text");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportcvTxt();
			}
		});
		btnExport.setBounds(276, 103, 160, 25);
		contentPane.add(btnExport);
		
		JButton btnExportLatex = new JButton("Export latex");
		btnExportLatex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportlatex();
			}
		});
		btnExportLatex.setBounds(276, 134, 163, 25);
		contentPane.add(btnExportLatex);
		
		
		list.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent evt) {
		        JList list = (JList)evt.getSource();
		        if (evt.getClickCount() == 2) {
		            int index = list.locationToIndex(evt.getPoint());
		            
		            Sections sec = new Sections();
					sec.setCV(cvs.get(index));
					sec.show();
		            
		        } 
		    }
		});
	}
	
	protected void merge() {
		int[] index = list.getSelectedIndices();
		
		if(index.length != 2){
			JOptionPane.showMessageDialog(null, "Please select two cvs");
		}else{
			MergeForm form = new MergeForm();
			CV difs = cvs.get(index[0]).differencesWith(cvs.get(index[1]));
			CV sim = cvs.get(index[0]).intersectWith(cvs.get(index[1]));
			
			form.setSimilarities(sim);
			form.setDifferences(difs);
			form.setListener(new Listener() {
				
				@Override
				public void cvready(CV cv) {
					cvs.add(cv);
					updateList();
				}
			});
			
			form.show();
		}
	}

	protected void exportlatex() {
		int i = list.getSelectedIndex();
		if(i>=0){
			try {
				cvs.get(i).exportLatex();
			} catch (Exception e) {

			}
		}
		
	}

	protected void exportcvTxt() {
		int i = list.getSelectedIndex();
		if(i>=0){
			try {
				cvs.get(i).exportTxt();
			} catch (Exception e) {

			}
		}
		
	}

	protected void importcv() {
		JFileChooser fileChooser = new JFileChooser();
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fileChooser.getSelectedFile();
		    String path = selectedFile.getAbsolutePath();
		    try{
		    	if(path.contains(".txt")){
			    	CV cv = CV.importText(path);
			    	cvs.add(cv);
			    	updateList();
			    }else if(path.contains(".tex")){
			    	CV cv = CV.importLatex(path);
			    	cvs.add(cv);
			    	updateList();
			    }else{
			    	JOptionPane.showMessageDialog(null, "Not supported format");
			    }
		    }catch (Exception e) {
		    	JOptionPane.showMessageDialog(null, "Not supported format");
			}
		    
		}
		
	}

	private void updateList() {
		DefaultListModel<CV> model = new DefaultListModel<>();

		for ( CV cv: cvs){
			model.addElement( cv );
		}
		list.setModel(model);
	}

	protected void newCV() {
		NewCV form = new NewCV();
		form.setListener(new Listener() {
			
			@Override
			public void cvready(CV cv) {
				cvs.add(cv);
				updateList();
			}
		});
		form.show();
	}
}
