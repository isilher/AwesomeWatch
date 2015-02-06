package nl.rikvanduijn.awesomewatch;

import android.app.Activity;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.widget.TextView;
import android.content.Intent;
import android.speech.RecognizerIntent;

import java.util.List;

public class MainActivity extends Activity {

    private TextView mTextView;
    private static final int SPEECH_REQUEST_CODE = 0;
    private static final String TAG = "speechDebug";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
//        stub.setOnLayoutInflatedListener(new WatchViewStub.OnLayoutInflatedListener() {
//            @Override
//            public void onLayoutInflated(WatchViewStub stub) {
//                mTextView = (TextView) stub.findViewById(R.id.text);
//            }
//        });
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
// Start the activity, the intent will be populated with the speech text
        startActivityForResult(intent, SPEECH_REQUEST_CODE);
    }

    // This callback is invoked when the Speech Recognizer returns.
    // This is where you process the intent and extract the speech text from the intent.
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK) {
            List<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            String spokenText = results.get(0);
            Log.v(TAG ,spokenText);
            if(spokenText.equals("double o7"))
            {
                // give welcome message
                Log.v(TAG, "it is indeed double o7");
                Intent intent = new Intent(this, WelcomeAgentActivity.class);
                startActivity(intent);
            }
            else
            {
                // give unauthorized message
                Log.v(TAG, "it is NOT double o7");
            }
            // Do something with spokenText
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
