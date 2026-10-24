class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int[] days = {0,31,59,90,120,151,181,212,243,273,304,334};
        int a1 = convert(arriveAlice, days);
        int a2 = convert(leaveAlice, days);
        int b1 = convert(arriveBob, days);
        int b2 = convert(leaveBob, days);
        int start = Math.max(a1,b1);
        int end = Math.min(a2,b2);
        return Math.max(0,end-start +1);
    }
    private int convert(String date, int[] days){
        int month = Integer.parseInt(date.substring(0,2));
        int day = Integer.parseInt(date.substring(3,5));
        return days[month -1] +day;
    }
}