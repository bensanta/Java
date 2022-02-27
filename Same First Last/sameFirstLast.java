public class sameFirstLast {



    
    public boolean sameFirstLast(int[] nums) {
        if (nums.length >= 1 && nums[0] == nums[nums.length-1]){
            return true;
        }
        else if ((nums.length >= 1 && nums[0] != nums[nums.length-1])){
            return false;
        }
        else if ((nums.length >= 1)){
            return false;
        }

    }
    
    public static void main(String[] args) {
        
    }
}
