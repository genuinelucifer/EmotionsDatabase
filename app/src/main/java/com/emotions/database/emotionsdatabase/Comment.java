package com.emotions.database.emotionsdatabase;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Abhinav Tripathi on 14-Aug-15.
 */
public class Comment extends Fragment {
    EditText etComment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View android = inflater.inflate(R.layout.comment, container, false);
        etComment = (EditText) android.findViewById(R.id.etComment);
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

        return android;
    }
}
