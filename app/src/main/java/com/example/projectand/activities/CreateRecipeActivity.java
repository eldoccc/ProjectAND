package com.example.projectand.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projectand.POJO.Recipe;
import com.example.projectand.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.LocalDate;

@RequiresApi(api = Build.VERSION_CODES.O)
public class CreateRecipeActivity extends AppCompatActivity {
    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("recipes");
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private EditText edtTitle;
    private EditText edtDesc;
    private LocalDate ld = LocalDate.now();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_recipe);
        edtTitle =  findViewById(R.id.edtTitle);
        edtDesc = findViewById(R.id.edtDesc);
        Button btnImg = findViewById(R.id.btnPic);
    }

    public void saveData(View v) {
        Recipe r = new Recipe(edtTitle.getText().toString(),edtDesc.getText().toString(),R.drawable.placeholder_1200x500,ld.toString(),0, user.getDisplayName(), user.getUid());
        myRef.push().setValue(r);
        goToMainActivity();
        finish();
    } //Todo change image source and manage pictures storage on firebase

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("OpenFragment", "CommunityFragment");
        startActivity(intent);
    }

}
