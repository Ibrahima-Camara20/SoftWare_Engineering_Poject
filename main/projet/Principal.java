
public class Principal {
    public static void main(String[] args) {
        System.out.println("Projet Bit Packing démarré !");

        int[] donnees = {10, 20, 30, 40, 50};

        EmpaquetBits empaqueteur = new EmpaquetCroise(false);
        empaqueteur.compresser(donnees);

        int[] sortie = new int[donnees.length];
        empaqueteur.decompresser(sortie);

        System.out.println("Test obtenir(2) = " + empaqueteur.obtenir(2)); // doit afficher 30
        System.out.print("Décompression brute : ");
        for (int v : sortie) {
            System.out.print(v + " ");
        }
        System.out.println();
    }
}
