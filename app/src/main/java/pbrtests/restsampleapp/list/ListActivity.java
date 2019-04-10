package pbrtests.restsampleapp.list;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import pbrtests.restsampleapp.LoginActivity;
import pbrtests.restsampleapp.R;
import pbrtests.restsampleapp.databinding.ActivityListBinding;
import pbrtests.restsampleapp.model.GithubItem;
import pbrtests.restsampleapp.model.GithubRestAdapter;
import pbrtests.restsampleapp.model.ListViewModel;
import pbrtests.restsampleapp.model.RetrofitModule;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ListActivity extends AppCompatActivity {

    private static final String TAG = "ListActivity";

    ActivityListBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_list);

        //attach listVM to dataBinding
        ListViewModel listViewModel = new ListViewModel();
        mBinding.setListViewModel(listViewModel);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String user = getIntent().getExtras().getString("USER");

        Log.d(TAG, "onCreate: user: " + user);

        //Create Retrofit
        Retrofit retrofit = new RetrofitModule().provideRetrofit();
        //create REST adapter with retrofit (includes GET Methods)
        GithubRestAdapter restAdapter = new GithubRestAdapter(retrofit);
        Call<List<GithubItem>> listCall = restAdapter.getGithubItems(user);

        //call list
        apiListCall(listCall);

    }

    private void apiListCall(Call<List<GithubItem>> listCall){

        //enqueue ensures that call is handled on background thread.
        listCall.enqueue(new Callback<List<GithubItem>>() {
            @Override
            public void onResponse(Call<List<GithubItem>> call, Response<List<GithubItem>> response) {

                //Response unsuccessfull
                if (!response.isSuccessful()){

                    Toast.makeText(ListActivity.this, "Failed response code: " + response.code(), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onResponse: " + response.code());
                    startLoginActivity();
                    //prevent NullException
                    return;
                }

                onRestPositiveResponse(response);

            }

            //Failed to connect
            @Override
            public void onFailure(Call<List<GithubItem>> call, Throwable t) {

                Toast.makeText(ListActivity.this, "Failure: " +t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: " + t.getMessage());
                startLoginActivity();
            }
        });
    }

    private void onRestPositiveResponse (Response<List<GithubItem>> response){

        //get Item lists and set it onto binding. This should refresh the listadapter.
        List<GithubItem> items = response.body();
        mBinding.setGithubItems(items);
        mBinding.getListViewModel().setListVisibility(true);
    }

    private void startLoginActivity(){

        Intent intent = new Intent(ListActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
