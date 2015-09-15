package ca.ualberta.cs.lonelytwitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by yishuo on 9/15/15.
 */
public abstract class Tweet extends Object implements Tweetable{
    // "extends Object" can be omitted
    // "abstract" will not allow us to call Tweet
    private String text;
    private Date date;
    private ArrayList<Mood> moodList;

    public Tweet(Date date, String tweet) {
        super();
        this.date = date; // "this" pointer to the current object
        this.text = tweet; // removing "this" still works as there is no other variable named "text"
    }

    public String getText() {
        return text;
    }

    public void setText(String text) throws IOException{
        if (text.length() <= 140) {
            this.text = text;
        } else {
            //throw new IllegalArgumentException("tweets can't be that long"); // runtime excepion
            throw new IOException("tweets can't be that long");
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Tweet(String text) {
        this.text = text;
        this.date = new Date(); // set to the current date and time
    }

    /*
    public Boolean isImportant() {
        return Boolean.FALSE;
    }
    */
    public abstract Boolean isImportant();
}
