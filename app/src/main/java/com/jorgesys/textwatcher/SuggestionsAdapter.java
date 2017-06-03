package com.jorgesys.textwatcher;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SuggestionsAdapter extends BaseAdapter implements Filterable {
    private Context context;
    private List<String> items;
    private List<String> filteredItems;
    private ItemFilter mFilter = new ItemFilter();
    private boolean isVideo;

    public SuggestionsAdapter(Context context, List<String> items) {
        this.context = context;
        this.items = items;
        this.filteredItems = items;
    }

    @Override
    public int getCount() {
        return filteredItems != null ? filteredItems.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return filteredItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.item_suggestions, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvNumber = (TextView) convertView.findViewById(R.id.txtNumber);
            viewHolder.tvDescription = (TextView) convertView.findViewById(R.id.txtDescription);

            if(isVideo) {
                viewHolder.tvNumber.setTextColor(ContextCompat.getColor(context, R.color.green));
                viewHolder.tvDescription.setTextColor(ContextCompat.getColor(context, R.color.green));
            }

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String item = filteredItems.get(position);
        if (item != null || viewHolder != null) {
            viewHolder.tvNumber.setText(item);
            viewHolder.tvDescription.setText(String.valueOf(getItemPosition(item) + 1));
        }
        return convertView;
    }

    private int getItemPosition(String item) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).equals(item)) {
                return i;
            }
        }
        return -1;
    }

    private static class ViewHolder {
        TextView tvNumber;
        TextView tvDescription;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String filterString = constraint.toString().toLowerCase();
            FilterResults results = new FilterResults();

            int count = items.size();
            final List<String> tempItems = new ArrayList<>(count);

            for (int i = 0; i < count; i++) {
                if (items.get(i).toLowerCase().contains(filterString)) {
                    tempItems.add(items.get(i));
                }
            }

            results.values = tempItems;
            results.count = tempItems.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredItems = (ArrayList<String>) results.values;
            notifyDataSetChanged();
        }
    }

    public Filter getFilter() {
        return mFilter;
    }

    public void setTypeVideo(boolean value) {
        isVideo = value;
    }
}
