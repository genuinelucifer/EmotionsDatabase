package com.emotions.database.emotionsdatabase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseObject;

/**
 * Created by Abhinav Tripathi on 14-Aug-15.
 */
public class Comment  extends AppCompatActivity {
    EditText etComment;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment);

        etComment = (EditText) findViewById(R.id.etComment);
        etComment.setText("");
        Utilities.setComment("");
        etComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Utilities.setComment(etComment.getText().toString());
            }
        });

        btnSubmit = (Button) findViewById(R.id.btnSubmitResponse);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseObject obj = new ParseObject("EmotionsDatabase");
                obj.put("Happiness", Utilities.getHappiness());
                obj.put("Sleepiness", Utilities.getSleepiness());
                int n = Utilities.getNumEngagements();
                String str = Integer.toString(n);
                if(n>3)
                    str = "Many";
                obj.put("Engagements", str);
                obj.put("Comment", etComment.getText().toString());
                obj.saveInBackground();
            }
        });
    }
}
