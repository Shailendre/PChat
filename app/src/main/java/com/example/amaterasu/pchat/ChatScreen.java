package com.example.amaterasu.pchat;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

/**
 * Created by dell on 28/2/16.
 */
public class ChatScreen extends AppCompatActivity {

    private static int RESULT_LOAD_IMG;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_screen);
        Toolbar toolbar=(Toolbar)findViewById(R.id.chat_toolbar);
        setSupportActionBar(toolbar);
        //setActionBar();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_chat_screen, menu);

        setActionBar();

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.icon_attachment){
            Intent galleryIntent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setActionBar(){

        ActionBar actionBar=getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDefaultDisplayHomeAsUpEnabled(true);


        if(getIntent().hasExtra("user_name")) {
            actionBar.setTitle(getIntent().getStringExtra("user_name"));
            actionBar.setLogo(R.mipmap.ic_launcher);
            //has to be set by cmds
            actionBar.setSubtitle("Offline");
        }
        /*
        if (getIntent().hasExtra("user_bmp_array")) {
            ImageView user_pic= new ImageView(this);
            Bitmap b = BitmapFactory.decodeByteArray(
                    getIntent().getByteArrayExtra("user_bmp_array"), 0, getIntent().getByteArrayExtra("user_bmp_array").length);
            getSupportActionBar().setIcon(R.drawable.dp);
        }
        */

    }

}
