package ca.ualberta.cs.lonelytwitter;

import android.text.BoringLayout;

import java.security.KeyStore;
import java.util.ArrayList;

/**
 * Created by yishuo on 9/29/15.
 */

// want to make observable
public class TweetList implements MyObserverable, MyObserver{
    private Tweet mostRecentTweet;
    private ArrayList<Tweet> tweets = new ArrayList<Tweet>();

    public void add(Tweet tweet) {
        mostRecentTweet = tweet;
        tweets.add(tweet);
        tweet.addObserver(this);
        notifyAllObservers();
    }

    public  Tweet getMostRecentTweet() {
        return mostRecentTweet;
    }

    public int count() {
        return tweets.size();
    }

    // lab 4
    /*
    addTweet() -- should throw an IllegalArgumentException when one tries to add a duplicate tweet
    ArrayList<Tweet> getTweets() -- sould return a list of tweets in chronological order
    Boolean hasTweet() -- should return true if there is a tweet that equals() another tweet
    removeTweet() -- should remove a tweet
    int getCount() -- should accurately count up the tweets
     */

    public void addTweet(Tweet tweet) {
        if (hasTweet(tweet)) {
            throw new IllegalArgumentException("tweet already exist");
        } else {
            tweets.add(tweet);
        }
    }

    public ArrayList<Tweet> getTweets() {
        return tweets;
    }

    public Boolean hasTweet(Tweet tweet) {
        int n = tweets.size();
        if (n == 0) {
            return Boolean.FALSE;
        }
        int i;
        for (i = 0; i < n; i = i + 1) {
            if (tweets.get(i).getText() == tweet.getText()) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    // returns a string of which tweet is removed
    public String removeTweet(Tweet tweet) {
        int n = tweets.size();
        int i;
        Tweet ht = new NormalTweet("AAA");
        for (i = 0; i < n; i = i + 1) {
            if (tweets.get(i).getText() == tweet.getText()) {
                ht = tweets.remove(i); // ht = ~
                return ht.getText();
            }
        }
        return ht.getText();

    }

    public int getCount() {
        return tweets.size();
    }

    // lab 5
    private volatile ArrayList<MyObserver> observers = new ArrayList<MyObserver>();

    public void addObserver(MyObserver observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (MyObserver observer : observers) {
            observer.myNotify(this);
        }
    }

    public void myNotify(MyObserverable observerable) {
        notifyAllObservers();
    }
}
