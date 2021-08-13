import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;

public class MedianFilter {
    protected static Double[] data;
    protected static Double[] processedData;

    /**
     * Reads data from given file name
     * 
     * @param fileName name of file containing data
     */
    protected static void readData(String fileName) {
        try {
            // initialize scanner with file
            Scanner file = new Scanner(new File(fileName));
            // create array with size given in first line of file
            data = new Double[Integer.parseInt(file.nextLine())];
            processedData = new Double[data.length];
            // extract data from file
            while (file.hasNextLine()) {
                Scanner line = new Scanner(file.nextLine());
                data[Integer.parseInt(line.next())] = line.nextDouble();
            }
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT FOUND: " + e.getMessage());
            System.exit(1);
        }
    }

    protected static double median(Double[] numbers) {
        // sort numbers in list
        Arrays.sort(numbers);
        int n = numbers.length;
        // return median, checking where data is odd or even length
        return (n % 2 != 0) ? numbers[((n + 1) / 2 - 1)] : (numbers[n / 2 - 1] + numbers[n / 2]) / 2;
    }

    protected static void myAssert(boolean expr) {
        if (!expr) {
            System.out.println("Width must be between 3 and 21 inclusive.");
            System.exit(1);
        }
    }
}
