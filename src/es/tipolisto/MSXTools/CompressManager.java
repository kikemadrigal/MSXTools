package es.tipolisto.MSXTools;

import java.util.ArrayList;
import java.util.regex.Pattern;
/**
 * 
 * @author MSX Murcia, tipolisto.es
 * Esta clase lleva algoritmos de compresi�n y descompresi�n de textos
 */
public class CompressManager {
	
	// Cada entrada RLE de 16 bits consta de dos partes: un contador (1 byte) y un valor (1 byte)
	// B�sicamente codificas el n�mero de veces que un valor dado se repite en posiciones contiguas
	public ArrayList<String> compress2Digits(String text) {
		ArrayList<String> arrayList=new ArrayList<String>();
		String content="";
		String tempCharacter="";	
		String character="";
		String stringCharCount="00";
		int charCount=0;
		int letter=0;
		//Obtenemos un array con las l�neas
		String[] lines=text.split("\n");
		//Por cada l�nea
		for (String line: lines) {
			//Vamos recorriendo de 2 en 2 letras del string
			for (int textIndex = 0;textIndex< line.length();textIndex=textIndex+2) {
				//Solo trabajaremos con los textos pares
				if ((line.length() % 2)==0) { 
					//Obtenemos los 2 d�gitos
					character=line.substring(textIndex,textIndex+2);
					//Si los d�gitos son los anteriores aumentamos el contador de repeticiones (charCount)
					if (tempCharacter.equals(character)) {		
						charCount++;
						if (charCount<9) stringCharCount="0"+String.valueOf(charCount);
						else stringCharCount=String.valueOf(charCount);
						//Quitamos la parte antigua del string (por ejemplo:0013 ya que ahora es 0113)
						letter -= 4;
						content=content.substring(0,letter);
					//Si no hay repeticiones ponemos ceros
					}else {
						stringCharCount="00";
						charCount=0;
					}
					content+=stringCharCount+character;  
				}	
				letter+=4;
				tempCharacter=character;
			}
			content+="\n";
			letter+=1;
			arrayList.add(content);
		}
		return arrayList;
	}
	
	
	//https://www.youtube.com/watch?v=7a4r3tIVtCE&t=319s
	public String compress1digit(String text) {
		StringBuilder sb=new StringBuilder();
		for (int textIndex=0;textIndex < text.length(); textIndex++) {
			int charCount=0;
			while (textIndex < text.length()-1 && text.charAt(textIndex) == text.charAt(textIndex+1) ) {
				charCount++;
				textIndex++;
			}
			sb.append(text.charAt(textIndex)).append(charCount);
		}
		return sb.toString();
	}
	


	//https://www.youtube.com/watch?v=7a4r3tIVtCE&t=319s
	private String compressWithPattern(String text) {
		StringBuilder sb=new StringBuilder();
		Pattern pattern =Pattern.compile("(([a-zA-Z])\\2*)");
		return sb.toString();
	}
	
	private String decompress(String text) {
		StringBuilder sb=new StringBuilder();
		for (int textindex=0;textindex<text.length();textindex++) {
			if (Character.isDigit(text.charAt(textindex))) {
				int charCount=Character.getNumericValue(text.charAt(textindex));
				while (charCount-- != 1)sb.append(text.charAt(textindex -1));
			}else {
				sb.append(text.charAt(textindex));
			}
		}
		return sb.toString();
	}

}
