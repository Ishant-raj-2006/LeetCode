class Solution {
    public boolean canAliceWin(int n) {
        int stonesToRemove = 10;
        boolean aliceTurn = true;
        while(stonesToRemove > 0){
            if(n < stonesToRemove){
                return !aliceTurn;
            }
            n -= stonesToRemove;
            stonesToRemove--;
            aliceTurn = !aliceTurn;
        }
        return !aliceTurn;
    }
}