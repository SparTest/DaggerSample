package dagger.examples.com.daggersample.presentation.app;

import android.app.Application;

import dagger.examples.com.daggersample.di.components.AppComponent;
import dagger.examples.com.daggersample.di.components.DaggerAppComponent;
import dagger.examples.com.daggersample.di.modules.AppModule;
import dagger.examples.com.daggersample.presentation.app.interfaces.IGraph;


/**
 * Custom application
 */
public class CustomApp extends Application implements IGraph {

    private AppComponent graph;


    @Override
    public void onCreate() {
        super.onCreate();

        this.createGraph();
    }

    /**
     * Graph object creation
     */
    private void createGraph() {
        this.graph = DaggerAppComponent.builder().appModule(new AppModule()).build();
    }

    @Override
    public AppComponent getGraph() {
        return this.graph;
    }
}
