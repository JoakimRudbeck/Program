public class ApproximateE {



    public static void main(String[] args) {

        double a;
        double h;
        double delta;
        double gränsvärdet;



        a = 2.7;                                                        // Startvärde på a
        h = 0.00001;                                                    // värde på h
        delta = 0.0001;                                                 // värde på precisionen

        gränsvärdet = (Math.pow(a, h) - 1) / h;                         // Beräknar gränsvärdet (a^h - 1) / h

        if(Math.abs(gränsvärdet - 1) < delta){                          // Om gränsvärdet var tillräckligt nära 1
            System.out.println("En approximation av e är " + a);
        }
        else{
            while(Math.abs(gränsvärdet - 1)  > delta){                  // Så länge gränsvärdet inte är tillräckligt nära 1
                a = a + 0.00001;                                        // Öka värdet på a
                gränsvärdet = (Math.pow(a, h) - 1) / h;                 // Beräkna nytt gränsvärde med nya uppdaterade värdet på a
            }
            System.out.println("En approximation av e är " + a);        // Nu har vi ett gränsvärde tillräckligt nära 1
        }
    }
}
