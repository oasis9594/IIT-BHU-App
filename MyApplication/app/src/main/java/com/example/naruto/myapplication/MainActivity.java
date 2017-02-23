package com.example.naruto.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.naruto.myapplication.Adapter.CustomRecyclerAdapter;
import com.example.naruto.myapplication.Keys.Query_data;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView msg_list;
    CustomRecyclerAdapter list_adap;
    boolean f_run2=true;
    ArrayList<Query_data> cartItems=new ArrayList<>();
    ImageButton submit;
    EditText input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        submit=(ImageButton)findViewById(R.id.submit);
        input=(EditText) findViewById(R.id.input_text);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Query_data query_data=new Query_data();
                query_data.setQuestion(input.getText().toString());
                input.setText("");
                query_data.setAnswer("PDFs from Ravi in the last 3 days\n" +
                        "From Ravi\n" +
                        "To Any\n" +
                        "ToDate Today\n" +
                        "FromDate Today-3\n" +
                        "HasAttachments YES\n" +
                        "AttachmentType PDF\n" +
                        "AttachmentSize Any\n" +
                        "AttachmentName Any\n" +
                        "Subject Any\n" +
                        "CC Any");
                cartItems.add(query_data);
                list_adap.notifyDataSetChanged();
                //msg_list.scrollToPosition(cartItems.size()-1);
            }
        });
        msg_list = (RecyclerView)findViewById(R.id.msg_list);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        msg_list.setLayoutManager(linearLayoutManager);
        list_adap = new CustomRecyclerAdapter(this,0);
        msg_list.setAdapter(list_adap);
        list_adap.notifyDataSetChanged();
        list_adap.setForcastList(cartItems);

    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

}
