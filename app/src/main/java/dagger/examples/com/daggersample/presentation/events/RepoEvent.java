package dagger.examples.com.daggersample.presentation.events;

import java.util.List;

import dagger.examples.com.daggersample.data.models.Repo;

public class RepoEvent {
    private List<Repo> repos;

    public RepoEvent(List<Repo> repos) {
        this.repos = repos;
    }

    public List<Repo> getRepos() {
        return repos;
    }

    public void setRepos(List<Repo> repos) {
        this.repos = repos;
    }
}
