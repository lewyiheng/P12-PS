package sg.edu.rp.c346.task_manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvTask;
    Button btnAdd;
    ArrayList<Task> tasks;
    ArrayAdapter aa;
    int notificationId = 001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvTask = findViewById(R.id.listView);
        btnAdd = findViewById(R.id.buttonAdd);

        updateTable();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivityForResult(intent, 9);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            updateTable();
        }
    }

    private void updateTable(){
        DBHelper db = new DBHelper(MainActivity.this);
        tasks = db.getAllTasks();
        aa = new ArrayAdapter<Task>(this, android.R.layout.simple_list_item_1 ,tasks);
        lvTask.setAdapter(aa);
        aa.notifyDataSetChanged();
        db.close();
    }

}
