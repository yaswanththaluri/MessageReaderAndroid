package androidapp.yashthaluri.com.messagereader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MessageReceiver.bindListener(new MessageListener() {
            @Override
            public void messageReceived(String message) {
                TextView textView = findViewById(R.id.msg);
                textView.setText(message);
                Toast.makeText(MainActivity.this, "New Message Received: " + message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

        }
        else
        {   ArrayList<String> arrPerm = new ArrayList<>();
            arrPerm.add(Manifest.permission.RECEIVE_SMS);
            arrPerm.add(Manifest.permission.READ_SMS);

            if(!arrPerm.isEmpty()) {
                String[] permissions = new String[arrPerm.size()];
                permissions = arrPerm.toArray(permissions);
                ActivityCompat.requestPermissions(this, permissions, 0);
            }
        }
    }
}
