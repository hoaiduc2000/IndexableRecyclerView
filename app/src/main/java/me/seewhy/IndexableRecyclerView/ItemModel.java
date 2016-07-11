package me.seewhy.IndexableRecyclerView;

/**
 * Created by BG204119 on 2015/12/30.
 */
public class ItemModel {
    String name;
    String url;
    int resourceId;

    public ItemModel(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public ItemModel(String name, int resourceId) {
        this.name = name;
        this.resourceId = resourceId;
    }
}
