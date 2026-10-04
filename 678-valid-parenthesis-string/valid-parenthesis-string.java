class Solution {
    public boolean checkValidString(String s) {
        int unpaired_left = 0;
        int unused_asterisk = 0;
        int asterisk_used_as_right = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '*') {
                if (unpaired_left > 0) {
                    --unpaired_left;
                    ++asterisk_used_as_right;
                } else {
                    ++unused_asterisk;
                }

            } else if (ch == '(') {
                ++unpaired_left;

            } else if (ch == ')') {
                if (unpaired_left > 0) {
                    --unpaired_left;
                } 
                else if (asterisk_used_as_right > 0) {
                    --asterisk_used_as_right;
                    ++unused_asterisk;
                } 
                else if (unused_asterisk > 0) {
                    --unused_asterisk;
                } 
                else {
                    return false;
                }
            }
        }

        return unpaired_left == 0;
    }
}