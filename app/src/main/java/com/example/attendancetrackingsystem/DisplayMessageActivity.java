package com.example.attendancetrackingsystem;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import org.restlet.resource.ClientResource;

//import static com.example.attendancetrackingsystem.MainActivity.EXTRA_MESSAGE_USERNAME;
import java.util.StringTokenizer;


public class DisplayMessageActivity extends AppCompatActivity {

    ImageView image;
    String tutor= "";
    String eventCode= "00000000";
    String groupNumber="";
    String eventDate = "";
    String roomNumber= "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Intent intent = getIntent();
        //String username = intent.getStringExtra(EXTRA_MESSAGE_USERNAME);
        //String passwort = intent.getStringExtra(MainActivity.EXTRA_MESSAGE_PASSWORT);
        //String code_id = intent.getStringExtra(MainActivity.GOOGLE_ID);
        String code_id = "104224594234921054639";
        String url = "http://ats-1-7.appspot.com/StudentXML/"+code_id +"/";
        new MyTask().execute(url);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String username = intent.getStringExtra(MainActivity.GOOGLE_USERNAME);
        String email = intent.getStringExtra(MainActivity.GOOGLE_EMAIL);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.out_username);
        textView.setText(username);

        //Show Passwort
        textView = findViewById(R.id.out_passwort);
        textView.setText(email);

        textView = findViewById(R.id.TXTtutor);
        textView.setText(tutor);

        textView = findViewById(R.id.TXTgroupNumber);
        textView.setText(groupNumber);

        textView = findViewById(R.id.TXTeventDate);
        textView.setText(eventDate);

        textView = findViewById(R.id.TXTroomNumber);
        textView.setText(roomNumber);



        image = (ImageView) findViewById(R.id.qr_image);

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(eventCode, BarcodeFormat.QR_CODE, 600, 600);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            image.setImageBitmap(bitmap);
        }
        catch (WriterException e) {
            e.printStackTrace();
        }

        code_id = "104224594234921054639";
        url = "http://www.ats-1-7.appspot.com/StudentXML/AttendanceList/"+code_id +"/";
        new MyTask2().execute(url);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //Fragment switchTo = null;
        //switchTo = new EventListFragment();

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.meeting_List) {
            Intent intent = new Intent(this, EventListActivity.class);
            startActivity(intent);
        }
        if (id == R.id.your_prof)
        {
            Intent intent = new Intent(this,TabTestActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    private class MyTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {

            String rawAnswer = "";
            try {
                rawAnswer = new ClientResource(params[0]).get().getText();
                rawAnswer=rawAnswer.replaceAll("(.*)ase2017(.*)","");
                rawAnswer=rawAnswer.replaceAll("(.*)http://www.w3.org/2001/XMLSchema-instance(.*)","");
                rawAnswer=rawAnswer.replaceAll("(.*)http://192.168.0.100:8080/Student.xsd(.*)","");
                rawAnswer=rawAnswer.replaceAll("(.*)lecture(.*)","");
                rawAnswer=rawAnswer.replaceAll("<student>","");
                rawAnswer=rawAnswer.replaceAll("</student>","");
                rawAnswer=rawAnswer.replaceAll("<studentid>","");
                rawAnswer=rawAnswer.replaceAll("</studentid>","");
                rawAnswer=rawAnswer.replaceAll("<tutorial>","");
                rawAnswer=rawAnswer.replaceAll("</tutorial>","");
                rawAnswer=rawAnswer.replaceAll("<tutorialnumber>","");
                rawAnswer=rawAnswer.replaceAll("</tutorialnumber>","");
                rawAnswer=rawAnswer.replaceAll("<tutor>","");
                rawAnswer=rawAnswer.replaceAll("</tutor>","");
                rawAnswer=rawAnswer.replaceAll("<roomnumber>","");
                rawAnswer=rawAnswer.replaceAll("</roomnumber>","");
                rawAnswer=rawAnswer.replaceAll("<date>","");
                rawAnswer=rawAnswer.replaceAll("</date>","");
                rawAnswer=rawAnswer.replaceAll("<qr>","");
                rawAnswer=rawAnswer.replaceAll("</qr>","");
                rawAnswer=rawAnswer.replaceAll("</asetutorials>","");


                StringTokenizer token = new StringTokenizer(rawAnswer);


                int length = token.countTokens();  // Anzahl Teile, die gefunden werden.
                String[] array = new String[ length ];  // Den Array herstellen
                for( int i = 0; i < length; i++ ) {
                    array[i] = token.nextToken();
                    System.out.println(array[i]);
                }
                groupNumber = array[1];
                tutor = array[2]+" "+ array[3];
                roomNumber = array[4];
                eventDate = array[29];
                eventCode=array[30];
                for(int i = 7; i < length; i+=2)
                {
                    //Controll aktiv event on Date;
                }
            } catch (Exception exc) {
                rawAnswer = "I mor dei URL lodt ned";
            }
            //System.out.printf(rawAnswer);
            return rawAnswer;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            //Show Data from the Databank
            /*TextView TV = (TextView) findViewById(R.id.AppEngine_DATA);
            TV.setText(result);*/
        }
    }
    private class MyTask2 extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {

            String rawAnswer = "";
            try {
                rawAnswer = new ClientResource(params[0]).get().getText();
                rawAnswer=rawAnswer.replaceAll("(.*)ase2017(.*)","");
                rawAnswer=rawAnswer.replaceAll("(.*)http://www.w3.org/2001/XMLSchema-instance(.*)","");
                rawAnswer=rawAnswer.replaceAll("(.*)http://192.168.0.100:8080/Student.xsd(.*)","");
                rawAnswer=rawAnswer.replaceAll("(.*)lecture(.*)","");
                rawAnswer=rawAnswer.replaceAll("<student>","");
                rawAnswer=rawAnswer.replaceAll("</student>","");
                rawAnswer=rawAnswer.replaceAll("<studentid>","");
                rawAnswer=rawAnswer.replaceAll("</studentid>","");
                rawAnswer=rawAnswer.replaceAll("<tutorial>","");
                rawAnswer=rawAnswer.replaceAll("</tutorial>","");
                rawAnswer=rawAnswer.replaceAll("<tutorialnumber>","");
                rawAnswer=rawAnswer.replaceAll("</tutorialnumber>","");
                rawAnswer=rawAnswer.replaceAll("<tutor>","");
                rawAnswer=rawAnswer.replaceAll("</tutor>","");
                rawAnswer=rawAnswer.replaceAll("<roomnumber>","");
                rawAnswer=rawAnswer.replaceAll("</roomnumber>","");
                rawAnswer=rawAnswer.replaceAll("<date>","");
                rawAnswer=rawAnswer.replaceAll("</date>","");
                rawAnswer=rawAnswer.replaceAll("<qr>","");
                rawAnswer=rawAnswer.replaceAll("</qr>","");
                rawAnswer=rawAnswer.replaceAll("</asetutorials>","");
                rawAnswer=rawAnswer.replaceAll("<attended>","");
                rawAnswer=rawAnswer.replaceAll("</attended>","");
                rawAnswer=rawAnswer.replaceAll("<presented>","");
                rawAnswer=rawAnswer.replaceAll("</presended>","");
                rawAnswer=rawAnswer.replaceAll("</asetutorials>","");
                rawAnswer=rawAnswer.replaceAll("</attendancelist>","");


                StringTokenizer token = new StringTokenizer(rawAnswer);


                int length = token.countTokens();  // Anzahl Teile, die gefunden werden.
                String[] array = new String[ length ];  // Den Array herstellen
                for( int i = 0; i < length; i++ ) {
                    array[i] = token.nextToken();
                    System.out.println(array[i]);
                }
                groupNumber = array[1];
                tutor = array[2]+" "+ array[3];
                roomNumber = array[4];
                eventDate = array[29];
                eventCode=array[30];
                for(int i = 7; i < length; i+=2)
                {
                    //Controll aktiv event on Date;
                }
            } catch (Exception exc) {
                rawAnswer = "I mor dei URL lodt ned";
            }
            //System.out.printf(rawAnswer);
            return rawAnswer;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            //Show Data from the Databank
            /*TextView TV = (TextView) findViewById(R.id.AppEngine_DATA);
            TV.setText(result);*/
        }
    }
}
