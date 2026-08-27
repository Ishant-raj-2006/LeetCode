class Solution {
    public String lexGreaterPermutation(String s, String target) {

        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < target.length(); i++) {

            int current = target.charAt(i) - 'a';

            if (freq[current] > 0) {
                ans.append(target.charAt(i));
                freq[current]--;
            } 
            else {

                for (int j = current + 1; j < 26; j++) {

                    if (freq[j] > 0) {

                        ans.append((char) (j + 'a'));
                        freq[j]--;

                        for (int k = 0; k < 26; k++) {
                            while (freq[k] > 0) {
                                ans.append((char) (k + 'a'));
                                freq[k]--;
                            }
                        }

                        return ans.toString();
                    }
                }

                // Backtracking
                while (ans.length() > 0) {

                    int pos = ans.length() - 1;
                    int prev = ans.charAt(pos) - 'a';

                    ans.deleteCharAt(pos);
                    freq[prev]++;

                    for (int j = prev + 1; j < 26; j++) {

                        if (freq[j] > 0) {

                            ans.append((char) (j + 'a'));
                            freq[j]--;

                            for (int k = 0; k < 26; k++) {
                                while (freq[k] > 0) {
                                    ans.append((char) (k + 'a'));
                                    freq[k]--;
                                }
                            }

                            return ans.toString();
                        }
                    }
                }

                return "";
            }
        }

        // s == target, so backtrack
        while (ans.length() > 0) {

            int pos = ans.length() - 1;
            int prev = ans.charAt(pos) - 'a';

            ans.deleteCharAt(pos);
            freq[prev]++;

            for (int j = prev + 1; j < 26; j++) {

                if (freq[j] > 0) {

                    ans.append((char) (j + 'a'));
                    freq[j]--;

                    for (int k = 0; k < 26; k++) {
                        while (freq[k] > 0) {
                            ans.append((char) (k + 'a'));
                            freq[k]--;
                        }
                    }

                    return ans.toString();
                }
            }
        }

        return "";
    }
}