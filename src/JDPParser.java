import java.io.*;

public class JDPParser {

    public static final String FORMAT = ".jdp";

    private static final int[] header = new int[]{0x72, 0xFE, 0x12, 0x01};

    public void export(String name, int[][] x, int[][] y, String description) throws IOException {
        try(OutputStream os = new FileOutputStream("output"+FORMAT, false)) {
            // write header
            writeIntArray(header, os);

            // write name
            final int[] nm = name.chars().toArray();
            os.write(nm.length);
            os.write(new byte[]{0,0,0});
            writeIntArray(nm, os);

            // write coords
            writeCoords(x, os);
            writeCoords(y, os);

            // write description
            final int[] desc = description.chars().toArray();
            os.write(desc.length);
            os.write(new byte[]{0,0,0});
            writeIntArray(desc, os);

            os.flush();
        }
    }

    private void writeCoords(int[][] arr, OutputStream os) throws IOException {
        for (int[] i : arr) {
            writeIntArray(new int[]{ i.length, 0, 0, 0}, os);
            for (int j : i) {
                writeCoord(j, os);
            }
        }
    }

    private void writeCoord(int coord, OutputStream os) throws IOException {
        final StringBuffer sb = new StringBuffer();
        sb.append(coord);

        // printing count
        writeIntArray(new int[]{ sb.length(), 0, 0, 0}, os);
        for (int i = 0; i < sb.length(); i++) {
            os.write(sb.charAt(i));
        }

    }

    /**
     * Writes int array to output stream
     * @param arr
     * @param os
     * @throws IOException
     */
    private void writeIntArray(int[] arr, OutputStream os) throws IOException {
        for (int i : arr) {
            os.write(i);
        }
    }


    /**
     * Print file content (DEBUG use only)
     * @param filePath
     * @throws IOException
     */
    public static void printFile(String filePath) throws IOException {
        final FileReader fr = new FileReader(filePath);

        while (fr.ready()) {
            final int val = fr.read();
            final String str = String.format("Byte = %s,\tvalue = %s", val, (char) val);
            System.out.println(str);
        }

    }

}
