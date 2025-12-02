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

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.qmdeve.blurview.widget.BlurBottomNavigationView;

import java.util.List;

public class TabViewManager {
    private final BlurBottomNavigationView mNavigationView;

    public TabViewManager(BlurBottomNavigationView navigationView) {
        this.mNavigationView = navigationView;
    }

    public void drawTabs(Canvas canvas, List<TabView> tabViews, int currentSelected, int fixedHeight) {
        if (tabViews.isEmpty()) return;

        int tabWidth = mNavigationView.getWidth() / tabViews.size();
        int tabHeight = fixedHeight;

        for (int i = 0; i < tabViews.size(); i++) {
            TabView tabView = tabViews.get(i);
            tabView.draw(canvas, i * tabWidth, 0, tabWidth, tabHeight,
                    i == currentSelected,
                    mNavigationView.getSelectedColor(),
                    mNavigationView.getUnselectedColor(),
                    mNavigationView.getTextSize(),
                    mNavigationView.isTextBold());
        }
    }

    public boolean handleTouchEvent(MotionEvent event, List<TabView> tabViews, int fixedHeight) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            float x = event.getX();
            float y = event.getY();

            if (y <= fixedHeight) {
                for (int i = 0; i < tabViews.size(); i++) {
                    TabView tabView = tabViews.get(i);
                    if (tabView.contains(x, y, tabViews.size(), mNavigationView.getWidth(), fixedHeight)) {
                        mNavigationView.setSelectedTab(i);
                        break;
                    }
                }
            }
        }
        return true;
    }
}