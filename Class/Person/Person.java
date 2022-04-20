
/**
 * Write a description of class Person here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Person
{
    // instance variables
    private int age;
    private String givenName;
    private String familyName;

    //Constructors
    //call this: Person p = new Person("Bpb", "Barker);
    public Person(String given, String family)
    {
        // initialise instance variables
        givenName = given;
        familyName = family;
        age = 0;        
    }
    
    //call this: Person p2 = new Person("Bpb", "Barker, 43);
    public Person(String given, String family, int age)
    {
        // initialise instance variables
        givenName = given;
        familyName = family;
        this.age = age;        
    }
    
    //Methods
    //Getters - tell the user information about the object
    public String getFullName(){
        return givenName + " " +familyName;
    }
    
    public int getAge(){
        return age;
    }
    
    //Setters - update information about the object
    public void increaseAge(){
        age++;
}
}
