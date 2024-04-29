
/**
 * The ClockDisplay class implements a digital clock display for a
 * European-style 24 hour clock. The clock shows hours and minutes. The 
 * range of the clock is 00:00 (midnight) to 23:59 (one minute before 
 * midnight).
 * 
 * The clock display receives "ticks" (via the timeTick method) every minute
 * and reacts by incrementing the display. This is done in the usual clock
 * fashion: the hour increments when the minutes roll over to zero.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class ClockDisplay
{
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private String displayString;    // simulates the actual display
    
    /**
     * Constructor for ClockDisplay objects. This constructor 
     * creates a new clock set at 00:00.
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        updateDisplay();
    }

    /**
     * Constructor for ClockDisplay objects. This constructor
     * creates a new clock set at the time specified by the 
     * parameters.
     */
    public ClockDisplay(int hour, int minute)
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        setTime(hour, minute);
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
        }
        updateDisplay();
    }

    /**
     * Set the time of the display to the specified hour and
     * minute.
     */
    public void setTime(int hour, int minute)
    {
        assert(validHour(hour) && validMinute(minute)): "Incorrect hour or minute.";
        hours.setValue(hour);
        minutes.setValue(minute);
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
    private void updateDisplay()
    {
        int hour = hours.getValue(); 
        if(hour == 12){
            displayString = hour + ":" + 
                        minutes.getDisplayValue() + " p.m.";
        }
        else if(hour < 12){
            displayString = hour + ":" + 
                        minutes.getDisplayValue() + " a.m.";
            if(hour == 0){
                hour = 12;
                displayString = hour + ":" + 
                        minutes.getDisplayValue() + " a.m.";     
            }
        }
        else{
            hour -= 12;
            displayString = hour + ":" + 
                        minutes.getDisplayValue() + " p.m.";
        }
    }
    
    /**
     * Check if the prompted hour is correct (between 0 and 23), for setTime method.
     * @author Fran.
     */
    private boolean validHour(int hour){
        if(hour >= 0 && hour <= 23){
            return true;
        }
        return false;
    }
    
    /**
     * Check if the prompted minute is correct (between 0 and 59), for setTime method.
     * @author Fran.
     */
    private boolean validMinute(int minute){
        if(minute >= 0 && minute <= 59){
            return true;
        }
        return false;
    }
}