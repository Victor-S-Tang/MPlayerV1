package com.example.sheng.mplayerv1;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sheng.mplayerv1.base.BaseFragment;
import com.example.sheng.mplayerv1.fragment.NetVideoFragment;
import com.example.sheng.mplayerv1.fragment.VideoFragment;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BottomBar mBottomBar;

    private List<Fragment> mBaseFragment;
    private int position;
    private Fragment mContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
        initView();
    }

    private void initFragment() {
        mBaseFragment = new ArrayList<>();
        mBaseFragment.add(new VideoFragment());//本地视频
        mBaseFragment.add(new NetVideoFragment());//在线视频
    }

    private void initView() {
        mBottomBar=findViewById(R.id.bottomBar);
        mBottomBar.setOnTabSelectListener(tabId -> {
            switch (tabId) {
                case R.id.tab_a:
                    position=0;
                    break;
                case R.id.tab_b:
                    position=1;
                    break;
            }
            //根据位置得到对应的Fragment
            Fragment to = getFragment();
            //替换
            switchFrament(mContent,to);

        });

//        mBottomBar.setOnTabReselectListener(new OnTabReselectListener() {
//            @Override
//            public void onTabReSelected(@IdRes int tabId) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.fl_tab_container,new VideoFragment()).commit();
//            }
//        });
    }

    private void switchFrament(Fragment from, Fragment to) {
        if(from != to){
            mContent = to;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //才切换
            //判断有没有被添加
            if(!to.isAdded()){
                //to没有被添加
                //from隐藏
                if(from != null){
                    ft.hide(from);
                }
                //添加to
                if(to != null){
                    ft.add(R.id.fl_tab_container,to).commit();
                }
            }else{
                //to已经被添加
                // from隐藏
                if(from != null){
                    ft.hide(from);
                }
                //显示to
                if(to != null){
                    ft.show(to).commit();
                }
            }
        }
    }

    private Fragment getFragment() {
        Fragment fragment = mBaseFragment.get(position);
        return fragment;
    }
}
