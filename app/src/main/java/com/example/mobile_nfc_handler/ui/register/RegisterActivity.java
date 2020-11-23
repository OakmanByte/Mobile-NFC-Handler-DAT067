package com.example.mobile_nfc_handler.ui.register;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobile_nfc_handler.R;
import com.example.mobile_nfc_handler.ui.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 *  Activity Class for registering a new User.
 */
public class RegisterActivity extends AppCompatActivity {

    private Button returnButton;
    private Button registerButton;

    private TextView usernameRegister;
    private TextView passwordRegister;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_register);

        this.setUpComponents();
        this.setUpListeners();
    }

    public void setUpComponents() {
        this.returnButton = findViewById(R.id.returnButtonRegister);
        this.registerButton = findViewById(R.id.RegisterButton);

        this.usernameRegister = findViewById(R.id.usernameRegister);
        this.passwordRegister = findViewById(R.id.passwordRegister);
    }

    public void setUpListeners() {
        // Button listener
        this.returnButton.setOnClickListener(e -> {
            //Return to main activity
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });

        this.registerButton.setOnClickListener(e -> {
            // Try to register a user
            mAuth.createUserWithEmailAndPassword(usernameRegister.getText().toString(), passwordRegister.getText().toString()).addOnCompleteListener(task -> {
                // If successful set user to the created user
                if(task.isSuccessful()){
                    System.out.println("Successful user creation");
                    FirebaseUser user = mAuth.getCurrentUser();
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