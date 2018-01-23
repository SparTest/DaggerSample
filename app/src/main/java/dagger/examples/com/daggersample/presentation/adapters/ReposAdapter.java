package dagger.examples.com.daggersample.presentation.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import dagger.examples.com.daggersample.R;
import dagger.examples.com.daggersample.data.models.Repo;

public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.Holder> {

    private List<Repo> repos;

    public ReposAdapter(List<Repo> repos) {
        this.repos = repos;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repo, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Repo data = this.repos.get(position);

        holder.bind(data);
    }

    @Override
    public int getItemCount() {
        return this.repos != null? this.repos.size() : 0;
    }

    public class Holder extends RecyclerView.ViewHolder {

        private TextView repoName;

        public Holder(View itemView) {
            super(itemView);

            this.bindViews(itemView);
        }

        private void bindViews(View item) {
            this.repoName = (TextView) item.findViewById(R.id.repoName);
        }

        public void bind(Repo data) {
            this.repoName.setText(data.getFull_name());
        }
    }
}
