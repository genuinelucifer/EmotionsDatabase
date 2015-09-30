package com.emotions.database.emotionsdatabase;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

/**
 * Created by Abhinav Tripathi on 14-Aug-15.
 * Edited on 17-Sep-15.
 */
public class EngageMedia extends AppCompatActivity {
    RadioGroup rgNumMedia;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.engagemedia);

        rgNumMedia = (RadioGroup) findViewById(R.id.rgNumMedia);
        Utilities.setNumEngagements(0);
        rgNumMedia.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbZero:
                        Utilities.setNumEngagements(0);
                        break;
                    case R.id.rbOne:
                        Utilities.setNumEngagements(1);
                        break;
                    case R.id.rbTwo:
                        Utilities.setNumEngagements(2);
                        break;
                    case R.id.rbThree:
                        Utilities.setNumEngagements(3);
                        break;
                    case R.id.rbMore:
                        Utilities.setNumEngagements(4);
                        break;
                    default:
                        break;
                }
            }
        });

        next = (Button) findViewById(R.id.btnNextEngage);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EngageMedia.this, ThankYou.class);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
