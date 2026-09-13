class Solution {
    public int strStr(String haystack, String needle) {


        char arr[]=haystack.toCharArray();
        char arr1[]=needle.toCharArray();

if(arr1.length==0){
    return 0;
}

        for(int i=0;i<=arr.length - arr1.length;i++){

            int j=0;

            while (j < arr1.length && arr[i + j] == arr1[j]) {
                j++;
            }

            if (j == arr1.length) {
                return i;
            }

        }
           
      return -1;
        
    }
}