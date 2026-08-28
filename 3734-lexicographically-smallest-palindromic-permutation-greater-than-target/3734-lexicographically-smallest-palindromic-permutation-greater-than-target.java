class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int odd = 0;
        int middle = -1;

        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                odd++;
                middle = i;
            }
        }

        if (odd > 1) {
            return "";
        }

        int[] halfFreq = new int[26];

        for (int i = 0; i < 26; i++) {
            halfFreq[i] = freq[i] / 2;
        }

        int halfLength = n / 2;
        String best = "";

        for (int change = halfLength - 1; change >= 0; change--) {
            int[] remaining = halfFreq.clone();
            boolean possible = true;

            for (int i = 0; i < change; i++) {
                int c = target.charAt(i) - 'a';

                if (remaining[c] == 0) {
                    possible = false;
                    break;
                }

                remaining[c]--;
            }

            if (!possible) {
                continue;
            }

            int t = target.charAt(change) - 'a';

            for (int c = t + 1; c < 26; c++) {
                if (remaining[c] == 0) {
                    continue;
                }

                StringBuilder half = new StringBuilder();

                for (int i = 0; i < change; i++) {
                    half.append(target.charAt(i));
                }

                half.append((char) ('a' + c));
                remaining[c]--;

                for (int x = 0; x < 26; x++) {
                    while (remaining[x] > 0) {
                        half.append((char) ('a' + x));
                        remaining[x]--;
                    }
                }

                String candidate = buildPalindrome(half, middle);

                if (candidate.compareTo(target) > 0 &&
                    (best.equals("") || candidate.compareTo(best) < 0)) {
                    best = candidate;
                }
            }
        }

        if (n % 2 == 1) {
            int[] remaining = halfFreq.clone();
            StringBuilder half = new StringBuilder();
            boolean possible = true;

            for (int i = 0; i < halfLength; i++) {
                int c = target.charAt(i) - 'a';

                if (remaining[c] == 0) {
                    possible = false;
                    break;
                }

                remaining[c]--;
                half.append((char) ('a' + c));
            }

            if (possible) {
                int targetMiddle = target.charAt(halfLength) - 'a';

                for (int c = targetMiddle + 1; c < 26; c++) {
                    if (freq[c] % 2 == 1) {
                        String candidate = buildPalindrome(half, c);

                        if (candidate.compareTo(target) > 0 &&
                            (best.equals("") || candidate.compareTo(best) < 0)) {
                            best = candidate;
                        }
                    }
                }
            }
        }

        int[] remaining = halfFreq.clone();
        StringBuilder half = new StringBuilder();
        boolean possible = true;

        for (int i = 0; i < halfLength; i++) {
            int c = target.charAt(i) - 'a';

            if (remaining[c] == 0) {
                possible = false;
                break;
            }

            remaining[c]--;
            half.append((char) ('a' + c));
        }

        if (possible) {
            for (int x = 0; x < 26; x++) {
                while (remaining[x] > 0) {
                    half.append((char) ('a' + x));
                    remaining[x]--;
                }
            }

            String candidate = buildPalindrome(half, middle);

            if (candidate.compareTo(target) > 0 &&
                (best.equals("") || candidate.compareTo(best) < 0)) {
                best = candidate;
            }
        }

        return best;
    }

    private String buildPalindrome(StringBuilder half, int middle) {
        StringBuilder result = new StringBuilder();

        result.append(half);

        if (middle != -1) {
            result.append((char) ('a' + middle));
        }

        for (int i = half.length() - 1; i >= 0; i--) {
            result.append(half.charAt(i));
        }

        return result.toString();
    }
}