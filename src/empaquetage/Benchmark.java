package empaquetage;
import java.util.Random;

public final class Benchmark {
    private Benchmark() {}

  
    private static int[] tableauAleatoire(int n, int max) {
        Random r = new Random(42); 
        int[] tab = new int[n];
        for (int i = 0; i < n; i++) tab[i] = r.nextInt(max);
        return tab;
    }

    // 🔹 Exécute les mesures sur un jeu de données et un empaqueteur
    public static void tester(int[] donnees, String type) {
        EmpaquetBits emp = FabriqueEmpaquet.creer(type, false);
        EmpaquetAbstrait a = (EmpaquetAbstrait) emp;

        emp.compresser(donnees);
        int[] sortie = new int[donnees.length];
        emp.decompresser(sortie);

        // Calcul taux compression
        double N = donnees.length * 32.0;
        double Nc = a.totalBitsCompresses();
        double taux = Nc / N;
        double gain = (1 - taux) * 100.0;

        System.out.printf(
            "%-12s | %7d | %7.3f ms | %7.3f ms | %7.6f ms | %7.2f %% gain | %7.0f bits\n",
            type,
            donnees.length,
            a.tempsCompresser / 1_000_000.0,
            a.tempsDecompresser / 1_000_000.0,
            a.tempsObtenir / 1_000_000.0,
            gain,
            Nc
        );
    }

    public static void executer() {
        System.out.println("=== BENCHMARK GLOBAL ===\n");
        System.out.println("Type         |  Taille |   Tcomp |   Tdecomp |    Tget |   Gain(%) |  BitsComp");
        System.out.println("--------------------------------------------------------------------------");

        // 🔹 Scénario 1 : petites valeurs
        int[] petits = tableauAleatoire(1000, 64);
        for (String type : new String[]{"croise", "noncroise", "debordement"}) {
            tester(petits, type);
        }

        // 🔹 Scénario 2 : grands entiers
        int[] grands = tableauAleatoire(1000, 1_000_000);
        for (String type : new String[]{"croise", "noncroise", "debordement"}) {
            tester(grands, type);
        }

        // 🔹 Scénario 3 : mélange petits + gros
        int[] mixte = new int[1000];
        Random r = new Random(123);
        for (int i = 0; i < 1000; i++) {
            mixte[i] = (i % 50 == 0) ? r.nextInt(1_000_000) : r.nextInt(128);
        }
        for (String type : new String[]{"croise", "noncroise", "debordement"}) {
            tester(mixte, type);
        }
    }
}
