import java.io.IOException;

public class App {

    public App() throws IOException {

        System.out.println("STARTED!");
//        JDPParser.printFile("aaa.jdp");

        System.out.println("\n\n\n\n");

        final int[][] u = new int[][]{
                {0, 5, 10, 30, 108, 110, 112, 120, 135, 150, 180},  // x
                {0, 30,  40,  50,  40,  30,  27,  20,  10,  5,  0 },// y
        };

        final int[][] d = new int[][]{
                {0, 5, 105, 106, 108, 110, 112, 120, 135, 150, 180},  // x
                {0, -20, -20, -20, -20, -20, -20, -20, -10,  -5, 0}   // y
        };

        new JDPParser().export("333", u, d, "testtesttest");

        JDPParser.printFile("output.jdp");
    }
}
