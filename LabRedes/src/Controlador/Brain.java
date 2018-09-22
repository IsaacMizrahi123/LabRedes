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
        String line;
        text = "";
        try {
            FileReader fr = new FileReader(f.getAbsolutePath());
            BufferedReader br = new BufferedReader(fr);
            line = br.readLine();
            while (line != null) {
                text = text + line;
                line = br.readLine();
            }
            br.close();
            fr.close();
            System.out.println(text);//Esto es para comprobar
            GetASCII(text);
            GetHexAndBin(text);
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

    public static void GetHexAndBin(String characters) {
        HexCode = "";
        Bin = "";
        String temp = "",as,bs;
        int a,b;
        for (int i = 0; i < characters.length(); i++) {
            temp = Integer.toHexString((int) characters.charAt(i));
            HexCode = HexCode + temp;
            a = Integer.parseInt(Character.toString(temp.charAt(0)),16);
            b = Integer.parseInt(Character.toString(temp.charAt(1)),16);
            as = Integer.toBinaryString(a);
            bs = Integer.toBinaryString(b);
            while(as.length()<4){
                as="0"+as;
            }
            while(bs.length()<4){
                bs="0"+bs;
            }
            Bin = Bin + as + bs;
        }
        System.out.println(HexCode);//Para comprobar
        System.out.println(Bin);//Para comprobar
    }

}
