package com.example.tts;

import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    TextToSpeech t;
    EditText editText;
    Set<String> a=new HashSet<>();
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText= findViewById(R.id.et);
        button =findViewById(R.id.button);
        t= new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i!=TextToSpeech.ERROR)
                a.add("male");
                Voice v= null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    v = new Voice("hi-in-x-cfn#male_3-local",new Locale("hi","IN"),400,200,false,a);
                    t.setVoice(v);
                }
                else{

                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s= editText.getText().toString();
                Double f1=0.6;
                Double f2=0.85;
                t.setPitch(f1.floatValue());
                t.setSpeechRate(f2.floatValue());
                t.speak(s, TextToSpeech.QUEUE_FLUSH, null);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Set<Voice> get=t.getVoices();
                    for (Iterator<Voice> it = get.iterator(); it.hasNext(); ) {
                        Voice v = it.next();
                        Log.e("------", v.toString());
                    }
                }
            }
        });
    }
}
