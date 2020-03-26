package desertstars.afcasiancup2019;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;



public class SquareView extends ViewGroup {

    public SquareView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int u, int r, int d) {
        getChildAt(0).layout(0, 0, r-l, d-u);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        View child = getChildAt(0);
        child.measure(widthMeasureSpec, widthMeasureSpec);
        int width = resolveSize(child.getMeasuredWidth(), widthMeasureSpec);
        child.measure(width, width);
        setMeasuredDimension(width, width);
    }
}