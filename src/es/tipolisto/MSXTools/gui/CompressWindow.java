package es.tipolisto.MSXTools.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Caret;
import javax.tools.FileObject;

import es.tipolisto.MSXTools.CompressManager;
import es.tipolisto.MSXTools.FileManager;
import es.tipolisto.MSXTools.StringManager;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.io.File;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class CompressWindow extends JFrame implements ActionListener, DocumentListener{

	private JPanel contentPane;
	private JLabel jLabelCompress;
	private JLabel jLabelDecompress;
	private JLabel jLabelFileSelected;
	private JLabel jLabelFileSave;
	private JLabel jLabelNumberButesCompress;
	private JLabel jLabelNumberButesDecompress;
	
	private JTextArea jTextAreaCompress;
	private JTextArea jTextAreaDecompress;
	
	private JToggleButton toogleButtonCompress;
	private JButton jButtonSsave;
	private JButton jButtonLoadFileHex;

	
	private FileManager fileManager;
	private CompressManager compressManager;


	public CompressWindow() {
		fileManager=new FileManager();
		compressManager=new CompressManager();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 622, 717);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//Centamos la ventana en la pantalla
		setLocationRelativeTo(null);
		setTitle("Compress / descompress");
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		
		componentsInizialice();
		
		
	}

	private void componentsInizialice() {
		JLabel jlaberlTitle = new JLabel("Compress / decompress");
		jlaberlTitle.setFont(new Font("Tahoma", Font.PLAIN, 17));
		jlaberlTitle.setBounds(185, 25, 201, 32);
		contentPane.add(jlaberlTitle);
		
		jLabelCompress = new JLabel("Decompress");
		jLabelCompress.setBounds(10, 208, 165, 14);
		contentPane.add(jLabelCompress);
		
		jLabelDecompress = new JLabel("Compress");
		jLabelDecompress.setBounds(10, 437, 154, 14);
		contentPane.add(jLabelDecompress);
		
		
		jLabelNumberButesCompress = new JLabel("");
		jLabelNumberButesCompress.setBounds(125, 208, 183, 14);
		contentPane.add(jLabelNumberButesCompress);
		
		jLabelNumberButesDecompress = new JLabel("");
		jLabelNumberButesDecompress.setBounds(147, 437, 183, 14);
		contentPane.add(jLabelNumberButesDecompress);
		
	
		
		jTextAreaCompress = new JTextArea();
		jTextAreaCompress.setBounds(10, 233, 586, 194);
		//jTextAreaCompress.setLineWrap(true);
		contentPane.add(jTextAreaCompress);
		//JScrollPane
		JScrollPane jscrollPaneCompress = new JScrollPane(jTextAreaCompress);
		jscrollPaneCompress.setBounds(10, 233, 586, 194);
		contentPane.add(jscrollPaneCompress);
		jTextAreaCompress.getDocument().addDocumentListener(this);
		
		jTextAreaDecompress = new JTextArea();
		jTextAreaDecompress.setBounds(10, 473, 586, 194);
		//jTextAreaDescompress.setLineWrap(true);
		contentPane.add(jTextAreaDecompress);
		JScrollPane jscrollPaneDescompress = new JScrollPane(jTextAreaDecompress);
		jscrollPaneDescompress.setBounds(10, 473, 586, 194);
		contentPane.add(jscrollPaneDescompress);

		
		toogleButtonCompress = new JToggleButton("Mode compress");
		toogleButtonCompress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		toogleButtonCompress.setBounds(437, 11, 144, 49);
		contentPane.add(toogleButtonCompress);
		toogleButtonCompress.addActionListener(this);
		
		jButtonSsave = new JButton("Save");
		jButtonSsave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showConfirmDialog(null, "Not implement");
			}
		});
		jButtonSsave.setBounds(10, 132, 165, 32);
		contentPane.add(jButtonSsave);
		
		jButtonLoadFileHex = new JButton("Load file Hex");
		jButtonLoadFileHex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jFileChooser=new JFileChooser(System.getProperty("user.dir"));
				jFileChooser.setDialogTitle("Selecciona un archivo");
				int result=jFileChooser.showSaveDialog(null);
				if(result==JFileChooser.APPROVE_OPTION) {
					//JOptionPane.showConfirmDialog(null, "WIP");
					String content="";
					File fileOrigin=jFileChooser.getSelectedFile();
					ArrayList<String> arrayList=fileManager.readFile(fileOrigin);
					System.out.println("Let s read the file "+fileOrigin.getAbsolutePath());
					
					for (String value:arrayList) {
						content +=value+"\n";
						System.out.println(value);
					}
					jTextAreaCompress.setText(content);
					ArrayList<String> values=compressManager.compress2Digits(content);
					System.out.println("El tamaño es "+String.valueOf(values.size()));
					String value=values.get(0);
					System.out.println("El valor es "+value);
					/*for (String value:values) {
						jTextAreaDecompress.setText("hola"+value);
					}*/
					
				}
			}
		});
		jButtonLoadFileHex.setBounds(10, 73, 165, 32);
		contentPane.add(jButtonLoadFileHex);
		
		jLabelFileSelected = new JLabel("");
		jLabelFileSelected.setBounds(10, 111, 568, 14);
		contentPane.add(jLabelFileSelected);
		
		jLabelFileSave = new JLabel("");
		jLabelFileSave.setBounds(10, 175, 571, 14);
		contentPane.add(jLabelFileSave);
		

		
		/*JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(10, 25, 89, 23);
		contentPane.add(btnNewButton);*/
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(toogleButtonCompress==e.getSource()) {
			//JOptionPane.showMessageDialog(null, "clickado!!!");
			if (toogleButtonCompress.isSelected()) {
				toogleButtonCompress.setText("Mode descompress");
				jLabelCompress.setText("Test to descompress:");
				jLabelDecompress.setText("Compress:");

			}else {
				toogleButtonCompress.setText("Mode compress");
				jLabelCompress.setText("Test to compress:");
				jLabelDecompress.setText("Descompress:");
			}
			jTextAreaCompress.setText("");
			jTextAreaDecompress.setText("");
		}
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		String text=jTextAreaCompress.getText();
		/*
		//Compress 1 digit :(
		String value=compressManager.compress1digit(text);
		jTextAreaDescompress.setText(String.valueOf(value));
		jLabelNumberButesDecompress.setText(String.valueOf(value.length()));
		*/
		//Compress 2 digits
		ArrayList<String> values=compressManager.compress2Digits(text);
		for (String value:values) {
			jTextAreaDecompress.setText(value);
		}
		jLabelNumberButesCompress.setText(String.valueOf(text.length()));
		jLabelNumberButesDecompress.setText(String.valueOf(StringManager.getByteNumber(values)));
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub

	}
	
	

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
