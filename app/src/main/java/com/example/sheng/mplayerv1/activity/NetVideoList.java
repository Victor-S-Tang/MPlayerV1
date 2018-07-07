package com.example.sheng.mplayerv1.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.sheng.mplayerv1.R;
import com.example.sheng.mplayerv1.adapter.SearchitemAdapter;
import com.example.sheng.mplayerv1.callback.StringDialogCallback;
import com.example.sheng.mplayerv1.domain.NetVideoBean;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.util.ArrayList;
import java.util.List;

import static com.example.sheng.mplayerv1.Api.BASE_URL;

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
 * Created by st on 2018/7/3
 */
public class NetVideoList extends AppCompatActivity {

    private RecyclerView mRlv;
    private SearchitemAdapter mAdapter;
    private List<NetVideoBean> mLists;
    private String mUrl;
    private  String mNextPager;
    private Toolbar mTbar;
    private TextView mTvTitle;
    private String mTitle;
    private RefreshLayout mRshL;
    private LinearLayout mLoadingView;
    private boolean mNoPage;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                  if (mLists.size()!=0){
                      mAdapter.setNewData(mLists);
                      mLoadingView.setVisibility(View.GONE);
                  }

                   break;
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_video_list);
        mUrl = getIntent().getStringExtra("NET_URL");
        mTitle=getIntent().getStringExtra("NET_VIDEO_TYPE");
        initView();
    }

    private void initView() {
        mLoadingView=findViewById(R.id.pb_loading_view);
        mTbar=findViewById(R.id.toolbar);
        mTvTitle=findViewById(R.id.toolbar_title);
        mRshL=findViewById(R.id.srl_net_video);
        setSupportActionBar(mTbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mTbar.setNavigationOnClickListener(v -> onBackPressed());
        mTvTitle.setText(mTitle);
        mRlv=findViewById(R.id.rv_net_video);
        mAdapter=new SearchitemAdapter(this);
        mRlv.setLayoutManager(new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false));
        mRlv.setAdapter(mAdapter);
        mLists=new ArrayList<>();
        getDataFromSpider(mUrl);
        refreshData();
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            NetVideoBean bean= (NetVideoBean) adapter.getItem(position);
            Intent intent =new Intent(NetVideoList.this,ContenView.class);
            intent.putExtra("NET_VIDEO_BEAN",bean);
            startActivity(intent);
        });
    }

    private void refreshData() {
        mRshL.setOnRefreshListener(refreshlayout -> {
            mNextPager=null;
            mAdapter.getData().clear();
            getDataFromSpider(mUrl);
            mAdapter.notifyDataSetChanged();
            refreshlayout.finishRefresh();
        });
        mRshL.setOnLoadmoreListener(refreshlayout -> {
            if (mNoPage){
                Toast.makeText(this,"没有下一页数据了",Toast.LENGTH_SHORT).show();
                mRshL.finishLoadmoreWithNoMoreData();
//                return;
            }else {
                getDataFromSpider(mNextPager);
                refreshlayout.finishLoadmore();
            }

        });
    }

    private void getDataFromSpider(String url) {
        OkGo.<String>get(url)//
                .tag(this)//
                .headers("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.139 Safari/537.36")//
                .headers("Referer","http://www.go1977.com/")
                .execute(new StringCallback() {
                    @Override
                    public void onError(Response<String> response) {

                    }

                    @Override
                    public void onSuccess(Response<String> response) {
                        String html= response.body();
                        parseHtml(html);
                    }
                });

    }

    private void parseHtml(String html) {
        Document document= Jsoup.parse(html);
        Elements getData=document.select("div.xing_vb");
        Elements lis=getData.select("ul li");
        for (Element e:lis){
            if (!e.select(".xing_vb4").isEmpty()){
                NetVideoBean bean=new NetVideoBean();
                String href=e.select("a").attr("href");
                bean.setHref(BASE_URL+href);
                Log.e("链接地址",BASE_URL+href);

                String title=e.getElementsByClass("xing_vb4").text();
                bean.setTitle(title);
                Log.e("视频名称",title);

                String type=e.getElementsByClass("xing_vb5").text();
                bean.setType(type);
                Log.e("类型",type);

                String date=e.getElementsByClass("xing_vb6").text();
                bean.setTime(date);
                Log.e("日期",date);
                mLists.add(bean);
            }

        }

        Elements getNextPage= document.getElementsByClass("pagelink_a");
        if (getNextPage.isEmpty()){
            Log.e("警告","没有下一页");
            mNoPage=true;
        }else {
            for (Element e :getNextPage){
                String page=e.text();
                Log.e("网页",page);
                if (page.equals("下一页")){

                    mNextPager=BASE_URL+e.attr("href");

                    Log.e("下一页地址",mNextPager);
                }
            }

        }


        handler.sendEmptyMessage(0);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
