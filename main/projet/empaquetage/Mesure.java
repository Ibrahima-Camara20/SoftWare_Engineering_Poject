import java.util.Arrays;

public final class Mesure {
    private Mesure() {}
    
    
    public static void calculerLatenceSeuil(EmpaquetAbstrait empaqueteur, int nOriginal) {
        double Tc = empaqueteur.tempsCompresser / 1_000_000.0; // ms
        double Td = empaqueteur.tempsDecompresser / 1_000_000.0; // ms
        double N = nOriginal * 32.0; // bits (chaque int = 32 bits non compressé)
        double Nc = empaqueteur.totalBitsCompresses(); // bits après compression

        if (N == Nc) {
            System.out.println("Pas de gain de compression (N == Nc)");
            return;
        }

        double tSeuil = (Tc + Td) / (N - Nc);
        System.out.printf("Latence seuil t_seuil = %.9f ms/bit%n", tSeuil);
        System.out.printf("Si la latence réseau > %.9f ms/bit, la compression devient avantageuse.%n", tSeuil);
    }

    

    public static void executer(int[] donnees, String type, boolean signe) {
        System.out.println("\n--- Mesure pour type = " + type + " ---");

        EmpaquetBits empaqueteur = FabriqueEmpaquet.creer(type, signe);

        empaqueteur.compresser(donnees);

        System.out.println("Données originales   : " + Arrays.toString(donnees));
        int[] compresse = ((EmpaquetAbstrait)empaqueteur).compresse;
        System.out.println("Tableau compressé    : " + Arrays.toString(compresse));
        System.out.printf("Temps compression    : %.3f ms%n", 
            ((EmpaquetAbstrait)empaqueteur).tempsCompresser / 1_000_000.0);

        int[] sortie = new int[donnees.length];
        empaqueteur.decompresser(sortie);
        System.out.println("Tableau décompressé  : " + Arrays.toString(sortie));
        System.out.printf("Temps décompression  : %.3f ms%n", 
            ((EmpaquetAbstrait)empaqueteur).tempsDecompresser / 1_000_000.0);

        int index = donnees.length / 2;
        int valeur = empaqueteur.obtenir(index);
        System.out.println("Valeur originale à l’indice " + index + " : " + donnees[index]);
        System.out.println("Valeur obtenue depuis compresse : " + valeur);
        System.out.printf("Temps d’obtention               : %.6f ms%n", 
            ((EmpaquetAbstrait)empaqueteur).tempsObtenir / 1_000_000.0);
        
     // 🔹 Calcul du seuil de latence
        calculerLatenceSeuil((EmpaquetAbstrait)empaqueteur, donnees.length);
    }
}
