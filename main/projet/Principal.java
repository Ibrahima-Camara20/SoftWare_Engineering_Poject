import util.BitsUtil;

public class Principal {
    public static void main(String[] args) {
        System.out.println("Projet Bit Packing démarré !");

        
        System.out.println("bitsNecessaires(5) = " + BitsUtil.bitsNecessaires(5));       
        System.out.println("bitsNecessaires(1024) = " + BitsUtil.bitsNecessaires(1024)); 
        System.out.println("bitsNecessaires(0) = " + BitsUtil.bitsNecessaires(0));       
    }
}