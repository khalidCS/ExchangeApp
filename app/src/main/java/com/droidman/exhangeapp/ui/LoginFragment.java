package com.droidman.exhangeapp.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.droidman.exhangeapp.common.Common;
import com.droidman.exhangeapp.common.Constants;
import com.droidman.exhangeapp.R;
import com.droidman.exhangeapp.model.User;
import com.droidman.exhangeapp.model.Table;
import com.droidman.exhangeapp.network.UserServices;
import com.droidman.exhangeapp.network.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    private Button btnLogin;
    private EditText etEmail;
    private EditText etPassword;
    private TextView tvContinueWithoutLogin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initViews(view);


        tvContinueWithoutLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),DashboardActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Common.getInstance().isConnectedToInternet((ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE))) {
                    Toast.makeText(getActivity(), "Check your internet connection", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (isValidUserInput()) {
                    UserServices service = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
                    Call<Table> call = service.login(etEmail.getText().toString(), etPassword.getText().toString());
                    call.enqueue(new Callback<Table>() {
                        @Override
                        public void onResponse(Call<Table> call, Response<Table> response) {
                            User user = Common.getInstance().extractUserFromResponse(response);
                            Intent i = new Intent(getActivity(),DashboardActivity.class);
                            i.putExtra(Constants.USER_ID,user.getId());
                            startActivity(i);
                        }

                        @Override
                        public void onFailure(Call<Table> call, Throwable t) {
                            Toast.makeText(getActivity(), "wrong email or password", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        return view;
    }

    private void initViews(View view) {
        btnLogin = view.findViewById(R.id.loginBtn);
        etEmail = view.findViewById(R.id.loginEmail);
        etPassword = view.findViewById(R.id.loginPassword);
        tvContinueWithoutLogin = view.findViewById(R.id.loginContinueWithoutLogin);
    }

    private boolean isValidUserInput() {
        boolean emailFlag = true;
        boolean passwordFlag = true;
        if(etEmail.getText() == null || etEmail.getText().toString().trim().equals("")){
            etEmail.setError("Email can not be empty");
            emailFlag = false;
        }else
            etEmail.setError(null);
        if(etPassword.getText() == null || etPassword.getText().toString().trim().equals("")){
            etPassword.setError("Password can not be empty");
            passwordFlag = false;
        }else
            etPassword.setError(null);
        return emailFlag && passwordFlag;
    }
}