package com.example.sheng.mplayerv1.adapter;

import android.content.Context;
import android.net.Uri;
import android.text.format.Formatter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.sheng.mplayerv1.R;
import com.example.sheng.mplayerv1.domain.VideoBean;
import com.example.sheng.mplayerv1.utils.Utils;

import java.io.File;

/**
 * 　　　　　　　　┏┓　　　┏┓+ +
 * 　　　　　　　┏┛┻━━━┛┻┓ + +
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━　　　┃ ++ + + +
 * 　　　　       	██ ━██  ┃+
 * 　　　　　　　┃　　　　　　　┃ +
 * 　　　　　　　┃　　　┻　　　┃
 * 　　　　　　　┃　　　　　　　┃ + +
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃ + + + +
 * 　　　　　　　　　┃　　　┃　　　　Code is far away from bug with the animal protecting
 * 　　　　　　　　　┃　　　┃ + 　　　　神兽保佑,代码无bug
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃　　+
 * 　　　　　　　　　┃　 　　┗━━━┓ + +
 * 　　　　　　　　　┃ 　　　　　　　┣┓
 * 　　　　　　　　　┃ 　　　　　　　┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛ + + + +
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛+ + + +
 * <p>
 * Created by st on 2018/3/17.
 */
public class VideoAdapter extends BaseQuickAdapter<VideoBean,BaseViewHolder> {
    private Context context;
    private Utils utils;
    public VideoAdapter(Context context) {
        super(R.layout.item_card_video);
        this.context = context;
        utils=new Utils();
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoBean item) {
          helper.setText(R.id.tv_name,item.getName())
                .setText(R.id.tv_time,utils.stringForTime((int) item.getDuration()))//用工具类转化时长
                .setText(R.id.tv_size, Formatter.formatFileSize(context,item.getSize()));//用工具类转化大小
          Glide.with(context).load(Uri.fromFile( new File( item.getData() ) )).thumbnail(0.6f).into((ImageView) helper.getView(R.id.iv_icon));//用播放路径当图片地址展示

    }

}
