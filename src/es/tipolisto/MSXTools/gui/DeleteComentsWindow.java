package es.tipolisto.MSXTools.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.tipolisto.MSXTools.ConvertCSVTSXToBas;
import es.tipolisto.MSXTools.ConvertDeleteComents;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class DeleteComentsWindow extends JFrame {
	private File fileOrigin;
	private File fileDestiny;
	private JPanel contentPane;
 

	public DeleteComentsWindow() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Delete comments");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(112, 11, 218, 39);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("The comments are the ones that start with 1' or 1 ' , Those containing !");
		lblNewLabel_1.setBounds(10, 58, 414, 25);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Los comentarios son los que empiezan por  1' o 1 ', est\u00E1n excluidos los que contienen !");
		lblNewLabel_2.setBounds(10, 82, 414, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel jLabelPathOriginFile = new JLabel("Path origin file");
		jLabelPathOriginFile.setBounds(10, 141, 414, 14);
		contentPane.add(jLabelPathOriginFile);
		
		JLabel jLabelPathDestinyFile = new JLabel("Path dstiny file");
		jLabelPathDestinyFile.setBounds(10, 200, 414, 14);
		contentPane.add(jLabelPathDestinyFile);
		
		JButton jButtonOriginFile = new JButton("Origin File");
		jButtonOriginFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jFileChooser=new JFileChooser(System.getProperty("user.dir"));
				jFileChooser.setDialogTitle("Selecciona un archivo");
				int result=jFileChooser.showSaveDialog(null);
				if(result==JFileChooser.APPROVE_OPTION) {
					fileOrigin=jFileChooser.getSelectedFile();
					jLabelPathOriginFile.setText(fileOrigin.getPath().toString());
				}
			}
		});
		jButtonOriginFile.setBounds(10, 107, 414, 23);
		contentPane.add(jButtonOriginFile);
		

		
		JButton jButtonDestinyFile = new JButton("Destiny file");
		jButtonDestinyFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jFileChooser=new JFileChooser(System.getProperty("user.dir"));
				jFileChooser.setDialogTitle("Selecciona un archivo");
				int result=jFileChooser.showSaveDialog(null);
				if(result==JFileChooser.APPROVE_OPTION) {
					fileDestiny=jFileChooser.getSelectedFile();
					jLabelPathDestinyFile.setText(fileDestiny.getPath().toString());
				}
			}
		});
		jButtonDestinyFile.setBounds(10, 166, 414, 23);
		contentPane.add(jButtonDestinyFile);
		
		JButton jButtonConvertir = new JButton("Covert");
		jButtonConvertir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fileOrigin==null) {
					JOptionPane.showConfirmDialog(null,"Select one file origin");
				}else if(fileDestiny!=null) {
					fileDestiny=new File(fileOrigin.getPath());
					ConvertDeleteComents conversor=new ConvertDeleteComents();
					conversor.converterWithFileDestiny(fileOrigin, fileDestiny);
					JOptionPane.showMessageDialog(null,"Convert!!");
				}else {
					ConvertDeleteComents conversor=new ConvertDeleteComents();
					conversor.converter(fileOrigin);
					JOptionPane.showMessageDialog(null,"Convert!");
				}
				
			}
		});
		jButtonConvertir.setBounds(10, 225, 414, 23);
		contentPane.add(jButtonConvertir);
		

	}

}
