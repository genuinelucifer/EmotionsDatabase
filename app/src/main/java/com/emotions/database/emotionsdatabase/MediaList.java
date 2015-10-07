package com.emotions.database.emotionsdatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class MediaList extends AppCompatActivity {

    Button next;
    CheckBox cbAll[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_list);

        cbAll = new CheckBox[Utilities.NUM_MEDIA_LIST];
        for(int i=0;i<Utilities.NUM_MEDIA_LIST; ++i)
        {
            cbAll[i] = (CheckBox) findViewById(Utilities.cbIDs[i]);
        }

        next = (Button) findViewById(R.id.btnNextMediaList);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<Utilities.NUM_MEDIA_LIST; ++i)
                {
                    Utilities.checkBoxesCheckedState[i] = cbAll[i].isChecked();
                }
                Intent i = new Intent(MediaList.this, EngageMedia.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_common, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_SetAlarm) {
            Intent i = new Intent(MediaList.this, NumAlarms.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
