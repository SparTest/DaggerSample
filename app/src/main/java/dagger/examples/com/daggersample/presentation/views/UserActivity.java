package dagger.examples.com.daggersample.presentation.views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import javax.inject.Inject;

import dagger.examples.com.daggersample.R;
import dagger.examples.com.daggersample.di.components.AppComponent;
import dagger.examples.com.daggersample.di.components.DaggerUserComponent;
import dagger.examples.com.daggersample.di.components.UserComponent;
import dagger.examples.com.daggersample.di.modules.UserModule;
import dagger.examples.com.daggersample.presentation.app.interfaces.IGraph;
import dagger.examples.com.daggersample.presentation.presenter.UserPresenter;
import dagger.examples.com.daggersample.presentation.views.interfaces.ILoginView;

public class UserActivity extends AppCompatActivity implements ILoginView {

    private ScrollView formContainer;
    private EditText email;

    @Inject
    protected UserPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_user);

        this.bindViews();

        this.injectComponents();

    }

    @Override
    protected void onStart() {
        super.onStart();

        this.presenter.start();
        this.presenter.setView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();

        this.presenter.stop();
    }

    private void injectComponents() {

        // 1. get app component 1st cause its a dependency of local component
        AppComponent graph = ((IGraph) this.getApplication()).getGraph();

        // 2. build local component
        UserComponent userGraph = DaggerUserComponent.builder().appComponent(graph).userModule(new UserModule()).build();

        // 3. inject fields
        userGraph.inject(this);

    }

    private void bindViews() {
        this.formContainer = (ScrollView) this.findViewById(R.id.login_form_container);
        this.email = (EditText) this.findViewById(R.id.email);
    }

    public void onLoginClick(View v) {
        this.presenter.validateUserName(this.getUserName());
    }

    @Override
    public String getUserName() {
        return (this.email != null)? this.email.getText().toString() : "";
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onUserResult(boolean success) {
        if (success) {
            this.navigateToList();
        } else {
            Toast.makeText(this, "Validation error", Toast.LENGTH_LONG).show();
        }
    }

    private void navigateToList() {
        Intent i = new Intent(this, ReposActivity.class);

        i.putExtra("user", this.getUserName());

        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        this.startActivity(i);
    }
}
