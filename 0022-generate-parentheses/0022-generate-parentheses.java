class Solution {
    public List<String> generateParenthesis(int n) {

        ArrayList<String> par=new ArrayList<>();

        backtrack("",0,0,n,par);

        return par;

        
        
    }

    public void backtrack(String current,int open,int close,int n, ArrayList<String> par){
        if(current.length() == 2*n){
            par.add(current);
            return;
        }

        if(open<n){
            backtrack(current + "(",open+1,close,n,par);
        }

        if(close<open){
            backtrack(current + ")", open, close+1,n,par);
                    }
    }
    }