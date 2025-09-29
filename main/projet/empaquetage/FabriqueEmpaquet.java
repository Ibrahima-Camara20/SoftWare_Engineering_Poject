public final class FabriqueEmpaquet {
    private FabriqueEmpaquet() {} 

    public static EmpaquetBits creer(String type, boolean signe) {
        switch (type.toLowerCase()) {
            case "croise":
                return new EmpaquetCroise(signe);
            case "noncroise":
                return new EmpaquetNonCroise(signe);
            case "debordement":
                return new EmpaquetDebordement(signe);
            default:
                throw new IllegalArgumentException("Type d'empaqueteur inconnu : " + type);
        }
    }
}
