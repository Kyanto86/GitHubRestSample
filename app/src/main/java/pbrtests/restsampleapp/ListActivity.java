package pbrtests.restsampleapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import pbrtests.restsampleapp.model.GithubItem;
import pbrtests.restsampleapp.model.GithubRestAdapter;
import pbrtests.restsampleapp.model.RecyclerAdapter;
import pbrtests.restsampleapp.model.RetrofitModule;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ListActivity extends AppCompatActivity {

    private static final String TAG = "ListActivity";

    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        String user = getIntent().getExtras().getString("USER");
        recyclerView = findViewById(R.id.rv_list_activity);
        final RecyclerAdapter recyclerAdapter = new RecyclerAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAdapter);

        Log.d(TAG, "onCreate: user: " + user);


        /*The below shouldn't be in an activity, but the purpose was to make a Retrofit example and try it out.*/

        //Create Retrofit
        Retrofit retrofit = new RetrofitModule().provideRetrofit();
        //create adapter with retrofit (includes GET Methods)
        GithubRestAdapter restAdapter = new GithubRestAdapter(retrofit);
        Call<List<GithubItem>> listCall = restAdapter.getGithubItems(user);

        //enqueue ensures that call is handled on background thread.
        listCall.enqueue(new Callback<List<GithubItem>>() {
            @Override
            public void onResponse(Call<List<GithubItem>> call, Response<List<GithubItem>> response) {

                //Response unsuccessfull
                if (!response.isSuccessful()){

                    Toast.makeText(ListActivity.this, "Failed response code: " + response.code(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onResponse: " + response.code());
                    startMainActivity();
                    //prevent NullException
                    return;
                }

                //get Item lists and send them to recyclerview. Separate, so it's easier to see that response.body returns list.
                List<GithubItem> items = response.body();
                recyclerAdapter.updateAdapter(items);
            }

            //Failed to connect
            @Override
            public void onFailure(Call<List<GithubItem>> call, Throwable t) {

                Toast.makeText(ListActivity.this, "Failure: " +t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: " + t.getMessage());
                startMainActivity();
            }
        });

    }

    private void startMainActivity(){

        Intent intent = new Intent(ListActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
