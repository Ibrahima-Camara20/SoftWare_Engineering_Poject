
public class Principal {
    public static void main(String[] args) {
        System.out.println("Projet Bit Packing démarré !");

        int[] donnees = {10, 20, 30, 40, 50};

      
  
        EmpaquetBits croise = FabriqueEmpaquet.creer("croise", false);
        croise.compresser(donnees);
        System.out.println("Croisé obtenir(2) = " + croise.obtenir(2));

        
        EmpaquetBits nonCroise = FabriqueEmpaquet.creer("noncroise", false);
        nonCroise.compresser(donnees);
        System.out.println("Non croisé obtenir(2) = " + nonCroise.obtenir(2));
    }
}
