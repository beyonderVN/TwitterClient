package com.longngohoang.twitter.mobile.ui.resume;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.longngohoang.twitter.appcore.data.backend.github.model.GitRepo;
import com.longngohoang.twitter.appcore.data.backend.github.model.UserGit;
import com.longngohoang.twitter.appcore.interactor.DefaultSubscriber;
import com.longngohoang.twitter.mobile.MainApplication;
import com.longngohoang.twitter.mobile.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;

public class ResumeFragment extends Fragment {
    private static final String TAG = "ResumeFragment";
    private static UserGit userGit;
    private static List<GitRepo> gitRepos;
    @BindView(R.id.ivBanner)
    ImageView ivBanner;
    @BindView(R.id.ivAvatar)
    ImageView ivAvatar;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.tvDes)
    TextView tvDes;
    @BindView(R.id.rvRepoList)
    RecyclerView rvRepoList;


    public ResumeFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static ResumeFragment newInstance(String param1, String param2) {
        ResumeFragment fragment = new ResumeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    Subscription subscription ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_resume, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        if (userGit != null) {
            Picasso.with(getContext()).load(userGit.getAvatarUrl()).into(ivAvatar);
            tvName.setText(userGit.getName());
            tvEmail.setText(userGit.getEmail());
            tvDes.setText(userGit.getHtmlUrl());
        }else {
            subscription =   MainApplication.getMainComponent().githubService().getUserGit("beyonderVN")
                    .subscribeOn(MainApplication.getMainComponent().schedulerProvider().computation())
                    .observeOn(MainApplication.getMainComponent().schedulerProvider().ui())
                    .subscribe(new DefaultSubscriber<UserGit>() {
                        @Override
                        public void onCompleted() {
                            super.onCompleted();
                            Log.d(TAG, "onCompleted: ");
                        }

                        @Override
                        public void onError(Throwable e) {
                            super.onError(e);
                            Log.d(TAG, "onError: ");
                        }

                        @Override
                        public void onNext(UserGit userGit1) {
                            super.onNext(userGit);
                            Log.d(TAG, "onNext: "+userGit1.getName());
                            userGit = userGit1;
                            Picasso.with(getContext()).load(userGit.getAvatarUrl()).into(ivAvatar);
                            tvName.setText(userGit.getName());
                            tvEmail.setText(userGit.getEmail());
                            tvDes.setText(userGit.getHtmlUrl());
                        }
                    });

        }
        if (gitRepos != null&&gitRepos.size()>0) {

        }else {
            subscription =   MainApplication.getMainComponent().githubService().getUserGit("beyonderVN")
                    .subscribeOn(MainApplication.getMainComponent().schedulerProvider().computation())
                    .observeOn(MainApplication.getMainComponent().schedulerProvider().ui())
                    .subscribe(new DefaultSubscriber<UserGit>() {
                        @Override
                        public void onCompleted() {
                            super.onCompleted();
                            Log.d(TAG, "onCompleted: ");
                        }

                        @Override
                        public void onError(Throwable e) {
                            super.onError(e);
                            Log.d(TAG, "onError: ");
                        }

                        @Override
                        public void onNext(UserGit userGit1) {
                            super.onNext(userGit);
                            Log.d(TAG, "onNext: "+userGit1.getName());
                            userGit = userGit1;
                            Picasso.with(getContext()).load(userGit.getAvatarUrl()).into(ivAvatar);
                            tvName.setText(userGit.getName());
                            tvEmail.setText(userGit.getEmail());
                            tvDes.setText(userGit.getHtmlUrl());
                        }
                    });

        }

    }

    @Override
    public void onDetach() {
        if (subscription != null) {
            subscription.unsubscribe();
        }
        super.onDetach();

    }




}
