class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int cnt =0,temp = Integer.MAX_VALUE;
        String ans = "";
        ArrayList<String> arr= new ArrayList<>();
        for(int i=0; i<n; i++){
            StringBuilder s2 = new StringBuilder();
            for(int j=i; j<n; j++){
                if(s.charAt(j) == '1') cnt++;
                if(cnt<=k)
                s2.append(s.charAt(j));
                if(cnt == k ){
                    arr.add(s2.toString());
                    int size = s2.length();
                    temp = Math.min(temp , size);
                }
            }
            cnt = 0;
        }
        Collections.sort(arr);
        for(String str : arr){
            if(str.length() == temp){
                ans += str;
                break;
            }
        }
        return ans;
    }
}