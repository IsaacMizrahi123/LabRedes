package Controlador;

import Vista.Principal;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 *
 * @author Isaac
 */
public class Brain {

    public static String text;//Texto leido del archivo txt.
    public static String ASCIICode;//Codigo ASCII del texto leido.
    public static String HexCode;//Codigo Hex del taxto leido.
    public static String Bin;//Codigo Binario del Hex.
    public static ArrayList DataWords;//Datawords de 128bits maximo
    public static String nombreDeSalida;//Nombre del txt de salida.
    //public static String DIV;//Polinomio necesario para codificar. Es un Binario
    public static ArrayList CodeWords;//Lista donde se almacenaran los codewords
    public static String Generador;

    public static void main(String[] args) {
        Principal ventana = new Principal();
        ventana.setVisible(true);
    }

    public static void GetInfo(File f, int tipo) { //Se consigue los valores de numregistro, numcampos, ks y tipos
        String line;//Con la que se saca linea por linea  el texto del txt.
        text = "";//Se "limpia" la variable donde todo el texto se va a contener.
        try {
            FileReader fr = new FileReader(f.getAbsolutePath());
            BufferedReader br = new BufferedReader(fr);
            line = br.readLine();
            while (line != null) {
                text = text + line;//Se agrega linea por linea del texto a la variable text. No se tiene ninguna separacion entre lineas.
                line = br.readLine();
            }
            br.close();
            fr.close();
            if(tipo == 1){
                System.out.println(text);//Esto es para comprobar
                GetASCII(text);//En esta funcion se le asigna a la variable "ASCIICode" la traduccion de todo "text" a ASCCI
            //GetHexAndBin(text);
                GetHexBinDataWords(text);//En esta funcion se hayan los valores hexadecimales y binarios del texto y se guarda en una lista los datawords
            }else{
                System.out.println("Su CodeWord es: "+text);//Esto es para comprobar
            }
                
            
            //Se debe leer DIV antes!
            
        } catch (IOException e) {
            System.out.println("Error en GetInfo");
        }
    }

    public static void GetASCII(String characters) {//Nunca lo usamos... Pero bueno
        ASCIICode = "";
        for (int i = 0; i < characters.length(); i++) {
            ASCIICode = ASCIICode + Integer.toString((int) characters.charAt(i));
        }
        System.out.println("El codigo ASCII es: "+ASCIICode);
    }

//    public static void GetHex(String characters) {//Es reemplazado por GetHexAndBin
//        HexCode = "";
//        for (int i = 0; i < characters.length(); i++) {
//            HexCode = HexCode + Integer.toHexString((int) characters.charAt(i));
//        }
//        System.out.println(HexCode);
//    }

//    public static void GetHexAndBin(String characters) {//Se va conviertiendo el texto leido a hexadecimal y enseguida a binario, para ahorrar tiempo.
//        HexCode = "";//Se "limpia" la variable HexCode donde se va a guardar la traduccion de "text" a su codigo hexadecimal.
//        Bin = "";//Se "limpia" la variable Bin donde se va a guardar la traduccion de los codigos Hexadecimales a Binario. Que es lo que se necesita enviar.
//        String temp = "", as, bs;//Variables necesarias para convertir y agregar caracteres a los Strings.
//        int a, b;
//        for (int i = 0; i < characters.length(); i++) {
//            temp = Integer.toHexString((int) characters.charAt(i));//Se convierte letra por letra a Hexadecimal, que siempre son dos letras. Ej: Letra:k -> Hex:4b
//            HexCode = HexCode + temp;//Se le agrega al string que va a mantener todos los codigos hexadecimales en secuencia.
//            a = Integer.parseInt(Character.toString(temp.charAt(0)), 16);//Se convierte el primer digito del numero hexadecimal a int para porder convertirlo a binario luego. Ej: String:4 -> Int:4
//            b = Integer.parseInt(Character.toString(temp.charAt(1)), 16);//Se convierte el segundo digito a numero. Ej: String:B -> Int:11
//            as = Integer.toBinaryString(a);//Convierte el int a binario. Ej: Int: 4 -> Bin: 100
//            bs = Integer.toBinaryString(b);//Convierte el segundo int a binario. Ej: Int:11 -> Bin:1011
//            while (as.length() < 4) {//Se le agregan los ceros necesario para completar los 4 digitos. Ej: Bin:100 -> String:0100
//                as = "0" + as;
//            }
//            while (bs.length() < 4) {
//                bs = "0" + bs;
//            }
//            Bin = Bin + as + bs;//Se guardan los codigos binarios. Cada caracter son 8 digitos binarios.
//        }
//        System.out.println(HexCode);//Para comprobar
//        System.out.println(Bin);//Para comprobar
//    }

//    public static void NameOutput(String s) {//Funcion para nombrar el archivo de salida. No se le esta agregando la extencion txt.
//        if (s.isEmpty()) {
//            nombreDeSalida = "salida";
//        } else {
//            nombreDeSalida = s;
//        }
//    }

    public static void GetHexBinDataWords(String characters) {//Se va conviertiendo el texto leido a hexadecimal y enseguida a binario, para ahorrar tiempo. Y enseguida se van separando las DataWords


        HexCode = "";//Se "limpia" la variable HexCode donde se va a guardar la traduccion de "text" a su codigo hexadecimal.
        Bin = "";//Se "limpia" la variable Bin donde se va a guardar la traduccion de los codigos Hexadecimales a Binario. Que es lo que se necesita enviar.
        String temp = "", as, bs;//Variables necesarias para convertir y agregar caracteres a los Strings.
        int a, b;
        DataWords = new ArrayList<>();//Inicializo donde voy a guardar los DataWords.
        String dataw = "";//Variable donde se guarden los binarios para formar los datawords.
        CodeWords = new ArrayList<>();//Inicializo la lista de codewords.
        for (int i = 0; i < characters.length(); i++) {
            temp = Integer.toHexString((int) characters.charAt(i));//Se convierte letra por letra a Hexadecimal, que siempre son dos letras. Ej: Letra:k -> Hex:4b
            HexCode = HexCode + temp;//Se le agrega al string que va a mantener todos los codigos hexadecimales en secuencia.
            a = Integer.parseInt(Character.toString(temp.charAt(0)), 16);//Se convierte el primer digito del numero hexadecimal a int para porder convertirlo a binario luego. Ej: String:4 -> Int:4
            b = Integer.parseInt(Character.toString(temp.charAt(1)), 16);//Se convierte el segundo digito a numero. Ej: String:B -> Int:11
            as = Integer.toBinaryString(a);//Convierte el int a binario. Ej: Int: 4 -> Bin: 100
            bs = Integer.toBinaryString(b);//Convierte el segundo int a binario. Ej: Int:11 -> Bin:1011
            while (as.length() < 4) {//Se le agregan los ceros necesario para completar los 4 digitos. Ej: Bin:100 -> String:0100
                as = "0" + as;
            }
            while (bs.length() < 4) {
                bs = "0" + bs;
            }
            Bin = Bin + as + bs;//Se guardan los codigos binarios. Cada caracter son 8 digitos binarios.

            dataw = dataw + as + bs;//Se le agrega el info al dataword que se esta formando.
            if (dataw.length() >= 128 || i == characters.length() - 1) {//Cuando el dataword llegue a los 128 digitos o cuando ya no se le vaya a agregar mas info, se agrega a la lista de DataWords.
                DataWords.add(dataw);
                CodeWords.add(GetCodeWord(dataw));//Se codifica enseguida.
                dataw = "";//Se reinicia la variable para grabar en ella los proximos 128 digitos.
            }
            System.out.println("El codeWord es: ");
            for (int j = 0; j < CodeWords.size(); j++) {
                System.out.println(CodeWords.get(j));
            }
        }
        System.out.println(HexCode);//Para comprobar
        System.out.println(Bin);//Para comprobar
        System.out.println("Deberian haber " + (Bin.length() / 128 + 1) + " Datawords aproximadamente.");//Para comprobar
        System.out.println("Lista con tamano: " + DataWords.size());//Para comprobar
        System.out.println("El ultimo data word es: ");//Para comprobar
        System.out.println((DataWords.get(DataWords.size() - 1)));
    }

    public static void AsignarPolinomioGenerador(String s) {
       Generador = s;
    }

    /*  
        A partir del dataword crea el codeword
        @param dataword
        @return codeword
    */
    public static String GetCodeWord(String dataword) {
        String dividendo = dataword;
        String ceros = "";
        for (int i = 0; i < Generador.length() - 1; i++) { //se generan la cantidad de 0's necesarios
            ceros = ceros + "0";
        }
        dividendo = dividendo + ceros; //se le adicionan los 0's al datawors
        System.out.println("Dividendo=" + dividendo);
        ceros = ceros + "0";
        System.out.println("Ceros=" + ceros);

        dividendo = dataword + Div(dividendo, Generador, ceros); //obtiene el residuo y se le adiciona al dataword para crear el codeword 
        System.out.println("Dividendo: " + dividendo);
        return dividendo;
    }

    /**
     * Realiza la division binaria
     * @param dividendo
     * @param divisor 
     * @param ceros 
     * @return residuo
     */
    public static String Div(String dividendo, String divisor, String ceros) {
        String residuo = dividendo.substring(0, divisor.length()); //se obtienen los primeros k digitos del divisor
        System.out.println("Residuo 1: " + residuo);
        for (int i = 0; i <= dividendo.length() - divisor.length(); i++) { //se realiza la division

            if (residuo.startsWith("1")) { //si el residuo empieza por 1 el XOR se debe realizar con el divisor, de lo contrario con la cadena de 0's
                System.out.println("Entro en 1");
                residuo = XOR(residuo.substring(1), divisor.substring(1));
                System.out.println("Residuo XOR=" + residuo);
            } else {
                System.out.println("Entro en 0");
                residuo = XOR(residuo.substring(1), ceros.substring(1));
                System.out.println("Residuo XOR=" + residuo);
            }
            if (i != dividendo.length() - divisor.length()) {
                residuo = residuo + dividendo.substring(divisor.length() + i, divisor.length() + i + 1); //Baja el siguiente digito para que quede de la misma longitud que el divisor 
            }
            System.out.println("Residuo " + i + "=" + residuo);
        }
        return residuo;
    }

    /**
     * Devuelve el XOR de las cadenas dadas
     * @param a cadena binaria 
     * @param b cadena binaria
     * @return la funcion XOR
     */
    public static String XOR(String a, String b) {
        String resultado = "";
        for (int i = 0; i < a.length(); i++) {
            if (a.substring(i, i + 1).equals(b.substring(i, i + 1))) {
                resultado = resultado + "0";
            } else {
                resultado = resultado + "1";
            }

        }
        return resultado;
    }

}
