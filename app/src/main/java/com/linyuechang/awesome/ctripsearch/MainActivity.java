package com.linyuechang.awesome.ctripsearch;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.AppBarLayout;
import android.view.Menu;
import android.view.MenuItem;
import com.yyydjk.library.BannerLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity {

    @BindView(R.id.app_bar)
    AppBarLayout mAppBar;
    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    @BindView(R.id.search_tab_container)
    View slideView;
    @BindView(R.id.top_container)
    LinearLayout mTop;
    @BindView(R.id.banner)
    BannerLayout bannerLayout;
    @BindView(R.id.rg_slide)
    RadioGroup rgSlide;
    @BindView(R.id.slide_bg)
    View mSlideView;

    private int underLineWidth = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        initBanner();
    }

    private void initView(){
        rgSlide.check(R.id.rb_left);
        mAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                slideView.setAlpha(getAlpha(verticalOffset, appBarLayout.getTotalScrollRange()));
                mTop.setAlpha(1.0f - getAlpha(verticalOffset, appBarLayout.getTotalScrollRange()));
            }
        });
        rgSlide.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_left:
                        slide(0);
                        break;
                    case R.id.rb_center:
                        slide(1);
                        break;
                    case R.id.rb_right:
                        slide(2);
                        break;
                }
            }
        });
    }

    private float getAlpha(int a, int b){
        float f = Math.abs((float)a/b);
        float result = 1.0f-f;
        return result;
    }

    private void initBanner(){
        List<Integer> viewList = new ArrayList<>();
        viewList.add(R.drawable.ctrip_01);
        viewList.add(R.drawable.ctrip_02);
        viewList.add(R.drawable.ctrip_03);
        bannerLayout.setViewRes(viewList);
    }

    private void slide(int position){
        if (underLineWidth <= 0) {
            underLineWidth = (ScreenHelper.getScreenWidth()-ScreenHelper.dip2px(20)) / 3;
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mSlideView.getLayoutParams();
            layoutParams.width = underLineWidth;
            mSlideView.setLayoutParams(layoutParams);
        }
        ObjectAnimator.ofFloat(mSlideView, "translationX", ScreenHelper.dip2px(10) + underLineWidth * position).setDuration(300).start();
    }
}
