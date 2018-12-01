package arvin.delacruz.com.delacruzarvinexam2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase db=FirebaseDatabase.getInstance();
    DatabaseReference root;
    EditText efirstName, elastName, eexam1, eexam2;
    TextView taverage;
    ArrayList<String> keyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseDatabase.getInstance();
        root = db.getReference("grade");
        efirstName = findViewById(R.id.firstName);
        elastName = findViewById(R.id.lastName);
        eexam1 = findViewById(R.id.exam1);
        eexam2 = findViewById(R.id.exam2);
        taverage = findViewById(R.id.average);
        keyList = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ss: dataSnapshot.getChildren()) {
                    keyList.add(ss.getKey());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void displayAve(View v){
        String fn = efirstName.getText().toString().trim();
        String ln = elastName.getText().toString().trim();
        Long e1 = Long.parseLong(eexam1.getText().toString().trim());
        Long e2 = Long.parseLong(eexam2.getText().toString().trim());
        Long ave = (e1 + e2)/2;

        StudentRecord average = new StudentRecord(fn, ln, ave);
        String key = root.push().getKey();
        root.child(key).setValue(average);
        keyList.add(key);

        taverage.setText(ave.toString());
        Toast.makeText(this,"Student Record added successfully...",Toast.LENGTH_LONG).show();

    }
}
