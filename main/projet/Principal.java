public class Principal {
    public static void main(String[] args) {
        System.out.println("Projet Bit Packing démarré !");

       
        int[] donnees = {10, 20, 30, 40, 50};

        
        Mesure.executer(donnees, "croise", false);
        Mesure.executer(donnees, "noncroise", false);
    }
}
