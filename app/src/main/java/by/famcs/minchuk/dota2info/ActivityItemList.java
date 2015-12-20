package by.famcs.minchuk.dota2info;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by USER on 22.11.2015.
 */
public class ActivityItemList extends AppCompatActivity {
    ListView listViewItem;
    List<Item> items;
    List<String> itemNames;
    List<String> itemPaths;
    JsonItemParser jsonItemParser;
    AdapterItem adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemlist);
        items = new ArrayList<>();
        itemNames = new ArrayList<>();
        itemPaths = new ArrayList<>();

        try {
            jsonItemParser = new JsonItemParser(new InputStreamReader(getApplication().getAssets().open("items.json")));
            jsonItemParser.parseItemList();
            items = jsonItemParser.getItemList();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Item item : items) {
            itemNames.add(item.getItemName());
            itemPaths.add(item.getImage());
        }

        listViewItem = (ListView) findViewById(R.id.listViewItem);
        adapter = new AdapterItem (this.getApplicationContext(),
                itemNames.toArray(new String[0]), itemPaths.toArray(new String[0]));
        adapter.setSuffix("");
        listViewItem.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listViewItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ActivityItemList.this, ActivityItemInfo.class);
                intent.putExtra("Item", items.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.item_list_menu, menu);
        MenuItem itemMin = menu.findItem(R.id.itemSortMin);
        MenuItem itemMax = menu.findItem(R.id.itemSortMax);
        itemMin.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Collections.sort(items, new Comparator<Item>() {
                    @Override
                    public int compare(Item lhs, Item rhs) {
                        if (Integer.parseInt(lhs.getCost()) > Integer.parseInt(rhs.getCost()))
                            return 1;
                        else if (Integer.parseInt(lhs.getCost()) < Integer.parseInt(rhs.getCost()))
                            return -1;
                        else
                            return 0;
                    }
                });
                itemNames.clear();
                itemPaths.clear();

                for (Item item1 : items) {
                    itemNames.add(item1.getItemName());
                    itemPaths.add(item1.getImage());
                }

                adapter.setStrings(itemNames.toArray(new String[0]), itemPaths.toArray(new String[0]));
                adapter.notifyDataSetChanged();

                return true;
            }
        });

        itemMax.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Collections.sort(items, new Comparator<Item>() {
                    @Override
                    public int compare(Item lhs, Item rhs) {
                        if (Integer.parseInt(lhs.getCost()) < Integer.parseInt(rhs.getCost()))
                            return 1;
                        else if (Integer.parseInt(lhs.getCost()) > Integer.parseInt(rhs.getCost()))
                            return -1;
                        else
                            return 0;
                    }
                });
                itemNames.clear();
                itemPaths.clear();

                for (Item item1 : items) {
                    itemNames.add(item1.getItemName());
                    itemPaths.add(item1.getImage());
                }

                adapter.setStrings(itemNames.toArray(new String[0]), itemPaths.toArray(new String[0]));
                adapter.notifyDataSetChanged();

                return true;
            }
        });
        return true;
    }
}
