class Solution {

    class Node {

        char leftChar;
        char rightChar;

        int prefix;
        int suffix;
        int longest;
        int length;

        Node(char leftChar, char rightChar,
             int prefix, int suffix,
             int longest, int length) {

            this.leftChar = leftChar;
            this.rightChar = rightChar;
            this.prefix = prefix;
            this.suffix = suffix;
            this.longest = longest;
            this.length = length;
        }
    }

    Node[] tree;
    char[] arr;

    public int[] longestRepeating(
        String s,
        String queryCharacters,
        int[] queryIndices
    ) {

        int n = s.length();

        arr = s.toCharArray();

        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int k = queryIndices.length;

        int[] answer = new int[k];

        for (int i = 0; i < k; i++) {

            int index = queryIndices[i];

            char ch = queryCharacters.charAt(i);

            update(
                1,
                0,
                n - 1,
                index,
                ch
            );

            answer[i] = tree[1].longest;
        }

        return answer;
    }


    
    private void build(int node, int start, int end) {

        
        if (start == end) {

            tree[node] = new Node(
                arr[start],
                arr[start],
                1,
                1,
                1,
                1
            );

            return;
        }

        int mid = start + (end - start) / 2;

        
        build(
            2 * node,
            start,
            mid
        );

        
        build(
            2 * node + 1,
            mid + 1,
            end
        );

        
        tree[node] = merge(
            tree[2 * node],
            tree[2 * node + 1]
        );
    }


    
    private Node merge(Node left, Node right) {

        char leftChar = left.leftChar;

        char rightChar = right.rightChar;

        int length = left.length + right.length;


        

        int prefix = left.prefix;

       
        if (left.prefix == left.length &&
            left.rightChar == right.leftChar) {

            prefix = left.length + right.prefix;
        }


        

        int suffix = right.suffix;

        
        if (right.suffix == right.length &&
            left.rightChar == right.leftChar) {

            suffix = left.suffix + right.length;
        }


        
        int longest = Math.max(
            left.longest,
            right.longest
        );


        
        if (left.rightChar == right.leftChar) {

            longest = Math.max(
                longest,
                left.suffix + right.prefix
            );
        }


        return new Node(
            leftChar,
            rightChar,
            prefix,
            suffix,
            longest,
            length
        );
    }


    
    private void update(
        int node,
        int start,
        int end,
        int index,
        char ch
    ) {

    
        if (start == end) {

            arr[index] = ch;

            tree[node] = new Node(
                ch,
                ch,
                1,
                1,
                1,
                1
            );

            return;
        }

        int mid = start + (end - start) / 2;



        if (index <= mid) {

            update(
                2 * node,
                start,
                mid,
                index,
                ch
            );
        }


        
        else {

            update(
                2 * node + 1,
                mid + 1,
                end,
                index,
                ch
            );
        }


        
        tree[node] = merge(
            tree[2 * node],
            tree[2 * node + 1]
        );
    }
}