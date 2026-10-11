
class Solution {
    public String minWindow(String s, String t) {

        if(s.length()<t.length()){
            return "";
        }

        HashMap<Character,Integer> needMap=new HashMap<>();

        for(int i=0;i<t.length();i++){
            char ch=t.charAt(i);

            needMap.put(ch,needMap.getOrDefault(ch,0)+1);
        }

        int need=t.length();
        int left=0;
        int start=0;
        int minLen=Integer.MAX_VALUE;

        for(int right=0;right<s.length();right++){
            char ch=s.charAt(right);

            if(needMap.getOrDefault(ch,0)>0){
                need--;

            }
            needMap.put(ch,needMap.getOrDefault(ch,0)-1);

            while(need==0){
                int windowLen=right-left+1;

                if(windowLen<minLen){
                    minLen=windowLen;
                    start=left;
                }

                char leftChar=s.charAt(left);

                int count=needMap.getOrDefault(leftChar,0)+1;
                needMap.put(leftChar,count);

                if(count>0){
                    need++;

                }
                left++;

            }
        }

        if(minLen==Integer.MAX_VALUE){
            return "";
        }

        return s.substring(start,start+minLen);     
    }
}