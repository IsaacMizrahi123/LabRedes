package Controlador;

import Vista.Principal;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Isaac
 */
public class Brain {

    public static String text;//Texto leido del archivo txt.
    public static String ASCIICode;//Codigo ASCII del texto leido.
    public static String HexCode;//Codigo Hex del taxto leido.
    public static String Bin;//Codigo Binario del Hex.
    public static String[] DataWords;//Datawords de 128bits maximo

    public static void main(String[] args) {
        Principal ventana = new Principal();
        ventana.setVisible(true);
    }

    public static void GetInfo(File f) { //Se consigue los valores de numregistro, numcampos, ks y tipos
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
            System.out.println(text);//Esto es para comprobar
            GetASCII(text);//En esta funcion se le asigna a la variable "ASCIICode" la traduccion de todo "text" a ASCCI
            GetHexAndBin(text);//En esta funcion 
        } catch (IOException e) {
            System.out.println("Error en GetInfo");
        }
    }

    public static void GetASCII(String characters) {//Nunca lo usamos... Pero bueno
        ASCIICode = "";
        for (int i = 0; i < characters.length(); i++) {
            ASCIICode = ASCIICode + Integer.toString((int) characters.charAt(i));
        }
        System.out.println(ASCIICode);
    }

    public static void GetHex(String characters) {//Es reemplazado por GetHexAndBin
        HexCode = "";
        for (int i = 0; i < characters.length(); i++) {
            HexCode = HexCode + Integer.toHexString((int) characters.charAt(i));
        }
        System.out.println(HexCode);
    }

    public static void GetHexAndBin(String characters) {//Se va conviertiendo el texto leido a hexadecimal y enseguida a binario, para ahorrar tiempo.
        HexCode = "";//Se "limpia" la variable HexCode donde se va a guardar la traduccion de "text" a su codigo hexadecimal.
        Bin = "";//Se "limpia" la variable Bin donde se va a guardar la traduccion de los codigos Hexadecimales a Binario. Que es lo que se necesita enviar.
        String temp = "",as,bs;//Variables necesarias para convertir y agregar caracteres a los Strings.
        int a,b;
        for (int i = 0; i < characters.length(); i++) {
            temp = Integer.toHexString((int) characters.charAt(i));//Se convierte letra por letra a Hexadecimal, que siempre son dos letras. Ej: Letra:k -> Hex:4b
            HexCode = HexCode + temp;//Se le agrega al string que va a mantener todos los codigos hexadecimales en secuencia.
            a = Integer.parseInt(Character.toString(temp.charAt(0)),16);//Se convierte el primer digito del numero hexadecimal a int para porder convertirlo a binario luego. Ej: String:4 -> Int:4
            b = Integer.parseInt(Character.toString(temp.charAt(1)),16);//Se convierte el segundo digito a numero. Ej: String:B -> Int:11
            as = Integer.toBinaryString(a);//Convierte el int a binario. Ej: Int: 4 -> Bin: 100
            bs = Integer.toBinaryString(b);//Convierte el segundo int a binario. Ej: Int:11 -> Bin:1011
            while(as.length()<4){//Se le agregan los ceros necesario para completar los 4 digitos. Ej: Bin:100 -> String:0100
                as="0"+as;
            }
            while(bs.length()<4){
                bs="0"+bs;
            }
            Bin = Bin + as + bs;//Se guardan los codigos binarios. Cada caracter son 8 digitos binarios.
        }
        System.out.println(HexCode);//Para comprobar
        System.out.println(Bin);//Para comprobar
    }

}
