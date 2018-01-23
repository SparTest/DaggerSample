package dagger.examples.com.daggersample.data.api;

import java.util.List;

import dagger.examples.com.daggersample.data.models.Repo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubAPI {

    @GET("users/{user}/repos")
    public Call<List<Repo>> getRepos(@Path("user") String user);
}
