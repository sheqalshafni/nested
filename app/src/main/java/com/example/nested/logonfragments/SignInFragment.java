package com.example.nested.logonfragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nested.HomeActivity;
import com.example.nested.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firestore.admin.v1beta1.Progress;

import butterknife.BindView;

import static android.support.constraint.Constraints.TAG;
import static android.view.View.inflate;

public class SignInFragment extends Fragment {
    public static SignInFragment getInstance(){
        return new SignInFragment();
    }

    @BindView(R.id.btnGuest)
    Button guestSignIn;
    @BindView(R.id.btnSignIn)
    Button signin;

    @BindView(R.id.etEmail)
    public EditText txtUsername;
    @BindView(R.id.etPassword)
    public EditText txtPassword;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseFirestore db;

    String email;
    String password;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signin, container, false);

        //init
        signin = view.findViewById(R.id.btnSignIn);
        guestSignIn = view.findViewById(R.id.btnGuest);

        final ProgressDialog progressDialog = new ProgressDialog(getActivity(), R.style.MyAlertDialogStyle);
        progressDialog.setMessage("Logging In");

        txtUsername = view.findViewById(R.id.etEmail);
        txtPassword = view.findViewById(R.id.etPassword);

        firebaseAuth = firebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        guestSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);

                getActivity().finish();
            }
        });

        if(firebaseAuth.getCurrentUser() != null && firebaseUser != null && firebaseUser.isEmailVerified()){
            Intent intent = new Intent(getActivity(), HomeActivity.class);
            startActivity(intent);
            getActivity().finish();
        } /*else if (firebaseUser != null && firebaseUser.isEmailVerified()){
            Intent intent = new Intent(getActivity(), LogOnActivity.class);
            startActivity(intent);
        }*/

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                try{

                    email = txtUsername.getText().toString();
                    password = txtPassword.getText().toString();

                    if (email.isEmpty()){
                        txtUsername.setError("Email is required");
                        txtUsername.requestFocus();
                    } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                        txtUsername.setError("Please enter a valid email address");
                        txtUsername.requestFocus();
                    } else if (password.isEmpty()){
                        txtPassword.setError("Password is required");
                        txtPassword.requestFocus();
                    } else {
                        progressDialog.show();
                        firebaseAuth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        try{

                                            if (task.isSuccessful()){

                                                if (firebaseUser != null && !firebaseUser.isEmailVerified()) {
                                                    progressDialog.dismiss();
                                                    Toast.makeText(getActivity(), "Please verify your email", Toast.LENGTH_SHORT).show();
                                                } else {

                                                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                                                    startActivity(intent);

                                                    getActivity().finish();
                                                    progressDialog.dismiss();
                                                }
                                            }else{
                                                progressDialog.dismiss();
                                                Toast.makeText(getActivity(), "Incorrect email or password" , Toast.LENGTH_SHORT).show();
                                            }

                                        }catch (Exception ex){
                                            progressDialog.dismiss();
                                            Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                                            Log.d(TAG, ex.getMessage());
                                        }
                                    }
                                });
                    }

                }catch(Exception ex){
                    progressDialog.dismiss();
                    Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

}
