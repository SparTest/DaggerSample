package dagger.examples.com.daggersample.presentation.views.interfaces;

import java.util.List;

import dagger.examples.com.daggersample.data.models.Repo;

public interface IGithubView {

    public void getRepos(String user);

    public void setData(List<Repo> repos);
}
