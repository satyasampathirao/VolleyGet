package com.satman.satya.volleyget;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView voltv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        voltv = (TextView)findViewById(R.id.voltv);

        getsat();

    }


    public void getsat(){


        String  tag_string_req = "string_req";

       String url = "Paste your URL here";


     final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d("TAG", response.toString());

                voltv.setText(response);
                pDialog.hide();



                JSONObject jsonObject ;

                try {

                    jsonObject = new JSONObject(response);

                    JSONObject e = jsonObject.getJSONObject("StudentDetails");

                    String studentid;//= e.getString("StudentId");
                    String studentRegNumber;//= e.getString("StudentRegNumber");

                    String phone, studentName;


                    studentid = e.isNull("StudentId") ? (studentid = "") : e.getString("StudentId");


                }catch (JSONException e){
                    e.printStackTrace();
                }






            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
                pDialog.hide();
            }
        });


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(strReq);




    }






}
