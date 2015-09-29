package ca.ualberta.cs.lonelytwitter;

import android.text.BoringLayout;

import java.security.KeyStore;
import java.util.ArrayList;

/**
 * Created by yishuo on 9/29/15.
 */
public class TweetList {
    private Tweet mostRecentTweet;
    private ArrayList<Tweet> tweets = new ArrayList<Tweet>();

    public void add(Tweet tweet) {
        mostRecentTweet = tweet;
        tweets.add(tweet);
    }

    public  Tweet getMostRecentTweet() {
        return mostRecentTweet;
    }

    public int count() {
        return tweets.size();
    }

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

    public void removeTweet(Tweet tweet) {
        int n = tweets.size();
        int i;
        //Tweet ht = new NormalTweet("AAA");
        for (i = 0; i < n; i = i + 1) {
            if (tweets.get(i).getText() == tweet.getText()) {
                tweets.remove(i); // ht = ~
                //return ht.getText();
            }
        }
        //return ht.getText();
    }

    public int getCount() {
        return tweets.size();
    }

}
