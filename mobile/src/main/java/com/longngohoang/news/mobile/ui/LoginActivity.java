package com.longngohoang.news.mobile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.longngohoang.news.mobile.MainApplication;
import com.longngohoang.news.mobile.R;
import com.longngohoang.news.mobile.ui.browser.BrowserActivity;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.twitter.sdk.android.core.models.User;

import rx.functions.Action1;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private TwitterLoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        loginButton = (TwitterLoginButton) findViewById(R.id.twitter_login_button);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                Log.d(TAG, "success: ");
                MainApplication.getMainComponent().twitterService().getUserProfile().subscribe(new Action1<User>() {
                    @Override
                    public void call(User user) {
                        Log.d(TAG, "call: "+user.name);
                    }
                });
                startActivity(new Intent(LoginActivity.this, BrowserActivity.class));
                finishAfterTransition();
            }

            @Override
            public void failure(TwitterException exception) {
                Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                Twitter.getSessionManager();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loginButton.onActivityResult(requestCode, resultCode, data);
    }
}
