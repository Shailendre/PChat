package com.example.amaterasu.pchat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by dell on 29/2/16.
 */
public class Account extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);

        ListView listView=(ListView)findViewById(R.id.account_listView);

        int[] accountImages={R.drawable.ic_account_change_name,R.drawable.ic_account_delete_account};
        String[] accountTextView={"Change Name","Delete Account"};

        listView.setAdapter(new AccountAdapter(this,accountImages,accountTextView));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = null;

                if (i == 0)
                    intent = new Intent(Account.this, ChangeName.class);
                if (i == 1)
                    intent = new Intent(Account.this, DeleteAccount.class);

                startActivity(intent);

            }
        });





    }


}
