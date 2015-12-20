package by.famcs.minchuk.dota2info;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;


public class FragmentAbilities extends Fragment {
    private Hero hero;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_abilities, container,false);
        hero = (Hero) getArguments().getSerializable("Hero");

        ExpandableListView listView = (ExpandableListView)v.findViewById(R.id.expandableListView);

        ArrayList<ArrayList<String>> groups = new ArrayList<>();
        for (Ability ability : hero.getAbilities()) {
            ArrayList<String> newAbility = new ArrayList<>();
            newAbility.add(Ability.getTagName(Ability.AbilityTags.DESCRIPTION) + ":\n"
                    + ability.getDescription());
            newAbility.add(Ability.getTagName(Ability.AbilityTags.MANACOST) + ":\n"
                    + ability.getManacost().toString());
            newAbility.add(Ability.getTagName(Ability.AbilityTags.COOLDOWNS) + ":\n"
                    + ability.getCooldowns().toString());
            groups.add(newAbility);
        }

        //Создаем адаптер и передаем context и список с данными
        ExpListAdapter adapter = new ExpListAdapter(this.getContext(), groups);
        for (Ability ability : hero.getAbilities()) {
            adapter.getAbilityNames().add(ability.getName());
        }
        listView.setAdapter(adapter);
        return v;
    }
}