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

@SuppressLint("Registered")
public class ResetPasswordActivity extends AppCompatActivity {

    /**
     * The userManager
     */
    private UserManager userManager;
    private String checked_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);
        this.userManager = FileManager.loadUserManager(this.getApplicationContext());
        addCheckButtonListener();
        addResetPasswordButtonListener();
    }


    /**
     * Activate the Check button.
     */
    @SuppressLint("SetTextI18n")
    private void addCheckButtonListener() {
        Button CheckButton = findViewById(R.id.checkaccount);
        EditText account = findViewById(R.id.old_account);
        EditText oldPassword = findViewById(R.id.oldpassword);
        TextView message = findViewById(R.id.textView);

        CheckButton.setOnClickListener((v) -> {
            String name = account.getText().toString();
            String password = oldPassword.getText().toString();
            if (this.userManager.login(name, password) == null) {
                message.setTextColor(Color.RED);
                if (this.userManager.checkUserNameValidity(name).equals("E-mail exist")) {
                    message.setText("Password Incorrect");
                } else {
                    message.setText("Username does not exist(valid)");
                }
            } else {
                message.setTextColor(Color.GREEN);
                message.setText("OK!");
            }
//            if (!account.getText().toString().contains("@")) {
//                message.setTextColor(Color.RED);
//                message.setText("Not a valid email");
//            } else if (this.userManager.login(account.getText().toString(),
//                    oldPassword.getText().toString()) != null) {
//                checked_account = account.getText().toString();
//                message.setTextColor(Color.GREEN);
//                message.setText("OK!");
//            } else {
//                message.setTextColor(Color.RED);
//                message.setText("try again");
//            }
        });
    }


    /**
     * Activate the ResetPassword button.
     */
    @SuppressLint("SetTextI18n")
    private void addResetPasswordButtonListener() {
        Button ResetButton = findViewById(R.id.resetbutton);
        EditText Password = findViewById(R.id.resetpassword);
        @SuppressLint("CutPasteId") TextView ResetResult = findViewById(R.id.textView);
        EditText Account = findViewById(R.id.old_account);
        @SuppressLint("CutPasteId") TextView message = findViewById(R.id.textView);

        ResetButton.setOnClickListener((v) -> {
            //If password is too short, ask the user to change a longer password.
            if (Password.getText().toString().length() < 5) {
                ResetResult.setTextColor(Color.RED);
                ResetResult.setText("Password too short");
                //if user input his name and password correctly, then he can reset a new password now.
            } else if (message.getText().toString().equals("OK!") &&
                    checked_account.equals(Account.getText().toString())) {
                this.userManager.getUser(Account.getText().toString()).setPassword(Password.getText().toString());
                message.setTextColor(Color.GREEN);
                message.setText("Reset Success");
                FileManager.saveToFile(this.getApplicationContext(), this.userManager, "UM");
                Intent tmp = new Intent(this, LoginActivity.class);
                startActivity(tmp);
            } else {
                ResetResult.setTextColor(Color.RED);
                ResetResult.setText("Please check your username and password again");
            }
        });
    }
}
