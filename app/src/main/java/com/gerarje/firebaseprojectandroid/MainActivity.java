package com.gerarje.firebaseprojectandroid;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity<btnSuscribeFirebase> extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String KEY_DECOUNT = "descount_key";

    private TextView tvDescountMessage;
    private Button btnSuscribeAndroid;
    private Button btnSuscribeFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDescountMessage = findViewById(R.id.tvDescountMessage);
        btnSuscribeAndroid = findViewById(R.id.btnSuscribeAndroid);
        btnSuscribeFirebase = findViewById(R.id.btnSuscribeFirebase);
        tvDescountMessage.setVisibility(View.GONE);

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener( MainActivity.this,  new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String newToken = instanceIdResult.getToken();
                Log.w(TAG,newToken);
                if (getIntent().getExtras() != null){
                    tvDescountMessage.setVisibility(View.VISIBLE);
                    String descount = getIntent().getExtras().getString(KEY_DECOUNT);
                    tvDescountMessage.append(descount);
                }
            }
        });
    }

    public void suscribeAndroid(View view){
        FirebaseMessaging.getInstance().subscribeToTopic("Android");
        Toast.makeText(this, "Felicidades te suscribiste a Android", Toast.LENGTH_SHORT).show();
    }

    public void sucribeFirebase(View view){
        FirebaseMessaging.getInstance().subscribeToTopic("Firebase");
        Toast.makeText(this, "Felicidades te suscribiste a Firebase", Toast.LENGTH_SHORT).show();
    }
}
