class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        remove(s, ans, 0, 0, new char[]{'(', ')'});
        return ans;
    }

    void remove(String s, List<String> ans, int i, int j, char[] p) {
        int count = 0;

        for (int k = i; k < s.length(); k++) {
            if (s.charAt(k) == p[0]) count++;
            if (s.charAt(k) == p[1]) count--;

            if (count < 0) {
                for (int x = j; x <= k; x++) {
                    if (s.charAt(x) == p[1] &&
                        (x == j || s.charAt(x - 1) != p[1])) {

                        remove(s.substring(0, x) +
                               s.substring(x + 1),
                               ans, k, x, p);
                    }
                }
                return;
            }
        }

        String rev = new StringBuilder(s).reverse().toString();

        if (p[0] == '(')
            remove(rev, ans, 0, 0, new char[]{')', '('});
        else
            ans.add(rev);
    }
}