class Solution {
    public int minOperations(String s) {
        int n = s.length();
        boolean sorted = true;
        char min = 'z';
        char max = 'a';
        for(int i=0; i<n; i++){
            char c = s.charAt(i);
            min = (char) Math.min(min , c);
            max = (char) Math.max(max , c);
            if(i >0 && c<s.charAt(i-1)){
                sorted = false;
            }
        }
        if(sorted){
            return 0; 
        }
        if(n==2){
            return -1;
        }
        if(s.charAt(0) == min || s.charAt(n-1) == max){
            return 1;
        }
        for(int i=1; i<n-1; i++){
            char c = s.charAt(i);
            if(c == min || c == max)
        {
            return 2;
        }
        }
        return 3;
    }
}