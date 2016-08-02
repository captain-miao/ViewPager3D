package com.github.captain_miao.viewpager3d;

import android.os.Bundle;
import android.support.v4.view.CustomViewPager;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.github.captain_miao.viewpager3d.animation.ZoomCardPageTransformer;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    CustomViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        mViewPager = (CustomViewPager) findViewById(R.id.view_pager);

        int defaultGap = SystemUtil.dpToPx(this, 48);
        mViewPager.setPadding(defaultGap, 0, defaultGap, 0);
        mViewPager.setClipToPadding(false);
        mViewPager.setPageMargin(defaultGap / 2);

        mViewPager.setPageTransformer(true, new ZoomCardPageTransformer(mViewPager, true));
        mViewPager.setAdapter(new SimapleAdapter());
    }




    private class SimapleAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return images.length;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            String model = images[position];
            LinearLayout view = (LinearLayout) MainActivity.this.getLayoutInflater().inflate(R.layout.item_3d_gallery_view, container, false);
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_movie);
            Picasso.with(MainActivity.this).load(model).into(imageView);
            container.addView(view);
            view.setTag(position);
            return view;
        }

        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            if (object instanceof ImageView) {
                ImageView imageView = (ImageView) object;
                container.removeView(imageView);
            }
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }



    public static String[] images =
            {
                    "http://rescdn.qqmail.com/dyimg/20140409/72B8663B7F23.jpg",
                    "http://www.gscn.com.cn/pic/0/10/13/74/10137424_638302.jpg",
                    "http://z.k1982.com/design_img/201412/2014120822492137209.jpg",
                    "http://hdimages.takungpao.com/2014/0114/20140114040855269.jpg",
                    "http://www.ellf.ru/uploads/posts/2010-02/1265238071_9454766-md.jpg",
                    "http://pic25.nipic.com/20121112/9211_140221458131_2.jpg",
                    "http://image.tianjimedia.com/uploadImages/2011/302/6ZI1A37D2433.jpg",
                    "http://photo.l99.com/bigger/9e2/1427726129531_9sgt2j.jpg",
                    "http://www.benbenla.cn/images/20120330/benbenla-04b.jpg",
                    "http://5.26923.com/download/pic/000/245/718dfc8322abe39627591e4a495767af.jpg",
                    "http://image.tianjimedia.com/uploadImages/2011/286/8X76S7XD89VU.jpg",
                    "http://tupian.enterdesk.com/2013/mxy/12/10/15/3.jpg",
                    "http://img.taopic.com/uploads/allimg/140326/235113-1403260I33562.jpg",
                    "http://pic4.nipic.com/20091215/2396136_140959028451_2.jpg",
                    "http://img4.duitang.com/uploads/item/201210/04/20121004233016_3vZPJ.thumb.600_0.jpeg",
                    "http://pic1.nipic.com/2008-10-30/200810309416546_2.jpg"
            };
}
