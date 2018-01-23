package dagger.examples.com.daggersample.presentation.views.interfaces;

public interface ILoginView {
    public String getUserName();
    public void showLoading();
    public void hideLoading();
    public void onUserResult(boolean success);
}
