package com.zhiyun.uiunittest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.zhiyun.uiunittest.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    private ArrayAdapter<String> mArrayAdapter;

    private NormalAdapter mNormalAdapter;

    private boolean isShow = true;

    private String[] data = {"Apple",
            "Banana", "Orange", "Watermelon",
            "Pear", "Grape", "Pineapple",
            "Strawberry", "Cherry", "Mango"};

    public static Intent createQuery(Context context, String query, String value) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("QUERY", query);
        intent.putExtra("VALUE", value);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding =  DataBindingUtil.setContentView(this,R.layout.activity_main);
        mBinding.setClick(new ProxyClick());
        mBinding.setLifecycleOwner(this);

        mArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        mBinding.lvData.setAdapter(mArrayAdapter);

        mBinding.lvData2.setAdapter(mArrayAdapter);

        mBinding.lvData.setOnItemClickListener((parent, view, position, id) -> {
            mBinding.tvTest02.setText(data[position]);
        });

        mBinding.lvData2.setOnItemClickListener((parent, view, position, id) -> {
            mBinding.tvTest01.setText(data[position]);
        });

        mNormalAdapter = new NormalAdapter(Arrays.asList(data));
        mBinding.rcvData.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mBinding.rcvData.setAdapter(mNormalAdapter);
    }


    public class ProxyClick{

        public void test1(){
            mBinding.tvTest01.setText("测试1");
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