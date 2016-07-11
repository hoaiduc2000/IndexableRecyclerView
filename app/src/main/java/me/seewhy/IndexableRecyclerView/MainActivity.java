package me.seewhy.IndexableRecyclerView;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private IndexableRecyclerView mIndexableRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initData();
    }

    private void initViews() {
        mIndexableRecyclerView = (IndexableRecyclerView) findViewById(R.id.indexable_recyclerview);
    }

    private void initData() {

        List<ItemModel> models = new ArrayList<>();
        models.add(new ItemModel("A", R.mipmap.grkj));
        models.add(new ItemModel("A", R.mipmap.hgl));
        models.add(new ItemModel("C", R.mipmap.hh));
        models.add(new ItemModel("D", R.mipmap.hd));
        models.add(new ItemModel("E", R.mipmap.hr));
        models.add(new ItemModel("F", R.mipmap.jm));
        models.add(new ItemModel("G", R.mipmap.wz));
        models.add(new ItemModel("H", R.mipmap.dx));
        models.add(new ItemModel("I", R.mipmap.zw));
        models.add(new ItemModel("K", R.mipmap.rs));
        models.add(new ItemModel("L", R.mipmap.ht));
        models.add(new ItemModel("M", R.mipmap.yq));
        models.add(new ItemModel("N", R.mipmap.lt));
        models.add(new ItemModel("Z", R.mipmap.nh));
        models.add(new ItemModel("360", R.mipmap.nh));
        models.add(new ItemModel("-1", R.mipmap.nh));

        IndexableRecyclerViewAdapter indexableRecyclerViewAdapter = new IndexableRecyclerViewAdapter(this, models);
        mIndexableRecyclerView.setAdapter(indexableRecyclerViewAdapter);

    }

}