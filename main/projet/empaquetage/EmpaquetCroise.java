public class EmpaquetCroise extends EmpaquetAbstrait {

    public EmpaquetCroise(boolean signe) { super(signe); }

    @Override
    protected void compresserInterne(int[] valeurs, int largeur) {
     
        int totalBits = n * largeur;
        int nbInts = (totalBits + 31) / 32;
        if (nbInts == 0) nbInts = 1;     
        compresse = new int[nbInts];

        int bitPos = 0;
        for (int i = 0; i < n; i++) {
            int v = valeurs[i];          
            for (int b = 0; b < largeur; b++) {
                int pos = bitPos + b;
                int bit = (v >>> b) & 1;
                compresse[pos >>> 5] |= (bit << (pos & 31));
            }
            bitPos += largeur;
        }
    }

    @Override
    protected int lireA(int indice, int largeur) {
       
        int bitPos = indice * largeur;
        int startInt = bitPos >>> 5;
        int offset   = bitPos & 31;

        long chunk = Integer.toUnsignedLong(compresse[startInt]) >>> offset;
        if (offset + largeur > 32 && startInt + 1 < compresse.length) {
            chunk |= (Integer.toUnsignedLong(compresse[startInt + 1]) << (32 - offset));
        }
        long mask = (largeur == 32) ? 0xFFFF_FFFFL : ((1L << largeur) - 1);
        return (int) (chunk & mask);     
    }

    @Override
    protected void decompresserInterne(int[] destination, int largeur) {
      
        for (int i = 0; i < n; i++) {
            destination[i] = lireA(i, largeur);
        }
    }
}
