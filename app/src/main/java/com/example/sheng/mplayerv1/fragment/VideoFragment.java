package com.example.sheng.mplayerv1.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.sheng.mplayerv1.R;
//import com.example.sheng.mplayerv1.activity.JcPlayer;
import com.example.sheng.mplayerv1.activity.JcPlayer;
import com.example.sheng.mplayerv1.activity.MPlayer;
import com.example.sheng.mplayerv1.activity.SearchActivity;
import com.example.sheng.mplayerv1.adapter.VideoAdapter;

import com.example.sheng.mplayerv1.base.BaseFragment;
import com.example.sheng.mplayerv1.domain.VideoBean;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

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
public class VideoFragment extends Fragment implements EasyPermissions.PermissionCallbacks {
    private RecyclerView mRecyclerView;
    private VideoAdapter mAdapter;
    private List<VideoBean> mList;
    private Context mContext;


    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    mAdapter.setNewData(mList);
                    break;
                case 1:

                    break;
            }
        }
    };
    private int RC_SMS_PERM=122;
    private TextView mToolbarTitle;
    private Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getActivity();
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fg_video,container,false);
        initView(view);
        return view;

    }

    private void initView(View view) {
        mToolbarTitle=view.findViewById(R.id.toolbar_title);
        // 清除默认的 Indicator

        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()). getSupportActionBar().setDisplayShowTitleEnabled(false);//除去Toolbartitle
        ((AppCompatActivity) getActivity()). getSupportActionBar().setDisplayHomeAsUpEnabled(false);//除去Toolbar默认显示的文字
//        toolbar.setTitle("在线视频");
        mToolbarTitle.setText("本地视频");
        mRecyclerView=view.findViewById(R.id.recyclerview);
        mAdapter=new VideoAdapter(mContext);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getContext(),2,GridLayoutManager.VERTICAL,false);
        mRecyclerView.setLayoutManager( gridLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mList=new ArrayList<>();
        mAdapter.setOnItemClickListener((adapter, view1, position) -> {
            VideoBean bean= (VideoBean) adapter.getItem(position);
            Intent intent=new Intent(getContext(),JcPlayer.class);
            intent.putExtra("VIDEO",bean.getData());
            intent.putExtra("TITLE",bean.getName());
            startActivity(intent);
        });
        //初始化控件
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
//            if (checkPermission( Manifest.permission.READ_EXTERNAL_STORAGE)) {
////                new ImageDataSource(this, null, this);
//                getData();
//            } else {
//                ActivityCompat.requestPermissions((Activity) mContext, new String[]{ Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION_STORAGE);
//            }
//        }

        if (EasyPermissions.hasPermissions(getContext(),Manifest.permission.READ_EXTERNAL_STORAGE)) {
            // Have permission, do the thing!
            getData();
//            Toast.makeText(getActivity(), "TODO: SMS things", Toast.LENGTH_LONG).show();
        } else {
            // Request one permission
            EasyPermissions.requestPermissions(this, null,
                    RC_SMS_PERM, Manifest.permission.READ_EXTERNAL_STORAGE);
        }
    }

    private void getData() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                ContentResolver resolver =mContext.getContentResolver();
                Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                String[] objs = {
                        MediaStore.Video.Media.DISPLAY_NAME,//视频文件在sdcard的名称
                        MediaStore.Video.Media.DURATION,//视频总时长
                        MediaStore.Video.Media.SIZE,//视频的文件大小
                        MediaStore.Video.Media.DATA,//视频的绝对地址
                        MediaStore.Video.Media.ARTIST,//歌曲的演唱者

                };
                Cursor cursor = resolver.query(uri, objs, null, null, null);
                if(cursor != null){
                    while (cursor.moveToNext()){
                        VideoBean videoBean=new VideoBean();

                        String name = cursor.getString(0);//视频的名称
                        videoBean.setName(name);

                        long duration = cursor.getLong(1);//视频的时长
                        videoBean.setDuration(duration);
                        Log.e("时长", String.valueOf(videoBean.getDuration()));

                        long size = cursor.getLong(2);//视频的文件大小
                        videoBean.setSize(size);
                        String playuri = cursor.getString(3);//视频的播放地址
                        videoBean.setData(playuri);
                        mList.add(videoBean);//写在上面
                    }

                    cursor.close();

                }

                handler.sendEmptyMessage(0);
            }

        }.start();


    }




    /**
     * 请求权限成功取数据
     * @param requestCode
     * @param perms
     */
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
//        ToastUtils.showToast(getApplicationContext(), "用户授权成功");
        Log.e("授权成功","授权成功");
        getData();
    }

    /**
     * 请求权限失败
     * @param requestCode
     * @param perms
     */
    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.refresh,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh_video:
                Log.e("我被点击", "getActivity（）在这里不能用了");
//                Toast.makeText(getContext(), "getContent()可用", Toast.LENGTH_LONG).show();
                mAdapter.getData().clear();
                mAdapter.notifyDataSetChanged();
                getData();
                return false;
            default:

                return super.onOptionsItemSelected(item);

        }

    }

}
