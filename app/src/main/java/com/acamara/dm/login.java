package com.acamara.dm;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ProgressBar progressbar;
    private EditText email;
    private EditText password;
    private Firebase firebase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(getApplicationContext());
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.login);
        /**
         * Access the firebase database using the direct link
         */

        //firebase = new Firebase("https://csci-3130-software-engineering.firebaseio.com/t1/user");
    }


    public void goToSignup(View view){
        Intent intent = new Intent(this, signup.class);
        startActivity(intent);
    }

    /**
     * method act as a button listenner
     * @param view
     */
    public void login(View view){
        email = findViewById(R.id.email_input);
        password = findViewById(R.id.password_input);

        String email_value=email.getText().toString();
        String password_value =password.getText().toString();

        Authenticate(email_value,password_value);

    }

    /**Method to authenticate user
     * Success case --> home.class
     * Fail case --> Ask to try again
     * @author  Adama
     * @param email
     * @param password
     */
    public void Authenticate(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            String userName = retrieveUserName(user.getUid());
                            Intent intent = new Intent(login.this, home_screen.class);
                            intent.putExtra("username",userName);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
        //return user;
    }

    /** Method called to retrieve user data when signed in
     * @author: Ben & Adama
     * @Iteration: 1
     * @return User object
     */
    public String retrieveUserName(final String uid){
        final String[] value = new String[1];
        firebase = new Firebase("https://csci-3130-software-engineering.firebaseio.com/t1/user/"+uid+"/username");
        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                value[0] = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Toast.makeText(login.this, "Failed to pull your data",
                        Toast.LENGTH_SHORT).show();
            }
        });
        return value[0];
    }

}
