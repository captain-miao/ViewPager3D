package com.github.captain_miao.viewpager3d.animation;

/**
 * Created by leo on 16/5/21.
 */
public class PagerAnimationUtil {


    /**
     * 获取偏移量比例系数
     *
     * @param position    view所在页面
     * @param currentItem Viewpager当前显示的页面
     */
    public static float getTranslationCoefficient(int currentItem, int position) {
        if (position == currentItem - 1 || position == currentItem + 1) {//右左边相邻的第1个Item
            return 1f;
        } else if (position == currentItem - 2 || position == currentItem + 2) {//右左边相邻的第2个Item
            return 2.5f;
        } else if (position == currentItem - 3 || position == currentItem + 3) {//右左边相邻的第3个Item
            return 4f;
        } else {
            return 0f;
        }
    }


    /**
     * 获计算最小偏移量
     *
     * @param location 页面所在位置
     * @param min
     * @param position viewpager滑动时 区间数值变化
     */
    public static float getTranslationSize(PagerLocation location, float min, float position) {
        if (location == PagerLocation.RIGHT) {
            return -Math.min(min, min * Math.abs(position));
        } else if (location == PagerLocation.LIFT) {
            return Math.min(min, min * Math.abs(position));
        } else {
            return Math.min(min, min * Math.abs(position));
        }

    }

    /**
     * 获取缩放比例系数
     *
     * @param position    view所在页面
     * @param currentItem Viewpager当前显示的页面
     */
    public static float getScaleCoefficient(int currentItem, int position) {
        if (position == currentItem - 1 || position == currentItem + 1) {//右左边相邻的第1个Item
            return 0.8f;
        } else if (position == currentItem - 2 || position == currentItem + 2) {//右左边相邻的第2个Item
            return 0.6f;
        } else if (position == currentItem - 3 || position == currentItem + 3) {//右左边相邻的第3个Item
            return 0.4f;
        } else {
            return 1.0f;
        }
    }


    /**
     * 获取缩放大小
     *
     * @param max
     * @param position
     */
    public static float getScaleSize(float max, float position) {
        return Math.max(max, 1 - Math.abs(position));
    }

    /**
     * 获取页面所在的位置
     * 当前页的右边、左边或者是当前页
     *
     * @param position    view所在页面
     * @param currentItem Viewpager当前显示的页面
     */
    public static PagerLocation getLocation(int currentItem, int position) {
        if (position == currentItem) {
            return PagerLocation.MIDDLE;
        } else if (position < currentItem) {
            return PagerLocation.LIFT;
        } else {
            return PagerLocation.RIGHT;
        }
    }


}
