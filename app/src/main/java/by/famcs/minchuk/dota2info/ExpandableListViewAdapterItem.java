package by.famcs.minchuk.dota2info;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 23.11.2015.
 */
public class ExpandableListViewAdapterItem extends BaseExpandableListAdapter{
    private ArrayList<ArrayList<String>> mGroups;
    private Context mContext;
    private List<String> charNames;

    public ExpandableListViewAdapterItem(Context context, ArrayList<ArrayList<String>> groups) {
        mContext = context;
        mGroups = groups;
        charNames = new ArrayList<>();
    }

    public List<String> getCharNames() {
        return charNames;
    }

    public void setCharNames(List<String> charNames) {
        this.charNames = charNames;
    }

    @Override
    public int getGroupCount() {
        return mGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mGroups.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mGroups.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_group, null);
        }

        TextView textGroup = (TextView) convertView.findViewById(R.id.textViewItemGroup);
        textGroup.setText(getCharNames().get(groupPosition));

        return convertView;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_child, null);
        }

        TextView textChild = (TextView) convertView.findViewById(R.id.textViewItemChild);
        textChild.setText(mGroups.get(groupPosition).get(childPosition));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
