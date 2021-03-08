public class Util {
    private String intArrToString(int[] ints) {
        int arrLen = ints.length;
        if (arrLen == 0) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder();
        builder.append("[ " + ints[0]);
        for (int i = 1; i < arrLen; i++) {
            builder.append("," + ints[i]);
        }
        builder.append(" ]");
        return builder.toString();
    }
    
    private String longArrToString(long[] longs) {
        int arrLen = longs.length;
        if (arrLen == 0) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder();
        builder.append("[ " + longs[0]);
        for (int i = 1; i < arrLen; i++) {
            builder.append("," + longs[i]);
        }
        builder.append(" ]");
        return builder.toString();
    }

    private String stringArrToString(String[] strs) {
        int arrLen = strs.length;
        if (arrLen == 0) {
            return "[]";
        }
        StringBuilder builder = new StringBuilder();
        builder.append("[ " + strs[0]);
        for (int i = 1; i < arrLen; i++) {
            builder.append("," + strs[i]);
        }
        builder.append(" ]");
        return builder.toString();
    }
}
