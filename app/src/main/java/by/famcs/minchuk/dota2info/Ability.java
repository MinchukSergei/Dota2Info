package by.famcs.minchuk.dota2info;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 18.12.2015.
 */
public class Ability implements Serializable {
    public enum AbilityTags {
        NAME, DESCRIPTION, MANACOST, COOLDOWNS
    }

    public static String getTagName(AbilityTags tag) {
        switch (tag) {
            case NAME:
                return "Name";
            case DESCRIPTION:
                return "Description";
            case MANACOST:
                return "ManaCost";
            case COOLDOWNS:
                return "Cooldowns";
        }
        return null;
    }

    private String name;
    private String description;
    private List<Integer> manacost;
    private List<Integer> cooldowns;

    public Ability() {
        this.manacost = new ArrayList<>();
        this.cooldowns = new ArrayList<>();
    }

    public List<Integer> getCooldowns() {
        return cooldowns;
    }

    public void setCooldowns(List<Integer> cooldowns) {
        this.cooldowns = cooldowns;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getManacost() {
        return manacost;
    }

    public void setManacost(List<Integer> manacost) {
        this.manacost = manacost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
