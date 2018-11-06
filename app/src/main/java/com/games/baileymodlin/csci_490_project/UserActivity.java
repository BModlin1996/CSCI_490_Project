<<<<<<< HEAD
package com.games.baileymodlin.csci_490_project;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class UserActivity extends AppCompatActivity {

    private String TAG = UserActivity.class.getSimpleName();
    private Button appeal, pay;
    private Intent intent;
    private ListView listView;

    ArrayList<HashMap<String, String>> ticketList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useractivity);

        appeal = findViewById(R.id.appealButton);
        pay = findViewById(R.id.payButton);
        listView = findViewById(R.id.ticketList);
        ticketList = new ArrayList<>();

        new GetStudInfo().execute();
        new GetAllTickets().execute();

        pay.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                changeActivity(0);
            }

        });

        appeal.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                changeActivity(1);
            }
        });
    }

    private void changeActivity(int id){
        switch(id){
            case 0:
                intent = new Intent(this, BillInfoActivity.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(this, AppealActivity.class);
                startActivity(intent);
                break;
        }
    }

    private class GetStudInfo extends AsyncTask<Void, Void, Void>{

        private String fName, lName, address, city, state, zip, phone, studentId;

        @Override
        protected Void doInBackground(Void... voids) {
            Student student = Student.getInstance();
            WebServiceConnect webCon = WebServiceConnect.getInstance();
            String jsonStr = webCon.getData("https://ccuresearch.coastal.edu/bcmodlin/CSCI_490/Resources/Login.php?email=" + student.getEmailAdd());
            Log.e(TAG, "Response from url: " + jsonStr);

            if(jsonStr != null){
                try {
                    JSONArray studArray = new JSONArray(jsonStr);
                    JSONObject stud = studArray.getJSONObject(0);

                    fName = stud.getString("Fname");
                    lName = stud.getString("Lname");
                    address = stud.getString("Address");
                    city = stud.getString("City");
                    state = stud.getString("State");
                    zip = stud.getString("Zip");
                    phone = stud.getString("Phone");
                    studentId = stud.getString("StudentID");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            return null;
        }

        protected void onPostExecute(Void result){
            Student student = Student.getInstance();
            student.setfName(fName);
            student.setlName(lName);
            student.setMailAdd(address);
            student.setCity(city);
            student.setState(state);
            student.setZip(zip);
            student.setPhone(phone);
            student.setStudId(studentId);
        }
    }

    private class GetAllTickets extends AsyncTask<Void, Void, Void> {

        private String ticketID, studentId, officerFName, officerLName, officerId, date, comment, price, status;


        @Override
        protected Void doInBackground(Void... voids) {
            Student student = Student.getInstance();
            WebServiceConnect webCon = WebServiceConnect.getInstance();
            String jsonStr = webCon.getData("https://ccuresearch.coastal.edu/bcmodlin/CSCI_490/index.php?run=getAllByStudId&param=" + student.getStudId());
            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {

                    JSONArray ticketArray = new JSONArray(jsonStr);

                    for(int i = 0; i < ticketArray.length(); i++) {
                        JSONObject ticketJSON = ticketArray.getJSONObject(i);

                        ticketID = ticketJSON.getString("TicketId");
                        officerFName = ticketJSON.getString("OfficerFName");
                        officerLName = ticketJSON.getString("OfficerLName");
                        officerId = ticketJSON.getString("OfficerId");
                        date = ticketJSON.getString("Date");
                        comment = ticketJSON.getString("Comment");
                        price = ticketJSON.getString("Price");
                        status = ticketJSON.getString("Status");

                        HashMap<String, String> ticketMap = new HashMap<>();

                        ticketMap.put("id", ticketID);
                        ticketMap.put("officerFName", officerFName);
                        ticketMap.put("officerLName", officerLName);
                        ticketMap.put("officerId", officerId);
                        ticketMap.put("date", date);
                        ticketMap.put("comment", comment);
                        ticketMap.put("price", price);
                        ticketMap.put("status", status);

                        ticketList.add(ticketMap);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "JSON parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Couldn't get json from server.  Check log for errors", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Couldn't get json from server.  Check log for errors", Toast.LENGTH_LONG).show();
                    }
                });
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            super.onPostExecute(result);
            ListAdapter listAdapter = new SimpleAdapter(UserActivity.this, ticketList, R.layout.list_item, new String[]{"id", "comment", "status", "price"}, new int[]{R.id.listTickID, R.id.listTickComment, R.id.listTickStatus, R.id.listTickPrice});
            listView.setAdapter(listAdapter);
        }
    }
}
=======
package com.games.baileymodlin.csci_490_project;

import android.os.Bundle;
import android.widget.EditText;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.HashMap;


public class UserActivity {
    private Bundle bundle;

    protected void onCreate(final Bundle savedInstanceBundle){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_useractivity);

        final EditText email = (EditText) findViewById(R.id.email);
        final EditText fname = (EditText) findViewById(R.id.Fname);
        final EditText lname = (EditText) findViewById(R.id.Lname);
        final EditText ccuid = (EditText) findViewById(R.id.CCUID);
        final EditText pass = (EditText) findViewById(R.id.loginPASS);
        bundle = savedInstanceState;
    }

}
>>>>>>> origin/master
