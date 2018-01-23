package dagger.examples.com.daggersample.presentation.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.examples.com.daggersample.R;
import dagger.examples.com.daggersample.di.components.AppComponent;
import dagger.examples.com.daggersample.di.components.DaggerAppComponent;
import dagger.examples.com.daggersample.di.components.DaggerReposComponent;
import dagger.examples.com.daggersample.di.components.ReposComponent;
import dagger.examples.com.daggersample.di.modules.AppModule;
import dagger.examples.com.daggersample.di.modules.ReposModule;
import dagger.examples.com.daggersample.data.models.Repo;
import dagger.examples.com.daggersample.presentation.adapters.ReposAdapter;
import dagger.examples.com.daggersample.presentation.app.interfaces.IGraph;
import dagger.examples.com.daggersample.presentation.presenter.ReposPresenter;
import dagger.examples.com.daggersample.presentation.views.interfaces.IGithubView;


public class ReposActivity extends AppCompatActivity implements IGithubView {

    @Inject
    protected ReposPresenter presenter;

    protected RecyclerView list;
    protected TextView empty;

    private ReposAdapter adapter;

    private String userName;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (this.getIntent().hasExtra("user")) {
            this.userName = this.getIntent().getStringExtra("user");
        } else {
            this.userName = "SparTest";
        }

        this.setContentView(R.layout.activity_repos);

        this.bindViews();

        this.injectComponents();
    }

    @Override
    protected void onStart() {
        super.onStart();

        this.presenter.setView(this);
        this.presenter.start();
    }

    @Override
    protected void onStop() {
        super.onStop();

        this.presenter.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();

        this.presenter.getReposFromUser(this.userName);
    }


    private void bindViews() {
        this.list = (RecyclerView) this.findViewById(android.R.id.list);
        this.empty = (TextView) this.findViewById(android.R.id.empty);

        this.configList();
    }

    private void injectComponents() {
        AppComponent appGraph = ((IGraph) this.getApplication()).getGraph();

        ReposComponent reposGraph = DaggerReposComponent.builder().appComponent(appGraph).reposModule(new ReposModule()).build();

        reposGraph.inject(this);
    }

    @Override
    public void getRepos(String user) {
        this.presenter.getReposFromUser(user);
    }

    @Override
    public void setData(List<Repo> repos) {
        if (repos != null) {
            this.adapter = new ReposAdapter(repos);
            this.list.setAdapter(this.adapter);
            this.adapter.notifyDataSetChanged();

        } else {
            this.list.setVisibility(View.GONE);
            this.empty.setVisibility(View.VISIBLE);
        }
    }

    private void configList() {
        this.list.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        this.list.setHasFixedSize(false);
        this.list.setAdapter(this.adapter);
    }
}
