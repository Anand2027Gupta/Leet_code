class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 1;
        }

        int minIdx = 0;
        int maxIdx = 0;

        
        for (int k = 1; k < n; k++) {
            if (nums[k] < nums[minIdx]) {
                minIdx = k;
            }
            if (nums[k] > nums[maxIdx]) {
                maxIdx = k;
            }
        }

        
        int i = Math.min(minIdx, maxIdx);
        int j = Math.max(minIdx, maxIdx);

        
        int costFront = j + 1;

        
        int costBack = n - i;

        
        int costBoth = (i + 1) + (n - j);

        return Math.min(costFront, Math.min(costBack, costBoth));
    }
}