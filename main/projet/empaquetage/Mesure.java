import java.util.Arrays;

public final class Mesure {
    private Mesure() {}

    public static void executer(int[] donnees, String type, boolean signe) {
        System.out.println("\n--- Mesure pour type = " + type + " ---");

        EmpaquetBits empaqueteur = FabriqueEmpaquet.creer(type, signe);

        
        long debutComp = System.nanoTime();
        empaqueteur.compresser(donnees);
        long finComp = System.nanoTime();

        System.out.println("Données originales   : " + Arrays.toString(donnees));

        
        int[] compresse = ((EmpaquetAbstrait)empaqueteur).compresse;
        System.out.println("Tableau compressé    : " + Arrays.toString(compresse));
        System.out.printf("Temps compression    : %.3f ms%n", (finComp - debutComp) / 1_000_000.0);

      
        int[] sortie = new int[donnees.length];
        long debutDecomp = System.nanoTime();
        empaqueteur.decompresser(sortie);
        long finDecomp = System.nanoTime();

        System.out.println("Tableau décompressé  : " + Arrays.toString(sortie));
        System.out.printf("Temps décompression  : %.3f ms%n", (finDecomp - debutDecomp) / 1_000_000.0);
    }
}
