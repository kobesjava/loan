package com.qtt.jinrong.ui.activity.user;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.qtt.framework.util.AdvancedBitmapUtils;
import com.qtt.framework.util.FileUtils;
import com.qtt.framework.util.LogUtil;
import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.user.DataUploadModel;
import com.qtt.jinrong.config.Constants;
import com.qtt.jinrong.ui.activity.common.BaseActivity;
import com.qtt.jinrong.ui.widget.CommonTitleBar;
import com.qtt.jinrong.util.BitmapUtil;
import com.qtt.jinrong.util.FileUtil;
import com.qtt.jinrong.view.IDataUploadVIew;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 资料上传
 * Created by yanxin on 16/3/30.
 */
@EActivity(R.layout.activity_data_upload)
public class DataUploadActivity extends BaseActivity implements IDataUploadVIew {

    private final int REQUEST_FRONT = 0x10;
    private final int REQUEST_BACK = 0x11;
    private final int REQUEST_BL = 0x12;
    private final int REQUEST_CREDIT = 0x13;
    private final int REQUEST_AVATAR = 0x14;

    @ViewById(R.id.titleBar)
    CommonTitleBar mTitleBar;

    @ViewById(R.id.recyclerview)
    RecyclerView recyclerView;

    /*@ViewById(R.id.idCardFront)
    SimpleDraweeView idCardFront;
    @ViewById(R.id.idCardBack)
    SimpleDraweeView idCardBack;
    @ViewById(R.id.businessLinsces)
    SimpleDraweeView businessLinsces;
    @ViewById(R.id.credit)
    SimpleDraweeView credit;
    @ViewById(R.id.avatar)
    SimpleDraweeView avatar;*/

    private String mCurrentPath;
    MyAdapter adapter;
    private List<DataUploadModel> models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        models = new ArrayList<>();
        DataUploadModel model = new DataUploadModel();
        models.add(model);
        model = new DataUploadModel();
        models.add(model);
        model = new DataUploadModel();
        //model.setPath("");
        models.add(model);http://img02.tooopen.com/images/20160216/tooopen_sy_156324542564.jpg
        model = new DataUploadModel();
        //model.setPath("http://www.52ij.com/uploads/allimg/160317/003T95164-3.jpg");
        models.add(model);
        model = new DataUploadModel();
        //model.setPath("http://pic32.nipic.com/20130829/12906030_124355855000_2.png");
        models.add(model);
    }

    @AfterViews
    void initView() {
        mTitleBar.setTitle("资料信息");
        mTitleBar.setActivity(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);
    }

    /*@Click(R.id.idCardFront)
    void clickidCardFront() {
        takePhotot(REQUEST_FRONT);
    }

    @Click(R.id.idCardBack)
    void clickidCardBack() {
        takePhotot(REQUEST_BACK);
    }

    @Click(R.id.businessLinsces)
    void clickbusinessLinsces() {
        takePhotot(REQUEST_BL);
    }

    @Click(R.id.credit)
    void clickcredit() {
        takePhotot(REQUEST_CREDIT);
    }

    @Click(R.id.avatar)
    void clickavatar() {
        takePhotot(REQUEST_AVATAR);
    }*/

    private void takePhotot(int requestCode) {
        Intent camIntent = new Intent("android.media.action.IMAGE_CAPTURE");
        Intent systemCamIntent = new Intent(camIntent);
        systemCamIntent.setComponent(new ComponentName("com.android.camera", "com.android.camera.Camera"));

        String oriPath = Constants.DATAINFO_DIR;
        if (!new File(oriPath).exists()) {
            new File(oriPath).mkdirs();
        }
        mCurrentPath = oriPath + File.separator + System.currentTimeMillis() + ".jpg";
        systemCamIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(mCurrentPath)));
        startActivityForResult(systemCamIntent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) return;

        if (!FileUtil.isExist(mCurrentPath)) return;

        //压缩 宽到2048
        BitmapUtil.scale(mCurrentPath,Constants.PIC_WIDTH,Constants.PIC_HEIGHT,85);

        try {
            //Uri uri = Uri.parse("file://" + mCurrentPath);
            if (requestCode == REQUEST_FRONT) {
                models.get(0).setPath(mCurrentPath);
                //idCardFront.setImageURI(uri);
            } else if (requestCode == REQUEST_BACK) {
                models.get(1).setPath(mCurrentPath);
                //idCardBack.setImageURI(uri);
            } else if (requestCode == REQUEST_BL) {
                models.get(2).setPath(mCurrentPath);
                //businessLinsces.setImageURI(uri);
            } else if (requestCode == REQUEST_CREDIT) {
                models.get(3).setPath(mCurrentPath);
                //credit.setImageURI(uri);
            } else if (requestCode == REQUEST_AVATAR) {
                models.get(4).setPath(mCurrentPath);
                //avatar.setImageURI(uri);
            }
            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /***
     * IDataUploadVIew
     ***/
    @Override
    public void onRequest() {
    }
    /*** IDataUploadVIew ***/

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    DataUploadActivity.this).inflate(R.layout.data_upload_item, parent,
                    false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

            boolean hasPic = false;
            if(!TextUtils.isEmpty(models.get(position).getPath())) {
                hasPic = true;
            }

            if(hasPic) {
                holder.defaultImg.setImageURI(null);
                holder.defaultImg.setVisibility(View.GONE);
            }

            if(position == 0) {
                if(!hasPic) holder.defaultImg.setBackgroundResource(R.drawable.idcard_demo_1);
                holder.img.setTag(R.id.img,REQUEST_FRONT);
                holder.tip.setText("点击扫描身份证正面");
                holder.tv.setText("身份证正面");
            } else if(position == 1) {
                if(!hasPic) holder.defaultImg.setBackgroundResource(R.drawable.idcard_demo_2);
                holder.img.setTag(R.id.img,REQUEST_BACK);
                holder.tip.setText("点击扫描身份证反面");
                holder.tv.setText("身份证反面");
            } else if(position == 2) {
                holder.img.setTag(R.id.img,REQUEST_BL);
                holder.tip.setText("点击扫描营业执照");
                holder.tv.setText("营业执照");
            } else if(position == 3) {
                holder.img.setTag(R.id.img,REQUEST_CREDIT);
                holder.tip.setText("点击扫描个人征信");
                holder.tv.setText("个人征信");
            } else if(position == 4) {
                holder.img.setTag(R.id.img,REQUEST_AVATAR);
                holder.tip.setText("点击扫描个人头像");
                holder.tv.setText("个人头像");
            }
            holder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    takePhotot((Integer)v.getTag(R.id.img));
                }
            });
            try {
                if(!TextUtils.isEmpty(models.get(position).getPath())) {
                    Uri uri = Uri.parse("file://" + models.get(position).getPath());
                    //Uri uri = Uri.parse(models.get(position).getPath());
                    holder.img.setImageURI(uri);
                } else {
                    holder.img.setImageURI(null);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return 5;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView defaultImg;
            SimpleDraweeView img;
            TextView tip;
            TextView tv;

            public MyViewHolder(View view)
            {
                super(view);
                defaultImg = (ImageView)view.findViewById(R.id.defaultImg);
                img = (SimpleDraweeView)view.findViewById(R.id.img);
                tip = (TextView) view.findViewById(R.id.tip);
                tv = (TextView) view.findViewById(R.id.tv);

                img.getHierarchy();
            }
        }
    }
}