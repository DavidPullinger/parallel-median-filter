import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class SerialMedianFilter {
    private static Double[] data;
    private static Double[] processedData;

    /**
     * Reads data from given file name
     * 
     * @param fileName name of file containing data
     */
    public static void readData(String fileName) {
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
        }
    }

    public static void medianFilter(int filterWidth, int border) {
        // needed for median filter
        int offset = 0;

        // fill left and right border
        for (int i = 0; i < Math.floor(border / 2.0); i++) {
            processedData[i] = data[i]; // left
            processedData[(data.length - 1) - i] = data[(data.length - 1) - i]; // right
        }

        // if filterwidth is even we need one more element for left border
        if (filterWidth % 2 == 0) {
            offset = 1; // need to shift data one element right because of border
            processedData[(int) Math.floor(border / 2.0)] = data[(int) Math.floor(border / 2.0)]; // left
        }

        // actual median filter
        for (int i = 0; i <= data.length - filterWidth; i++) {
            // fill up temp array with raw data from index i to index i+filterwidth
            Double[] temp = new Double[filterWidth];
            for (int j = i; j < i + filterWidth; j++) {
                temp[j - i] = data[j];
            }
            // add median to correct index of processed data
            processedData[(int) Math.floor(border / 2.0) + offset + i] = median(temp);
        }
    }

    public static double median(Double[] numbers) {
        // sort numbers in list
        Arrays.sort(numbers);
        int n = numbers.length;
        // return median, checking where data is odd or even length
        return (n % 2 != 0) ? numbers[((n + 1) / 2 - 1)] : (numbers[n / 2 - 1] + numbers[n / 2]) / 2;
    }

    public static void myAssert(boolean expr) {
        if (!expr) {
            System.out.println("Width must be between 3 and 21 inclusive.");
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        // get args from command line
        String fileName = args[0];
        int filterWidth = Integer.parseInt(args[1]);
        // assert filter width length
        myAssert(filterWidth <= 21 && filterWidth >= 3);
        // read and process data
        readData("data/" + fileName + ".txt");
        medianFilter(filterWidth, filterWidth - 1);
        // output processed data
        System.out.println(processedData.length);
        for (int i = 0; i < processedData.length; i++) {
            Double d = processedData[i];
            System.out.println(i + " " + Math.round(d * 1e5) / 1e5);
        }
    }
}
