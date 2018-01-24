package dagger.examples.com.daggersample.di.modules;

import org.greenrobot.eventbus.EventBus;

import dagger.Module;
import dagger.Provides;
import dagger.examples.com.daggersample.usecases.interactor.UserInteractor;
import dagger.examples.com.daggersample.presentation.presenter.UserPresenter;


/**
 * Custom validateUser component implementation
 */
@Module
public class UserModule {

    /**
     * Create a validateUser presenter
     *
     * @return Login presenter
     */
    @Provides
    public UserPresenter getPresenter() {
        return new UserPresenter();
    }

    @Provides
    public UserInteractor getInteractor(EventBus bus) {
        return new UserInteractor(bus);
    }
}
