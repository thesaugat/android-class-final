package com.thesaugat.androidclass.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.thesaugat.androidclass.R;
import com.thesaugat.androidclass.activity.HomeActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    TextView forgetPassword, registerTV;
    Button login;
    EditText emailField, passwordField;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
//         Inflate the layout for this fragment
        forgetPassword = view.findViewById(R.id.forgetPasswordTV);
        login = view.findViewById(R.id.loginBtn);
        emailField = view.findViewById(R.id.notificationTitleET);
        passwordField = view.findViewById(R.id.passwordFieldET);
        registerTV = view.findViewById(R.id.registerTV);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateData()) {
                    openHome();
                }
            }
        });

        registerTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignUpFragment signUpFragment = new SignUpFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.mainFrameContainer, signUpFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        return view;
    }

    private Boolean validateData() {
        Boolean isAllOK = true;
        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();

        if (email.isEmpty() && password.isEmpty()) {
            emailField.setError("Email cannot be empty");
            passwordField.setError("Password cannot be empty");
            createAToast("Email and Password must not be empty");
            return false;
        } else if (email.isEmpty()) {
            emailField.setError("Email cannot be empty");
            return false;
        } else if (password.isEmpty()) {
            passwordField.setError("Password cannot be empty");
            return false;
        }
        if (password.length() < 7) {
            passwordField.setError("Password should be more than 7 character");
            return false;
        }
        if (!validateEmail(email)) {
            emailField.setError("Please Provide Valid Email Address.");
            return false;
        }
        return isAllOK;
    }

    void createAToast(String message) {

        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }


    void openHome() {
        Intent homeIntent = new Intent(getActivity(), HomeActivity.class);
        startActivity(homeIntent);
        getActivity().finish();
    }


    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
}