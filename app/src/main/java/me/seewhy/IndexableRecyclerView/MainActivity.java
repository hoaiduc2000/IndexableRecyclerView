package me.seewhy.IndexableRecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements SearchView.OnQueryTextListener {

    private IndexableRecyclerView mIndexableRecyclerView;
    private RecyclerView mRecyclerView;
    private SearchView mSearchView;
    private List<ItemModel> models;
    private IndexableRecyclerViewAdapter indexableRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initData();
    }

    private void initViews() {
        mIndexableRecyclerView = (IndexableRecyclerView) findViewById(R.id.indexable_recyclerview);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mSearchView = (SearchView) findViewById(R.id.search_view);
    }

    private void initData() {

        models = new ArrayList<>();
        models.add(new ItemModel("AAAA", R.mipmap.grkj));
        models.add(new ItemModel("BBBB", R.mipmap.hgl));
        models.add(new ItemModel("CCCC", R.mipmap.hh));
        models.add(new ItemModel("DDDD", R.mipmap.hd));
        models.add(new ItemModel("EEEE", R.mipmap.hr));
        models.add(new ItemModel("FFFF", R.mipmap.jm));
        models.add(new ItemModel("GGGG", R.mipmap.wz));
        models.add(new ItemModel("BCDE", R.mipmap.wz));
        models.add(new ItemModel("BACD", R.mipmap.wz));
        models.add(new ItemModel("GGGG", R.mipmap.wz));
        models.add(new ItemModel("HHHH", R.mipmap.dx));
        models.add(new ItemModel("AAAA", R.mipmap.zw));
        models.add(new ItemModel("KKKK", R.mipmap.rs));
        models.add(new ItemModel("LLLL", R.mipmap.ht));
        models.add(new ItemModel("13", R.mipmap.nh));
        models.add(new ItemModel("MMMM", R.mipmap.yq));
        models.add(new ItemModel("NNNN", R.mipmap.lt));
        models.add(new ItemModel("QQAT", R.mipmap.lt));
        models.add(new ItemModel("PPPR", R.mipmap.lt));
        models.add(new ItemModel("OOOO", R.mipmap.nh));
        models.add(new ItemModel("14", R.mipmap.nh));

        models.add(new ItemModel("BACD", R.mipmap.wz));
        models.add(new ItemModel("GGGG", R.mipmap.wz));
        models.add(new ItemModel("HHHH", R.mipmap.dx));
        models.add(new ItemModel("AAAA", R.mipmap.zw));
        models.add(new ItemModel("KKKK", R.mipmap.rs));
        models.add(new ItemModel("LLLL", R.mipmap.ht));
        models.add(new ItemModel("13", R.mipmap.nh));
        models.add(new ItemModel("MMMM", R.mipmap.yq));
        models.add(new ItemModel("NNNN", R.mipmap.lt));
        models.add(new ItemModel("QQAT", R.mipmap.lt));
        models.add(new ItemModel("PPPR", R.mipmap.lt));
        models.add(new ItemModel("OOOO", R.mipmap.nh));
        models.add(new ItemModel("14", R.mipmap.nh));

        models.add(new ItemModel("BACD", R.mipmap.wz));
        models.add(new ItemModel("GGGG", R.mipmap.wz));
        models.add(new ItemModel("HHHH", R.mipmap.dx));
        models.add(new ItemModel("AAAA", R.mipmap.zw));
        models.add(new ItemModel("KKKK", R.mipmap.rs));
        models.add(new ItemModel("ZZZZ", R.mipmap.ht));
        models.add(new ItemModel("13", R.mipmap.nh));
        models.add(new ItemModel("MMMM", R.mipmap.yq));
        models.add(new ItemModel("NNNN", R.mipmap.lt));
        models.add(new ItemModel("QQAT", R.mipmap.lt));
        models.add(new ItemModel("PPPR", R.mipmap.lt));
        models.add(new ItemModel("OOOO", R.mipmap.nh));
        models.add(new ItemModel("14", R.mipmap.nh));

        models.add(new ItemModel("ZZZ", R.mipmap.nh));
        models.add(new ItemModel("MMMM", R.mipmap.yq));
        models.add(new ItemModel("NNNN", R.mipmap.lt));
        models.add(new ItemModel("QQAT", R.mipmap.lt));

        mSearchView.setOnQueryTextListener(this);
        mRecyclerView.addItemDecoration(new Divider(this));
        indexableRecyclerViewAdapter = new IndexableRecyclerViewAdapter(this, models);
        mIndexableRecyclerView.setAdapter(indexableRecyclerViewAdapter);

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        List<ItemModel> mFilterList = filter(models, newText);
        indexableRecyclerViewAdapter.animateTo(mFilterList);
        mRecyclerView.scrollToPosition(0);
        return false;
    }

    public List<ItemModel> filter(List<ItemModel> mList, String query) {
        query = query.toLowerCase();

        List<ItemModel> mFilterList = new ArrayList<>();
        for (ItemModel item : mList) {
            String text = item.name.toLowerCase();
            if (text.contains(query)) {
                mFilterList.add(item);
            }
        }
        return mFilterList;
    }
}