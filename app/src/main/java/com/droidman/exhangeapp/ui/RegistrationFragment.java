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
import android.widget.Toast;

import com.droidman.exhangeapp.common.Common;
import com.droidman.exhangeapp.common.Constants;
import com.droidman.exhangeapp.R;
import com.droidman.exhangeapp.model.User;
import com.droidman.exhangeapp.model.Table;
import com.droidman.exhangeapp.network.RetrofitClientInstance;
import com.droidman.exhangeapp.network.UserServices;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationFragment extends Fragment {

    private Button btnRegister;
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etPhone;
    private EditText etEmail;
    private EditText etPassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        initViews(view);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Common.getInstance().isConnectedToInternet((ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE))) {
                    Toast.makeText(getActivity(), "Check your internet connection", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (isValidUserInput()) {
                    UserServices service = RetrofitClientInstance.getRetrofitInstance().create(UserServices.class);
                    Call<Table> call = service.register(etFirstName.getText().toString(), etLastName.getText().toString(), etPhone.getText().toString(), etEmail.getText().toString(), etPassword.getText().toString());
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
                            Toast.makeText(getActivity(), "Your email is already registered", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        return view;
    }

    private void initViews(View view) {
        btnRegister = view.findViewById(R.id.btnSignup);
        //btnGoToLogin = view.findViewById(R.id.registrationGoToLogin);
        etFirstName = view.findViewById(R.id.registrationFirstName);
        etLastName = view.findViewById(R.id.registrationLastName);
        etPhone = view.findViewById(R.id.registrationPhone);
        etEmail = view.findViewById(R.id.registrationEmail);
        etPassword = view.findViewById(R.id.registrationPassword);
    }

    private boolean isValidUserInput() {
        boolean emailFlag = true;
        boolean passwordFlag = true;
        boolean firstNameFlag = true;
        boolean lastNameFlag = true;
        boolean phoneFlag = true;
        if(etFirstName.getText() == null || etFirstName.getText().toString().trim().equals("")){
            etFirstName.setError("First name can not be empty");
            firstNameFlag = false;
        }else
            etFirstName.setError(null);
        if(etLastName.getText() == null || etLastName.getText().toString().trim().equals("")){
            etLastName.setError("Last name can not be empty");
            lastNameFlag = false;
        }else
            etLastName.setError(null);
        if(etPhone.getText() == null || etPhone.getText().toString().trim().equals("")){
            etPhone.setError("Phone can not be empty");
            phoneFlag = false;
        }else
            etPhone.setError(null);
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
        return firstNameFlag && lastNameFlag && phoneFlag && emailFlag && passwordFlag;
    }

}
