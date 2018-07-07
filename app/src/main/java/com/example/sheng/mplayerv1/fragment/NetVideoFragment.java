package com.example.sheng.mplayerv1.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sheng.mplayerv1.R;
import com.example.sheng.mplayerv1.activity.NetVideoList;
import com.example.sheng.mplayerv1.activity.SearchActivity;
import com.example.sheng.mplayerv1.adapter.IndicatorExpandableListAdapter;
import com.example.sheng.mplayerv1.domain.Group;
import com.example.sheng.mplayerv1.domain.Item;

import java.util.ArrayList;

import static com.example.sheng.mplayerv1.Api.ACTION_MOVIE_URL;
import static com.example.sheng.mplayerv1.Api.ANIMATION_URL;
import static com.example.sheng.mplayerv1.Api.CHINA_TV_URL;
import static com.example.sheng.mplayerv1.Api.COMEDY_MOVIE_URL;
import static com.example.sheng.mplayerv1.Api.DOCUMENTARY_URL;
import static com.example.sheng.mplayerv1.Api.ETHIC_MOVIE_URL;
import static com.example.sheng.mplayerv1.Api.EURAMERICAN_TY_URL;
import static com.example.sheng.mplayerv1.Api.FEATURE_MOVIE_URL;
import static com.example.sheng.mplayerv1.Api.GANGTAI_TV_URL;
import static com.example.sheng.mplayerv1.Api.HORROR_MOVIE_URL;
import static com.example.sheng.mplayerv1.Api.LOVE_MOVIE_URL;
import static com.example.sheng.mplayerv1.Api.RIHAN_TY_URL;
import static com.example.sheng.mplayerv1.Api.SCIENCE_MOVIE_URL;
import static com.example.sheng.mplayerv1.Api.VARIETY_URL;
import static com.example.sheng.mplayerv1.Api.WAR_MOVIE_URL;

/**
 * //                            _ooOoo_
 * //                           o8888888o
 * //                           88" . "88
 * //                           (| -_- |)
 * //                           O\  =  /O
 * //                        ____/`---'\____
 * //                      .'  \\|     |//  `.
 * //                     /  \\|||  :  |||//  \
 * //                    /  _||||| -:- |||||-  \
 * //                    |   | \\\  -  /// |   |
 * //                    | \_|  ''\---/''  |   |
 * //                    \  .-\__  `-`  ___/-. /
 * //                  ___`. .'  /--.--\  `. . __
 * //               ."" '<  `.___\_<|>_/___.'  >'"".
 * //              | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 * //              \  \ `-.   \_ __\ /__ _/   .-` /  /
 * //         ======`-.____`-.___\_____/___.-`____.-'======
 * //                            `=---='
 * //        ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 * //                      Buddha Bless, No Bug !
 * /**
 * Created by st on 2018/6/9
 */
public class NetVideoFragment extends Fragment {
    private Context mContent;

    private ArrayList<Group> gData ;
    private ArrayList<ArrayList<Item>> iData ;
    private ArrayList<Item> lData ;
    private Context mContext;
    private ExpandableListView myExlist;
    private IndicatorExpandableListAdapter myAdapter ;
    private Toolbar toolbar;
    private TextView mToolbarTitle;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContent=getActivity();
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fg_net_video,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        myExlist = view.findViewById(R.id.exlist);
        mToolbarTitle=view.findViewById(R.id.toolbar_title);
        // 清除默认的 Indicator
        myExlist.setGroupIndicator(null);
        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()). getSupportActionBar().setDisplayShowTitleEnabled(false);//除去Toolbartitle
        ((AppCompatActivity) getActivity()). getSupportActionBar().setDisplayHomeAsUpEnabled(false);//除去Toolbar默认显示的文字
//        toolbar.setTitle("在线视频");
        mToolbarTitle.setText("在线视频");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {

        //数据准备
        gData = new ArrayList<>();
        iData = new ArrayList<>();
        gData.add(new Group("电影",""));
        gData.add(new Group("连续剧",""));
        gData.add(new Group("综艺",VARIETY_URL));
        gData.add(new Group("动漫",ANIMATION_URL));
        gData.add(new Group("记录片",DOCUMENTARY_URL));

        lData = new ArrayList<>();

        //电影组
        lData.add(new Item(ACTION_MOVIE_URL,"动作片"));
        lData.add(new Item(COMEDY_MOVIE_URL,"喜剧片"));
        lData.add(new Item(LOVE_MOVIE_URL,"爱情片"));
        lData.add(new Item( SCIENCE_MOVIE_URL,"科幻片"));
        lData.add(new Item(HORROR_MOVIE_URL,"恐怖片"));
        lData.add(new Item(FEATURE_MOVIE_URL,"剧情片"));
        lData.add(new Item(WAR_MOVIE_URL,"战争片"));
        lData.add(new Item(ETHIC_MOVIE_URL,"伦理片"));
        iData.add(lData);
        //连续剧组
        lData = new ArrayList<>();
        lData.add(new Item(CHINA_TV_URL, "国产剧"));
        lData.add(new Item(GANGTAI_TV_URL, "港台剧"));
        lData.add(new Item(EURAMERICAN_TY_URL, "日韩剧"));
        lData.add(new Item(RIHAN_TY_URL, "欧美剧"));
        iData.add(lData);
        //综艺组
        lData = new ArrayList<>();
        iData.add(lData);
        //动漫组
        lData = new ArrayList<>();
        iData.add(lData);
        //记录片组
        lData = new ArrayList<>();
        iData.add(lData);

        myAdapter = new IndicatorExpandableListAdapter(gData,iData);
        myExlist.setAdapter(myAdapter);

        //为列表设置点击事件
//        myExlist.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//            @Override
//            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
////                Toast.makeText(parent.getContext(), "你点击了：" + iData.get(groupPosition).get(childPosition).getiUrl(), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getContext(), NetVideoList.class);
//                intent.putExtra("NET_URL",iData.get(groupPosition).get(childPosition).getiUrl());
//                intent.putExtra("NET_VIDEO_TYPE",iData.get(groupPosition).get(childPosition).getiName());
//                startActivity(intent);
//
//                return true;
//            }
//        });
        //Lambda表达式
        myExlist.setOnChildClickListener( (ExpandableListView parent, View v, int groupPosition, int childPosition, long id) ->{
//                Toast.makeText(parent.getContext(), "你点击了：" + iData.get(groupPosition).get(childPosition).getiUrl(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), NetVideoList.class);
                intent.putExtra("NET_URL",iData.get(groupPosition).get(childPosition).getiUrl());
                intent.putExtra("NET_VIDEO_TYPE",iData.get(groupPosition).get(childPosition).getiName());
                startActivity(intent);

                return true;
            }
        );
//        myExlist.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
//            @Override
//            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
//                if (iData.get(groupPosition).isEmpty()) {// isEmpty没有
//                    Toast.makeText(parent.getContext(), "你点击了：" + gData.get(groupPosition).getgUrl(), Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getContext(), NetVideoList.class);
//                    intent.putExtra("NET_URL",gData.get(groupPosition).getgUrl());
//                    intent.putExtra("NET_VIDEO_TYPE",gData.get(groupPosition).getgName());
//                    startActivity(intent);
//
//
//                    return true;
//                } else {
//                    return false;
//                }
////                return false;
//            }
//        });
        //Lambdas表达式
        myExlist.setOnGroupClickListener((ExpandableListView parent, View v, int groupPosition, long id)->{
            if (iData.get(groupPosition).isEmpty()) {// isEmpty没有
//                Toast.makeText(parent.getContext(), "你点击了：" + gData.get(groupPosition).getgUrl(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), NetVideoList.class);
                intent.putExtra("NET_URL",gData.get(groupPosition).getgUrl());
                intent.putExtra("NET_VIDEO_TYPE",gData.get(groupPosition).getgName());
                startActivity(intent);


                return true;
            } else {
                return false;
            }
        });

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main,menu);
//        return true;
//    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_video:
                Log.e("我被点击","getActivity（）在这里不能用了");
//                Toast.makeText(getContext(),"getContent()可用",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getContext(), SearchActivity.class));
                return false;
            default:

                return super.onOptionsItemSelected(item);

        }

//        int id=item.getItemId();
//        if (id==R.id.search_video){
//
////            Intent intent=new Intent(MainActivity.this,SearchActivity.class);
////            startActivity(intent);
////            try {
////                Toast.makeText(this,"缓存大小为"+getTotalCacheSize(this),Toast.LENGTH_LONG).show();
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
////            clearAllCache(this);
//            return false;
//        }
//        return super.onOptionsItemSelected(item);
    }
}
