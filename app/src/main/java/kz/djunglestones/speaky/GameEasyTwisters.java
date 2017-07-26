package kz.djunglestones.speaky;

import android.media.Image;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.widget.Toast;


import com.github.andreilisun.swipedismissdialog.SwipeDismissDialog;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class GameEasyTwisters extends AppCompatActivity {

    private TextView textview_timertime,textView_score,textView_accuracy;
    private CircleImageView imgTwister,btn_sound,mic_button;
    private TextView textview_twister;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    private static String speechResult=" ";
    private  static Words[] givenWords;
    private static TextToSpeech t1;
    private int wordCounter;
    private  static int mCurrentIndex = 0;
    Button btnContinue;
    SwipeDismissDialog swipeDismissDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_easy_twisters);
        textview_timertime = (TextView)findViewById(R.id.textview_timertime);
        textView_score = (TextView)findViewById(R.id.textview_score);
        textView_accuracy = (TextView)findViewById(R.id.textview_accuracy);
        imgTwister = (CircleImageView) findViewById(R.id.imgTwister);
        textview_twister = (TextView)findViewById(R.id.textview_twister);
        btn_sound = (CircleImageView)findViewById(R.id.btn_sound);
        mic_button = (CircleImageView)findViewById(R.id.mic_button);


        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });

        btn_sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timerHandler.removeCallbacks(timerRunnable);
                String toSpeak = textview_twister.getText().toString();
                Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);

            }
        });

        mic_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });

        givenWords= new Words[]{
                new Words(0,"what noise  annoys an oyster",R.drawable.oyster),
                new Words(1,"black background brown background.",R.drawable.oyster),
                new Words(2,"tie twine to three tree twigs",R.drawable.oyster),
                new Words(3,"three short sword sheaths",R.drawable.oyster),
                new Words(4,"a quick-witted cricket critic",R.drawable.oyster),
                new Words(5,"how can a clam cram in a clean cream can",R.drawable.oyster),
                 new Words(6,"coy knows pseudonoise codes",R.drawable.oyster),
                 new Words(7,"clean clams crammed in clean cans",R.drawable.oyster),
                 new Words(8,"can you can a can as a canner can can a can",R.drawable.oyster),
                 new Words(9,"Sheena leads, Sheila needs.",R.drawable.oyster),
                 new Words(10,"santa's short suit shrunk",R.drawable.oyster),
                 new Words(11,"Wayne went to Wales to watch walruses.",R.drawable.oyster),
                 new Words(13,"Eleven benevolent elephants",R.drawable.oyster),
                 new Words(14,"Willy's real rear wheel",R.drawable.oyster),
                 new Words(15,"Send toast to ten tense stout saints' ten tall tents",R.drawable.oyster),
                 new Words(16,"Two tried and true tridents",R.drawable.oyster),
                 new Words(17,"Green glass globes glow greenly",R.drawable.oyster),
                 new Words(18,"The queen in green screamed",R.drawable.oyster),
                 new Words(19,"Six slimy snails sailed silently",R.drawable.oyster),


        };
        String word = givenWords[mCurrentIndex].getWord();
        textview_twister.setText(word);



    }

    Handler timerHandler = new Handler();
    private float millis;
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            millis = millis + .1f;

            textview_timertime.setText(String.format("%5.1f", millis).replace('.',','));

            timerHandler.postDelayed(this,100);
        }

        public void stop(){
            timerHandler.removeCallbacks(timerRunnable);
        }



    };





    private void promptSpeechInput() {
        timerRunnable.run();
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                textview_twister.getText().toString());
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void onPause(){
        if(t1 !=null){
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) throws NullPointerException {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    speechResult = result.get(0);
                }
                break;
            }

        }

        if(!(speechResult.isEmpty()) && speechResult.toLowerCase().equals(textview_twister.getText().toString().toLowerCase())){
//            txtSpeechInput.append(String.valueOf(" "+txtSpeechInput.getText().toString().split(" ").length));
            Toast toast = Toast.makeText(getApplicationContext(), "Accepted "+speechResult, Toast.LENGTH_SHORT);
            toast.show();
            if (wordCounter>=5){
                Toast.makeText(getApplicationContext(), "Accepted You have more than 5", Toast.LENGTH_SHORT);

            }
            updateWord();

        }
//        else if ((speechResult.toLowerCase()).equals("next")){
//            updateWord();
//            Toast.makeText(getApplicationContext(), "NEXT "+speechResult, Toast.LENGTH_SHORT).show();
//        }
//        else if((txtSpeechInput.getText().toString().toLowerCase()).equals("previous")){
//
//        }
        else{
            Toast toastLost = Toast.makeText(getApplicationContext(), "LOST ", Toast.LENGTH_SHORT);
            toastLost.show();
            showDialogContinue();
            updateWord();


            Toast toastAdvice = Toast.makeText(getApplicationContext(), "Advice", Toast.LENGTH_SHORT);
            toastAdvice.show();
        }
    }

    private void showDialogContinue() {
        View dialog = LayoutInflater.from(getBaseContext()).inflate(R.layout.dialog_success,null);
        swipeDismissDialog = new SwipeDismissDialog.Builder(GameEasyTwisters.this)
                .setView(dialog)
                .build().show();
         btnContinue = (Button)dialog.findViewById(R.id.btnContinue);
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                swipeDismissDialog.dismiss();
            }
        });
    }

    private void updateWord() {
        wordCounter+=1;
        calculate();
        mCurrentIndex = (mCurrentIndex + 1) % givenWords.length;
        String word = givenWords[mCurrentIndex+1].getWord();
        textview_twister.setText(word)   ;

    }

    private void calculate() {
        int accuracy = Integer.parseInt(textView_accuracy.getText().toString());
        int wordCalculator =(wordCounter*10)*100/accuracy;
        textView_score.setText(wordCalculator+" XP");
    }
}
