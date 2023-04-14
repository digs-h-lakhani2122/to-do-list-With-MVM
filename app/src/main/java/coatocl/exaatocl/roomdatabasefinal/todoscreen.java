package coatocl.exaatocl.roomdatabasefinal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class todoscreen extends AppCompatActivity
{

    EditText titleEdit,descriptionEdit,dateEdit;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todoscreen);

        titleEdit = findViewById(R.id.titleEdit);
        descriptionEdit = findViewById(R.id.descriptionEdit);
        dateEdit = findViewById(R.id.dateEdit);

        button =findViewById(R.id.button);

        button.setOnClickListener(v -> {
          saveTask();  
        });

    }

    private void saveTask()
    {
        String sTitle = titleEdit.getText().toString().trim();
        String sDescription= descriptionEdit.getText().toString().trim();
        String sDate = dateEdit.getText().toString().trim();

        if (sTitle.isEmpty()) {
            titleEdit.setError("Task required");
            titleEdit.requestFocus();
            return;
        }

        if (sDescription.isEmpty()) {
            descriptionEdit.setError("Desc required");
            descriptionEdit.requestFocus();
            return;
        }

        if (sDate.isEmpty()) {
            dateEdit.setError("Finish by required");
            dateEdit.requestFocus();
            return;
        }

        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task
                CustomModel customModel = new CustomModel();
                customModel.setTask_title(sTitle);
                customModel.setTask_description(sDescription);
                customModel.setTask_date(sDate);
//                customModel.setFinished(false);

                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .dao()
                        .insert(customModel);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveTask st = new SaveTask();
        st.execute();

    }
}
