package fall2018.csc2017.slidingtiles;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

@SuppressLint("Registered")
public class ResetPasswordActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpassword);

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
            if (!account.getText().toString().contains("@")) {
                message.setTextColor(Color.RED);
                message.setText("Not a valid email");
            } else if (Main.INSTANCE.getUserManager().login(account.getText().toString(),
                    oldPassword.getText().toString()) != null) {
                message.setTextColor(Color.GREEN);
                message.setText("OK!");
            } else {
                message.setTextColor(Color.RED);
                message.setText("try again");
            }
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
            } else if (message.getText().toString().equals("OK!")) {
                Main.INSTANCE.getUserManager().getUser(Account.getText().toString()).setPassword(Password.getText().toString());
                message.setTextColor(Color.GREEN);
                message.setText("Reset Success");
                Main.INSTANCE.saveUserManagerToFile(this.getApplicationContext());
                Intent tmp = new Intent(this, LoginActivity.class);
                startActivity(tmp);
            }
        });
    }
}
