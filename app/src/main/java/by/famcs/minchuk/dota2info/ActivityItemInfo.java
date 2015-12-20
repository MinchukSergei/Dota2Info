package by.famcs.minchuk.dota2info;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.ExpandableListView;

import java.util.ArrayList;

/**
 * Created by USER on 23.11.2015.
 */
public class ActivityItemInfo extends AppCompatActivity {
    ExpandableListView expandableListView;
    Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);
        item = (Item) getIntent().getSerializableExtra("Item");

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListViewItem);

        ArrayList<ArrayList<String>> groups = new ArrayList<ArrayList<String>>();

        for (Item.ItemTags tag : item.getCharacteristics()) {
            ArrayList<String> newCharacteristic = new ArrayList<>();
            newCharacteristic.add(item.getValueByTag(tag).toString());
            groups.add(newCharacteristic);
        }

        //Создаем адаптер и передаем context и список с данными
        ExpandableListViewAdapterItem adapter = new ExpandableListViewAdapterItem(getApplicationContext(), groups);
        for (Item.ItemTags tag : item.getCharacteristics()) {
            adapter.getCharNames().add(tag.getTagName());
        }
        expandableListView.setAdapter(adapter);
    }



}
