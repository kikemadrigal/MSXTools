package es.tipolisto.MSXTools;

public class NumberManager {
	
	public static String decimalAHexadecimal(int decimal) {
	    String hexadecimal = "";
	    String caracteresHexadecimales = "0123456789abcdef";
	    while (decimal > 0) {
	        int residuo = decimal % 16;
	        hexadecimal = caracteresHexadecimales.charAt(residuo) + hexadecimal; // Agregar a la izquierda
	        decimal /= 16;
	    }
	    return hexadecimal;
	}
	

}
