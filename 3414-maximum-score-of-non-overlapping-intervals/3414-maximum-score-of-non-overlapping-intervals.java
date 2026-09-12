import java.util.*;

class Solution {

    static class Interval {
        int start, end, weight, index;

        Interval(int start, int end, int weight, int index) {
            this.start = start;
            this.end = end;
            this.weight = weight;
            this.index = index;
        }
    }

    static class Result {
        long score;
        List<Integer> indices;

        Result(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        Interval[] arr = new Interval[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new Interval(
                intervals.get(i).get(0),
                intervals.get(i).get(1),
                intervals.get(i).get(2),
                i
            );
        }

        Arrays.sort(arr, (a, b) -> {
            if (a.start != b.start) {
                return Integer.compare(a.start, b.start);
            }
            return Integer.compare(a.index, b.index);
        });

        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            next[i] = findNext(arr, i + 1, arr[i].end);
        }

        Result[][] dp = new Result[n + 1][5];

        for (int k = 0; k <= 4; k++) {
            dp[n][k] = new Result(0, new ArrayList<>());
        }

        for (int i = 0; i <= n; i++) {
            dp[i][0] = new Result(0, new ArrayList<>());
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int k = 1; k <= 4; k++) {

                Result skip = dp[i + 1][k];

                Result nextResult = dp[next[i]][k - 1];

                List<Integer> takeIndices =
                    new ArrayList<>(nextResult.indices);

                takeIndices.add(arr[i].index);

                Collections.sort(takeIndices);

                Result take = new Result(
                    arr[i].weight + nextResult.score,
                    takeIndices
                );

                dp[i][k] = better(skip, take);
            }
        }

        List<Integer> answer = dp[0][4].indices;

        int[] result = new int[answer.size()];

        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }

        return result;
    }

    private int findNext(Interval[] arr, int left, int currentEnd) {
        int right = arr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid].start > currentEnd) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private Result better(Result a, Result b) {
        if (a.score != b.score) {
            return a.score > b.score ? a : b;
        }

        return compareLists(a.indices, b.indices) <= 0 ? a : b;
    }

    private int compareLists(List<Integer> a, List<Integer> b) {
        int size = Math.min(a.size(), b.size());

        for (int i = 0; i < size; i++) {
            if (!a.get(i).equals(b.get(i))) {
                return Integer.compare(a.get(i), b.get(i));
            }
        }

        return Integer.compare(a.size(), b.size());
    }
}