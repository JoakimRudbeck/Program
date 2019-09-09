 /*
        Instruktioner för en ovan användare:
            * Gå till en online java compiler, t.ex. https://www.onlinegdb.com/online_java_compiler
            * Kopiera allt som finns i denna fil
            * Klistra in allt som finns i denna fil i den java online compiler som du använder.
            * Kör programmet i din java online compiler (run/execute/grön knapp med play symbol eller motsv.)
 */
import java.util.Scanner;

public class CaesarChiffer {

    // Alla java-program börjar alltid här i main-metoden.
    public static void main(String[] args) {
        System.out.println("Välkommen till ett program om caesarchiffer");

        // En oändlig loop som anropar menyn om och om igen.
        while(true){
            menu();
        }
    }

    // Denna scanner används för att ta in input från användaren.
    public static Scanner scanner = new Scanner(System.in);

    private static void menu() {
        // try och catch används för att hantera fel som kan uppstå.
        try{
            System.out.println(); // En tom rad
            System.out.println("Vad vill du göra?");
            System.out.println("    [1] Kryptera en text.");
            System.out.println("    [2] Dekryptera en text.");
            // Plockar ut första tecknet som användaren skriver in.
            int val = Integer.valueOf(scanner.nextLine().substring(0,1));

            // Baserat på användarens val utförs antingen kryptering eller dekryptering.
            switch(val){
                case 1:
                    kryptera();
                    break;
                case 2:
                    dekryptera();
                    break;
                default:
                    System.out.println("Vänligen ange 1 eller 2.");
            }
        }
        catch(Exception e){
            System.out.println("Något gick fel.");
        }
    }

    private static void kryptera() {
        System.out.println("Skriv in en text som du vill kryptera");
        String text = scanner.nextLine();
        System.out.println("Hur många steg vill du shifta?");
        int nyckel = Integer.valueOf(scanner.nextLine());
        // Texten (text) och nyckeln (nyckel) skickas till metoden kryptera och tillbaks kommer de krypterade meddelandet.
        String krypteratMeddelande = kryptera(text, nyckel);
        System.out.println("Krypterat meddelande är " + krypteratMeddelande);
    }

    private static String kryptera(String text, int nyckel) {
        String krypteratMeddelande = "";
        // För varje tecken c i texten:
        for(char c : text.toCharArray()){
            // 65 motsvarar stora A, 66 Stora B osv...
            // 97 motsvarar lilla a, 98 motsvarar lilla b osv...
            // Se ASCII-tabell

            // Stora bokstäver
            if(65 <= c && c <= 90) {
                // Förskjuter tecknet åt höger nyckelns antal steg
                // Tar hänsyn till att efter A kommer Z (därav modulo 26)
                krypteratMeddelande += (char) (65 + ((int) c-65 + nyckel + 26) % 26); // % betyder modulo
            }
            // Små bokstäver
            else if(97 <= c && c <= 122){
                krypteratMeddelande += (char) (97 + ((int) c-97 + nyckel + 26) % 26);
            }
            // Övriga tecken ändras inte
            else{
                krypteratMeddelande += "" + c;
            }
        }
        return krypteratMeddelande;
    }

    private static void dekryptera() {
        System.out.println("Skriv in en text som du vill dekryptera");
        String text = scanner.nextLine();
        System.out.println("Hur många steg vill du shifta?");
        int nyckel = Integer.valueOf(scanner.nextLine());
        String dekrypteratMeddelande = dekryptera(text, nyckel);
        System.out.println("Dekrypterat meddelande är " + dekrypteratMeddelande);
    }

    private static String dekryptera(String text, int nyckel) {
        // Att dekryptera är samma sak som att kryptera fast med nyckel = -nyckel
        return kryptera(text,-nyckel);
    }
}
