package dagger.examples.com.daggersample.di.components;

import dagger.Component;
import dagger.examples.com.daggersample.di.modules.UserModule;
import dagger.examples.com.daggersample.di.scopes.ActivityScope;
import dagger.examples.com.daggersample.usecases.interactor.UserInteractor;
import dagger.examples.com.daggersample.presentation.views.UserActivity;
import dagger.examples.com.daggersample.presentation.presenter.UserPresenter;


/**
 * Specific validateUser component. Uses the validateUser module.
 *
 * Publishes elements used in the validateUser module. Uses the main component as a dependency.
 */
@ActivityScope
@Component(modules = UserModule.class, dependencies = AppComponent.class)
public interface UserComponent {

// PROVIDED ELEMENTS

    /**
     * Publish a method to retrieve the validateUser presenter
     *
     * @return
     */
    public UserPresenter getPresenter();

    public UserInteractor getInteractor();

// ELEMENTS WHERE INJECTION TAKES PLACE

    /**
     * Dependency injection in validateUser activ
     *
     * @param activ
     */
    public void inject(UserActivity activ);

    /**
     * Depedency injection in validateUser presenter
     *
     * @param presenter
     */
    public void inject(UserPresenter presenter);
}
