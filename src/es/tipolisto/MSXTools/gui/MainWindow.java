package es.tipolisto.MSXTools.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class MainWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
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
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		JLabel lblNewLabel = new JLabel("Tools MSX");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(164, 11, 120, 21);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(lblNewLabel);
		
		JButton jButtonDeleteComents = new JButton("Delete coments");
		jButtonDeleteComents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteComentsWindow frame = new DeleteComentsWindow();
				frame.setVisible(true);
			}
		});
		jButtonDeleteComents.setBounds(118, 57, 232, 23);
		contentPane.add(jButtonDeleteComents);
		
		JButton jButonCSVToBas = new JButton("CSV / TSX to Bas");
		jButonCSVToBas.setBounds(118, 103, 232, 23);
		jButonCSVToBas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CSVTSXToBasWindow csvToBasWindow=new CSVTSXToBasWindow();
				csvToBasWindow.setVisible(true);
			}
		});
		contentPane.add(jButonCSVToBas);
		
		JButton btnNewButton = new JButton("Compress / decompress RLE 16");
		btnNewButton.setBounds(118, 192, 232, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CompressWindow frame = new CompressWindow();
				frame.setVisible(true);
			}
		});
		contentPane.add(btnNewButton);
		
		JButton jButtonCSVToHex = new JButton("CSV / TSX  to Hex");
		jButtonCSVToHex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CSVTSXToHexWindow frame=new CSVTSXToHexWindow();
				frame.setVisible(true);
			}
		});
		jButtonCSVToHex.setBounds(118, 146, 232, 23);
		contentPane.add(jButtonCSVToHex);
		
		JLabel lblNewLabel_1 = new JLabel("MSX Murcia 2022 ");
		lblNewLabel_1.setBounds(284, 236, 140, 14);
		contentPane.add(lblNewLabel_1);
	}
}
