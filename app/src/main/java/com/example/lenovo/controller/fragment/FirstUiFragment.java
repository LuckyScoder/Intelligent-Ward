package com.example.lenovo.controller.fragment;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.czp.library.ArcProgress;
import com.example.lenovo.controller.R;

/**
 * Created by Administrator on 2017/4/19.
 */

public class FirstUiFragment extends Fragment implements ArcProgress.OnCenterDraw{
    private ArcProgress progress1;
    private ArcProgress progress2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.first_ui,container,false);
        progress1 = (ArcProgress)view.findViewById(R.id.average_temp_progress);
        progress1.setOnCenterDraw(this);
        progress1.setProgress(20);

        progress2 = (ArcProgress)view.findViewById(R.id.average_hum_progress);
        progress2.setOnCenterDraw(this);
        progress2.setProgress(20);
        return view;
    }

        public void draw(Canvas canvas, RectF rectF, float x, float y, float storkeWidth, int progress) {
            Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            textPaint.setTextSize(30);
            textPaint.setStrokeWidth(35);
            textPaint.setColor(getResources().getColor(R.color.textColor));
            String progressStr = String.valueOf(progress+"%");
            float textX = x-(textPaint.measureText(progressStr)/2);
            float textY = y-((textPaint.descent()+textPaint.ascent())/2);
            canvas.drawText(progressStr,textX,textY,textPaint);
        }

}
