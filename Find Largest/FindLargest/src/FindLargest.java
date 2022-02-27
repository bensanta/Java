import java.util.Arrays;

public class FindLargest {
    

    public static int FindLargest() {
    //Arrays to start with
    String[] months = {"Jan", "Feb", "Mar", "Apr","May", "Jun",
                        "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    int[] temps = {7, 12, 21, 26, 29, 32, 35, 31, 24, 20, 13, 4};

    int highest = temps[0];
    int highestLocation = 0;

        for (int i = 0; i < temps.length; i++){
            if (temps[i] > highest){
                highest = temps[i];
                highestLocation = i;
            }
        }
        System.out.println(highest + " - " + months[highestLocation]);
        return highest;
    }
    // Still need to connect highest value's location to the months

    public static void main(String[] args) {
        FindLargest();
    }
}
