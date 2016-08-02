/*
 * Copyright 2014 Toxic Bakery
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.captain_miao.viewpager3d.animation;

import android.support.v4.view.ViewPager;
import android.view.View;

public class BackgroundToForegroundTransformer extends BaseTransformer {

    private int mDefaultGap;
    private ViewPager mViewPager;

    public BackgroundToForegroundTransformer(int defaultGap) {
        this.mDefaultGap = defaultGap;
    }

    public BackgroundToForegroundTransformer(int defaultGap, ViewPager viewPager) {
        this.mViewPager = viewPager;
    }

    @Override
	protected void onTransform(View view, float position) {
		final float height = view.getHeight();
		final float width = view.getWidth();
		final float scale = min(position < 0 ? 1f : Math.abs(1f - position), 0.8f);

		view.setScaleX(scale);
		view.setScaleY(scale);
		view.setPivotX(width * 0.5f);
		view.setPivotY(height * 0.5f);
		view.setTranslationX(position < 0 ? width * position : -width * position * 0.25f);
	}

    @Override
    protected void onPostTransform(View view, float position) {
        super.onPostTransform(view, position);
        if(position == -1){
            view.setScaleX(0.8f);
            view.setAlpha(1f);
        } else if(position == 0){
            view.setAlpha(1f);
        } else if(position == 1){
            view.setScaleX(0.8f);
            view.setAlpha(1f);
        }
    }

    private static final float min(float val, float min) {
		return val < min ? min : val;
	}

}
