/*
 * MIT License
 *
 * Copyright (c) 2025 QmDeve
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * ===========================================
 * Project: QmBlurView
 * Created Date: 2025-10-21
 * Author: QmDeve
 * GitHub: https://github.com/QmDeve/QmBlurView
 *
 * Contributors:
 * - QmDeve - https://github.com/QmDeve
 * - Ahmed Sbai - https://github.com/sbaiahmed1
 * ===========================================
 */

package com.qmdeve.blurview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.TypedValue;

import androidx.core.content.ContextCompat;

import com.qmdeve.blurview.util.MenuUtils;
import com.qmdeve.blurview.util.Utils;

public class TabView {
    private Context mContext;
    private MenuUtils.MenuItem mMenuItem;
    private int mIndex;
    private Drawable mIcon;
    private float mIconSize;
    private final Rect mTextBounds;

    public TabView(Context context, MenuUtils.MenuItem menuItem, int index, float iconSize) {
        this.mContext = context;
        this.mMenuItem = menuItem;
        this.mIndex = index;
        this.mIconSize = iconSize;
        this.mTextBounds = new Rect();

        if (menuItem.getIcon() != null && !menuItem.getIcon().isEmpty()) {
            mIcon = loadIcon(menuItem.getIcon());
        }

        if (mIcon == null) {
            mIcon = createPlaceholderIcon();
        }
    }

    private Drawable loadIcon(String iconRef) {
        try {
            if (iconRef.startsWith("?")) {
                String attrIdStr = iconRef.substring(1);
                try {
                    int attrId = Integer.parseInt(attrIdStr);
                    TypedValue typedValue = new TypedValue();
                    if (mContext.getTheme().resolveAttribute(attrId, typedValue, true)) {
                        return ContextCompat.getDrawable(mContext, typedValue.resourceId);
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

            int iconResId = mContext.getResources().getIdentifier(iconRef, "drawable", mContext.getPackageName());
            if (iconResId != 0) {
                return ContextCompat.getDrawable(mContext, iconResId);
            }

            iconResId = mContext.getResources().getIdentifier(iconRef, "mipmap", mContext.getPackageName());
            if (iconResId != 0) {
                return ContextCompat.getDrawable(mContext, iconResId);
            }
        } catch (Exception e) {
            Log.e("BlurBottomNavigation", "Error loading icon: " + iconRef, e);
        }
        return null;
    }

    private Drawable createPlaceholderIcon() {
        ColorDrawable placeholder = new ColorDrawable(Color.LTGRAY);
        placeholder.setBounds(0, 0, (int) mIconSize, (int) mIconSize);
        return placeholder;
    }

    public void draw(Canvas canvas, int left, int top, int width, int height, boolean isSelected, int selectedColor, int unselectedColor, float textSize, boolean textBold) {
        int color = isSelected ? selectedColor : unselectedColor;

        boolean hasIcon = hasIcon();
        boolean hasTitle = hasTitle();

        int contentHeight = calculateContentHeight(height, hasIcon, hasTitle, textSize);
        int contentTop = top + (height - contentHeight) / 2;

        if (hasIcon && hasTitle) {
            drawIconAndText(canvas, left, contentTop, width, contentHeight, color, textSize, textBold);
        } else if (hasIcon) {
            drawIconOnly(canvas, left, contentTop, width, contentHeight, color);
        } else if (hasTitle) {
            drawTextOnly(canvas, left, contentTop, width, contentHeight, color, textSize, textBold);
        }
    }

    private void drawIconAndText(Canvas canvas, int left, int top, int width, int height, int color, float textSize, boolean textBold) {
        int iconLeft = left + (width - (int) mIconSize) / 2;
        int iconTop = top;

        if (mIcon != null) {
            mIcon.setBounds(iconLeft, iconTop, iconLeft + (int) mIconSize, iconTop + (int) mIconSize);
            mIcon.mutate();
            mIcon.setColorFilter(color, PorterDuff.Mode.SRC_IN);
            mIcon.draw(canvas);
            mIcon.clearColorFilter();
        }

        Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(color);
        textPaint.setTextSize(textSize);
        textPaint.setTypeface(textBold ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
        textPaint.setTextAlign(Paint.Align.CENTER);

        textPaint.getTextBounds(mMenuItem.getTitle(), 0, mMenuItem.getTitle().length(), mTextBounds);

        float textX = left + width / 2f;
        float textY = top + (int) mIconSize + mTextBounds.height() + TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 4, mContext.getResources().getDisplayMetrics());

        canvas.drawText(mMenuItem.getTitle(), textX, textY, textPaint);
    }

    private void drawIconOnly(Canvas canvas, int left, int top, int width, int height, int color) {
        int iconLeft = left + (width - (int) mIconSize) / 2;
        int iconTop = top + (height - (int) mIconSize) / 2;

        if (mIcon != null) {
            mIcon.setBounds(iconLeft, iconTop, iconLeft + (int) mIconSize, iconTop + (int) mIconSize);
            mIcon.mutate();
            mIcon.setColorFilter(color, PorterDuff.Mode.SRC_IN);
            mIcon.draw(canvas);
            mIcon.clearColorFilter();
        }
    }

    private void drawTextOnly(Canvas canvas, int left, int top, int width, int height, int color, float textSize, boolean textBold) {
        Paint textPaint = new Paint(android.graphics.Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(color);
        textPaint.setTextSize(textSize);
        textPaint.setTypeface(textBold ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
        textPaint.setTextAlign(Paint.Align.CENTER);

        textPaint.getTextBounds(mMenuItem.getTitle(), 0, mMenuItem.getTitle().length(), mTextBounds);

        float textX = left + width / 2f;
        float textY = top + height / 2f + mTextBounds.height() / 2f;

        canvas.drawText(mMenuItem.getTitle(), textX, textY, textPaint);
    }

    private int calculateContentHeight(int availableHeight, boolean hasIcon, boolean hasTitle, float textSize) {
        if (hasIcon && hasTitle) {
            Paint textPaint = new Paint();
            textPaint.setTextSize(textSize);
            textPaint.getTextBounds(mMenuItem.getTitle(), 0, mMenuItem.getTitle().length(), mTextBounds);

            return (int) (mIconSize + mTextBounds.height() + Utils.dp2px(mContext.getResources(), 4));
        } else if (hasIcon) {
            return (int) mIconSize;
        } else if (hasTitle) {
            Paint textPaint = new Paint();
            textPaint.setTextSize(textSize);
            textPaint.getTextBounds(mMenuItem.getTitle(), 0, mMenuItem.getTitle().length(), mTextBounds);
            return mTextBounds.height();
        }
        return availableHeight;
    }

    private boolean hasIcon() {
        return mMenuItem.getIcon() != null && !mMenuItem.getIcon().isEmpty() && mIcon != null;
    }

    private boolean hasTitle() {
        return mMenuItem.getTitle() != null && !mMenuItem.getTitle().isEmpty();
    }

    public boolean contains(float x, float y, int tabCount, int viewWidth, int fixedHeight) {
        int tabWidth = viewWidth / tabCount;
        int left = mIndex * tabWidth;
        int right = left + tabWidth;
        return x >= left && x <= right && y >= 0 && y <= fixedHeight;
    }
}