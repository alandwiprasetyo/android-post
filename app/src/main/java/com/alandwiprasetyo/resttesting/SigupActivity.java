package com.alandwiprasetyo.resttesting;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 19/03/16.
 */
public class SigupActivity  extends Activity{
    AutoCompleteTextView etEmail;
    EditText etFullName;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (AutoCompleteTextView) findViewById(R.id.email);
        etFullName = (EditText) findViewById(R.id.full_name);
        button= (Button) findViewById(R.id.email_sign_in_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new onSendData().execute(etEmail.getText().toString(), etFullName.getText().toString());

            }
        });
    }


    private class onSendData extends AsyncTask<String, Void, Void> {
        ProgressDialog progressDialog = new ProgressDialog(SigupActivity.this);

        String json;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Send data...");
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(String... arg) {
            // TODO Auto-generated method stub
            String email = arg[0];
            String fullname = arg[1];
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("fullname", fullname));
            params.add(new BasicNameValuePair("email", email));
            ServiceHandler serviceClient = new ServiceHandler();
            json = serviceClient.makeServiceCall("http://firzariswandy.16mb.com/test.php", ServiceHandler.POST, params);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
        }
    }

}
