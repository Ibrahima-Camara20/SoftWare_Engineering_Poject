public class Principal {
    public static void main(String[] args) {
        System.out.println("Projet Bit Packing démarré !");

       
        int[] donnees = {10, 20, 30, 40, 50, 60, 70, 80};

        
        Mesure.executer(donnees, "croise", false);
        Mesure.executer(donnees, "noncroise", false);
        
        System.out.println("-----------------------Acceder Croise------------------------------");
        
        EmpaquetBits croise = FabriqueEmpaquet.creer("croise", false);
        croise.compresser(donnees);

        int index = 6;
        long debut = System.nanoTime();
        int valeur = croise.obtenir(index);
        long fin = System.nanoTime();

        System.out.println("Valeur originale à l'indice " + index + " : " + donnees[index]);
        System.out.println("Valeur obtenue depuis compresse : " + valeur);
        System.out.printf("Temps d'obtention               : %.6f ms%n", (fin - debut) / 1_000_000.0);
        System.out.println("-----------------------Acceder NonCroise------------------------------");
        
        EmpaquetBits croisee = FabriqueEmpaquet.creer("noncroise", false);
        croisee.compresser(donnees);

        int indexx = 6;
        long debutt = System.nanoTime();
        int valeurr = croisee.obtenir(indexx);
        long finn = System.nanoTime();

        System.out.println("Valeur originale à l'indice " + indexx + " : " + donnees[indexx]);
        System.out.println("Valeur obtenue depuis compresse : " + valeur);
        System.out.printf("Temps d'obtention               : %.6f ms%n", (finn - debutt) / 1_000_000.0);
    }
}
