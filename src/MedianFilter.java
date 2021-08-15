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

    protected static void prefix(int filterWidth, int border) {
        for (int i = 0; i < border; i++) {
            double d = Math.round(data[i] * 1e5) / 1e5;
            System.out.println(i + " " + String.format("%.5f", d));
        }
        // if filter width is even we need one more element for prefix border
        if (filterWidth % 2 == 0) {
            double d = Math.round(data[border] * 1e5) / 1e5;
            System.out.println(border + " " + String.format("%.5f", d));
        }
    }

    protected static void suffix(int filterWidth, int border) {
        for (int i = data.length - border; i < data.length; i++) {
            double d = Math.round(data[i] * 1e5) / 1e5;
            System.out.println(i + " " + String.format("%.5f", d));
        }
    }

    protected static void medianFilter(int filterWidth, int border) {
        for (int i = 0; i <= data.length - filterWidth; i++) {
            // fill up temp array with raw data from index i to index i+filter width
            Double[] temp = new Double[filterWidth];
            for (int j = i; j < i + filterWidth; j++) {
                temp[j - i] = data[j];
            }
            // add median to correct index of processed data
            processedData[i] = median(temp);
        }
    }

    protected static void myAssert(boolean expr) {
        if (!expr) {
            System.out.println("Width must be between 3 and 21 inclusive.");
            System.exit(1);
        }
    }
}
