package tv.icntv.app.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import tv.icntv.app.R;
import tv.icntv.app.adapter.ListViewNewsAdapter;
import tv.icntv.app.bean.News;
import tv.icntv.app.widget.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guanshiliang on 13-6-7.
 */
public class Index extends BaseActivity {

    private PullToRefreshListView lvNews;

    private ListViewNewsAdapter lvNewsAdapter;

    private List<News> lvNewsData = new ArrayList<News>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);

        initNewsListView();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float x = event.getRawX();
        float y = event.getRawY();

        //showMessage("x="+x+",y="+y);
        return true;
    }

    private void initNewsListView() {
        //lvNews = (PullToRefreshListView)findViewById(R.id.frame_listview_news);
        lvNewsAdapter = new ListViewNewsAdapter(this, lvNewsData,
                R.layout.news_listitem);
        showMessage("initNewsListView");

        ImageView setting = (ImageView)findViewById(R.id.main_footbar_setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Index.this,About.class);
                startActivity(intent);
                //finish();
            }
        });

    }
    private void showMessage(String s) {

        Toast toast = Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT);
        toast.show();
    }
}
