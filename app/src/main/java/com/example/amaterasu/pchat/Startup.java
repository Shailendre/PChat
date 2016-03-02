package com.example.amaterasu.pchat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A Startup screen that takes in usrName and usrNo to the server
 * via next btn to the verification screen/layout
 */
public class Startup extends AppCompatActivity  {

    //component variables
    String usrName,usrNo,c_code;
    Button bToVerify;
    EditText edit_usrName,edit_usrNo;
    Spinner countryCode;
    final int ERR_USRNAME=0;
    final int ERR_NUMBER=1;
    final int NO_ERR=4;
    final int ERR_NAME_NUMBER=2;
    final int ERR_C_CODE=3;
    int error;

    final Context context = this;
    /*
    * Helper function to return string from edittext
    */
    public String editTextToString(EditText editText){

        return editText.getText().toString();

    }

    /*
    * Helper function to validate name and number
    */
    public int validateInfo(String usrName,String usrNo,String ccode){

        String name_regex="^[A-Za-z]{1,}[.]{0,1}[A-Za-z]{0,}";
        String numb_regex="^[2-9]{1}\\d{9}$";

        Pattern name_patt=Pattern.compile(name_regex,Pattern.CASE_INSENSITIVE);
        Pattern numb_patt=Pattern.compile(numb_regex,Pattern.CASE_INSENSITIVE);

        Matcher name_match=name_patt.matcher(usrName);
        Matcher numb_match=numb_patt.matcher(usrNo);

        if(!name_match.find())
            return ERR_USRNAME;

        if(!numb_match.find())
            return ERR_NUMBER;

        if(ccode.equals(countryCode.getItemAtPosition(0).toString()))
            return ERR_C_CODE;

        else
            return NO_ERR;

    }


    /*
    * Helper function for dialog
    */
    public void openDialog(int errorCase) {

        //Dialog dialog = new Dialog(this);
        //dialog.setContentView(R.layout.dialog_activity);

        if (errorCase == ERR_NUMBER) {
            invokePrompt(context, getResources().getString(R.string.dialog_title_invalid_numb));
            //dialog_info.setText(R.string.dialog_validation_false_numb);
        }
        else if (errorCase == ERR_USRNAME) {
            invokePrompt(context, getResources().getString(R.string.dialog_title_invalid_name));
            //dialog_info.setText(R.string.dialog_validation_false_name);
        }
        else if (errorCase == ERR_C_CODE) {
            invokePrompt(context, getResources().getString(R.string.dialog_title_invalid_ccode));
            //dialog_info.setText(R.string.dialog_validation_false_ccode);
        }
        else{
            invokePrompt(context,getResources().getString(R.string.dialog_title_invalid_name_numb));
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startup_activity);


        //initalize the component variables
        bToVerify=(Button)findViewById(R.id.bToVerify);
        edit_usrName=(EditText)findViewById(R.id.usrName);
        edit_usrNo=(EditText)findViewById(R.id.usrNo);
        countryCode=(Spinner)findViewById(R.id.countryCode);

        //on btn click event
        bToVerify.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                usrName=editTextToString(edit_usrName);
                usrNo=editTextToString(edit_usrNo);
                c_code=countryCode.getSelectedItem().toString();

                error=validateInfo(usrName,usrNo,c_code);

                //if valid then send the info to power_server
                if(error==NO_ERR)
                    startActivity(new Intent(Startup.this,Otp.class));

                    //call the dailog prompt
                else{
                    if(error==ERR_USRNAME){
                        edit_usrName.setText("");
                        openDialog(ERR_USRNAME);
                    }

                    else if(error==ERR_NUMBER) {
                        edit_usrNo.setText("");
                        openDialog(ERR_NUMBER);
                    }

                    else if (error==ERR_C_CODE)
                        openDialog(ERR_C_CODE);

                    else {
                        edit_usrName.setText("");
                        edit_usrNo.setText("");
                        openDialog(ERR_NAME_NUMBER);
                    }
                }

            }
        });

    }


    private void invokePrompt(Context context,String msg){

        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage(msg);
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });


        AlertDialog alert11 = builder1.create();
        alert11.show();

    }

}