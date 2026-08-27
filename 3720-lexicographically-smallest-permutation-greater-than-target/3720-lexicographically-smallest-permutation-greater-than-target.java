class Solution {
    public String lexGreaterPermutation(String s, String target) {

        int n = s.length();

        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        StringBuilder prefix = new StringBuilder();

        
        for (int i = 0; i < n; i++) {

            int t = target.charAt(i) - 'a';

            
            if (freq[t] > 0) {

                freq[t]--;
                prefix.append(target.charAt(i));

            } else {

                
                for (int c = t + 1; c < 26; c++) {

                    if (freq[c] > 0) {

                        StringBuilder ans = new StringBuilder(prefix);

                        ans.append((char) ('a' + c));

                        freq[c]--;

                        
                        for (int x = 0; x < 26; x++) {
                            while (freq[x] > 0) {
                                ans.append((char) ('a' + x));
                                freq[x]--;
                            }
                        }

                        return ans.toString();
                    }
                }

                
                break;
            }
        }

        
        for (int change = n - 1; change >= 0; change--) {

            freq = new int[26];

            
            for (char ch : s.toCharArray()) {
                freq[ch - 'a']++;
            }

            
            boolean possible = true;

            for (int i = 0; i < change; i++) {

                int c = target.charAt(i) - 'a';

                if (freq[c] == 0) {
                    possible = false;
                    break;
                }

                freq[c]--;
            }

            if (!possible) {
                continue;
            }

            
            int t = target.charAt(change) - 'a';

            for (int c = t + 1; c < 26; c++) {

                if (freq[c] > 0) {

                    StringBuilder ans = new StringBuilder();

                    
                    ans.append(target.substring(0, change));

                    
                    ans.append((char) ('a' + c));

                    freq[c]--;

                    
                    for (int x = 0; x < 26; x++) {
                        while (freq[x] > 0) {
                            ans.append((char) ('a' + x));
                            freq[x]--;
                        }
                    }

                    return ans.toString();
                }
            }
        }

        return "";
    }
}