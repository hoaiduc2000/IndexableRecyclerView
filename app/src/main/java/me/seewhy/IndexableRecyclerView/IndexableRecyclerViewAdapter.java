package me.seewhy.IndexableRecyclerView;

import android.content.ClipData;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

public class IndexableRecyclerViewAdapter extends RecyclerView.Adapter implements SectionedRecyclerAdapter.SectionedRecyclerDelegate {
    public static final String TAG = "IndexableRecyclerViewAdapter";
    public static final int TYPE_BANNER = 0;
    private final LayoutInflater mLayoutInflater;

    private List<ItemModel> mItemModels;
    private int mLineNumber = 0;
    LinkedHashMap<String, List<ItemModel>> mSectionedHashMap;

    public IndexableRecyclerViewAdapter(Context context, List<ItemModel> models) {
        mItemModels = models;
        mLayoutInflater = LayoutInflater.from(context);
        init();
    }

    private void init() {
        mSectionedHashMap = new LinkedHashMap<>();
        mSections.clear();
        sort();
        for (int i = 0; i < mItemModels.size(); i++) {
            String ch = mItemModels.get(i).name;
            if (ch == null || ch.isEmpty() || !Character.isUpperCase(ch.codePointAt(0)))
                ch = "#";
            String c = String.valueOf(ch.charAt(0));
            List<ItemModel> itemModels = mSectionedHashMap.get(c);
            if (itemModels == null) {
                itemModels = new ArrayList<>();
            }
            itemModels.add(mItemModels.get(i));
            mSectionedHashMap.put(String.valueOf(ch.charAt(0)), itemModels);
        }
        calculateSectionPosition();
    }

    public void sort() {
        Collections.sort(mItemModels, new Comparator<ItemModel>() {
            @Override
            public int compare(ItemModel lhs, ItemModel rhs) {
                return String.valueOf(lhs.name.charAt(0)).compareTo(String.valueOf(rhs.name.charAt(0)));
            }
        });
        List<ItemModel> modelList = new ArrayList<>();
        for (int i = 0; i < mItemModels.size(); i++) {
            String ch = mItemModels.get(i).name;
            if (ch == null || ch.isEmpty() || !Character.isUpperCase(ch.codePointAt(0)))
                modelList.add(mItemModels.get(i));
        }
        mItemModels.addAll(modelList);
        for (int i = 0; i < modelList.size(); i++)
            mItemModels.remove(0);


    }

    private void calculateSectionPosition() {
        Set<String> keySet = mSectionedHashMap.keySet();
        String strings[] = new String[keySet.size()];
        ArrayList<String> mString = new ArrayList<>();
        keySet.toArray(strings);
        Arrays.sort(strings);
        for (int i = 1; i < strings.length; i++)
            mString.add(strings[i]);
        mString.add(strings[0]);
        int pos = 0;
        for (int i = 0; i < mString.size(); i++) {
            SectionedRecyclerAdapter.Section section = new SectionedRecyclerAdapter.Section(pos, mString.get(i));
            mSections.add(section);
            pos += mSectionedHashMap.get(mString.get(i)).size();
        }

        mLineNumber = pos;
    }

    @Override
    public List<SectionedRecyclerAdapter.Section> getSections() {
        return mSections;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BannerViewHolder(mLayoutInflater.inflate(R.layout.recycler_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((BannerViewHolder) holder).mTextView.setText(mItemModels.get(position).name);
    }

    @Override
    public int getItemCount() {
        return mLineNumber;
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_BANNER;
    }

    public static class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextView;

        public BannerViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.image_view_item);
            mTextView = (TextView) itemView.findViewById(R.id.text_view_contact);
        }
    }

    public ItemModel removeItem(int position) {
        final ItemModel model = mItemModels.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, ItemModel model) {
        mItemModels.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final ItemModel model = mItemModels.remove(fromPosition);
        mItemModels.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }

    public void animateTo(List<ItemModel> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(List<ItemModel> newModels) {
        for (int i = mItemModels.size() - 1; i >= 0; i--) {
            final ItemModel model = mItemModels.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<ItemModel> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final ItemModel model = newModels.get(i);
            if (!mItemModels.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<ItemModel> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final ItemModel model = newModels.get(toPosition);
            final int fromPosition = mItemModels.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }
}


