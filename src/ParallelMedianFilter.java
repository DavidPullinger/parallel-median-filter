import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ParallelMedianFilter extends MedianFilter {

    private static class MedianFilterTask extends RecursiveAction {
        private static final int SEQ_CUTOFF = 1000;
        private int start;
        private int end;
        private int filterWidth;

        public MedianFilterTask(int s, int e, int fw) {
            start = s;
            end = e;
            filterWidth = fw;
        }

        @Override
        protected void compute() {
            if (end - start < SEQ_CUTOFF) {
                medianFilter(filterWidth, start, end);
            } else {
                MedianFilterTask left = new MedianFilterTask(start, (start + end) / 2, filterWidth);
                MedianFilterTask right = new MedianFilterTask((start + end) / 2, end, filterWidth);
                invokeAll(left, right);
            }
        }
    }

    private static final ForkJoinPool fjPool = new ForkJoinPool();

    private static void parallelMedianFilter(int filterWidth) {
        fjPool.invoke(new MedianFilterTask(0, data.length - filterWidth, filterWidth));
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
            parallelMedianFilter(filterWidth);
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
