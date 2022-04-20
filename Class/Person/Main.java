
/**
 * Write a description of class Main here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Main
{
    public static void main(String[] args){
        Person bob = new Person ("Bob", "Barker", 96);
        Person alan = new Person ("Alan", "Turing");
        Person ada = new Person ("Ada", "Lovelace");
        
        System.out.println(bob.getFullName());
        System.out.println(alan.getFullName());
        System.out.println(ada.getFullName());
        System.out.println(ada.getAge());
        ada.increaseAge();
        System.out.println(ada.getAge());
    }
}
