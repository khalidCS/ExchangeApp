package com.droidman.exhangeapp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.droidman.exhangeapp.R;

public class UserActivity extends FragmentActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        changeFragment(new LoginFragment());
    }


    public void goToLoginPage(View v){
        changeFragment(new LoginFragment());
    }

    public void goToRegistrationPage(View v){
        changeFragment(new RegistrationFragment());
    }

    private void changeFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.linearLayoutUserActivity, fragment);
        fragmentTransaction.commit();
    }


}
