import empaquetage.*;

public class Principal {
    public static void main(String[] args) {
        System.out.println("Projet Bit Packing démarré !");

        int[] donnees1 = {10, 20, 30, 40, 50, 60, 70, 80};
        int[] donnees2 = {1, 2, 3, 1024, 4, 5, 2048}; 

        System.out.println("\n================== Jeu de données 1 ==================");
        testerTous(donnees1);

        System.out.println("\n================== Jeu de données 2 ==================");
        testerTous(donnees2);
    }

    private static void testerTous(int[] donnees) {
        String[] types = {"croise", "noncroise", "debordement"};

        for (String type : types) {
            try {
                Mesure.executer(donnees, type, false);
                Benchmark.executer();
            } catch (Exception e) {
                System.out.println("⚠️ Erreur pour le type " + type + " : " + e.getMessage());
            }
        }
    }
    
}
