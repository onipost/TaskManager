package com.example.developer.taskmanagerv05;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteTransactionListener;
import android.util.Log;
import android.widget.Toast;

public class DataBase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "TaskDB.db";
    private static final int DATABASE_VERSION = 5;

    SQLiteTransactionListener TransactionListener = new SQLiteTransactionListener() {
        @Override
        public void onBegin() {
            Log.d("TRANSACTION", "Transaction start", new Throwable().getCause());
        }
        @Override
        public void onCommit() {
            Log.d("TRANSACTION", "Transaction before commit", new Throwable().getCause());
        }
        @Override
        public void onRollback() {
            Log.d("TRANSACTION", "Transaction rollBack", new Throwable().getCause());
        }
    };
    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransactionWithListener(TransactionListener);
        try {
            db.execSQL("create table Users ("
                    + "_id integer primary key autoincrement,"
                    + "name text"
                    + ");");
            db.execSQL("create table TaskGroups ("
                    + "_id integer primary key autoincrement,"
                    + "title text"
                    + ");");
            db.execSQL("insert into TaskGroups (title) values ('Все');");
            db.execSQL("insert into TaskGroups (title) values ('Рабочие проекты');");
            db.execSQL("insert into TaskGroups (title) values ('Домашние дела');");
            db.execSQL("insert into TaskGroups (title) values ('Личные поручения');");
            db.execSQL("create table TaskItems ("
                    + "_id integer primary key autoincrement,"
                    + "title text,"
                    + "parent_id int"
                    + ");");
            db.setTransactionSuccessful();
        }catch (Exception exception){
            Log.d("EXCEPTION", exception.getClass() + " error: " + exception.getMessage());
        }finally {
            db.endTransaction();
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.beginTransactionWithListener(TransactionListener);
        try {
            db.execSQL("drop table Users");
            db.execSQL("create table Users ("
                    + "_id integer primary key autoincrement,"
                    + "name text"
                    + ");");
            db.execSQL("drop table TaskGroups");
            db.execSQL("create table TaskGroups ("
                    + "_id integer primary key autoincrement,"
                    + "title text"
                    + ");");
            db.execSQL("insert into TaskGroups (title) values ('Все');");
            db.execSQL("insert into TaskGroups (title) values ('Рабочие проекты');");
            db.execSQL("insert into TaskGroups (title) values ('Домашние дела');");
            db.execSQL("insert into TaskGroups (title) values ('Личные поручения');");
            db.execSQL("drop table TaskItems");
            db.execSQL("create table TaskItems ("
                    + "_id integer primary key autoincrement,"
                    + "title text,"
                    + "parent_id int"
                    + ");");
            db.setTransactionSuccessful();
        }catch (Exception exception){
            Log.d("EXCEPTION", exception.getClass()+", message: "+exception.getMessage());
        }finally {
            db.endTransaction();
        }
    }
    public void insert(SQLiteDatabase db, String tableName, ContentValues cv){
        db.beginTransactionWithListener(TransactionListener);
        try {
            long inserted_id = db.insert(tableName, null, cv);
            if(inserted_id == -1){
                Log.d("DB_ERROR", "insert data error: "+Toast.LENGTH_SHORT);
            }
            db.setTransactionSuccessful();
        } catch (Exception exception){
            Log.d("EXCEPTION", exception.getClass() + " error: " + exception.getMessage());
        } finally {
            db.endTransaction();
        }
    }
    public void update(SQLiteDatabase db, String tableName, ContentValues cv, String whereString, String[] whereArgs){
        db.beginTransactionWithListener(TransactionListener);
        try {
            db.update(tableName, cv, whereString, whereArgs);
            db.setTransactionSuccessful();
        }catch (Exception exception){
            Log.d("EXCEPTION", exception.getClass() + " error: " + exception.getMessage());
        }
        finally {
            db.endTransaction();
        }
    }
    public void delete(SQLiteDatabase db, String tableName, String whereString, String[] whereArgs){
        db.beginTransactionWithListener(TransactionListener);
        try {
            db.delete(tableName, whereString, whereArgs);
            db.setTransactionSuccessful();
        }catch (Exception exception){
            Log.d("EXCEPTION", exception.getClass() + " error: " + exception.getMessage());
        }
        finally {
            db.endTransaction();
        }
    }
    public Cursor selectBase(SQLiteDatabase db, String tableName, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy, String limit){
        db.beginTransactionWithListener(TransactionListener);
        Cursor cursor = db.query(tableName, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
        db.setTransactionSuccessful();
        db.endTransaction();
        return cursor;
    }
    public Cursor selectAllByColumns (SQLiteDatabase db, String tableName, String[] columns){
        db.beginTransactionWithListener(TransactionListener);
        Cursor cursor = db.query(tableName, columns, null, null, null, null, null, null);
        db.setTransactionSuccessful();
        db.endTransaction();
        return cursor;
    }
    public Cursor selectByColumnsWhere(SQLiteDatabase db, String tableName, String[] columns, String selection, String[] selectionArgs){
        db.beginTransactionWithListener(TransactionListener);
        Cursor cursor = db.query(tableName, columns, selection, selectionArgs, null, null, null, null);
        db.setTransactionSuccessful();
        db.endTransaction();
        return cursor;
    }
    public Cursor selectAll(SQLiteDatabase db, String tableName){
        db.beginTransactionWithListener(TransactionListener);
        Cursor cursor = db.query(tableName, null, null, null, null, null, null, null);
        db.setTransactionSuccessful();
        db.endTransaction();
        return cursor;
    }
    public void closeDB(SQLiteDatabase db){
        db.close();
    }
}
