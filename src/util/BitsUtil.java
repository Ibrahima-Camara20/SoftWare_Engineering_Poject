package util;
public final class BitsUtil {
    private BitsUtil() {} 

   
    public static int bitsNecessaires(int valeur) {
        return 32 - Integer.numberOfLeadingZeros(valeur);
    }
    
    public static void afficherBits(int[] tableau) {
        for (int i = 0; i < tableau.length; i++) {
            String bits = String.format("%32s", Integer.toBinaryString(tableau[i]))
                               .replace(' ', '0');
            System.out.println("compresse[" + i + "] = " + bits);
        }
    }
}