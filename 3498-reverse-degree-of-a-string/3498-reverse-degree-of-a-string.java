class Solution {
    public int reverseDegree(String s) {
int reverseDegree=0;
        for(int i=0;i<s.length();i++){
            int alphaPosition=s.charAt(i)-'a'+1;

            int reversePosition=27-alphaPosition;

            int product=(i+1)*reversePosition;

        
             reverseDegree += product;

        }
        return reverseDegree;
        
    }
}