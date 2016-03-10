package com.example.echozhou.recyclecardviewtest;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PullToRefreshRecycleView mRecyclerView;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecycleView();
        initPullToRefresh();
    }

    private void initPullToRefresh() {
        mRecyclerView.setScrollingWhileRefreshingEnabled(true);
        mRecyclerView.setMode(PullToRefreshBase.Mode.BOTH);
        mRecyclerView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                Toast.makeText(MainActivity.this, "下拉刷新", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        mRecyclerView.onRefreshComplete();
                    }
                }, 4000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                Toast.makeText(MainActivity.this, "上拉加载", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        mRecyclerView.onRefreshComplete();
                    }
                }, 4000);
            }
        });
    }

    private void initRecycleView() {
        List list = new ArrayList();
        for (int i=0;i<10;i++){
            list.add(i);
        }
        mRecyclerView = (PullToRefreshRecycleView) findViewById(R.id.recycle_view);
        recyclerView = mRecyclerView.getRefreshableView();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(list,this));
    }
}
