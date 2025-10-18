package empaquetage;
public class EmpaquetNonCroise extends EmpaquetAbstrait {

    public EmpaquetNonCroise(boolean signe) { super(signe); }

    @Override
    protected void compresserInterne(int[] valeurs, int largeur) {
        int approxBitsParInt = (32 / largeur) * largeur;
        if (approxBitsParInt == 0) approxBitsParInt = 32;
        int nbInts = Math.max(1, (n * largeur + approxBitsParInt - 1) / approxBitsParInt);
        compresse = new int[nbInts];
        

        int bitPos = 0; 
        int curInt = 0;
        int offset = 0; 
        for (int i = 0; i < n; i++) {
            if (offset + largeur > 32) {
                curInt++;
                offset = 0;
                if (curInt >= compresse.length) {
                    int[] tmp = new int[compresse.length + 1];
                    System.arraycopy(compresse, 0, tmp, 0, compresse.length);
                    compresse = tmp;
                }
            }

            int v = valeurs[i]; 
            int mask = (largeur == 32) ? -1 : ((1 << largeur) - 1);
            compresse[curInt] |= (v & mask) << offset;

            offset += largeur;
            if (offset == 32) { curInt++; offset = 0; }
        }
    }

    @Override
    protected int lireA(int indice, int largeur) {
        int curInt = 0, offset = 0;
        for (int i = 0; i < indice; i++) {
            if (offset + largeur > 32) { curInt++; offset = 0; }
            offset += largeur;
            if (offset == 32) { curInt++; offset = 0; }
        }
        if (offset + largeur > 32) { curInt++; offset = 0; }

        int mask = (largeur == 32) ? -1 : ((1 << largeur) - 1);
        int enc  = (compresse[curInt] >>> offset) & mask;
        return enc; 
    }

    @Override
    protected void decompresserInterne(int[] destination, int largeur) {
        int curInt = 0, offset = 0;
        for (int i = 0; i < n; i++) {
            if (offset + largeur > 32) { curInt++; offset = 0; }
            int mask = (largeur == 32) ? -1 : ((1 << largeur) - 1);
            destination[i] = (compresse[curInt] >>> offset) & mask; 
            offset += largeur;
            if (offset == 32) { curInt++; offset = 0; }
        }
    }
}
