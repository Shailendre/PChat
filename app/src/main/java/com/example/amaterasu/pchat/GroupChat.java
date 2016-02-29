package com.example.amaterasu.pchat;

import android.app.SearchManager;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 29/2/16.
 */
public class GroupChat extends AppCompatActivity {


    ArrayList<SelectUser> selectUsers;
    ListView listView;
    Cursor phones;
    ContentResolver resolver;
    SelectUserAdapter adapter;

    private ImageView imageView;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_chat);

        Toolbar toolbar = (Toolbar) findViewById(R.id.groupchat_toolbar);
        setSupportActionBar(toolbar);

        imageView = (ImageView)findViewById(R.id.groupchat_imageView);
        imageView.setImageResource(R.drawable.ic_groupchat_icon);

        listView = (ListView)findViewById(R.id.groupchat_listView);
        selectUsers = new ArrayList<SelectUser>();
        resolver = GroupChat.this.getContentResolver();

        phones = GroupChat.this.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");


        ActionBar actionBar=getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        LoadContact loadContact = new LoadContact(selectUsers,listView,phones,resolver,adapter,getApplicationContext(),true);
        loadContact.execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_groupchat_screen, menu);

        searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.icon_groupchat_search));
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // newText is text entered by user to SearchView
                LoadContact.adapter.filter(newText);
                return false;
            }
        });



        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.icon_groupchat_next){

        }

        return true;
    }




}
