package dagger.examples.com.daggersample.di.modules;

import android.content.Context;
import android.util.EventLog;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Main application component implementation
 */
@Module
public class AppModule {

    //protected Context cntxt;


    public AppModule() {

    }

    /*
    public AppModule(Context cntxt) {
        this.cntxt = cntxt;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return this.cntxt;
    }
    */

    /**
     * Create an event bus
     *
     * @return Shared event bus
     */
    @Provides
    @Singleton
    public EventBus getBus() {
        return EventBus.getDefault();
    }

    @Provides
    @Singleton
    public Retrofit getAdapter(OkHttpClient client, GsonConverterFactory factory) {
        final String BASE_URL = "https://api.github.com";

        Retrofit adapter =
            new Retrofit.Builder().addConverterFactory(factory).baseUrl(BASE_URL).client(client).build();

        return adapter;
    }

    @Provides
    @Singleton
    public GsonConverterFactory getFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    public HttpLoggingInterceptor getInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return interceptor;
    }

    @Provides
    @Singleton
    public OkHttpClient getClient(HttpLoggingInterceptor interceptor) {
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
    }
}
