import java.util.Arrays;

class Solution {
    public boolean checkInclusion(String s1, String s2) {

        int[] freq = new int[26];
        int[] window = new int[26];

        int k = s1.length();

        
        for (int i = 0; i < s1.length(); i++) {
            freq[s1.charAt(i) - 'a']++;
        }

        
        if (s1.length() > s2.length()) {
            return false;
        }

        
        for (int i = 0; i < k; i++) {
            window[s2.charAt(i) - 'a']++;
        }

        
        if (Arrays.equals(freq, window)) {
            return true;
        }

        
        for (int right = k; right < s2.length(); right++) {

            int left = right - k;

            
            window[s2.charAt(left) - 'a']--;

            
                       
                       
                       
                       
             window[s2.charAt(right) - 'a']++;

            
            if (Arrays.equals(freq, window)) {
                return true;
            }
        }

        return false;
    }
}