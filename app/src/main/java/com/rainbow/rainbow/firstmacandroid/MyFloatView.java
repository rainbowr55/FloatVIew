package com.rainbow.rainbow.firstmacandroid;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
/**
 * Created by rainbow on 15/5/13.
 */
public class MyFloatView extends ImageView {
    Context context = null;

    private float mTouchStartX;
    private float mTouchStartY;
    private float x;
    private float y;

    private WindowManager wm=(WindowManager)getContext().getApplicationContext().getSystemService(Context.WINDOW_SERVICE);

    //此wmParams为获取的全局变量，用以保存悬浮窗口的属性
    private WindowManager.LayoutParams wmParams = ((MyApplication)getContext().getApplicationContext()).getMywmParams();

    public MyFloatView(Context context) {
        super(context);
        this.context = context;
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取相对屏幕的坐标，即以屏幕左上角为原点
        x = event.getRawX();
        y = event.getRawY()-25;   //25是系统状态栏的高度
        Log.i("currP", "currX"+x+"====currY"+y);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //获取相对View的坐标，即以此View左上角为原点
                mTouchStartX =  event.getX();
                mTouchStartY =  event.getY();

                Log.i("startP", "startX"+mTouchStartX+"====startY"+mTouchStartY);
                //
                //showToast();

                break;
            case MotionEvent.ACTION_MOVE:
                updateViewPosition();
                break;

            case MotionEvent.ACTION_UP:
                updateViewPosition();
                mTouchStartX=mTouchStartY=0;
                break;
        }
        return false;
    }

    private void updateViewPosition(){
        //更新浮动窗口位置参数
        wmParams.x=(int)( x-mTouchStartX);
        wmParams.y=(int) (y-mTouchStartY);
        wm.updateViewLayout(this, wmParams);

    }

    private void showToast(){
        LayoutInflater li=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=li.inflate(R.layout.toast,null);
        //把布局文件toast.xml转换成一个view
        Toast toast=new Toast(context);
        toast.setView(view);
        //载入view,即显示toast.xml的内容
        TextView tv=(TextView)view.findViewById(R.id.tv1);
        tv.setText("Toast显示View内容");
        //修改TextView里的内容
        toast.setDuration(Toast.LENGTH_SHORT);
        //设置显示时间，长时间Toast.LENGTH_LONG，短时间为Toast.LENGTH_SHORT,不可以自己编辑
        toast.show();
    }

}