package com.qtt.jinrong.ui.activity.user;

import android.content.ComponentName;
import android.content.Intent;
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
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.qtt.framework.util.LogUtil;
import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.account.DataModel;
import com.qtt.jinrong.bean.account.DataResponse;
import com.qtt.jinrong.bean.account.DataUploadRequest;
import com.qtt.jinrong.config.Constants;
import com.qtt.jinrong.enums.DataTypeEnum;
import com.qtt.jinrong.presenter.IDataUploadPresenter;
import com.qtt.jinrong.presenter.impl.DataUploadPresenterImpl;
import com.qtt.jinrong.ui.activity.common.BaseActivity;
import com.qtt.jinrong.ui.widget.CommonTitleBar;
import com.qtt.jinrong.util.BitmapUtil;
import com.qtt.jinrong.util.FileUtil;
import com.qtt.jinrong.util.ToastUtil;
import com.qtt.jinrong.view.IDataUploadVIew;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 资料上传
 * Created by yanxin on 16/3/30.
 */
@EActivity(R.layout.activity_data_upload)
public class DataUploadActivity extends BaseActivity implements IDataUploadVIew {

    private final int REQUEST_TAKEPHOTO = 0x10;

    @ViewById(R.id.titleBar)
    CommonTitleBar mTitleBar;

    @ViewById(R.id.recyclerview)
    RecyclerView recyclerView;

    private String mCurrentPath;
    MyAdapter adapter;
    private List<DataModel> models = new ArrayList<>(5);
    private int mIndex;
    private IDataUploadPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DataModel dataModel = new DataModel();
        dataModel.setImgType(DataTypeEnum.身份证正面.getCode());
        models.add(dataModel);
        dataModel = new DataModel();
        dataModel.setImgType(DataTypeEnum.身份证反面.getCode());
        models.add(dataModel);
        dataModel = new DataModel();
        dataModel.setImgType(DataTypeEnum.营业执照.getCode());
        models.add(dataModel);
        dataModel = new DataModel();
        dataModel.setImgType(DataTypeEnum.个人征信.getCode());
        models.add(dataModel);
        dataModel = new DataModel();
        dataModel.setImgType(DataTypeEnum.个人头像.getCode());
        models.add(dataModel);
        mPresenter = new DataUploadPresenterImpl(this);
    }

    @AfterViews
    void initView() {
        mTitleBar.setTitle("资料信息");
        mTitleBar.setActivity(this);
        mTitleBar.setRightViewVisible(View.VISIBLE, "保存");
        mTitleBar.setTitleBarListener(new CommonTitleBar.TitleBarListener() {
            @Override
            public void leftOnClick() {

            }

            @Override
            public void rightOnClick() {
                mPresenter.uploadData();
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter();
        recyclerView.setAdapter(adapter);

        mPresenter.request();
    }

    private void takePhotot() {
        Intent camIntent = new Intent("android.media.action.IMAGE_CAPTURE");
        Intent systemCamIntent = new Intent(camIntent);
        systemCamIntent.setComponent(new ComponentName("com.android.camera", "com.android.camera.Camera"));

        String oriPath = Constants.DATAINFO_DIR;
        if (!new File(oriPath).exists()) {
            new File(oriPath).mkdirs();
        }
        mCurrentPath = oriPath + File.separator + System.currentTimeMillis() + ".jpg";
        systemCamIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(mCurrentPath)));
        startActivityForResult(systemCamIntent, REQUEST_TAKEPHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) return;

        if (!FileUtil.isExist(mCurrentPath)) return;

        //压缩 宽到2048
        BitmapUtil.scale(mCurrentPath,Constants.PIC_WIDTH,Constants.PIC_HEIGHT,85);

        try {
            if (requestCode == REQUEST_TAKEPHOTO) {
                models.get(mIndex).setPath(mCurrentPath);
                models.get(mIndex).setIsUpload(true);
                adapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

            holder.img.setTag(R.id.img,position);
            if(position == 0) {
                if(!hasPic) holder.defaultImg.setBackgroundResource(R.drawable.idcard_demo_1);
                holder.tip.setText("点击扫描身份证正面");
                holder.tv.setText("身份证正面");
            } else if(position == 1) {
                if(!hasPic) holder.defaultImg.setBackgroundResource(R.drawable.idcard_demo_2);
                holder.tip.setText("点击扫描身份证反面");
                holder.tv.setText("身份证反面");
            } else if(position == 2) {
                holder.tip.setText("点击扫描营业执照");
                holder.tv.setText("营业执照");
            } else if(position == 3) {
                holder.tip.setText("点击扫描个人征信");
                holder.tv.setText("个人征信");
            } else if(position == 4) {
                holder.tip.setText("点击扫描个人头像");
                holder.tv.setText("个人头像");
            }
            holder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mIndex = (Integer)v.getTag(R.id.img);
                    takePhotot();
                }
            });
            try {
                if(!TextUtils.isEmpty(models.get(position).getPath())) {
                    Uri uri = Uri.parse("file://" + models.get(position).getPath());
                    holder.img.setImageURI(uri);
                } else if(!TextUtils.isEmpty(models.get(position).getFilePath())) {

                    ControllerListener listener = new BaseControllerListener(){

                        @Override
                        public void onFinalImageSet(String id, Object imageInfo, Animatable animatable) {
                            super.onFinalImageSet(id, imageInfo, animatable);
                        }

                        @Override
                        public void onFailure(String id, Throwable throwable) {
                            super.onFailure(id, throwable);
                            LogUtil.e("LOADIMAGE","Exception:"+throwable.getMessage());
                        }
                    };

                    Uri uri = Uri.parse(models.get(position).getFilePath());

                    DraweeController controller = Fresco.newDraweeControllerBuilder()
                            .setControllerListener(listener)
                            .setUri(uri)
                            .setOldController(holder.img.getController())
                            .build();
                    holder.img.setController(controller);
                    //holder.img.setImageURI(uri);
                } else {
                    holder.img.setImageURI(null);
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return models==null?0:models.size();
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

    /***
     * IDataUploadVIew
     ***/
    @Override
    public List<DataUploadRequest> getRequest() {
        List<DataUploadRequest> requests = new ArrayList<>();
        for(int i=0;i<models.size();i++) {
            if(!TextUtils.isEmpty(models.get(i).getPath())
                    && models.get(i).isUpload()) {
                DataUploadRequest request = new DataUploadRequest();
                request.setUserId(getUserId());
                request.setImgType(models.get(i).getImgType());
                request.setFileByte(FileUtil.getBytesFromFile(models.get(i).getPath()));
                requests.add(request);
            }
        }
        return requests;
    }

    @Override
    public void onRequest(DataResponse response) {
        List<DataModel> mDatas;
        if(response == null || !response.isSuccess()
                || response.getData() == null) {
            mDatas = new ArrayList<>();
        } else {
            mDatas = response.getData();
        }
        List<Integer> types = DataTypeEnum.getCodes();
        for(int i=0;i<mDatas.size();i++) {
            if(mDatas.get(i).getImgType() == null) {
                mDatas.remove(i);
                i--;
            } else {
                types.remove(mDatas.get(i).getImgType());
            }

        }

        for(int i=0;i<mDatas.size();i++) {
            DataTypeEnum dataTypeEnum = DataTypeEnum.find(mDatas.get(i).getImgType());
            if(dataTypeEnum != null) {
                models.get(dataTypeEnum.getIndex()).setFilePath(mDatas.get(i).getFilePath());
            }
        }

        adapter.notifyDataSetChanged();

    }

    @Override
    public void onUpload(DataUploadRequest request) {
        for(int i=0;i<models.size();i++) {
            if(models.get(i).getImgType().intValue() == request.getImgType().intValue()) {
                ToastUtil.showShortToast(DataTypeEnum.find(request.getImgType()).name()+"上传成功");
                models.get(i).setIsUpload(false);
                break;
            }
        }
    }
    /*** IDataUploadVIew ***/

}