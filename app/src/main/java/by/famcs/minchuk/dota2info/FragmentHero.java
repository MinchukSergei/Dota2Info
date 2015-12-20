package by.famcs.minchuk.dota2info;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FragmentHero extends Fragment {
    private Hero hero;
    private TextView heroName;
    private ListView listView;
    private ImageView heroImage;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_hero, container, false);

        listView = (ListView) v.findViewById(R.id.listViewOne);
        heroName = (TextView) v.findViewById(R.id.textViewHeroName);
        heroImage = (ImageView) v.findViewById(R.id.imageViewHeroImage);


        hero = (Hero) getArguments().getSerializable("Hero");
        int id = v.getResources().getIdentifier(hero.getImagePath() + "_full", "drawable", v.getContext().getPackageName());
        heroImage.setBackgroundResource(id);
        heroName.setText(hero.getHeroLocalizedName());
        listView.setAdapter(getAdapter());
        return v;
    }

    private SimpleAdapter getAdapter() {
        List<Map<String, String>> data = new ArrayList<>();
        for (Hero.HeroTags tag : hero.getCharacteristics()) {
            Map<String, String> datum = new HashMap<>(2);
            datum.put("title", Hero.getTagName(tag));
            String roles = "";
            if (Hero.getTagName(tag).equals(Hero.getTagName(Hero.HeroTags.ROLES))) {
                for (Integer integer : hero.getRoles()) {
                    roles += Hero.getRole(Hero.HeroRoles.values()[integer]).toString();
                    roles += '\n';
                }
                datum.put("date", roles);
            } else {
                datum.put("date", hero.getValueByTag(tag).toString());
            }
            data.add(datum);
        }
        SimpleAdapter adapter = new SimpleAdapter(this.getContext(), data,
                android.R.layout.simple_list_item_2,
                new String[] {"title", "date"},
                new int[] {android.R.id.text1,
                        android.R.id.text2}) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = (TextView) view.findViewById(android.R.id.text1);
                TextView textView1 = (TextView) view.findViewById(android.R.id.text2);
                textView1.setTextColor(getResources().getColor(R.color.dark_color));
                textView.setTextColor(getResources().getColor(R.color.dark_color));
                return view;
            }
        };
        return adapter;
    }
}