package com.example.mobile_nfc_handler.ui.register;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobile_nfc_handler.R;
import com.example.mobile_nfc_handler.data.User;
import com.example.mobile_nfc_handler.database.DatabaseInformation;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

/**
 *  Activity Class for registering a new User.
 */
public class RegisterActivity extends AppCompatActivity {

    private Button returnButton;
    private Button registerButton;

    private TextView usernameRegister;
    private TextView passwordRegister;

    private FirebaseAuth mAuth;
    private FirebaseDatabase db;

    private String emailInput;
    private String passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = DatabaseInformation.getDb();
        mAuth = DatabaseInformation.getmAuth();
        setContentView(R.layout.activity_register);

        this.setUpComponents();
        this.setUpListeners();
    }

    public void setUpComponents() {
        this.returnButton = findViewById(R.id.returnButtonRegister);
        this.registerButton = findViewById(R.id.RegisterButton);

        this.usernameRegister = findViewById(R.id.usernameRegister);
        this.passwordRegister = findViewById(R.id.passwordRegister);

        this.emailInput = this.usernameRegister.getText().toString();
        this.passwordInput = this.passwordRegister.getText().toString();

    }

    public void setUpListeners() {
        // Button listener
        this.returnButton.setOnClickListener(e -> {
            //Return to main activity
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });

        this.registerButton.setOnClickListener(e -> {

            //Create new user
            mAuth.createUserWithEmailAndPassword(this.emailInput, this.passwordInput).addOnCompleteListener(task -> {
                // If successful set user to the created user
                if(task.isSuccessful()){
                    System.out.println("Successful user creation");
                    FirebaseUser user = mAuth.getCurrentUser();
                    // Create User for database
                    User newUser = new User(mAuth.getUid(),this.emailInput, false);
                    // Put the user class on the new users UID
                    db.getReference().child("users").child(user.getUid()).setValue(newUser);

                    user.sendEmailVerification();
                }
                else{
                    System.out.println("Failed to create user");
                    Toast.makeText(RegisterActivity.this, "Failed to create user",Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}