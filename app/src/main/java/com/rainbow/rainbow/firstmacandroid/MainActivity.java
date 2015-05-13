package com.rainbow.rainbow.firstmacandroid;

import android.content.Context;
import android.graphics.PixelFormat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    /** Called when the activity is first created. */
    /*用windowManager创建的View，当不需要时，务必记住使用windowManager的removeView方法来移除，请在Activity相关生命周期中自行添加扫尾工作*/
    private WindowManager wm=null;
    /*悬浮窗口坐标的移动实际是windowMananager.LayoutParams中x和y的变换，但是要注意设置相应的gravity*/
    private WindowManager.LayoutParams wmParams=null;
    /*悬浮View的代码，这里用一个ImageView作为演示*/
    private MyFloatView myFV=null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //创建悬浮窗口
        createView();
    }

    private void createView(){
        myFV=new MyFloatView(getApplicationContext());
        myFV.setImageResource(R.drawable.img_kuaidiyuan);
        //获取WindowManager
        wm=(WindowManager)getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        //设置LayoutParams(全局变量）相关参数
        wmParams = ((MyApplication)getApplication()).getMywmParams();

        /**
         *以下都是WindowManager.LayoutParams的相关属性
         * 具体用途可参考SDK文档
         */
        wmParams.type= WindowManager.LayoutParams.TYPE_PHONE;   //设置window type
        wmParams.format= PixelFormat.RGBA_8888;   //设置图片格式，效果为背景透明

        //设置Window flag
        /*
         * FLAG_NOT_TOUCH_MODAL    = 0x00000020;(十六进制)-->32(十进制)-->100000(二进制)
         * FLAG_NOT_FOCUSABLE      = 0x00000008;(十六进制)--> 8(十进制)-->001000(二进制)
         * LayoutParams.FLAG_NOT_TOUCH_MODAL | LayoutParams.FLAG_NOT_FOCUSABLE = 101000(二进制)-->40(十进制)-->0x0028(十六进制)
         **/
       // wmParams.flags= WindowManager.LayoutParams.
                //WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
               // | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        /*
         * 下面的flags属性的效果形同“锁定”。
         * 悬浮窗不可触摸，不接受任何事件,同时不影响后面的事件响应。
         wmParams.flags=LayoutParams.FLAG_NOT_TOUCH_MODAL
                               | LayoutParams.FLAG_NOT_FOCUSABLE
                               | LayoutParams.FLAG_NOT_TOUCHABLE;
        */

        wmParams.gravity= Gravity.LEFT|Gravity.TOP;   //调整悬浮窗口至左上角
        //以屏幕左上角为原点，设置x、y初始值
        wmParams.x=0;
        wmParams.y=0;

        //设置悬浮窗口长宽数据
        wmParams.width=100;
        wmParams.height=100;

        //显示myFloatView图像
        wm.addView(myFV, wmParams);

        myFV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"float View clicked!",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        //在程序退出(Activity销毁）时销毁悬浮窗口
        wm.removeView(myFV);
    }
}
