package dagger.examples.com.daggersample.presentation.presenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import dagger.examples.com.daggersample.di.components.AppComponent;
import dagger.examples.com.daggersample.di.components.DaggerAppComponent;
import dagger.examples.com.daggersample.di.components.DaggerReposComponent;
import dagger.examples.com.daggersample.di.components.ReposComponent;
import dagger.examples.com.daggersample.di.modules.AppModule;
import dagger.examples.com.daggersample.di.modules.ReposModule;
import dagger.examples.com.daggersample.presentation.events.ErrorEvent;
import dagger.examples.com.daggersample.presentation.events.RepoEvent;
import dagger.examples.com.daggersample.usecases.interactor.ReposInteractor;
import dagger.examples.com.daggersample.presentation.views.interfaces.IGithubView;

public class ReposPresenter {

    private IGithubView view;

    @Inject
    protected ReposInteractor interactor;

    @Inject
    protected EventBus bus;

    public ReposPresenter() {
        this.injectComponents();
    }

    private void injectComponents() {
        AppComponent appGraph = DaggerAppComponent.builder().appModule(new AppModule()).build();

        DaggerReposComponent.builder().appComponent(appGraph).reposModule(new ReposModule()).build().inject(this);
    }

    public void getReposFromUser(String user) {
        this.interactor.getRepos(user);
    }

    public IGithubView getView() {
        return view;
    }

    public void setView(IGithubView view) {
        this.view = view;
    }

    public void start() {
        this.bus.register(this);
    }

    public void stop() {
        this.bus.unregister(this);
    }

    @Subscribe
    public void onRepoEvent(RepoEvent event) {
        this.view.setData(event.getRepos());
    }

    public void onErrorEvent(ErrorEvent event) {
        this.view.setData(null);
    }

}
