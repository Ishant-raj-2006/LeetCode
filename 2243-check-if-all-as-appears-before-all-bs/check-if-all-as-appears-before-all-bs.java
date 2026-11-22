class Solution {
    public boolean checkString(String s) {
       boolean foundB = false;
       for(char ch : s.toCharArray()){
        if(ch == 'b'){
            foundB = true;
        }
        else if (ch == 'a' && foundB){
            return false;
        }
       }
       return true;
    }
}