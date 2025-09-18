
public class Principal {
    public static void main(String[] args) {
        System.out.println("Projet Bit Packing démarré !");

        int[] donnees = {10, 20, 30, 40, 50};

        // --- Version croisée ---
        EmpaquetBits croise = new EmpaquetCroise(false);
        croise.compresser(donnees);
        int[] sortie1 = new int[donnees.length];
        croise.decompresser(sortie1);
        System.out.println("Croisé obtenir(2) = " + croise.obtenir(2));
        System.out.print("Croisé décompression : ");
        for (int v : sortie1) System.out.print(v + " ");
        System.out.println();

        // --- Version non croisée ---
        EmpaquetBits nonCroise = new EmpaquetNonCroise(false);
        nonCroise.compresser(donnees);
        int[] sortie2 = new int[donnees.length];
        nonCroise.decompresser(sortie2);
        System.out.println("Non croisé obtenir(2) = " + nonCroise.obtenir(2));
        System.out.print("Non croisé décompression : ");
        for (int v : sortie2) System.out.print(v + " ");
        System.out.println();
    }
}
