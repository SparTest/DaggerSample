package dagger.examples.com.daggersample.usecases.interactor;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import dagger.examples.com.daggersample.data.api.GithubAPI;
import dagger.examples.com.daggersample.di.components.AppComponent;
import dagger.examples.com.daggersample.di.components.DaggerAppComponent;
import dagger.examples.com.daggersample.di.components.DaggerReposComponent;
import dagger.examples.com.daggersample.di.components.ReposComponent;
import dagger.examples.com.daggersample.di.modules.AppModule;
import dagger.examples.com.daggersample.di.modules.ReposModule;
import dagger.examples.com.daggersample.data.models.Repo;
import dagger.examples.com.daggersample.presentation.events.ErrorEvent;
import dagger.examples.com.daggersample.presentation.events.RepoEvent;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReposInteractor {

    @Inject
    protected EventBus bus;

    @Inject
    protected GithubAPI service;

    public ReposInteractor() {
        this.injectComponents();
    }

    private void injectComponents() {
        AppComponent appGraph = DaggerAppComponent.builder().appModule(new AppModule()).build();

        ReposComponent reposGraph = DaggerReposComponent.builder().appComponent(appGraph).reposModule(new ReposModule()).build();

        reposGraph.inject(this);
    }


    public void getRepos(String user) {
        Call<List<Repo>> call = this.service.getRepos(user);

        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                bus.post(new RepoEvent(response.body()));
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                bus.post(new ErrorEvent());
            }
        });
    }
}
