package com.qmdeve.blurview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.qmdeve.blurview.base.BaseBlurViewGroup;
import com.qmdeve.blurview.util.Utils;

public class BlurViewGroup extends ViewGroup {

    private final BaseBlurViewGroup mBaseBlurViewGroup;

    public BlurViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        mBaseBlurViewGroup = new BaseBlurViewGroup(context, attrs);
    }

    @Override
    public boolean isInEditMode() {
        return super.isInEditMode();
    }

    public void setBlurRadius(float radius) {
        mBaseBlurViewGroup.setBlurRadius(radius);
    }

    public void setDownsampleFactor(float factor) {
        mBaseBlurViewGroup.setDownsampleFactor(factor);
    }

    public void setOverlayColor(int color) {
        mBaseBlurViewGroup.setOverlayColor(color);
    }

    public void setCornerRadius(float radius) {
        mBaseBlurViewGroup.setCornerRadius(radius);
    }

    public Bitmap getBlurredBitmap() {
        return mBaseBlurViewGroup.getBlurredBitmap();
    }

    public int getOverlayColor() {
        return mBaseBlurViewGroup.getOverlayColor();
    }

    public void release() {
        mBaseBlurViewGroup.release();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mBaseBlurViewGroup.onAttachedToWindow(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        mBaseBlurViewGroup.onDetachedFromWindow();
        super.onDetachedFromWindow();
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        if (!mBaseBlurViewGroup.isRendering()) super.draw(canvas);
    }

    @Override
    protected void dispatchDraw(@NonNull Canvas canvas) {
        boolean shouldDrawBlur = !Utils.sIsGlobalCapturing || mBaseBlurViewGroup.isRendering();

        if (!isInEditMode() && shouldDrawBlur) {
            mBaseBlurViewGroup.drawBlurredBitmap(canvas, getWidth(), getHeight());
        } else if (isInEditMode()) {
            mBaseBlurViewGroup.drawPreviewBackground(canvas, getWidth(), getHeight());
        }

        if (mBaseBlurViewGroup.getCornerRadius() > 0) {
            canvas.save();
            mBaseBlurViewGroup.clipCanvasWithRoundedCorner(canvas, getWidth(), getHeight());
            super.dispatchDraw(canvas);
            canvas.restore();
        } else {
            super.dispatchDraw(canvas);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = getChildCount();

        int maxChildWidth = 0;
        int maxChildHeight = 0;
        int childState = 0;

        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                final LayoutParams lp = (LayoutParams) child.getLayoutParams();

                measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);

                maxChildWidth = Math.max(maxChildWidth, child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin);
                maxChildHeight = Math.max(maxChildHeight, child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin);
                childState = combineMeasuredStates(childState, child.getMeasuredState());
            }
        }

        maxChildWidth += getPaddingLeft() + getPaddingRight();
        maxChildHeight += getPaddingTop() + getPaddingBottom();
        maxChildWidth = Math.max(maxChildWidth, getSuggestedMinimumWidth());
        maxChildHeight = Math.max(maxChildHeight, getSuggestedMinimumHeight());

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int measuredWidth;
        int measuredHeight;

        if (widthMode == MeasureSpec.EXACTLY) {
            measuredWidth = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            measuredWidth = Math.min(maxChildWidth, widthSize);
        } else {
            measuredWidth = maxChildWidth;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            measuredHeight = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            measuredHeight = Math.min(maxChildHeight, heightSize);
        } else {
            measuredHeight = maxChildHeight;
        }

        setMeasuredDimension(measuredWidth, measuredHeight);

        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                final LayoutParams lp = (LayoutParams) child.getLayoutParams();

                int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec, getPaddingLeft() + getPaddingRight() + lp.leftMargin + lp.rightMargin, lp.width);
                int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec, getPaddingTop() + getPaddingBottom() + lp.topMargin + lp.bottomMargin, lp.height);

                if (lp.width == LayoutParams.MATCH_PARENT) {
                    int availableWidth = measuredWidth - getPaddingLeft() - getPaddingRight() - lp.leftMargin - lp.rightMargin;
                    if (availableWidth > 0) {
                        childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(availableWidth, MeasureSpec.EXACTLY);
                    }
                }

                if (lp.height == LayoutParams.MATCH_PARENT) {
                    int availableHeight = measuredHeight - getPaddingTop() - getPaddingBottom() - lp.topMargin - lp.bottomMargin;
                    if (availableHeight > 0) {
                        childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(availableHeight, MeasureSpec.EXACTLY);
                    }
                }

                if (lp.gravity != -1 && (lp.gravity & Gravity.FILL_HORIZONTAL) == Gravity.FILL_HORIZONTAL) {
                    int availableWidth = measuredWidth - getPaddingLeft() - getPaddingRight() - lp.leftMargin - lp.rightMargin;
                    if (availableWidth > 0 && lp.width != LayoutParams.MATCH_PARENT) {
                        childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(availableWidth, MeasureSpec.EXACTLY);
                    }
                }

                if (lp.gravity != -1 && (lp.gravity & Gravity.FILL_VERTICAL) == Gravity.FILL_VERTICAL) {
                    int availableHeight = measuredHeight - getPaddingTop() - getPaddingBottom() - lp.topMargin - lp.bottomMargin;
                    if (availableHeight > 0 && lp.height != LayoutParams.MATCH_PARENT) {
                        childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(availableHeight, MeasureSpec.EXACTLY);
                    }
                }

                child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        final int count = getChildCount();

        final int parentLeft = getPaddingLeft();
        final int parentTop = getPaddingTop();
        final int parentRight = r - l - getPaddingRight();
        final int parentBottom = b - t - getPaddingBottom();
        final int parentWidth = parentRight - parentLeft;
        final int parentHeight = parentBottom - parentTop;

        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                final LayoutParams lp = (LayoutParams) child.getLayoutParams();

                final int childWidth = child.getMeasuredWidth();
                final int childHeight = child.getMeasuredHeight();

                int childLeft;
                int childTop;

                int gravity = lp.gravity;
                if (gravity == -1) {
                    gravity = Gravity.TOP | Gravity.START;
                }

                final int horizontalGravity = gravity & Gravity.HORIZONTAL_GRAVITY_MASK;
                final int verticalGravity = gravity & Gravity.VERTICAL_GRAVITY_MASK;

                childLeft = switch (horizontalGravity) {
                    case Gravity.CENTER_HORIZONTAL -> parentLeft + (parentWidth - childWidth) / 2 + lp.leftMargin - lp.rightMargin;
                    case Gravity.RIGHT -> parentRight - childWidth - lp.rightMargin;
                    default -> parentLeft + lp.leftMargin;
                };

                childTop = switch (verticalGravity) {
                    case Gravity.CENTER_VERTICAL -> parentTop + (parentHeight - childHeight) / 2 + lp.topMargin - lp.bottomMargin;
                    case Gravity.BOTTOM -> parentBottom - childHeight - lp.bottomMargin;
                    default -> parentTop + lp.topMargin;
                };

                childLeft = Math.max(parentLeft, Math.min(childLeft, parentRight - childWidth));
                childTop = Math.max(parentTop, Math.min(childTop, parentBottom - childHeight));

                child.layout(childLeft, childTop, childLeft + childWidth, childTop + childHeight);
            }
        }
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        if (p instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) p);
        } else if (p instanceof MarginLayoutParams) {
            return new LayoutParams((MarginLayoutParams) p);
        }
        return new LayoutParams(p);
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    public static class LayoutParams extends MarginLayoutParams {
        public int gravity = -1;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray a = c.obtainStyledAttributes(attrs, new int[]{ android.R.attr.layout_gravity });
            gravity = a.getInt(0, -1);
            a.recycle();
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }
    }
}