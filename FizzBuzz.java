public class FizzBuzz
{
    public FizzBuzz()
    {
        print();
    }
    
    /**
     * Return true if n is a multiple of m, and false otherwise.
     */
    private boolean isMultipleOf(int n, int m) {
        if(n % m == 0) {  
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Print the numbers from 1 to 100. 
     * For multiples of three print “Fizz” instead of the number. 
     * For the multiples of five print “Buzz”. 
     * For numbers which are multiples of both three and five print “FizzBuzz”.
     */
    public void print()
    {
        int counter = 1;
        
        while (counter <= 100) {
            boolean printed = false;
            
            if(isMultipleOf(counter, 3)) {
                printed = true;
                System.out.print("Fizz");
            }
            
            if(isMultipleOf(counter, 5)) {
                printed = true;
                System.out.print("Buzz");
            }
            
            if(!printed) {
                System.out.println(counter); 
            }
            else {
                System.out.println(); 
            }
            
            counter ++;
        }
    }
    
    /**
     * The same as above.
     */
    public void print_for() 
    {
        for(int i = 1; i <= 100; i ++) {
            boolean printed = false;
            
            if(isMultipleOf(i, 3)) {
                printed = true;
                System.out.print("Fizz");
            }
            
            if(isMultipleOf(i, 5)) {
                printed = true;
                System.out.print("Buzz");
            }
            
            if(!printed) {
                System.out.println(i); 
            }
            else {
                System.out.println(); 
            }
        }
    } 
    
}
