class Solution {
    public boolean isIsomorphic(String s, String t) {

        

        if(s.length()!=t.length()){
            return false;
        }

        int[] mapst=new int[256];
        int[] mapts=new int[256];

        for(int i=0;i<s.length();i++){
            char a=s.charAt(i);
            char b=t.charAt(i);

            if(mapst[a]!=0 && mapst[a]!=b){
                return false;


            }

            if(mapts[b]!=0 && mapts[b] !=a){
                return false;
            }

            mapst[a]=b;
            mapts[b]=a;
        }

        return true;

        
    }
}