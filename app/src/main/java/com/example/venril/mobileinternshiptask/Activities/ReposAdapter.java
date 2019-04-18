package com.example.venril.mobileinternshiptask.Activities;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.venril.mobileinternshiptask.ApiData.UserRepoModel;
import com.example.venril.mobileinternshiptask.R;

import java.util.List;

public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.RecyclerViewHolder> {
    private List<UserRepoModel> userRepoModelList;
    private RepoDetailsClickListener repoDetailsClickListener;

    public ReposAdapter(List<UserRepoModel> userRepoModelList) {
        this.userRepoModelList = userRepoModelList;
    }

    public void setOnRepoDetailsItemClickedListener(RepoDetailsClickListener repoDetailsItemClickedListener) {
        this.repoDetailsClickListener = repoDetailsItemClickedListener;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {
        UserRepoModel userRepoModel = userRepoModelList.get(i);
        recyclerViewHolder.repoName.setText(userRepoModel.getName());
        recyclerViewHolder.repoDescription.setText(userRepoModel.getDescription());
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new RecyclerViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.repo_recycler_item, viewGroup, false));
    }

    @Override
    public int getItemCount() {
        return userRepoModelList.size();
    }



    public interface RepoDetailsClickListener {
        void onRepoItemClicked(int position, View view);
    }



    class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView repoName;
        private TextView repoDescription;

        RecyclerViewHolder(View view) {
            super(view);
            repoName = view.findViewById(R.id.repo_name_textview);
            repoDescription = view.findViewById(R.id.repo_description_textview);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            repoDetailsClickListener.onRepoItemClicked(getAdapterPosition(), v);
        }
    }
}
