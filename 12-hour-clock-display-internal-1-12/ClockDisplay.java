
public class ClockDisplay
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private String displayString;    // simulates the actual display
    
    private boolean isAM;
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 12:00 am.
     */
    public ClockDisplay()
    {   
        hours = new NumberDisplay(13);    // hours value ranges from 0 to 13(13 not included)  // if hours rolled over to 0, add 1 to hours to be 1 (in timeTick)
        hours.setValue(12);
        minutes = new NumberDisplay(60);
        isAM = true;
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     */    
    public ClockDisplay(int hour, int minute, boolean am)    // let user define AM or PM
    {
        hours = new NumberDisplay(13);
        minutes = new NumberDisplay(60);
        setTime(hour, minute, am);
    }

    /**
     * This method should get called once every minute - it makes
     * the clock display go one minute forward.
     */
    public void timeTick()
    {
        minutes.increment();
        if(minutes.getValue() == 0) {  // it just rolled over!
            hours.increment();
            
            if(hours.getValue() == 0) {    // if hours rolled over to 0, add 1 to it  // in this way hours can only be between 1 to 12
                hours.increment();
            }
            else if(hours.getValue() == 12) { // if hours rolled over to 12, change between AM and PM
                if(isAM) {
                    isAM = false;
                }
                else {
                    isAM = true;
                }
            }
            
        }
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute, and whether it is AM or not.
     */
    public void setTime(int hour, int minute, boolean am)    // 12 hour clock: updated setTime to set isAM
    {
        if(hour == 0 || hour >= 13) {    // hour is not allowed to be 0, or >=13. We will set hour to default 12 if such value is entered. Just like in the 24 hour clock, invald hour value is set to 0.
            hours.setValue(12);
        }
        else {
            hours.setValue(hour);
        }        
        minutes.setValue(minute);
        isAM = am;
        updateDisplay();
    }

    /**
     * Return the current time of this display in the format HH:MM.
     */
    public String getTime()
    {
        return displayString;
    }
    
    /**
     * Update the internal string that represents the display.
     */    
    private void updateDisplay()    // updated to display am and pm for 12 hour clock
    {   
        displayString = hours.getDisplayValue() + ":" + 
                        minutes.getDisplayValue() + " ";
        if(isAM) {
            displayString = displayString + "am"; 
        }
        else {
            displayString = displayString + "pm";
        }
    }
}
