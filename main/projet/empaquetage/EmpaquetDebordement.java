import util.BitsUtil;


public class EmpaquetDebordement extends EmpaquetAbstrait {
    private int[] zoneDebordement; 
    private int nbDebordement;     // combien de valeurs dans la zone
    private int largeurNormal;     // largeur des petits nombres
    private int largeurIndex;      // largeur pour indexer la zone
    private int largeurTotal;      // largeurNormal + 1 (flag)

    public EmpaquetDebordement(boolean signe) {
        super(signe);
    }

    @Override
    protected void compresserInterne(int[] valeurs, int largeur) {
        
        int max = 0;
        for (int v : valeurs) {
            if (v <= 7) max = Math.max(max, v);
        }
        largeurNormal = BitsUtil.bitsNecessaires(max);

        
        int count = 0;
        for (int v : valeurs) if (v > (1 << largeurNormal) - 1) count++;
        nbDebordement = count;
        zoneDebordement = new int[count];

       
        largeurIndex = BitsUtil.bitsNecessaires(count);
        largeurTotal = 1 + Math.max(largeurNormal, largeurIndex);

       
        int[] encodes = new int[valeurs.length];
        int j = 0;
        for (int i = 0; i < valeurs.length; i++) {
            int v = valeurs[i];
            if (v <= (1 << largeurNormal) - 1) {
                
                encodes[i] = v << 1; 
            } else {
                
                zoneDebordement[j] = v;
                encodes[i] = (j << 1) | 1;
                j++;
            }
        }

       
        this.largeur = largeurTotal;
        EmpaquetCroise pack = new EmpaquetCroise(false);
        pack.compresser(encodes);
        this.compresse = ((EmpaquetAbstrait) pack).compresse;
    }

    @Override
    protected int lireA(int indice, int largeur) {
        EmpaquetCroise pack = new EmpaquetCroise(false);
        pack.compresse = this.compresse;
        pack.n = this.n;
        pack.largeur = this.largeur;
        int code = pack.lireA(indice, largeurTotal);

        int flag = code & 1;
        int val = code >>> 1;
        if (flag == 0) return val;
        else return zoneDebordement[val];
    }

    @Override
    protected void decompresserInterne(int[] destination, int largeur) {
        for (int i = 0; i < n; i++) {
            destination[i] = lireA(i, largeur);
        }
    }
}
