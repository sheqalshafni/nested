package com.example.nested.logonfragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nested.R;
import com.example.nested.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import butterknife.BindView;

public class RegisterFragment extends Fragment {
    public static RegisterFragment getInstance(){
        return new RegisterFragment();
    }

    @BindView(R.id.txtUsername)
    EditText txtUsername;
    @BindView(R.id.txtPassword)
    EditText txtPassword;
    @BindView(R.id.btnRegister)
    Button btnRegister;

    FirebaseAuth firebaseAuth;

    String email;
    String password;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        txtUsername = view.findViewById(R.id.txtUsername);
        txtPassword = view.findViewById(R.id.txtPassword);
        btnRegister = view.findViewById(R.id.btnRegister);

        firebaseAuth = firebaseAuth.getInstance();

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Registering");

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                    }else if (password.length()<6){
                        txtPassword.setError("Password is too short. Minimum characters are 6");
                        txtPassword.requestFocus();
                    }else {
                        progressDialog.show();
                        firebaseAuth.createUserWithEmailAndPassword(txtUsername.getText().toString(), txtPassword.getText().toString())
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                        if(task.isSuccessful()){
                                            progressDialog.dismiss();
                                            FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                                            firebaseUser.sendEmailVerification()
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if(task.isSuccessful()){
                                                                String uid = firebaseAuth.getCurrentUser().getUid();
                                                                FirebaseFirestore db = FirebaseFirestore.getInstance();
                                                                DocumentReference uidRef = db.collection("users").document(uid);
                                                                User newUser = new User();
                                                                uidRef.set(newUser);
                                                                Toast.makeText(getActivity(), "Email verification sent", Toast.LENGTH_SHORT).show();
                                                                txtUsername.setText("");
                                                                txtPassword.setText("");
                                                            }else{
                                                                progressDialog.dismiss();
                                                                Toast.makeText(getActivity(), "Fail to send email verification", Toast.LENGTH_LONG).show();
                                                            }
                                                        }
                                                    });
                                        }else{
                                            progressDialog.dismiss();
                                            Toast.makeText(getActivity(), "Email already exist", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                    }

                }catch (Exception ex){
                    progressDialog.dismiss();
                    Toast.makeText(getActivity(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }
}
