import java.util.concurrent.RecursiveAction;

public class ParallelMedianFilter extends MedianFilter {
    private static final int SEQ_CUTOFF = 10;

    class MedianFilterTask extends RecursiveAction {
        private int start;
        private int end;
        private Double[] data;

        private MedianFilterTask(int s, int e, Double[] d) {
            start = s;
            end = e;
            data = d;
        }

        @Override
        protected void compute() {
            if (end - start < SEQ_CUTOFF) {

            }
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
        // serialMedianFilter(filterWidth, filterWidth - 1);
        // output processed data
        System.out.println(processedData.length);
        for (int i = 0; i < processedData.length; i++) {
            Double d = processedData[i];
            System.out.println(i + " " + Math.round(d * 1e5) / 1e5);
        }
    }
}
