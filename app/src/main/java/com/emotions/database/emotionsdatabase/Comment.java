package com.emotions.database.emotionsdatabase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * Created by Abhinav Tripathi on 14-Aug-15.
 */
public class Comment  extends AppCompatActivity {
    EditText etComment;

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
    }
}
