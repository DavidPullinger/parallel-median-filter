import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SerialMedianFilter {
    private static Double[] data;

    /**
     * reads data from given file name
     * 
     * @param fileName name of file containing data
     */
    public static void readData(String fileName) {
        try {
            // initialize scanner with file
            Scanner file = new Scanner(new File(fileName));
            // create array with size given in first line of file
            data = new Double[Integer.parseInt(file.nextLine())];
            // extract data from file
            while (file.hasNextLine()) {
                Scanner line = new Scanner(file.nextLine());
                data[Integer.parseInt(line.next())] = line.nextDouble();
            }
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        readData(args[0]);
    }
}
