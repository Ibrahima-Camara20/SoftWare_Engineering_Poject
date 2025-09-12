package util;

public final class BitsUtil {
    private BitsUtil() {} 

   
    public static int bitsNecessaires(int v) {
        if (v < 0) {
            throw new IllegalArgumentException("bitsNecessaires attend un entier >= 0");
        }
        return v == 0 ? 1 : 32 - Integer.numberOfLeadingZeros(v);
    }
}