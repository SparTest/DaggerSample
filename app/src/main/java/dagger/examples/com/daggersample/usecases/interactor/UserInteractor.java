package dagger.examples.com.daggersample.usecases.interactor;

import org.greenrobot.eventbus.EventBus;

import dagger.examples.com.daggersample.presentation.events.UserEvent;


public class UserInteractor {

    protected EventBus bus;


    public UserInteractor(EventBus bus) {
        this.bus = bus;
    }

    public void validateUser(String name) {
        try {
            Thread.sleep(1000L);

            this.bus.post(new UserEvent(true));

        } catch (Exception e) {
            this.bus.post(new UserEvent(false));
        }
    }
}
