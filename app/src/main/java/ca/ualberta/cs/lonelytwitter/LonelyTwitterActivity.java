package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LonelyTwitterActivity extends Activity {

	private static final String FILENAME = "file.sav";
	private EditText bodyText;
	private ListView oldTweetsList;
    private ArrayList<Tweet> tweets = new ArrayList<Tweet>();
    private ArrayAdapter<Tweet> adapter; // = new ArrayAdapter<Tweet>(this,
                                         // R.layout.list_item, tweets);


	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState); // view
		setContentView(R.layout.main); // view

		bodyText = (EditText) findViewById(R.id.body); // view
		Button saveButton = (Button) findViewById(R.id.save); // view
        Button clearButton = (Button) findViewById(R.id.clear); // view
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList); // view

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);
				String text = bodyText.getText().toString(); // controller
				//saveInFile(text, new Date(System.currentTimeMillis()));
                tweets.add(new NormalTweet(text)); // controller
				//finish();
                adapter.notifyDataSetChanged(); // view
                saveInFile(); // model
			}
		});

        clearButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                tweets.clear(); // controller
                adapter.notifyDataSetChanged(); // view
                clearInFile(); // model
            }
        });

    }

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart(); // view
        loadFromFile(); // model
		//String[] tweets = loadFromFile();
		//ArrayList<Tweet> tweets = new ArrayList<Tweet>();
		//ArrayAdapter<Tweet> adapter = new ArrayAdapter<Tweet>(this,
		//		R.layout.list_item, tweets);
        adapter = new ArrayAdapter<Tweet>(this, R.layout.list_item, tweets); // view
		oldTweetsList.setAdapter(adapter); // view
	}

	private void loadFromFile() {
		//ArrayList<String> tweets = new ArrayList<String>();
		try {
			FileInputStream fis = openFileInput(FILENAME); // model
			BufferedReader in = new BufferedReader(new InputStreamReader(fis)); // model
            /*
			String line = in.readLine();
			while (line != null) {
				tweets.add(line);
				line = in.readLine();
			}
			*/
            Gson gson = new Gson(); // model
            Type listType = new TypeToken<ArrayList<NormalTweet>>() {}.getType(); // model
            tweets = gson.fromJson(in, listType); // controller
            // use Mylist.class() for simple

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
            //throw new RuntimeException();
            tweets = new ArrayList<Tweet>(); // controller
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
            throw new RuntimeException(e); // controller
		}
		//return tweets.toArray(new String[tweets.size()]);
	}
	
	private void saveInFile() {
		try {
			FileOutputStream fos = openFileOutput(FILENAME, 0); // model
			//(FILENAME, Context.MODE_APPEND);
			//fos.write(new String(date.toString() + " | " + text)
			//		.getBytes());
            OutputStreamWriter writer = new OutputStreamWriter(fos); // model
            Gson gson = new Gson(); // model
            gson.toJson(tweets, writer); // model
            writer.flush(); // model
			fos.close(); // model
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
            throw new RuntimeException(e); // controller
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
            throw new RuntimeException(e); // controller
		}
	}
    private void clearInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, 0); // model
            OutputStreamWriter writer = new OutputStreamWriter(fos); // model
            Gson gson = new Gson(); // model
            gson.toJson(tweets, writer); // model
            writer.flush(); // model
            fos.close(); // model
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e); // controller
        } catch (IOException e) {
            throw new RuntimeException(e); // controller
        }
    }
}