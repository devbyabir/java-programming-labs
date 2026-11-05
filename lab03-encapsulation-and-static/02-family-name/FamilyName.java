public class FamilyName {
    static String surName;
    String givenName;

    public FamilyName(String givenName) {
        this.givenName = givenName;
    }

    public static void main(String[] args) {
        FamilyName.surName = "Abir";

        FamilyName Ahmmed = new FamilyName("Ahmmed");
        FamilyName Hasan = new FamilyName("Hasan");
        FamilyName Alak = new FamilyName("Alak");

        System.out.println("Full Name: " + FamilyName.surName + " " + Ahmmed.givenName);
        System.out.println("Full Name: " + FamilyName.surName + " " + Hasan.givenName);
        System.out.println("Full Name: " + FamilyName.surName + " " + Alak.givenName);
    }
}
