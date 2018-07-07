//package com.example.sheng.mplayerv1.customview;
//
//import android.content.Context;
//import android.content.pm.ActivityInfo;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.util.AttributeSet;
//import android.util.Log;
//import android.view.View;
//import android.widget.Toast;
//
//import com.dueeeke.videoplayer.player.IjkVideoView;
//import com.dueeeke.videoplayer.util.WindowUtil;
//import com.example.sheng.mplayerv1.R;
//
//public class RotateInFullscreenController extends StandardVideoController {
//
//        private boolean isLandscape;
//
//        public RotateInFullscreenController(@NonNull Context context) {
//            super(context);
//        }
//
//        public RotateInFullscreenController(@NonNull Context context, @Nullable AttributeSet attrs) {
//            super(context, attrs);
//        }
//
//        public RotateInFullscreenController(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//            super(context, attrs, defStyleAttr);
//        }
//
//
//        @Override
//        protected void doStartStopFullScreen() {
//            if (isLandscape) {
//                WindowUtil.scanForActivity(getContext()).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//                isLandscape = false;
//                Log.e("我被触发了","我是if1");
//            } else {
//                WindowUtil.scanForActivity(getContext()).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//                isLandscape = true;
//                Log.e("我被触发了","我是if2");
//            }
//            fullScreenButton.setSelected(isLandscape);
//
//        }
//
//    /**
//     * 点击触发进入全状态播放
//     * @param playerState
//     */
//    @Override
//        public void setPlayerState(int playerState) {
//            super.setPlayerState(playerState);
//            switch (playerState) {
//                case IjkVideoView.PLAYER_FULL_SCREEN:
//                    Log.e("我被触发了","setPlayer");
//                    fullScreenButton.setSelected(false);
//                    break;
//            }
//
//        }
//
//        @Override
//        public void onClick(View v) {
//
//            int i = v.getId();
//            if (i == R.id.fullscreen) {
//                Log.e("我被触发了","ifa");
//                doStartStopFullScreen();
//            } else if (i == R.id.lock) {
//                Log.e("我被触发了","ifb");
//                doLockUnlock();
//            } else if (i == R.id.iv_play || i == R.id.thumb || i == R.id.iv_replay) {
//                Log.e("我被触发了","ifc");
//                doPauseResume();
//            } else if (i == R.id.back) {
//                if (isLandscape) WindowUtil.scanForActivity(getContext()).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//                mediaPlayer.stopFullScreen();
//                Log.e("我被触发了","ifd");
//            }
//        }
//
//        @Override
//        public boolean onBackPressed() {
//            if (isLocked) {
//                show();
//                Toast.makeText(getContext(), R.string.lock_tip, Toast.LENGTH_SHORT).show();
//                return true;
//            }
//            if (mediaPlayer.isFullScreen()) {
//                if (isLandscape) WindowUtil.scanForActivity(getContext()).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//                mediaPlayer.stopFullScreen();
//                setPlayerState(IjkVideoView.PLAYER_NORMAL);
//                return true;
//            }
//            return super.onBackPressed();
//        }
//    }