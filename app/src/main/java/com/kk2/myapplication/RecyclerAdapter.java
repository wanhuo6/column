package com.kk2.myapplication;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHold> {

    private List<Integer> heartList;
    private int recyclerViewHeight;
    private int maxHeart=200;

    public void setData(List<Integer> list,int height) {
        this.recyclerViewHeight=height;
        Log.e("---===",recyclerViewHeight+"----");
        this.heartList = list;
    }

    @NonNull
    @Override
    public ViewHold onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHold(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHold viewHold, int i) {

        int heart = heartList.get(i);
        LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) viewHold.column.getLayoutParams();
        if (heart<50){
            viewHold.column.setBackgroundColor(Color.GREEN);
        }else if (heart<100){
            viewHold.column.setBackgroundColor(Color.YELLOW);
        }else if (heart<150){
            viewHold.column.setBackgroundColor(Color.BLACK);
        }else if (heart<200){
            viewHold.column.setBackgroundColor(Color.RED);
        }else{
            viewHold.column.setBackgroundColor(Color.BLUE);
        }
        layoutParams.height= (int) (Double.valueOf(heart)/maxHeart*recyclerViewHeight);
        viewHold.column.setLayoutParams(layoutParams);

    }

    @Override
    public int getItemCount() {
        if (heartList == null || heartList.isEmpty()) {
            return 0;
        }
        return heartList.size();
    }

    public class ViewHold extends RecyclerView.ViewHolder {
        public View parent;
        public View column;

        public ViewHold(@NonNull View itemView) {
            super(itemView);
            column = itemView.findViewById(R.id.column);

        }
    }
}
