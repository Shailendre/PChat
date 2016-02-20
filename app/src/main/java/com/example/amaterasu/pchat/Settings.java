package com.example.amaterasu.pchat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

/**
 * Created by dell on 20/2/16.
 */
public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        ListView listView=(ListView)findViewById(R.id.settings_listView);

         int[] settingsImages={R.drawable.ic_settings_help,R.drawable.ic_settings_account,R.drawable.ic_settings_invite};
         String[] settingsTextView={"Help","Account","Invite friends"};

        listView.setAdapter(new SettingsAdapter(this,settingsImages,settingsTextView));

    }
}
