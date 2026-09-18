class Solution {

    public List<String> maxNumOfSubstrings(String s) {

        int n = s.length();

        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, n);

        
        for (int i = 0; i < n; i++) {

            int c = s.charAt(i) - 'a';

            first[c] = Math.min(first[c], i);
            last[c] = i;
        }

        
        List<int[]> intervals = new ArrayList<>();

        
        for (int c = 0; c < 26; c++) {

            if (first[c] == n) {
                continue;
            }

            int l = first[c];
            int r = last[c];

            boolean valid = true;

            
            for (int i = l; i <= r; i++) {

                int current = s.charAt(i) - 'a';

                
                if (first[current] < l) {
                    valid = false;
                    break;
                }

                
                r = Math.max(r, last[current]);
            }

            if (valid) {
                intervals.add(new int[]{l, r});
            }
        }

        
        intervals.sort((a, b) -> Integer.compare(a[1], b[1]));

        List<String> answer = new ArrayList<>();

        int prevEnd = -1;

        for (int[] interval : intervals) {

            int l = interval[0];
            int r = interval[1];

            if (l > prevEnd) {

                answer.add(s.substring(l, r + 1));

                prevEnd = r;
            }
        }

        return answer;
    }
}