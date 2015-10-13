package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import junit.framework.TestCase;

/**
 * Created by wz on 14/09/15.
 */
public class LonelyTwitterActivityTest extends ActivityInstrumentationTestCase2 {

    private EditText bodyText;
    private Button saveButton;

    public LonelyTwitterActivityTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    public void testStart() throws Exception {
        Activity activity = getActivity();

    }

    // from https://github.com/joshua2ua/lonelyTwitter/blob/f15tuesday/app/src/androidTest/java/ca/ualberta/cs/lonelytwitter/LonelyTwitterActivityTest.java
    public void testEditATweet() {
        // starts lonelyTwitter
        LonelyTwitterActivity activity = (LonelyTwitterActivity) getActivity();
        // reset the app to a known state
        activity.getTweets().clear();

        // user clicks on tweet they want to edit
        bodyText = activity.getBodyText();

        activity.runOnUiThread(new Runnable() {
            public void run() {
                bodyText.setText("hamburgers");
            }
        });
        getInstrumentation().waitForIdleSync(); // make sure our UI thread finished

        saveButton = activity.getSaveButton();

        activity.runOnUiThread(new Runnable() {
            public void run() {
                saveButton.performClick();
            }
        });
        getInstrumentation().waitForIdleSync(); // make sure our UI thread finished

        final ListView oldTweetsList = activity.getOldTweetsList();
        Tweet tweet = (Tweet) oldTweetsList.getItemAtPosition(0);
        assertEquals("hamburgers", tweet.getText());

        // Set up an ActivityMonitor
        Instrumentation.ActivityMonitor receiverActivityMonitor =
                getInstrumentation().addMonitor(EditTweetActivity.class.getName(),
                        null, false);

        activity.runOnUiThread(new Runnable() {
            public void run() {
                View v = oldTweetsList.getChildAt(0);
                oldTweetsList.performItemClick(v, 0, v.getId());
            }
        });
        getInstrumentation().waitForIdleSync(); // make sure our UI thread finished


        // Following was stolen from https://developer.android.com/training/activity-testing/activity-functional-testing.html 2015-10-13

        // Validate that ReceiverActivity is started
        EditTweetActivity receiverActivity = (EditTweetActivity)
                receiverActivityMonitor.waitForActivityWithTimeout(1000);
        assertNotNull("ReceiverActivity is null", receiverActivity);
        assertEquals("Monitor for ReceiverActivity has not been called",
                1, receiverActivityMonitor.getHits());
        assertEquals("Activity is of wrong type",
                EditTweetActivity.class, receiverActivity.getClass());

        // Remove the ActivityMonitor
        getInstrumentation().removeMonitor(receiverActivityMonitor);

        // assert that the tweet being shown on the edit screen is the tweet
        // we click on
        bodyText = activity.getBodyText();
        tweet = (Tweet) oldTweetsList.getItemAtPosition(0);
        assertEquals(bodyText, tweet.getText());

        // edit the text of that tweet
        bodyText = activity.getBodyText();

        activity.runOnUiThread(new Runnable() {
            public void run() {
                bodyText.setText("hamburgers");
            }
        });
        getInstrumentation().waitForIdleSync(); // make sure our UI thread finished

        // save our edits
        saveButton = activity.getSaveButton();

        activity.runOnUiThread(new Runnable() {
            public void run() {
                saveButton.performClick();
            }
        });
        getInstrumentation().waitForIdleSync(); // make sure our UI thread finished

        // assert that our edits were saved into the tweet correctly
        bodyText = activity.getBodyText();
        assertEquals(bodyText, "hunburger");

        // assert that our edits are shown on the screen to the user
        // back in the main activity
        bodyText = activity.getBodyText();
        tweet = (Tweet) oldTweetsList.getItemAtPosition(0);
        assertEquals(bodyText, tweet.getText());


        // end of test: clear the data
        // end of test: make sure the edit activity is closed
        receiverActivity.finish();
    }
    /*
    public void testEditATweet() {
        // starts lonelyTweeter
        LonelyTwitterActivity activity = (LonelyTwitterActivity) getActivity();
        // reset the ap to a known state
        activity.getTweets().clear();

        // user clicks on twee the want to edit
        bodyText = activity.getBodyText();

        activity.runOnUiThread(new Runnable() {
            public void run() {
                bodyText.setText("hamburgers");
            }
        });

        getInstrumentation().waitForIdleSync(); // make sure our UI thread finishes
        //bodyText.setText("hamburgers");


        saveButton = activity.getSaveButton();

        saveButton = activity.getSaveButton();
        activity.runOnUiThread(new Runnable() {
            public void run() {
                saveButton.performClick();
            }
        });
        //saveButton.performClick();
        getInstrumentation().waitForIdleSync();
        final ListView oldTweetsList = activity.getOldTweetsList();
        Tweet tweet = (Tweet) oldTweetsList.getItemAtPosition(0);
        assertEquals("hamburgers", tweet.getText());

        activity.runOnUiThread(new Runnable() {
            public void run() {
                View v = oldTweetsList.getChildAt(0);
                oldTweetsList.performItemClick(v, 0, v.getId());
            }
        });
        getInstrumentation().waitForIdleSync();

        // Following was from https://developer.android.com/training/activity-testing/activity-functional-testing.html
        // Set up an ActivityMonitor
        Instrumentation.ActivityMonitor receiverActivityMonitor =
                getInstrumentation().addMonitor(EditTweetActivity.class.getName(),
                        null, false);
        // Validate that ReceiverActivity is started
        EditTweetActivity receiverActivity = (EditTweetActivity)
                receiverActivityMonitor.waitForActivityWithTimeout(1000);
        assertNotNull("ReceiverActivity is null", receiverActivity);
        assertEquals("Monitor for ReceiverActivity has not been called",
                1, receiverActivityMonitor.getHits());
        assertEquals("Activity is of wrong type",
                EditTweetActivity.class, receiverActivity.getClass());
        // Remove the ActivityMonitor
        getInstrumentation().removeMonitor(receiverActivityMonitor);


        // assert that the tweet being shown on the edit screen is the tweet
        // we click on
        //assertEquals(bodyText, tweet.getText());

        // edit the text of that tweet
        //EditTweetActivity editTweetActivity = new EditTweetActivity();

        // save our edits


        // assert that our edits were saved into the tweet correctly


        // assert that our edits are shown on the screen to the user
        // back in the main activity


        // end of test, clear the data
        // end of test: make sure the edit activity is closed
        receiverActivity.finish();
    }
    */
}