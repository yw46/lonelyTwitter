package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by yishuo on 9/29/15.
 */
public class TweetListTest extends ActivityInstrumentationTestCase2 implements MyObserver{
    public TweetListTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    /*
    public void setUp() {

    }

    public void tearDown() {

    }
    */

    public void testHoldsStuff() {
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        list.add(tweet);
        assertSame(list.getMostRecentTweet(), tweet);
    }

    public void testHoldsManyThings() {
        TweetList list = new TweetList();
        Tweet tweet = new NormalTweet("test");
        list.add(tweet);
        assertEquals(list.count(), 1);
        list.add(new NormalTweet("test"));
        assertEquals(list.count(), 2);
    }

    /*
    // lab 4

    //addTweet() -- should throw an IllegalArgumentException when one tries to add a duplicate tweet
    //getTweets() -- sould return a list of tweets in chronological order
    //hasTweet() -- should return true if there is a tweet that equals() another tweet
    //removeTweet() -- should remove a tweet
    //getCount() -- should accurately count up the tweets


    public void test() {
        TweetList list = new TweetList();
        Tweet tweet;
        Boolean ht;

        // addTweet()
        tweet = new NormalTweet("test");
        list.addTweet(tweet);
        tweet = new NormalTweet("hello");
        list.addTweet(tweet);
        tweet = new NormalTweet("test");
        //list.addTweet(tweet); // IllegalArgumentException


        // getTweets
        ArrayList<Tweet> tlist = list.getTweets();

        // hasTweet
        tweet = new NormalTweet("test");
        ht = list.hasTweet(tweet); // True
        assertSame(ht, Boolean.TRUE);

        // removeTweet()
        tweet = new NormalTweet("test");
        String temp = list.removeTweet(tweet); // "test" removed. returns a string for now in order to see if that tweet has been removed or not
        assertSame(temp, "test");

        // getCount()
        assertEquals(list.getCount(), 1);
    }
    */


    // lab 5
    private Boolean weWereNotified;

    public void myNotify(MyObserverable observer) {
        weWereNotified = Boolean.TRUE;
    }

    public void testObservable() {
        TweetList list = new TweetList();
        // needs an addObserber
        list.addObserver(this);
        Tweet tweet = new NormalTweet("test"); // we shouldn't have gotten notified here
        weWereNotified = Boolean.FALSE;
        list.add(tweet); // we should have been notified here
        assertTrue(weWereNotified);
    }

    public void testModifyTweetInList() {
        TweetList list = new TweetList();
        // needs an addObserber
        list.addObserver(this);
        Tweet tweet = new NormalTweet("test");
        // we shouldn't have gotten notified here
        list.add(tweet);
        // we should have been notified here
        weWereNotified = Boolean.FALSE;
        tweet.setText("different text");
        assertTrue(weWereNotified);
    }
}
