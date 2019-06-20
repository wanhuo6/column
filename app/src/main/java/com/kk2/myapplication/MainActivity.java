package com.kk2.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private List<Integer> heartList;
    private int heart;

    private Handler mHandler;
    private int recyclerViewHeight;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

    }

    private void initView() {
        recyclerView = findViewById(R.id.recyclerView);

    }

    boolean isFirst=true;

    private void initData() {
        random=new Random();
        recyclerAdapter = new RecyclerAdapter();
        heartList = new ArrayList<>();
        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        if (isFirst){
                            isFirst=false;
                            recyclerViewHeight = recyclerView.getHeight();
                            recyclerAdapter.setData(heartList,recyclerViewHeight);
                        }
                        Log.e("---", recyclerViewHeight + "----");
                    }
                });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                if (bottom < oldBottom) {
                    recyclerView.post(new Runnable() {
                        @Override
                        public void run() {
                            if (recyclerAdapter.getItemCount() > 0) {
                                recyclerView.smoothScrollToPosition(recyclerAdapter.getItemCount() - 1);
                            }
                        }
                    });
                }
            }
        });
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                heartList.add(random.nextInt(200));
                recyclerAdapter.notifyDataSetChanged();
                recyclerView.scrollToPosition(heartList.size() - 1);
                mHandler.sendEmptyMessageDelayed(1, 1000);
            }
        };
        mHandler.sendEmptyMessageDelayed(1, 1000);
    }

}
