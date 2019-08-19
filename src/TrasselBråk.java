import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class TrasselBråk {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        for(;;){
            menu();
        }
    }

    private static void menu() {
        System.out.println();
        System.out.println("Vad vill du göra?");
        System.out.println("    [1] Ange ett trasseltal på formen a/b ");
        System.out.println("    [2] Ange en konfiguration på formen T...TRT...TRT...");
        System.out.println("    [3] Göra olika drag live");
        System.out.println();

        switch (scanner.nextLine()){
            case "1":
                insertTrasseltal();
                break;
            case "2":
                insertConfiguration();
                break;
            case "3":
                insertMove();
                break;
        }
    }

    private static void insertMove() {
        List<Integer> t = Arrays.asList(0,0);

        for(;;){
            if(t.get(0) == 0 || t.get(1) == 0){
                System.out.println("Vi gör en tvinning eftersom trasseltal är 0.");
                System.out.println("Trasseltal = 1");
                t = Arrays.asList(1,1);
            }
            System.out.println("Vad vill du göra (R/T)?");
            switch(scanner.nextLine().charAt(0)){
                case 'R':
                    t = roteraFunction(t);
                    System.out.println("Trasseltal = " + utskrift(t));
                    break;
                case 'T':
                    t = tvinnaFunction(t);
                    System.out.println("Trasseltal = " + utskrift(t));
                    break;
                default:
                    menu();
            }
        }
    }

    private static void insertConfiguration() {
        System.out.println("Ange en konfiguration");
        String startKonfiguration = scanner.nextLine();
        List<Integer> t = Arrays.asList(1,1);
        for(char c : startKonfiguration.substring(1).toCharArray()){
            switch(c){
                case 'R':
                    t = roteraFunction(t);
                    break;
                case 'T':
                    t = tvinnaFunction(t);
                    break;
            }
        }
        System.out.println("Motsvarande trasseltal är " + utskrift(t));
    }

    private static void insertTrasseltal() {
        String recept = "";
        System.out.println("Vad har du för trasseltal? Svara på formen a/b");
        List<Integer> tStart = Arrays.stream(scanner.nextLine().split("/")).map(s->Integer.valueOf(s)).collect(Collectors.toList());
        List<Integer> t = new ArrayList<>(tStart);

        while(t.get(0) != 0 && t.get(1) != 0){

            if(trasselPositivt(t)){
                recept += "R";
                t = roteraFunction(t);
            }
            else{
                recept += "T";
                t = tvinnaFunction(t);
            }
        }
        System.out.println("Du ska göra " + recept + " för att lösa upp knuten.");
        kontrollera(tStart, recept);
    }

    private static String kompakt(String recept) {
        return Arrays.stream(recept.split("R")).map(t -> "RT" + t.length()).collect(Collectors.joining()).substring(1);
    }

    private static void kontrollera(List<Integer> tStart, String recept) {
        System.out.println("KONTROLLERAR:");
        System.out.println("Startar med Trasseltal = " + utskrift(tStart));

        for(char c : recept.toCharArray()){
            switch(c){
            case 'T':
                System.out.println("Utför T --> Trasseltal = " + utskrift(tvinnaFunction(tStart)));
                break;
            case 'R':
                System.out.println("Utför R --> Trasseltal = " + utskrift(roteraFunction(tStart)));
                break;
            }
        }
        System.out.println("Slutgiltigt värde på trasseltalet = " + utskrift(tStart));
    }

    private static String utskrift(List<Integer> t) {
        switch(t.get(1)){
            case 0:
                return 0 + "";
            case 1:
                return t.get(0) + "";
            case -1:
                return -1*t.get(0) + "";
            default:
                return t.get(0) + "/" + t.get(1);

        }
    }

    private static boolean trasselPositivt(List<Integer> t) {
        boolean täljareOchNämnarePos = t.get(0) > 0 && t.get(1) > 0;
        boolean täljareOchNämnareNeg = t.get(0) < 0 && t.get(1) < 0;
        boolean trasselPositivt = täljareOchNämnarePos || täljareOchNämnareNeg;
        return trasselPositivt;
    }

    private static List<Integer> tvinnaFunction(List<Integer> t) {
        t.set(0, t.get(0) + t.get(1));
        return t;
    }

    private static List<Integer> roteraFunction(List<Integer> t) {
        int täljareTemp = t.get(0);
        t.set(0,-1*t.get(1));
        t.set(1, täljareTemp);
        return t;
    }


}
