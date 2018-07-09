package com.playment.playmenttask;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.List;

public class MainClass extends Activity implements View.OnTouchListener {
    private RelativeLayout viewAdd;
    private SeekBar seekBar;
    private final int mDefaultWidth = 50;
    private final int halfWidth = mDefaultWidth/2;
    private StringBuilder stringBuilder;
    private Button btCoordinates;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        bindIds();
        stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        clickListeners();
    }

    private void clickListeners() {
        viewAdd.setOnTouchListener(this);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float scale = progress / 100f + 1;
                if(viewAdd.getChildCount()!=0) {
                    LinearLayout layout = (LinearLayout) viewAdd.getChildAt(viewAdd.getChildCount() - 1);
                    layout.setScaleX(scale);
                    layout.setScaleY(scale);
                }

            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        btCoordinates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                stringBuilder.append("]");
                Toast.makeText(MainClass.this,stringBuilder.toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void bindIds() {
        seekBar = findViewById(R.id.seekBar);
        viewAdd = findViewById(R.id.view_add);
        btCoordinates = findViewById(R.id.button);
    }

    int count = 0;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        LinearLayout layout = new LinearLayout(this);
        layout.setLayoutParams(new LinearLayout.LayoutParams(mDefaultWidth, mDefaultWidth));
        layout.setX(x);
        layout.setY(y);
        layout.setBackgroundColor(Color.parseColor("#3F51B5"));
        viewAdd.addView(layout);
        count++;
        String coordinates = "{coordinates: "+"[("+String.valueOf(x-halfWidth) +" , "+ String.valueOf(y+halfWidth)+")"+" "+"("+String.valueOf(x+halfWidth) +" , "+ String.valueOf(y+halfWidth)+")"+" "+"("+String.valueOf(x+halfWidth) +" , "+
                String.valueOf(y-halfWidth)+")"+" "+"("+String.valueOf(x-halfWidth) +" , "+ String.valueOf(y-halfWidth)+")]"+" "+"}";
        stringBuilder.append(coordinates+" ,");

        return false;
    }


}
