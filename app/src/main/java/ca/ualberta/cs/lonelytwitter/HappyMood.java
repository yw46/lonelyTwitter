package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by yishuo on 9/15/15.
 */
public class HappyMood extends Mood {
    public HappyMood(String mood, Date date) {
        super(mood, date);
    }

    public HappyMood(String currentmood) {
        super(currentmood);
    }

    public String isHappy() {
        return "Happy yay";
    }

    @Override
    public String getCurrentmood() {
        return "Happy: " + super.getCurrentmood();
    }
}
