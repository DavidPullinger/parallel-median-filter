
public class SerialMedianFilter extends MedianFilter {

    public static void serialMedianFilter(int filterWidth, int border) {
        // needed for median filter
        int offset = 0;

        // fill left and right border
        for (int i = 0; i < Math.floor(border / 2.0); i++) {
            processedData[i] = data[i]; // left
            processedData[(data.length - 1) - i] = data[(data.length - 1) - i]; // right
        }

        // if filter width is even we need one more element for left border
        if (filterWidth % 2 == 0) {
            offset = 1; // need to shift data one element right because of border
            processedData[(int) Math.floor(border / 2.0)] = data[(int) Math.floor(border / 2.0)]; // left
        }

        // actual median filter
        for (int i = 0; i <= data.length - filterWidth; i++) {
            // fill up temp array with raw data from index i to index i+filter width
            Double[] temp = new Double[filterWidth];
            for (int j = i; j < i + filterWidth; j++) {
                temp[j - i] = data[j];
            }
            // add median to correct index of processed data
            processedData[(int) Math.floor(border / 2.0) + offset + i] = median(temp);
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
        serialMedianFilter(filterWidth, filterWidth - 1);
        // output processed data
        System.out.println(processedData.length);
        for (int i = 0; i < processedData.length; i++) {
            Double d = processedData[i];
            System.out.println(i + " " + Math.round(d * 1e5) / 1e5);
        }
    }
}
