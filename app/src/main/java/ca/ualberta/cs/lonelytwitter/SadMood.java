package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by yishuo on 9/15/15.
 */
public class SadMood extends Mood {
    public SadMood(String mood, Date date) {
        super(mood, date);
    }

    public SadMood(String currentmood) {
        super(currentmood);
    }

    public String isHappy() {
        return "Sad";
    }

    @Override
    public String getCurrentmood() {
        return "Sad: " + super.getCurrentmood();
    }

}
