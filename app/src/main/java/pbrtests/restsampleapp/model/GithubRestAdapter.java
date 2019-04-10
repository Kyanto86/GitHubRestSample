package pbrtests.restsampleapp.model;

import java.util.List;

import pbrtests.restsampleapp.util.UrlManager;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class GithubRestAdapter {

   private final GitHubService gitHubService;



    public GithubRestAdapter(Retrofit retrofit) {
        this.gitHubService = retrofit.create(GitHubService.class);
    }

    public interface GitHubService{

        @GET(UrlManager.REPOS)
        Call<List<GithubItem>> getGithubItems(
                @Path("user") String user
        );
    }

    public Call<List<GithubItem>> getGithubItems (String user){

        return gitHubService.getGithubItems(user);
    }
}
