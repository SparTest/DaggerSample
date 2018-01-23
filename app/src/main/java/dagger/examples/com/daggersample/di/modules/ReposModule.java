package dagger.examples.com.daggersample.di.modules;

import dagger.Module;
import dagger.Provides;
import dagger.examples.com.daggersample.data.api.GithubAPI;
import dagger.examples.com.daggersample.usecases.interactor.ReposInteractor;
import dagger.examples.com.daggersample.presentation.presenter.ReposPresenter;
import retrofit2.Retrofit;

@Module
public class ReposModule {

    @Provides
    public ReposPresenter getPresenter() {
        return new ReposPresenter();
    }

    @Provides
    public ReposInteractor getInteractor() {
        return new ReposInteractor();
    }

    @Provides
    public GithubAPI getService(Retrofit adapter) {
        return adapter.create(GithubAPI.class);
    }
}
