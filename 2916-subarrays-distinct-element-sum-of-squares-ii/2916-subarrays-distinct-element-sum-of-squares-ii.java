
class Solution {
    static final long MOD = 1_000_000_007L;

    int n;
    long[] sum, squareSum, lazy;

    public int sumCounts(int[] nums) {
        n = nums.length;

        sum = new long[4 * n];
        squareSum = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100001];
        Arrays.fill(last, -1);

        long answer = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            int previous = last[x];

            update(1, 0, n - 1, previous + 1, i);

            answer = (answer + squareSum[1]) % MOD;

            last[x] = i;
        }

        return (int) answer;
    }

    void update(int node, int start, int end,
                int left, int right) {
        if (left > end || right < start) {
            return;
        }

        if (left <= start && end <= right) {
            long oldSum = sum[node];
            long len = end - start + 1L;

            squareSum[node] =
                (squareSum[node] + 2L * oldSum + len) % MOD;

            sum[node] = (sum[node] + len) % MOD;
            lazy[node] = (lazy[node] + 1) % MOD;
            return;
        }

        push(node, start, end);

        int mid = start + (end - start) / 2;

        update(node * 2, start, mid, left, right);
        update(node * 2 + 1, mid + 1, end, left, right);

        sum[node] =
            (sum[node * 2] + sum[node * 2 + 1]) % MOD;

        squareSum[node] =
            (squareSum[node * 2] + squareSum[node * 2 + 1]) % MOD;
    }

    void push(int node, int start, int end) {
        if (lazy[node] == 0 || start == end) {
            return;
        }

        int mid = start + (end - start) / 2;

        apply(node * 2, start, mid, lazy[node]);
        apply(node * 2 + 1, mid + 1, end, lazy[node]);

        lazy[node] = 0;
    }

    void apply(int node, int start, int end, long add) {
        long oldSum = sum[node];
        long len = end - start + 1L;

        squareSum[node] = (
            squareSum[node]
            + 2L * add % MOD * oldSum % MOD
            + add * add % MOD * len % MOD
        ) % MOD;

        sum[node] = (oldSum + add * len) % MOD;
        lazy[node] = (lazy[node] + add) % MOD;
    }
}
