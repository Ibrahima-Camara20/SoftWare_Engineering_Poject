public interface EmpaquetBits {
    void compresser(int[] source);
    void decompresser(int[] destination);
    int obtenir(int i);
    int longueur();
    long totalBitsCompresses();
}