package service;

import java.util.List;

import models.Repo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {
    @GET("users/{username}/repos")
    Call<List<Repo>> listRepos(@Path("username") String user);
}
