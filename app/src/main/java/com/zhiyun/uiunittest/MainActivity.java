package com.zhiyun.uiunittest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.zhiyun.uiunittest.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    private boolean isShow = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding =  DataBindingUtil.setContentView(this,R.layout.activity_main);
        mBinding.setClick(new ProxyClick());
        mBinding.setLifecycleOwner(this);

    }


    public class ProxyClick{

        public void test1(){
            mBinding.tvTest02.setText("测试1");
//            mBinding.tvTest02.setVisibility(View.GONE);
//            if(isShow){
//                mBinding.tvTest02.setVisibility(View.GONE);
//            }else {
//                mBinding.tvTest02.setVisibility(View.VISIBLE);
//            }
//            isShow = !isShow;
//            if (mBinding.tvTest02.getVisibility() == View.VISIBLE) {
//                mBinding.tvTest02.setVisibility(View.GONE);
//            } else {
//            }
        }
    }
}