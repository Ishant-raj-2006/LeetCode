class Solution {
    public int totalNumbers(int[] digits) {
        Set<Integer> set = new TreeSet<>();

        for (int a : digits) {
            for (int b : digits) {
                for (int c : digits) {
                    if (a == 0 || c % 2 != 0) continue;

                    int n = a * 100 + b * 10 + c;

                    int[] cnt = new int[10];
                    for (int d : digits) cnt[d]++;

                    if (--cnt[a] >= 0 && --cnt[b] >= 0 && --cnt[c] >= 0)
                        set.add(n);
                }
            }
        }
        return set.size();
    }
}