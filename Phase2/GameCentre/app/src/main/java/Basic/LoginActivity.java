package Basic;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import fall2018.csc2017.slidingtiles.R;

public class LoginActivity extends AppCompatActivity {

    /**
     * The userManager
     */
    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        setupLogInButtonListener();
        setupRegisterButtonListener();
        setupResetPasswordButtonListener();
    }

    /**
     * Activate the LogIn button.
     */
    @SuppressLint("SetTextI18n")
    private void setupLogInButtonListener() {
        Button loginButton = findViewById(R.id.login);
        EditText nameInput = findViewById(R.id.name);
        EditText passwordInput = findViewById(R.id.password);
        TextView messageBox = findViewById(R.id.success);
        this.userManager = FileManager.loadUserManager(this.getApplicationContext());

        loginButton.setOnClickListener((v) -> {
            String name = nameInput.getText().toString();
            String password = passwordInput.getText().toString();
            if (this.userManager.login(name, password) == null) {
                messageBox.setTextColor(Color.RED);
                String nameValidity = this.userManager.checkUserNameValidity(name);
                switch (nameValidity) {
                    case "E-mail exist":
                        messageBox.setText("Password Incorrect");
                        break;
                    case "OK!":
                        messageBox.setText("Username does not exist");
                        break;
                    default:
                        messageBox.setText(nameValidity);
                        break;
                }
            } else {
                DataManager.INSTANCE.setCurrentUserName(name);
                Intent tmp = new Intent(this, SelectGameActivity.class);
                startActivity(tmp);
            }
        });
    }

    /**
     * Activate the ResetPassword button.
     */
    private void setupResetPasswordButtonListener() {
        Button resetPasswordButton = findViewById(R.id.resetPassword);

        resetPasswordButton.setOnClickListener((v) -> {
            Intent tmp = new Intent(this, ResetPasswordActivity.class);
            startActivity(tmp);
        });
    }

    /**
     * Activate the Register button.
     */
    private void setupRegisterButtonListener() {
        Button registerButton = findViewById(R.id.register);

        registerButton.setOnClickListener((v) -> {
            Intent tmp = new Intent(this, RegistrationActivity.class);
            startActivity(tmp);
        });
    }

    @Override
    public void onBackPressed() {
        Intent temp = new Intent(Intent.ACTION_MAIN);
        temp.addCategory(Intent.CATEGORY_HOME);
        temp.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(temp);
    }

}
