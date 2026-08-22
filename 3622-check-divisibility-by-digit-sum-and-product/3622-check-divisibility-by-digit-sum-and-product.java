class Solution {
    public boolean checkDivisibility(int n) {
        int number=n;
        int digit=0;
        int sum=0;
        int product=1;

        while(n!=0){
            digit=n%10;

            sum += digit;
            product *= digit;

            n=n/10;
        }

        int divisible=sum+product;

        return number%divisible==0;

        
    }
}