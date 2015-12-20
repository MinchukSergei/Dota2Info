package by.famcs.minchuk.dota2info;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ActivityHeroList extends AppCompatActivity {
    ListView heroList;
    JsonHeroParser parser;
    List<Hero> allHeroes;
    List<Hero> heroes;
    List<String> heroNames;
    List<String> heroPaths;
    AdapterItem adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_herolist);
        heroList = (ListView) findViewById(R.id.listViewHero);

        heroNames = new ArrayList<>();
        heroPaths = new ArrayList<>();

        try {
            parser = new JsonHeroParser(new InputStreamReader(getApplication().getAssets().open("heroes.json")));
            parser.parseHeroList();
            heroes = parser.getHeroesList();
            allHeroes = heroes;
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Hero hero : heroes) {
            heroNames.add(hero.getHeroLocalizedName());
            heroPaths.add(hero.getImagePath());
        }

        adapter = new AdapterItem (this.getApplicationContext(), heroNames.toArray(new String[0]),
                heroPaths.toArray(new String[0]));
        adapter.setSuffix("_full");
        heroList.setAdapter(adapter);

        heroList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ActivityHeroList.this, ActivityHeroInfo.class);
                intent.putExtra("Hero", heroes.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.hero_list_menu, menu);

        MenuItem menuItemCarry = menu.findItem(R.id.itemCarry);
        menuItemCarry.setOnMenuItemClickListener(itemClickListener(Hero.HeroRoles.CARRY));
        MenuItem menuItemDisabler = menu.findItem(R.id.itemDisabler);
        menuItemDisabler.setOnMenuItemClickListener(itemClickListener(Hero.HeroRoles.DISABLER));
        MenuItem menuItemInitiator = menu.findItem(R.id.itemInitiator);
        menuItemInitiator.setOnMenuItemClickListener(itemClickListener(Hero.HeroRoles.INITIATOR));
        MenuItem menuItemJungler = menu.findItem(R.id.itemJungler);
        menuItemJungler.setOnMenuItemClickListener(itemClickListener(Hero.HeroRoles.JUNGLER));
        MenuItem menuItemSupport = menu.findItem(R.id.itemSupport);
        menuItemSupport.setOnMenuItemClickListener(itemClickListener(Hero.HeroRoles.SUPPORT));
        MenuItem menuItemDurable = menu.findItem(R.id.itemDurable);
        menuItemDurable.setOnMenuItemClickListener(itemClickListener(Hero.HeroRoles.DURABLE));
        MenuItem menuItemPusher = menu.findItem(R.id.itemPusher);
        menuItemPusher.setOnMenuItemClickListener(itemClickListener(Hero.HeroRoles.PUSHER));
        MenuItem menuItemNuker = menu.findItem(R.id.itemNuker);
        menuItemNuker.setOnMenuItemClickListener(itemClickListener(Hero.HeroRoles.NUKER));
        MenuItem menuItemEscape = menu.findItem(R.id.itemEscape);
        menuItemEscape.setOnMenuItemClickListener(itemClickListener(Hero.HeroRoles.ESCAPE));
        MenuItem menuItemAll = menu.findItem(R.id.itemAll);
        menuItemAll.setOnMenuItemClickListener(itemClickListener(Hero.HeroRoles.ALL));


        return super.onCreateOptionsMenu(menu);
    }

    private MenuItem.OnMenuItemClickListener itemClickListener(final Hero.HeroRoles role) {
        MenuItem.OnMenuItemClickListener itemClickListener = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                List<Hero> heroListRoles = getRoleList(role);
                List<String> heroNames = new ArrayList<>();
                List<String> heroPaths = new ArrayList<>();
                for (Hero hero : heroListRoles) {
                    heroNames.add(hero.getHeroLocalizedName());
                    heroPaths.add(hero.getImagePath());
                }
                heroes = heroListRoles;
                AdapterItem adapter = new AdapterItem(getApplicationContext(),
                        heroNames.toArray(new String[0]), heroPaths.toArray(new String[0]));
                adapter.setSuffix("_full");
                heroList.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                return true;
            }
        };
        return itemClickListener;
    }

    public List<Hero> getRoleList(Hero.HeroRoles role) {
        List<Hero> heroList = new ArrayList<>();
        if (role.equals(Hero.HeroRoles.ALL)) {
            for (Hero hero: allHeroes) {
                heroList.add(hero);
            }
            return heroList;
        }

        for (Hero hero : allHeroes) {
            if (hero.getRoles().contains(role.getVal())) {
                heroList.add(hero);
            }
        }

        return heroList;
    }
}
