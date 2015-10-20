    package com.linhphan.simplerecyclerview.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.linhphan.simplerecyclerview.R;
import com.linhphan.simplerecyclerview.view.adapter.MyAdapter;

import java.util.ArrayList;

    public class MainActivity extends AppCompatActivity {
        private RecyclerView mRecyclerView;
        private ImageButton mImgPlus;
        private MyAdapter mAdapter;
        private RecyclerView.LayoutManager mLayoutManager;
        private ArrayList<String>mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mImgPlus = (ImageButton) findViewById(R.id.img_btn_plus);
        mImgPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAdapter != null)
                    mAdapter.add(mData.size(), String.valueOf(mData.size()));
            }
        });

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mData = new ArrayList<>();
        for (int i=0; i< 50; i++){
            mData.add("item "+ i+1);
        }
        mAdapter = new MyAdapter(mData);
        mRecyclerView.setAdapter(mAdapter);
    }

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
    }


    }
