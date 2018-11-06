package com.games.baileymodlin.csci_490_project;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BillInfoActivity extends AppCompatActivity {

    private String TAG = BillInfoActivity.class.getSimpleName();
    private Button continueButton;
    private TextView infoView;

    Ticket ticket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_info);

        continueButton = findViewById(R.id.submitButtonBI);
        infoView = findViewById(R.id.infoView);

        new GetSingleTicketInfo().execute();

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BillInfoActivity.this, CheckOutForm.class);
                startActivity(intent);
            }
        });
    }

    private class GetSingleTicketInfo extends AsyncTask<Void, Void, Void>{

        private String ticketID, studentId, officerFName, officerLName, officerId, date, comment, price, status;

        @Override
        protected Void doInBackground(Void... voids) {
            String funcId = "getTicketInfo";
            String ticketId = "1";
            WebServiceConnect webCon = WebServiceConnect.getInstance();
            String jsonStr = webCon.getData("https://ccuresearch.coastal.edu/bcmodlin/CSCI_490/index.php?run=getTicketInfo&param=1");
            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONArray ticketArray = new JSONArray(jsonStr);
                    JSONObject ticket = ticketArray.getJSONObject(0);

                    ticketID = ticket.getString("TicketId");
                    officerFName = ticket.getString("OfficerFName");
                    officerLName = ticket.getString("OfficerLName");
                    officerId = ticket.getString("OfficerId");
                    date = ticket.getString("Date");
                    comment = ticket.getString("Comment");
                    price = ticket.getString("Price");
                    status = ticket.getString("Status");

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
            ticket = new Ticket(ticketID, officerFName, officerLName, officerId, date, comment, price);
            infoView.setText(ticket.toString());
        }
    }

}


