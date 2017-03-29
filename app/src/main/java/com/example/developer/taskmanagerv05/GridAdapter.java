package com.example.developer.taskmanagerv05;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GridAdapter extends CursorAdapter {
    public Activity activity_base;
    public GridAdapter(Context context, Cursor c, int flags, Activity activity) {
        super(context, c, flags);
        activity_base = activity;
    }
    String GridItemName;
    int GridItemId;
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.grid_view_item, parent, false);
    }
    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        RelativeLayout item = (RelativeLayout) view.findViewById(R.id.item);
        final EditText itemText = (EditText) view.findViewById(R.id.item_text);
        final TextView itemId = (TextView) view.findViewById(R.id.item_id);
        String title = cursor.getString(cursor.getColumnIndex("title"));
        final String _id = cursor.getString(cursor.getColumnIndex("_id"));
        itemText.setText(title);
        itemId.setText(_id);
        int width = Resources.getSystem().getDisplayMetrics().widthPixels / 2;
        item.setLayoutParams(new RelativeLayout.LayoutParams(width, width));

        LinearLayout itemControls = (LinearLayout) item.findViewById(R.id.item_controls);
        ImageView itemDelete = (ImageView) itemControls.findViewById(R.id.item_delete);
        ImageView itemEdit = (ImageView) itemControls.findViewById(R.id.item_edit);
        itemDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GridItemId = Integer.parseInt(itemId.getText().toString());
                GridItemName = itemText.getText().toString();
                View parent = (View) view.getParent();
                View modal = activity_base.findViewById(R.id.delete_modal);
                ((TaskGroupsActivity) activity_base).setDataToDeleteModal(GridItemId, GridItemName);
                ((TaskGroupsActivity) activity_base).animateModal(parent, modal, false);
            }
        });
        itemEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GridItemId = Integer.parseInt(itemId.getText().toString());
                GridItemName = itemText.getText().toString();
                View parent = (View) view.getParent();
                View modal = activity_base.findViewById(R.id.edit_modal);
                ((TaskGroupsActivity) activity_base).setDataToEditModal(GridItemId, GridItemName);
                ((TaskGroupsActivity) activity_base).animateModal(parent, modal, false);
            }
        });
    }
}
