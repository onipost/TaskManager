package com.example.developer.taskmanagerv05;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

public class GroupListTasks extends Activity {

    private DataBase database;
    private SQLiteDatabase instance_db;
    private GridView mGridView;
    private GridAdapter gridAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list_tasks);
        initViews();
        Intent intent = getIntent();
        String group_id = intent.getStringExtra("id");
        Log.d("PARENT" ,""+group_id);
        database = new DataBase(this);
        instance_db = database.getWritableDatabase();

        //Cursor data = database.selectByColumnsWhere(instance_db, "TaskItems", new String[] { "_id", "title" }, "parent_id", new String[]{ group_id });
        //gridAdapter = new GridAdapter(this, data, 0, this);
        //mGridView.setAdapter(gridAdapter);
    }
    public void initViews(){
        mGridView = (GridView) findViewById(R.id.grid_view);
    }
}
