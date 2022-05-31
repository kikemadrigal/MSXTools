package es.tipolisto.MSXTools.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import es.tipolisto.MSXTools.ConvertCSVTSXToBas;
import es.tipolisto.MSXTools.CovertCSVTSXToHex;
import es.tipolisto.MSXTools.FileManager;
import utils.Constants;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

/**
 * 
 * @author MSX Murcia, tipolisto
 *Esta clase muestra una pantalla en la que elegimos el archivo a convertir
 *tambi�n tiene un campo de texto, sino se intruduce en este capo tento se le asignar�
 *un nombre de texto autom�tico al archivo destino
 */
public class CSVTSXToHexWindow extends JFrame {
	private File fileOrigin;
	private File fileDestiny;
	private FileManager fileManager;
	private JPanel contentPane;
	private JTextField jTextFieldNewNamedestinyFile;



	/**
	 * Create the frame.
	 */
	public CSVTSXToHexWindow() {
		fileManager=new FileManager();
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 463, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CSV / TSX to Hex");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(151, 29, 168, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Select file origin");
		lblNewLabel_1.setBounds(10, 69, 427, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel jLabelPathFileOrigin = new JLabel("");
		jLabelPathFileOrigin.setBounds(10, 123, 427, 14);
		contentPane.add(jLabelPathFileOrigin);
		
		JLabel lblNewLabel_2 = new JLabel("Enter the name of the output file or leave it blank to make it automatic");
		lblNewLabel_2.setBounds(10, 144, 427, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel jLabelDestinyFile = new JLabel("");
		jLabelDestinyFile.setBounds(10, 200, 427, 14);
		contentPane.add(jLabelDestinyFile);
		
		jLabelDestinyFile.setText(fileManager.createFileDestiny().getAbsolutePath());
		
		JButton jButtonSelectFileOrigin = new JButton("File origin");
		jButtonSelectFileOrigin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					JFileChooser jFileChooser=new JFileChooser(System.getProperty("user.dir"));
					jFileChooser.setDialogTitle("Selecciona un archivo");
					int result=jFileChooser.showSaveDialog(null);
					if(result==JFileChooser.APPROVE_OPTION) {
						fileOrigin=jFileChooser.getSelectedFile();
						String fileDestinyPathParent=fileOrigin.getParent();
						String fileDestinyName=fileOrigin.getName().substring(0,fileOrigin.getName().length()-4)+"-hex.txt";
						fileDestinyPathParent=fileDestinyPathParent+"\\"+fileDestinyName;
						jLabelPathFileOrigin.setText(fileOrigin.getPath().toString());
						fileDestiny=new File(fileDestinyPathParent);
						jLabelDestinyFile.setText(fileDestinyPathParent);
					}
				
			}
		});
		jButtonSelectFileOrigin.setBounds(10, 89, 427, 23);
		contentPane.add(jButtonSelectFileOrigin);
		

		
		jTextFieldNewNamedestinyFile = new JTextField();
		jTextFieldNewNamedestinyFile.setBounds(10, 169, 427, 20);
		contentPane.add(jTextFieldNewNamedestinyFile);
		jTextFieldNewNamedestinyFile.setColumns(10);
		

		
		JButton btnNewButton = new JButton("Covert!!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				convert();
			}
		});
		btnNewButton.setBounds(10, 228, 427, 57);
		contentPane.add(btnNewButton);
		

	}
	

	
	private void convert() {
		if(fileOrigin==null) {
			JOptionPane.showMessageDialog(null, "Tienes que seleccionar un archivo"); 
		}else {
			try {
				//nombreArchivoConvertido=nombreArchivoAConvertir.substring(0,nombreArchivoAConvertir.length()-8)+".bas";
				if (jTextFieldNewNamedestinyFile.getText()==null || jTextFieldNewNamedestinyFile.getText()=="") {
					fileDestiny=new File(fileOrigin.getPath());
				}
				CovertCSVTSXToHex conversor=new CovertCSVTSXToHex();
				ArrayList<String> arrayList=conversor.extractHexData(fileOrigin);
				conversor.writeHexDataToFile(fileDestiny,arrayList);
				/*for(String data:arrayList) {
					System.out.println(data);
				}*/
				JOptionPane.showMessageDialog(null, "Convertido"); 
				
			}catch(Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage()); 
			}
			
		}
	}
}
