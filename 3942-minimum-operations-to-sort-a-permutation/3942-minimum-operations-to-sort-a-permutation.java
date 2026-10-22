
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int first = nums[0];

        boolean increasing = true;
        boolean decreasing = true;

        for (int i = 0; i < n; i++) {
            if (nums[i] != (first + i) % n) {
                increasing = false;
            }

            if (nums[i] != (first - i + n) % n) {
                decreasing = false;
            }
        }

        int ans = Integer.MAX_VALUE;

        if (increasing) {
            int rotate = (n - first) % n;
            int reverseRotateReverse = first + 2;

            ans = Math.min(rotate, reverseRotateReverse);
        }

        if (decreasing) {
            int reverseThenRotate = n - first;
            int rotateThenReverse = first + 2;

            ans = Math.min(ans,
                    Math.min(reverseThenRotate, rotateThenReverse));
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
