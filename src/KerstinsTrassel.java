import java.util.Scanner;

public class KerstinsTrassel {

    public static void main(String[] args) {
        int tangleNum = 0; // Täljare
        int tangleDen = 1; // Nämnare
        String tangle = ""; // Trassel ges som en textsträng
        Scanner scanner = new Scanner(System.in); // För input från användaren
        while (!tangle.equals("S")) {
            System.out.println("Skriv in ett trassel på formatet RTRRTTT. Avsluta med S.");
            tangle = scanner.nextLine().toUpperCase(); // Trasselsträngen läses in och görs om till stora bokstäver
            for (char c : tangle.toCharArray()) {
                if (c == 'R') {
                    int temp = tangleDen * (-1);
                    tangleDen = tangleNum;
                    tangleNum = temp;
                } else if (c == 'T') {
                    tangleNum += tangleDen;
                } else if (c == 'S') {
                    tangle = "S"; // S någonstans i strängen avslutar programmet.
                } else {
                    System.out.println("Endast R och T är tillåtna i trasslet.");
                }
            }
            if (tangle.equals("S")) {
                // Ingen utskrift eftersom programmet ska avslutas    
            } else if (tangleDen == 1) {
                System.out.println("Trasseltal = " + tangleNum);
            } else if (tangleDen == -1) {
                System.out.println("Trasseltal = " + (-tangleNum));
            } else if (tangleDen < 0) {
                System.out.println("Trasseltal = " + (-tangleNum) + "/" + (-tangleDen));
            } else {
                System.out.println("Trasseltal = " + tangleNum + "/" + tangleDen);
            }
            tangleDen = 1; // Sätt startvärden
            tangleNum = 0;
        }
    }
}