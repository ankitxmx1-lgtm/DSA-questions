class Solution {
    public int largestPalindrome(int n) {
        if (n == 1) return 9;
        long maxNum = (long) Math.pow(10, n) - 1;
        long minNum = maxNum / 10;
        
        for (long i = maxNum; i > minNum; i--) {
            long palindrome = createPalindrome(i);
            for (long j = maxNum; j * j >= palindrome; j--) {
                if (palindrome % j == 0) {
                    return (int) (palindrome % 1337);
                }
            }
        }
        return 0;
    }

    private long createPalindrome(long num) {
        String s = Long.toString(num);
        String rev = new StringBuilder(s).reverse().toString();
        return Long.parseLong(s + rev);
    }
}
