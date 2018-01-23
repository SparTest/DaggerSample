package dagger.examples.com.daggersample.di.components;

import dagger.Component;
import dagger.examples.com.daggersample.di.modules.ReposModule;
import dagger.examples.com.daggersample.di.scopes.ActivityScope;
import dagger.examples.com.daggersample.presentation.presenter.ReposPresenter;
import dagger.examples.com.daggersample.presentation.views.ReposActivity;
import dagger.examples.com.daggersample.usecases.interactor.ReposInteractor;


@ActivityScope
@Component(modules = ReposModule.class, dependencies = AppComponent.class)
public interface ReposComponent {
    public ReposPresenter getPresenter();

    public ReposInteractor getInteractor();

    public void inject(ReposActivity activ);

    public void inject(ReposPresenter presenter);

    public void inject(ReposInteractor interactor);
}
