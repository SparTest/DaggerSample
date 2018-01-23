package dagger.examples.com.daggersample.di.components;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Component;
import dagger.examples.com.daggersample.di.modules.AppModule;
import retrofit2.Retrofit;


/**
 * Main application component. Uses the main application module.
 * Publishes shared elements across the whole app.
 * It's used as a dependency of other components
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    /**
     * Publish a method to retrieve the event bus
     *
     * @return
     */
    //public Context context();
    public EventBus getBus();

    public Retrofit getAdapter();
}
