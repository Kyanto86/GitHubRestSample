package pbrtests.restsampleapp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import pbrtests.restsampleapp.databinding.ActivityLoginBinding;
import pbrtests.restsampleapp.list.ListActivity;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding mBinding;

    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);


        mBinding.btnGotolist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = mBinding.et.getText().toString();

                if (user.isEmpty()){

                    Toast.makeText(LoginActivity.this, "Please enter a user name.", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onClick: User name empty.");
                    return;
                }

                Intent intent = new Intent(LoginActivity.this, ListActivity.class);
                intent.putExtra("USER", user);
                startActivity(intent);
            }
        });
    }
}
