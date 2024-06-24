package btl.btl_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.btl.btl_android.Activity.DangKy;
import com.btl.btl_android.Activity.DangNhap;

public class MainActivity extends AppCompatActivity {

    Button bDN, bDK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bDN = (Button) findViewById(R.id.btnDN);
        bDK = (Button) findViewById(R.id.btnDK);
        bDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DangKy.class);
                startActivity(i);
            }
        });
        bDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DangNhap.class);
                startActivity(i);
            }
        });
    }
}