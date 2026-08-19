class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        
        Map<Integer, Set<Integer>> map=new HashMap<>();

        for(int[] seat : reservedSeats){
            map.computeIfAbsent(seat[0], x->new HashSet<>())
            .add(seat[1]);
        }

        long answer = 2L * n;

        for(Map.Entry<Integer,Set<Integer>> entry : map.entrySet()){
            Set<Integer> reserved = entry.getValue();

            boolean left=true;
            boolean middle=true;
            boolean right=true;

            for(int seat=2;seat <= 5; seat++){
                if(reserved.contains(seat)){
                    left=false;
                    break;
                }
            }

            for(int seat=4;seat<=7;seat++){
                if(reserved.contains(seat)){
                    middle=false;
                    break;
                }
            }

            for(int seat=6;seat<=9;seat++){
                if(reserved.contains(seat)){
                    right=false;
                    break;
                }
            }

             answer -= 2;

             if(left && right){
                answer += 2;
             }

             else if(left || middle || right){
                answer +=1;
             }
        }

           return (int) answer;     
        
    }
}