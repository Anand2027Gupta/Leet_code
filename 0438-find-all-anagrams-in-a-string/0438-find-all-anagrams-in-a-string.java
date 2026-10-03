import java.util.*;

class Solution {
    public List<Integer> findAnagrams(String s, String p) {

        List<Integer> ans = new ArrayList<>();

        int n = s.length();
        int m = p.length();

        if (m > n) return ans;

        int[] freq = new int[26];
        int[] window = new int[26];

        
        for (int i = 0; i < m; i++) {
            freq[p.charAt(i) - 'a']++;
        }

       
        for (int i = 0; i < m; i++) {
            window[s.charAt(i) - 'a']++;
        }

        if (Arrays.equals(freq, window)) {
            ans.add(0);
        }

       
        for (int right = m; right < n; right++) {

            int left = right - m;

            
            window[s.charAt(left) - 'a']--;

            
            window[s.charAt(right) - 'a']++;

            if (Arrays.equals(freq, window)) {
                ans.add(left + 1);
            }
        }

        return ans;
    }
}