public class SerialMedianFilter extends MedianFilter {

    private static void serialMedianFilter(int filterWidth) {
        medianFilter(filterWidth, 0, data.length - filterWidth);
    }

    public static void main(String[] args) {
        // get args from command line
        String fileName = args[0];
        int filterWidth = Integer.parseInt(args[1]);
        int border = (int) Math.floor((filterWidth - 1) / 2.0);
        int offset = 0;
        if (filterWidth % 2 == 0)
            offset = 1;
        // assert filter width length
        myAssert(filterWidth <= 21 && filterWidth >= 3);

        // read and process data
        readData("data/" + fileName + ".txt");
        System.gc();
        for (int i = 0; i < 15; i++) {
            float startTime = System.nanoTime();
            serialMedianFilter(filterWidth);
            System.out.println(System.nanoTime() - startTime);
        }

        // output processed data
        /*
         * System.out.println(processedData.length); prefix(filterWidth, border); for
         * (int i = 0; i <= processedData.length - filterWidth; i++) { Double d =
         * Math.round(processedData[i] * 1e5) / 1e5; System.out.println((i + border +
         * offset) + " " + String.format("%.5f", d)); } suffix(filterWidth, border);
         */
    }
}
