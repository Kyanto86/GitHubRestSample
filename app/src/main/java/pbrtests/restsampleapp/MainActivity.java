package pbrtests.restsampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pbrtests.restsampleapp.list.ListActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editText = findViewById(R.id.et);

        Button btn_list = findViewById(R.id.btn_gotolist);
        btn_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = editText.getText().toString();

                if (user.isEmpty()){

                    Toast.makeText(MainActivity.this, "Please enter a user name.", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onClick: User name empty.");
                    return;
                }

                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                intent.putExtra("USER", user);
                startActivity(intent);
            }
        });
    }
}
