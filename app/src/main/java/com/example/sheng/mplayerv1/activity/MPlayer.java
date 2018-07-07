package com.example.sheng.mplayerv1.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

//import com.dueeeke.videoplayer.player.PlayerConfig;
import com.example.sheng.mplayerv1.R;
//import com.example.sheng.mplayerv1.customview.RotateIjkVideoView;
//import com.example.sheng.mplayerv1.customview.RotateInFullscreenController;

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
 * Created by st on 2018/7/4
 */
public class MPlayer extends AppCompatActivity {
//    private RotateIjkVideoView mIjkVideoView;
//    private RotateInFullscreenController mController;
    private String url;
    private String title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m);
//        url=getIntent().getStringExtra("VIDEO");//播放路径
//        title=getIntent().getStringExtra("TITLE");//获取传过来的标题
//        mIjkVideoView = findViewById(R.id.player);
////        mIjkVideoView.startFullScreen();
//        mController = new RotateInFullscreenController(this);
//        mIjkVideoView.setUrl(url);
//        mIjkVideoView.setTitle(title);
//        mIjkVideoView.startFullScreen();
//        mIjkVideoView.setOnClickListener(v -> {
//            mIjkVideoView.setVideoController(mController);
//            //需在setVideoController之后调用
//            mController.setPlayerState(mIjkVideoView.getCurrentPlayerState());
//            mController.setPlayState(mIjkVideoView.getCurrentPlayState());
//        });
//        PlayerConfig playerConfig = new PlayerConfig.Builder()
////                .enableCache() //启用边播边缓存功能
//                .autoRotate() //启用重力感应自动进入/退出全屏功能
//                .enableMediaCodec()//启动硬解码，启用后可能导致视频黑屏，音画不同步
//                .usingSurfaceView() //启用SurfaceView显示视频，不调用默认使用TextureView
//                .savingProgress() //保存播放进度
////                .disableAudioFocus() //关闭AudioFocusChange监听
//                .build();
//        mIjkVideoView.setPlayerConfig(playerConfig);
//        mIjkVideoView.start();
    }
}
