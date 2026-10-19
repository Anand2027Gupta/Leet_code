class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        Arrays.sort(nums);

        ArrayList<Integer> targetArray=new ArrayList<>();

        for(int i=0;i<nums.length;i++){
            if(nums[i]==target){
                targetArray.add(i);
                

                
            }
            
        }
        return targetArray;
        
    }
}