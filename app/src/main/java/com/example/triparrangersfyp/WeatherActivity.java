package com.example.triparrangersfyp;

import static com.android.volley.toolbox.Volley.newRequestQueue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class WeatherActivity extends AppCompatActivity {

    EditText et;
    TextView tv, tv2;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        et=(EditText) findViewById(R.id.weatherED);
        tv=(TextView) findViewById(R.id.WeatherTV);
        btn=(Button)findViewById(R.id.weatherbtn);
        tv2=findViewById(R.id.Weatherrrr);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetWeather(v);
            }
        });
    }
    public void GetWeather(View v)
    {
        String APIkey="429c595c80d5f7fcddb5718200ac63f6";
        String city=et.getText().toString();
        String url="https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=429c595c80d5f7fcddb5718200ac63f6";

        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject object = response.getJSONObject("main");
                    String temp = object.getString("temp");
                    Double temperature=Double.parseDouble(temp)-273.15;
                    tv.setText(temperature.toString().substring(0,5)+"C");
                    if(temperature>25)
                        tv2.setText("Its Hot");
                    else if(temperature>15 && temperature < 25)
                        tv2.setText("Pleasant Weather");
                    else if(temperature<15)
                        tv2.setText("Cold Weather");


                } catch (JSONException e) {
                    Toast.makeText(WeatherActivity.this, e.toString(),Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(WeatherActivity.this, error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);

    }
}