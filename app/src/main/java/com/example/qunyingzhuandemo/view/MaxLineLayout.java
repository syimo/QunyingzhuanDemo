package com.example.qunyingzhuandemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 最多展示一行child view，超过一行或者一行放不下则不展示，
 */
public class MaxLineLayout extends ViewGroup {

    public static final String TAG = "MaxLineLayout";


    public MaxLineLayout(Context context) {
        super(context);
    }

    public MaxLineLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MaxLineLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MaxLineLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    private int max_child_size = 0;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int total_width = MeasureSpec.getSize(widthMeasureSpec);

        int total_child_width = 0;

        max_child_size = 0;

        Log.e(TAG, "onMeasure total_width===" + total_width);

        for (int i = 0; i < getChildCount(); i++) {

            View child = getChildAt(i);

            if (child.getVisibility() == View.GONE) {
                continue;
            }

            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams mLP = (MarginLayoutParams) child.getLayoutParams();

            //子view 宽高
            int childWidth = child.getMeasuredWidth() + mLP.leftMargin + mLP.rightMargin;

            Log.e(TAG, "onMeasurechildWidth===" +  child.getMeasuredWidth());

            total_child_width += childWidth;
            if (total_child_width <= total_width) {
                max_child_size++;
            }
        }
        Log.e(TAG, "onMeasure max_child_size===" + max_child_size);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int left = 0;

        int count = 0;

        for (int j = 0; j < getChildCount(); j++) {

            if (count > 0 && count >= max_child_size) break;

            View child = getChildAt(j);

            if (child.getVisibility() == View.GONE) continue;

            MarginLayoutParams mLP = (MarginLayoutParams) child.getLayoutParams();

            int lc = left + mLP.leftMargin;
            int tc = mLP.topMargin;
            int rc = lc + child.getMeasuredWidth();
            int bc = tc + child.getMeasuredHeight();

            child.layout(lc, tc, rc, bc);

            left += child.getMeasuredWidth() + mLP.leftMargin + mLP.rightMargin;

            count++;
        }


    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

}
