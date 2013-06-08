package tv.icntv.app.ui;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import tv.icntv.app.AppException;
import tv.icntv.app.MainApplication;
import tv.icntv.app.R;

import android.os.Handler;
import tv.icntv.app.api.ApiClient;
import tv.icntv.app.bean.Update;

/**
 * Created by tian on 13-6-8.
 */
public class About extends BaseActivity {
    Button checkButton = null;
    MyHandler mHandler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        mHandler = new MyHandler();
        checkButton = (Button) findViewById(R.id.about_checkupdate);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkButton.setText("更新中...");
                // new Thread(new MyThread()).start();
                new Thread() {
                    @Override
                    public void run() {

                        Update update = null;
                        try {
                            update = ApiClient.checkUpdate();
                            Message msg = new Message();
                            Bundle b = new Bundle();
                            b.putString("key", "完成:" + update.getVersionCode());
                            //b.putString("key", "完成");
                            msg.setData(b);
                            About.this.mHandler.sendMessage(msg);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }
                }.start();
            }
        });

    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            //super.handleMessage(msg);
            Bundle nb = msg.getData();
            checkButton.setText(nb.getString("key"));

            Toast.makeText(getApplicationContext(), nb.getString("key"), Toast.LENGTH_LONG).show();
            //Toast.makeText()nb.get("key");
        }


    }
}
