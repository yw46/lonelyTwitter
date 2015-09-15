package ca.ualberta.cs.lonelytwitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by yishuo on 9/15/15.
 */
public abstract class Mood{
    private String currentmood;
    private Date date;
    public abstract String isHappy();

    public Mood(String mood, Date date) {
        this.currentmood = mood;
        this.date = date;
    }

    public Mood(String currentmood) {
        this.currentmood = currentmood;
        this.date = new Date();
    }

    public String getCurrentmood() {
        return currentmood;
    }

    public void setCurrentmood(String currentmood) throws IOException {
        // <= 100
        if (currentmood.length() <= 100) {
            this.currentmood = currentmood;
        } else {
            //throw new IllegalArgumentException("tweets can't be that long"); // runtime excepion
            throw new IOException("mood can't be that long");
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
