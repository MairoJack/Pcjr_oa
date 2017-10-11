package com.pcjr.pcjr_oa.ui.decorator;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * Created by mario on 2017/8/23.
 */

public class PointEvaluator implements TypeEvaluator {

    @Override
    public Object evaluate(float v, Object o, Object t1) {
        PointF startPoint = (PointF) o;
        PointF endPoint = (PointF) t1;
        int x = (int) (startPoint.x + v * (endPoint.x - startPoint.x));
        int y = (int) (startPoint.y + v * (endPoint.y - startPoint.y));

        return new PointF(x, y);
    }
}
