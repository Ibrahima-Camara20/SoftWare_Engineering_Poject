import util.BitsUtil;
import util.CodecSigne;


public abstract class EmpaquetAbstrait implements EmpaquetBits {
    protected final boolean signe;   
    protected int n;                 
    protected int largeur;           
    protected int[] compresse;       

    public EmpaquetAbstrait(boolean signe) {
        this.signe = signe;
    }

    @Override
    public int longueur() {
        return n;
    }

    protected int calculerLargeur(int[] source) {
        int max = 0;
        for (int v : source) {
            int u = signe ? encoderSigne(v) : v;
            if (u > max) max = u;
        }
        return source.length == 0 ? 0 : BitsUtil.bitsNecessaires(max);
    }

    protected int encoderSigne(int v) {
        if (signe) {
            return CodecSigne.encoderZigZag(v);
        } else {
            if (v < 0) {
                throw new IllegalArgumentException("Valeur négative en mode non signé !");
            }
            return v;
        }
    }

    protected int decoderSigne(int u) {
        if (signe) {
            return CodecSigne.decoderZigZag(u);
        } else {
            return u;
        }
    }

    @Override
    public long totalBitsCompresses() {
        return (long) compresse.length * 32L;
    }

    protected abstract void compresserInterne(int[] valeurs, int largeur);
    protected abstract int  lireA(int indice, int largeur);
    protected abstract void decompresserInterne(int[] destination, int largeur);

    @Override
    public void compresser(int[] source) {
        this.n = source.length;
        this.largeur = calculerLargeur(source);
        int[] valeursEncodees = new int[n];
        for (int i = 0; i < n; i++) {
            valeursEncodees[i] = encoderSigne(source[i]);
        }
        compresserInterne(valeursEncodees, largeur);
    }

    @Override
    public void decompresser(int[] destination) {
        decompresserInterne(destination, largeur);
        for (int i = 0; i < n; i++) {
            destination[i] = decoderSigne(destination[i]);
        }
    }

    @Override
    public int obtenir(int i) {
        int u = lireA(i, largeur);
        return decoderSigne(u);
    }
}
