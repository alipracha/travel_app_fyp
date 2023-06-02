package com.example.triparrangersfyp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.triparrangersfyp.Retrofit.RetrofitClient;
import com.example.triparrangersfyp.Tourist.ShowSeatsActivity;
import com.example.triparrangersfyp.Tourist.Users_Dashboard;
import com.example.triparrangersfyp.util.Endpoint;
import com.example.triparrangersfyp.util.TinyDB;
import com.google.android.gms.tasks.Task;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;

public class JazzCashActivity extends AppCompatActivity {

    WebView webView;
    String JazzUrl;
    String TxnRef;
    ProgressDialog progressDialog;
    int amount, gettaskid, gettaskerid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jazz_cash);
        webView = findViewById(R.id.webView);
        amount = getIntent().getIntExtra("AMOUNT", 0);
        JazzUrl = Endpoint.JAZZ_URL;
        JazzFunction();
        webView.addJavascriptInterface(new WebAppInterface(this), "ok");

    }

    public class WebAppInterface {
        Context mContext;

        /**
         * Instantiate the interface and set the context
         */
        WebAppInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public void performClick() {

/*
            ClientPendingTaskFragment clientPendingTaskFragment = new ClientPendingTaskFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.pending_task_linear_frag,
                    clientPendingTaskFragment).addToBackStack(null);
            fragmentTransaction.commit();
*/


            Toast.makeText(JazzCashActivity.this, "Booking Request Sent", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),
                    Users_Dashboard.class));
            finishAffinity();

/*
            Intent intent=new Intent(JazzCashActivity.this,);
            intent.putExtra("PAYMENTCHECK",1);
            startActivity(intent);
*/
        }
    }

    private void JazzFunction() {
        getDate();
        webView.setInitialScale(1);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);
        webView.setWebViewClient(new WebViewClient());

        webView.loadUrl(JazzUrl + amount + "&id=" + new TinyDB(this).getInt("CUSTOMER_ID"));
    }

    private void getDate() {
        DateFormat dfDate = new SimpleDateFormat("yyyyMMdd");
        String date = dfDate.format(Calendar.getInstance().getTime());
        DateFormat dfTime = new SimpleDateFormat("hhmmss");
        String time = dfTime.format(Calendar.getInstance().getTime());
        TxnRef = "T" + date + time;
    }


}