
public class Member
{
    // instance variables
    private String name;
    private int year;
    private boolean studentMembership;
    
    private Member coach;

    /**
     * Constructor for objects of class Member
     */
    public Member(String inputName, int inputYear)
    {
        // initialise instance variables
        name = inputName;
        year = inputYear;
        studentMembership = false;
    }

    public String getName()
    {
        return name;
    }
    
    public int getYear()
    {
        return year;
    }
    
    public boolean hasStudentMembership()
    {
        return studentMembership;
    }
    
    public void setStudent() {
        studentMembership = true;
    }
    
    public void changeName(String inputName) {
        name = inputName;
    }
    
    public void show() {
        System.out.print("Name: " + name + ", member since " + year);        
        if(studentMembership) {
            System.out.print(" (student)");
        }
        
        if(2020-year >= 10) {
            System.out.print(", senior");
        }
        
        if(coach != null && !coach.hasStudentMembership()) {
            System.out.println();
            System.out.print("Coach ");
            coach.show();
        }
        
        System.out.println();
    }
    
    public int memberYears() {
        return 2020-year;
    }
    
    public void setCoach(Member inputMember) {
        if(!inputMember.hasStudentMembership()) {
            coach = inputMember;
        }        
    }
}
