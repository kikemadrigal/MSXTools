package es.tipolisto.MSXTools;
import java.awt.EventQueue;
import java.io.File;

import javax.swing.*;

import es.tipolisto.MSXTools.gui.CompressWindow;
import es.tipolisto.MSXTools.gui.MainWindow;

 
public class Main {
	public static void main(String[] args) {
		//Si hay argumentos
		if(args.length>0) {
			//System.out.println("Se enontraron argumentos ");
			String originFile="";
			String destinyFile="";
			//Si se encuentra la letra -m (modo)
			for (String arg: args){
				//System.out.println("Argumento "+arg); 
				//m=mode
				int mPosition=arg.indexOf("-m");
				//o=origen
				int oPosition=arg.indexOf("-o");
				//d=destiny
				int dPosition=arg.indexOf("-d");
				int hPosition=arg.indexOf("-h");
				boolean originDefined=false;
				boolean destinyDefined=false;
				/*if (arg.length()<3) {
					System.out.println("Undefined parameter "+arg);
					break;
				}*/
				if (hPosition != -1){
					sendHelpMessage();
/***************************************Archivo origen*****************************/
				}else if (oPosition != -1){
					System.out.println("Encontrado -o");
					originFile=arg.substring(oPosition+3, arg.length());
					System.out.println("Ha entrado con la o "+originFile);
					try {
						File fileOrigin=new File(originFile);
						if (fileOrigin.exists()) {
							originDefined=true;
							System.out.println("File origin: "+originFile); 
						}else {
							System.out.println("File not found: "+originFile); 
						}
					}catch(Exception ex) {
						System.out.println("Exception "+ex.getMessage()); 
					}
/***************************************Archivo destino*****************************/
				}else if (dPosition != -1){
					destinyFile=arg.substring(dPosition+3, arg.length());
					try {
						File fileDestiny=new File(destinyFile);
						if (fileDestiny.exists()) {
							destinyDefined=true;
							System.out.println("File destiny: "+destinyFile); 
						}else {
							String newDestinyFile=(String) originFile.subSequence(0, originFile.length()-3);
							destinyFile=newDestinyFile+"bas";
							destinyDefined=true;
							System.out.println("File not found: "+destinyFile+" replaced by: "+destinyFile); 
						}
					}catch(Exception ex) {
						System.out.println("Exception "+ex.getMessage()); 
					}
/***************************************Borrado de comentarios (Si se han cumplido las 2 anteriores)*****************************/			
				}else if(mPosition != -1){
					if (arg.length()>2 && arg.charAt(mPosition+3)=='d') {
						//necesitamos que esté definido el archivo orgigen y destino
						//if (destinyDefined && originDefined) {
							try {
								ConvertDeleteComents conversor=new ConvertDeleteComents();
								File fileOrigin=new File(originFile);
								if(fileOrigin.exists() && fileOrigin.canRead()) {
									System.out.println("Let's remove the comments from the file "+originFile);
									conversor.converter(fileOrigin);
								}else {
									//JOptionPane.showConfirmDialog(null, "Impossible read");
									System.out.println("Impossible read "+originFile);
									System.out.println("Impossible read "+fileOrigin.getAbsolutePath()+"\\"+fileOrigin.getName());
									System.exit(0);
								}
								
							}catch(Exception ex) {
								System.out.println("There was a problem deleting comments");
								System.exit(0);
							}
						/*}else {
							System.out.println(" "+destinyDefined+" "+originDefined);
						}*/
					}else {
						System.out.println("Mode found but not defined");
						System.exit(0);
					}
				}else {
					System.out.println("Mode not found");
					sendHelpMessage();
				}
			}
			
		}
		//Sino se escribe ningún argumento lanzamos la interface gráfica
		else {
			sendHelpMessage();
			createMainWindow();
		}
		
	}
	
	private static void showLogo() {
		System.out.print( "                                                  \n"+
							"                                  .               \n"+
							"                                  i.              \n"+
							" MSX MUrcia 2022              :.  i.  ..          \n"+
							"                         :.    i  .  .i   ..:     \n"+
							"                          .::.  .....   .::.      \n"+
							"                               :i::::i. .         \n"+
							"                          ... .i::.:.:i  ...      \n"+
							"                              .i:::.::i           \n"+
							"                            .:  :i:i::  :.        \n"+
							"                         .::.            .::.     \n"+
							"               :bZ       .    .i  .. .r.    .     \n"+
							"  :            grQ            .   i.              \n"+
							"B5Q      .:vKQBBRQ                i.              \n"+
							"B.XUL7L5BBBBgsv MB                .               \n"+
							"Ur   7qP7.        Mr                              \n"+
							":giJM1.            B.                             \n"+
							 "B..               UQrrsri::...               5qS \n"+
							 "Bb              :KDr2M5ii77Yj1sJL2S511uY7r:.:B B \n"+
							 "5Bs          :qRQqi.                      i2BB B \n"+
							 "7vQq     :7IRBKr.             sEQL     :vSSJ:  B \n"+
							 "rB .PQDDqJvIr                 B.7g rKDgUi      B \n"+
							 ".B    7P  r.                  M: BXs:          B \n"+
							  "B1vr:.PL.KI...               dj          .iJP B \n"+
							  "B:v7vL7jUJUu2YvuI25121IujLYrrB2      .r1PIr B.B \n"+
							  "Q.7PQBR2riirigBqr77sYLv7vYvvrB2  .r2K5r.    PQd \n"+
							  "Q B          .:     ....::i::XI Qur.            \n"+
							  "gKQ                          .5 B               \n\n\n");
	}
	
	private static void sendHelpMessage() {
		showLogo();
		System.out.println("write java -jar -m=<parameter> <origin file> <destiny file>");
		System.out.println("-m=d for deletecomments on file bas");
		System.out.println("-m=b for converter file csv to bas");
		System.out.println("-m=h for converter file tsx to hex");
	}
	
	

	private static void createMainWindow() {
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
}
