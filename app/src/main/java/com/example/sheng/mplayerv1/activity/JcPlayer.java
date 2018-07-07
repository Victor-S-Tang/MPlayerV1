package com.example.sheng.mplayerv1.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;


import com.example.sheng.mplayerv1.R;
import com.example.sheng.mplayerv1.domain.VideoBean;

import org.song.videoplayer.DemoQSVideoView;
import org.song.videoplayer.IVideoPlayer;
import org.song.videoplayer.PlayListener;
import org.song.videoplayer.Util;
import org.song.videoplayer.media.AndroidMedia;
import org.song.videoplayer.media.BaseMedia;
import org.song.videoplayer.media.IjkMedia;

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
 * Created by st on 2018/7/1
 */
public class JcPlayer extends Activity {
    DemoQSVideoView demoVideoView;
    private String url;
    private String title;
    Class decodeMedia;


    boolean flag;//记录退出时播放状态 回来的时候继续播放
    int position;//记录销毁时的进度 回来继续盖进度播放
    //    DanmakuControl danmakuControl;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jc_player);
//        VideoBean videoBean= (VideoBean) getIntent().getSerializableExtra("VIDEO");
        url=getIntent().getStringExtra("VIDEO");//播放路径
        title=getIntent().getStringExtra("TITLE");//获取传过来的标题
        initView();
    }

    private void initView() {
        demoVideoView = (DemoQSVideoView) findViewById(R.id.qs);
//        demoVideoView.getCoverImageView().setImageResource(R.mipmap.cover);
        demoVideoView.setLayoutParams(new LinearLayout.LayoutParams(-1, getResources().getDisplayMetrics().widthPixels * 9 / 16));
        //进入全屏的模式 0横屏 1竖屏 2传感器自动横竖屏 3根据视频比例自动确定横竖屏             -1什么都不做
        demoVideoView.enterFullMode = 3;
        demoVideoView.enterWindowFullscreen();
        //是否非全屏下也可以手势调节进度
        demoVideoView.isWindowGesture = true;
        demoVideoView.setPlayListener(new PlayListener() {
            @Override
            public void onStatus(int status) {//播放状态 1为播放
                if (status == IVideoPlayer.STATE_AUTO_COMPLETE) {
                    demoVideoView.quitWindowFullscreen();
                    finish();
                }//播放完成退出全屏
               Log.e("A这是什么", String.valueOf(status));
                if (status==6){

                }
            }

            @Override//全屏/普通/浮窗
            public void onMode(int mode) {
                if(mode==100){

                }
//                ;
                Log.e("这是什么", String.valueOf(mode));
            }

            @Override
            public void onEvent(int what, Integer... extra) {
                if (what == DemoQSVideoView.EVENT_CONTROL_VIEW & Build.VERSION.SDK_INT >= 19 & !demoVideoView.isWindowFloatMode())
                    if (extra[0] == 0)//状态栏隐藏/显示
                        Util.CLEAR_FULL(JcPlayer.this);
//                    Log.e("这是什么", String.valueOf());
                    else
                        Util.SET_FULL(JcPlayer.this);
            }

        });

        play(url, AndroidMedia.class,title);//系统硬解码
//        play(url, IjkMedia.class,title);//ijk_ffmepg解码
//        play(url, IjkExoMedia.class,title);//ijk_exo解码
    }

    private void play(String url, Class<? extends BaseMedia> decodeMedia, String title) {
        demoVideoView.release();
        demoVideoView.setDecodeMedia(decodeMedia);
        demoVideoView.setUp(url,title);
//
//        qsVideoView.seekTo(12300);
//        demoVideoView.setMute(mute);//设置静音
        demoVideoView.play();
        this.url = url;
        this.title=title;
        this.decodeMedia = decodeMedia;
    }
    //返回键

    @Override
    public void onBackPressed() {
        //全屏和系统浮窗不finish
        if (demoVideoView.onBackPressed()) {
//            if (demoVideoView.isSystemFloatMode())
//                //系统浮窗返回上一界面
//                moveTaskToBack(true);
            return;
        }

        super.onBackPressed();
    }

    //=======================以下生命周期控制=======================

    @Override
    public void onResume() {
        super.onResume();
        if (flag)
            demoVideoView.play();
        handler.removeCallbacks(runnable);
        if (position > 0) {
            demoVideoView.seekTo(position);
            position = 0;
        }
    }



    @Override
    public void onPause() {
        super.onPause();
        if (demoVideoView.isSystemFloatMode())
            return;
        //暂停
        flag = demoVideoView.isPlaying();
        demoVideoView.pause();
    }


    @Override
    public void onStop() {
        super.onStop();
        if (demoVideoView.isSystemFloatMode())
            return;
        //不马上销毁 延时15秒
        handler.postDelayed(runnable, 1000 * 15);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();//销毁
        if (demoVideoView.isSystemFloatMode())
            demoVideoView.quitWindowFloat();
        demoVideoView.release();
    }


    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (demoVideoView.getCurrentState() != IVideoPlayer.STATE_AUTO_COMPLETE)
                position = demoVideoView.getPosition();
            demoVideoView.release();
        }
    };


}
