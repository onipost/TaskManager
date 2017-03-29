package com.example.developer.taskmanagerv05;

import android.animation.Animator;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

public class TaskGroupsActivity extends Activity {
    private DataBase database;
    private SQLiteDatabase instance_db;
    private GridView mGridView;
    private GridAdapter gridAdapter;
    private Button mSaveEditItem;
    private Button mConfirmDeleteItem;
    private Button mAddButton;
    private RelativeLayoutCustom mAddButtonTrigger;
    private Listeners listeners;
    private AnimateBuilder animateBuilder;

    public View editModal;
    public View deleteModal;
    public View addModal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_groups);
        initViews();
        database = new DataBase(this);
        instance_db = database.getWritableDatabase();
        listeners = new Listeners();
        animateBuilder = new AnimateBuilder(this);
        setName(database, instance_db);

        Cursor data = database.selectAll(instance_db, "TaskGroups");
        gridAdapter = new GridAdapter(this, data, 0, this);
        mGridView.setAdapter(gridAdapter);
        bindListeners();

        animateBuilder.objectAnimator(mAddButtonTrigger, R.animator.add_button);
    }
    public void bindListeners(){
        mGridView.setOnItemClickListener(itemClickListener);
        mGridView.setOnItemLongClickListener(itemLongClickListener);
        mSaveEditItem.setOnClickListener(saveEditItemListener);
        mConfirmDeleteItem.setOnClickListener(confirmDeleteItemListener);
        mAddButton.setOnClickListener(addItemListener);
        mAddButtonTrigger.setOnClickListener(openAddModal);
    }
    public void initViews(){
        mGridView = (GridView) findViewById(R.id.grid_view);
        mSaveEditItem = (Button) findViewById(R.id.edit_save);
        mConfirmDeleteItem = (Button) findViewById(R.id.delete_save);
        mAddButton = (Button) findViewById(R.id.add_save);
        editModal = findViewById(R.id.edit_modal);
        deleteModal = findViewById(R.id.delete_modal);
        addModal = findViewById(R.id.add_modal);
        mAddButtonTrigger = (RelativeLayoutCustom) findViewById(R.id.add_button);
    }

    /* SETTERS */

    public void setName(DataBase database, SQLiteDatabase instance_db){
        Cursor usernameCursor = database.selectAll(instance_db, "Users");
        usernameCursor.moveToFirst();
        String mUserName = usernameCursor.getString(usernameCursor.getColumnIndex("name"));
        TextView helloText = (TextView) findViewById(R.id.hello_text);
        helloText.setText(helloText.getText().toString()+" "+mUserName);
    }
    public void setDataToEditModal(int id, String title){
        EditText editTextModal = (EditText) findViewById(R.id.edit_text);
        TextView editItemId = (TextView) findViewById(R.id.edit_item_id);
        String id_str = id+"";
        editItemId.setText(id_str);
        editTextModal.setText(title);
    }
    public void setDataToDeleteModal(int id, String title){
        TextView textModal = (TextView) findViewById(R.id.delete_text);
        TextView itemId = (TextView) findViewById(R.id.delete_item_id);
        String id_str = id+"";
        itemId.setText(id_str);
        textModal.setText(textModal.getText().toString()+ " "+title+"?");
    }

    /* ANIMATE */

    public void animateModal(View item, View modal, boolean reverse){
        View itemControls = item.findViewById(R.id.item_controls);
        View overlay = findViewById(R.id.overlay);
        Log.d("AM", "true");
        if(!reverse) {
            animateBuilder.objectAnimator(mAddButtonTrigger, R.animator.add_button_r);
            animateBuilder.animatorUtils(mGridView, R.anim.scale);
            overlay.setVisibility(View.VISIBLE);
            animateBuilder.objectAnimator(modal, R.animator.edit_modal);
            animateBuilder.objectAnimator(itemControls, R.animator.item_controls_container_reverse);
            animateBuilder.objectAnimator(overlay, R.animator.overlay);
        }else{
            Animator.AnimatorListener listener = listeners.reverseModalOverlayListener(overlay);
            animateBuilder.animatorUtils(mGridView, R.anim.scale_r);
            animateBuilder.objectAnimator(modal, R.animator.edit_modal_r);
            animateBuilder.objectAnimator(overlay, R.animator.overlay_r, listener);
            animateBuilder.objectAnimator(mAddButtonTrigger, R.animator.add_button_offset);
        }
    }
    public void animateModal(View modal, boolean reverse){
        View overlay = findViewById(R.id.overlay);
        if(!reverse) {
            animateBuilder.objectAnimator(mAddButtonTrigger, R.animator.add_button_r);
            animateBuilder.animatorUtils(mGridView, R.anim.scale);
            overlay.setVisibility(View.VISIBLE);
            animateBuilder.objectAnimator(modal, R.animator.edit_modal);
            animateBuilder.objectAnimator(overlay, R.animator.overlay);
        }else{
            Animator.AnimatorListener listener = listeners.reverseModalOverlayListener(overlay);
            animateBuilder.animatorUtils(mGridView, R.anim.scale_r);
            animateBuilder.objectAnimator(modal, R.animator.edit_modal_r);
            animateBuilder.objectAnimator(overlay, R.animator.overlay_r, listener);
            animateBuilder.objectAnimator(mAddButtonTrigger, R.animator.add_button_offset);
        }
    }
    public void refresh_cursor(SQLiteDatabase instance_db,String tableName){
        Cursor newData = database.selectAll(instance_db, tableName);
        gridAdapter.changeCursor(newData);
    }
    public void refresh_modal(int modalID){
        View view = this.findViewById(modalID);
        switch (modalID){
            case R.id.add_modal:
                EditText text_add = (EditText) view.findViewById(R.id.add_text);
                text_add.setText("");
                break;
            case R.id.delete_modal:
                TextView id_delete = (TextView) view.findViewById(R.id.delete_item_id);
                TextView text_delete = (TextView) view.findViewById(R.id.delete_text);
                text_delete.setText("");
                id_delete.setText("");
                break;
            case R.id.edit_modal:
                TextView id_edit = (TextView) view.findViewById(R.id.edit_item_id);
                EditText text_edit = (EditText) view.findViewById(R.id.edit_text);
                text_edit.setText("");
                id_edit.setText("");
                break;
        }
    }
    /* LISTENERS */


    View.OnClickListener saveEditItemListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            } catch (Exception e) {}
            TextView id_view = (TextView) findViewById(R.id.edit_item_id);
            EditText name_view = (EditText) findViewById(R.id.edit_text);
            String id = id_view.getText().toString();
            String title = name_view.getText().toString();
            ContentValues cv = new ContentValues();
            cv.put("title", title);
            database.update(instance_db, "TaskGroups", cv,  "_id = ?", new String[] { id });
            refresh_cursor(instance_db, "TaskGroups");
            animateModal(v, editModal, true);
        }
    };
    View.OnClickListener confirmDeleteItemListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            TextView id_view = (TextView) findViewById(R.id.delete_item_id);
            String id = id_view.getText().toString();
            database.delete(instance_db, "TaskGroups", "_id = ?", new String[] { id });
            refresh_cursor(instance_db, "TaskGroups");
            animateModal(v, deleteModal, true);

        }
    };
    View.OnClickListener addItemListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            } catch (Exception e) {}
            EditText item_name_view = (EditText) findViewById(R.id.add_text);
            String itemName = item_name_view.getText().toString();
            ContentValues cv = new ContentValues();
            cv.put("title", itemName);
            database.insert(instance_db, "TaskGroups", cv);
            refresh_cursor(instance_db, "TaskGroups");
            animateModal(v, addModal, true);
            item_name_view.setText("");
        }
    };
    View.OnClickListener openAddModal = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            View modal = findViewById(R.id.add_modal);
            animateModal(modal, false);
        }
    };
    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(getApplicationContext(), GroupListTasks.class);
            intent.putExtra("id", ""+id);
            startActivity(intent);
        }
    };
    AdapterView.OnItemLongClickListener itemLongClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            View itemControls =  mGridView.getChildAt(position);
            if(itemControls!= null) {
                View ctrl = itemControls.findViewById(R.id.item_controls);
                animateBuilder.objectAnimator(ctrl, R.animator.item_controls_container);
            }else{
                Log.d("LongClick", "LongClick called on null reference");
            }
            return true;
        }
    };
}

//TODO: FIX OVERLAY. Не перекрывает элементы в гриде
//TODO: Чистить модальное окно/Протестировать
//TODO: запретить добавление пустых элементов
//TODO: Растянуть градиент