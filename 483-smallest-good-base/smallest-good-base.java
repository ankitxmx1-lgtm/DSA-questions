class Solution {
    public String smallestGoodBase(String n) {
        long num = Long.parseLong(n);
        int maxM = (int) (Math.log(num) / Math.log(2));

        for (int m = maxM; m >= 2; m--) {
            long k = (long) Math.pow(num, 1.0 / m);
            long sum = 0;
            long p = 1;
            boolean isValid = true;

            for (int i = 0; i <= m; i++) {
                sum += p;
                if (i < m) {
                    if (Long.MAX_VALUE / k < p) {
                        isValid = false;
                        break;
                    }
                    p *= k;
                }
            }

            if (isValid && sum == num) {
                return String.valueOf(k);
            }
        }

        return String.valueOf(num - 1);
    }
}
