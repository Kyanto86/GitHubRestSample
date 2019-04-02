package pbrtests.restsampleapp.model;

import okhttp3.OkHttpClient;
import pbrtests.restsampleapp.errorHandling.ErrorInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitModule {

    public Retrofit provideRetrofit(){

        /*OkHttpClient is not necessary yet, just one step into direction of later implenting DaggerInjection and RxJava*/
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new ErrorInterceptor())
                .retryOnConnectionFailure(true)
                .build();


       //Build and return retrofit instance
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(UrlManager.API_HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
