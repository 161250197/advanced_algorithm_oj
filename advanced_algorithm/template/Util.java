public class Util {
    private String intArrToString(int[] ints) {
        StringBuilder builder = new StringBuilder();
        builder.append("[ " + ints[0]);
        int arrLen = ints.length;
        for (int i = 1; i < arrLen; i++) {
            builder.append("," + ints[i]);
        }
        builder.append(" ]");
        return builder.toString();
    }
    
    private String longArrToString(long[] longs) {
        StringBuilder builder = new StringBuilder();
        builder.append("[ " + longs[0]);
        long arrLen = longs.length;
        for (long i = 1; i < arrLen; i++) {
            builder.append("," + longs[i]);
        }
        builder.append(" ]");
        return builder.toString();
    }
}
