package com.github.captain_miao.viewpager3d.animation;

import android.support.v4.view.CustomViewPager;
import android.view.View;

import com.github.captain_miao.viewpager3d.SystemUtil;


/**
 * Created by leo on 16/5/20.
 * card相关的动画效果类
 */
public class ZoomCardPageTransformer implements CustomViewPager.PageTransformer {
    private CustomViewPager viewPager;
    private int windowWidth = 0;
    private int itemCount = 5;
    private float itemWidth = 0;
    private float translation;
    private int currentItem = 0;
    private boolean is3D = false;

    public ZoomCardPageTransformer(CustomViewPager viewPager, boolean is3D) {
        this.viewPager = viewPager;
        this.is3D = is3D;
        init();
    }

    private void init() {
        windowWidth = SystemUtil.getScreenWidth(viewPager.getContext());
        itemWidth = windowWidth / itemCount;
        translation = itemWidth;
    }


    /**
     * Viewpager 滑动时回调方法
     *
     * @param view     滑动页面
     * @param position 位移区间
     */
    public void transformPage(View view, float position) {
        //获取ViewPager当前显示的页面
        currentItem = viewPager.getCurrentItem();
        setPositionViewAnimation(view, position);
    }


    /**
     * 对View进行动画效果处理
     */
    private void setPositionViewAnimation(View view, float position) {
        //View所在页面
        int pos = (int) view.getTag();
        //是否在当前显示页的右边
        PagerLocation location = PagerAnimationUtil.getLocation(currentItem, pos);
        //缩放比例
        float scaleFactor = PagerAnimationUtil.getScaleCoefficient(currentItem, pos);
        //位移比例
        float transFactor = PagerAnimationUtil.getTranslationCoefficient(currentItem, pos);
        //位移距离
        float translationFactor = translation * PagerAnimationUtil.getTranslationSize(location, transFactor, position);
        //缩放大小
        float scale = PagerAnimationUtil.getScaleSize(scaleFactor, position);
        //对View进行动画处理
        view.setAlpha(0.5f + (scale - scaleFactor) / (1 - scaleFactor) * (1 - 0.5f));
        view.setScaleX(scale);
        view.setScaleY(scale);
        setViewRotation(view, location, position);
        view.setTranslationX(translationFactor);
        //Log.e("position:", "" + position);
    }

    /**
     * 设置view旋转角度（3D效果）
     */
    public void setViewRotation(View view, PagerLocation location, float position) {
        if (is3D) {
            if (location == PagerLocation.LIFT) {
                view.setRotationY(24);
            } else if (location == PagerLocation.RIGHT) {
                view.setRotationY(-24);
            } else {
                view.setRotationY(0);
            }
        }
    }

}
