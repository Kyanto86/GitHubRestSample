package pbrtests.restsampleapp.errorHandling;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;


/*ErrorInterceptor is not necessary yet, just one step into direction of later implenting DaggerInjection and RxJava*/
public class ErrorInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Response response = chain.proceed(chain.request());

        if (!response.isSuccessful()){

            throw new GitHubException(response.code(), response.message());
        }
        return response;
    }
}
