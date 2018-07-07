//package com.example.sheng.mplayerv1.adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseExpandableListAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.example.sheng.mplayerv1.R;
//import com.example.sheng.mplayerv1.domain.Group;
//import com.example.sheng.mplayerv1.domain.Item;
//
//import java.util.ArrayList;
//
///**
// * 　　　　　　　　┏┓　　　┏┓+ +
// * 　　　　　　　┏┛┻━━━┛┻┓ + +
// * 　　　　　　　┃　　　　　　　┃
// * 　　　　　　　┃　　　━　　　┃ ++ + + +
// * 　　　　       	██ ━██  ┃+
// * 　　　　　　　┃　　　　　　　┃ +
// * 　　　　　　　┃　　　┻　　　┃
// * 　　　　　　　┃　　　　　　　┃ + +
// * 　　　　　　　┗━┓　　　┏━┛
// * 　　　　　　　　　┃　　　┃
// * 　　　　　　　　　┃　　　┃ + + + +
// * 　　　　　　　　　┃　　　┃　　　　Code is far away from bug with the animal protecting
// * 　　　　　　　　　┃　　　┃ + 　　　　神兽保佑,代码无bug
// * 　　　　　　　　　┃　　　┃
// * 　　　　　　　　　┃　　　┃　　+
// * 　　　　　　　　　┃　 　　┗━━━┓ + +
// * 　　　　　　　　　┃ 　　　　　　　┣┓
// * 　　　　　　　　　┃ 　　　　　　　┏┛
// * 　　　　　　　　　┗┓┓┏━┳┓┏┛ + + + +
// * 　　　　　　　　　　┃┫┫　┃┫┫
// * 　　　　　　　　　　┗┻┛　┗┻┛+ + + +
// * <p>
// * Created by st on 2018/3/17.
// */
//public class MyBaseExpandableListAdapter extends BaseExpandableListAdapter {
//
//    private ArrayList<Group> gData;
//    private ArrayList<ArrayList<Item>> iData;
//
//    public MyBaseExpandableListAdapter(ArrayList<Group> gData,ArrayList<ArrayList<Item>> iData) {
//        this.gData = gData;
//        this.iData = iData;
//
//    }
//
//    @Override
//    public int getGroupCount() {
//        return gData.size();
//    }
//
//    @Override
//    public int getChildrenCount(int groupPosition) {
//        return iData.get(groupPosition).size();
//    }
//
//    @Override
//    public Group getGroup(int groupPosition) {
//        return gData.get(groupPosition);
//    }
//
//    @Override
//    public Item getChild(int groupPosition, int childPosition) {
//        return iData.get(groupPosition).get(childPosition);
//    }
//
//    @Override
//    public long getGroupId(int groupPosition) {
//        return groupPosition;
//    }
//
//    @Override
//    public long getChildId(int groupPosition, int childPosition) {
//        return childPosition;
//    }
//
//    /**
//     * 必须返回true不然子item展开后滑动出现错位
//     * @return
//     */
//    @Override
//    public boolean hasStableIds() {
//        return true;
//    }
//
//    //取得用于显示给定分组的视图. 这个方法仅返回分组的视图对象
//    @Override
//    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
//
//        ViewHolderGroup groupHolder;
//        if(convertView == null){
//            convertView = LayoutInflater.from(parent.getContext()).inflate(
//                    R.layout.item_exlist_group, parent, false);
//            groupHolder = new ViewHolderGroup();
//            groupHolder.tv_group_name = (TextView) convertView.findViewById(R.id.tv_group_name);
//            convertView.setTag(groupHolder);
//        }else{
//            groupHolder = (ViewHolderGroup) convertView.getTag();
//        }
//        groupHolder.tv_group_name.setText(gData.get(groupPosition).getgName());
//        return convertView;
//    }
//
//    //取得显示给定分组给定子位置的数据用的视图
//    @Override
//    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//        ViewHolderItem itemHolder;
//        if(convertView == null){
//            convertView = LayoutInflater.from(parent.getContext()).inflate(
//                    R.layout.item_exlist_item, parent, false);
//            itemHolder = new ViewHolderItem();
//            itemHolder.img_icon = (ImageView) convertView.findViewById(R.id.img_icon);
//            itemHolder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
//            convertView.setTag(itemHolder);
//        }else{
//            itemHolder = (ViewHolderItem) convertView.getTag();
//        }
//        itemHolder.img_icon.setImageResource(iData.get(groupPosition).get(childPosition).getiId());
//        itemHolder.tv_name.setText(iData.get(groupPosition).get(childPosition).getiName());
//        return convertView;
//    }
//
//    //设置子列表是否可选中
//    @Override
//    public boolean isChildSelectable(int groupPosition, int childPosition) {
//        return true;
//    }
//
//
//    private static class ViewHolderGroup{
//        private TextView tv_group_name;
//    }
//
//    private static class ViewHolderItem{
//        private ImageView img_icon;
//        private TextView tv_name;
//    }
//
//}
