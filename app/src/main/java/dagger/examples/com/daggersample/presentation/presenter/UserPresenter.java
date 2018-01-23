package dagger.examples.com.daggersample.presentation.presenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

//import dagger.examples.com.daggersample.di.DaggerLoginComponent;
import dagger.examples.com.daggersample.di.components.AppComponent;
import dagger.examples.com.daggersample.di.components.DaggerAppComponent;
import dagger.examples.com.daggersample.di.components.DaggerUserComponent;
import dagger.examples.com.daggersample.di.modules.AppModule;
import dagger.examples.com.daggersample.di.modules.UserModule;
import dagger.examples.com.daggersample.presentation.views.interfaces.ILoginView;
import dagger.examples.com.daggersample.presentation.events.UserEvent;
import dagger.examples.com.daggersample.usecases.interactor.UserInteractor;

public class UserPresenter {

    private static final String TAG = UserPresenter.class.getSimpleName();

    @Inject
    protected EventBus bus;

    @Inject
    protected UserInteractor interactor;

    private ILoginView view;

    public UserPresenter() {
        this.injectComponents();
    }

    private void injectComponents() {
        AppComponent appGraph = DaggerAppComponent.builder().appModule(new AppModule()).build();

        DaggerUserComponent.builder().appComponent(appGraph).userModule(new UserModule()).build().inject(this);
    }

    public void validateUserName(String name) {
        this.view.showLoading();

        this.interactor.validateUser(name);
    }

    public void setView(ILoginView view) {
        this.view = view;
    }

    public void start() {
        this.bus.register(this);
    }

    public void stop() {
        this.bus.unregister(this);
    }

    @Subscribe
    public void onUserVerificationEvent(UserEvent event) {
        this.view.hideLoading();

        this.view.onUserResult(event.isResult());
    }

}
