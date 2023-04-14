package coatocl.exaatocl.roomdatabasefinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.WindowManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    RecyclerView recycler;
    Adapter adapter;
    LinearLayoutManager linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        floatingActionButton =findViewById(R.id.floatingActionButton);
        recycler =findViewById(R.id.recycler);
        linearLayout = new LinearLayoutManager(this);
        recycler.setLayoutManager(linearLayout);


        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(this,todoscreen.class);
            startActivity(intent);
        });
        
        getTask();

    }

    private void getTask()
    {
        class GetTasks extends AsyncTask<Void, Void, List<CustomModel>> {

            @Override
            protected List<CustomModel> doInBackground(Void... voids) {
                List<CustomModel> taskList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .dao()
                        .getAll();
                return taskList;
            }

            @Override
            protected void onPostExecute(List<CustomModel> tasks) {
                super.onPostExecute(tasks);
                adapter = new Adapter(MainActivity.this, tasks);
                recycler.setAdapter(adapter);
            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }
}
