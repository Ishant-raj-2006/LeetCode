class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if(sum>9*num){
            return "";
        }
        StringBuilder ans = new StringBuilder();
        int nines = sum/9;
        int remainder = sum % 9;
        ans.append("9".repeat(nines));
        if(remainder >0){
            ans.append(remainder);
        }
        while(ans.length() < num){
            ans.append('0');
        }
        return ans.toString();
    }

}