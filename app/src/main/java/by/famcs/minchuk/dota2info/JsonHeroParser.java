package by.famcs.minchuk.dota2info;

import android.util.JsonReader;
import android.util.JsonToken;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import by.famcs.minchuk.dota2info.Hero.HeroTags;

/**
 * Created by USER on 18.12.2015.
 */
public class JsonHeroParser {
    private JsonReader jsonReader;
    private List<Hero> heroesList;

    public JsonHeroParser(InputStreamReader reader) {
        this.heroesList = new ArrayList<>();
        jsonReader = new JsonReader(reader);
    }

    public void parseHeroList() throws IOException {
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            heroesList.add(parseHero());
        }
        jsonReader.endArray();
    }

    private Hero parseHero() throws IOException {
        Hero hero = new Hero();
        jsonReader.beginObject();

        while (jsonReader.hasNext()) {
            String currentTag = jsonReader.nextName();
            if (currentTag.equals(Hero.getTagName(HeroTags.ID))) {
                hero.setId(jsonReader.nextInt());
            } else if (currentTag.equals(Hero.getTagName(HeroTags.HERO_LOCALIZED_NAME))) {
                hero.setHeroLocalizedName(jsonReader.nextString());
            } else if (currentTag.equals(Hero.getTagName(HeroTags.IMAGE_PATH))) {
                hero.setImagePath(jsonReader.nextString());
            } else if (currentTag.equals(Hero.getTagName(HeroTags.BIOGRAPHY))) {
                hero.setBiography(jsonReader.nextString());
            } else if (currentTag.equals(Hero.getTagName(HeroTags.ROLES))) {
                hero.setRoles(parseRoles());
            } else if (currentTag.equals(Hero.getTagName(HeroTags.ABILITIES))) {
                hero.setAbilities(parseAbilities());
            } else if (currentTag.equals(Hero.getTagName(HeroTags.BASE_INTELLIGENCE))) {
                hero.setBaseIntelligence(jsonReader.nextInt());
            } else if (currentTag.equals(Hero.getTagName(HeroTags.INTELLIGENCE_GAIN))) {
                hero.setIntelligenceGain(jsonReader.nextDouble());
            } else if (currentTag.equals(Hero.getTagName(HeroTags.BASE_AGILITY))) {
                hero.setBaseAgility(jsonReader.nextInt());
            } else if (currentTag.equals(Hero.getTagName(HeroTags.AGILITY_GAIN))) {
                hero.setAgilityGain(jsonReader.nextDouble());
            } else if (currentTag.equals(Hero.getTagName(HeroTags.BASE_STRENGTH))) {
                hero.setBaseStrength(jsonReader.nextInt());
            } else if (currentTag.equals(Hero.getTagName(HeroTags.STRENGTH_GAIN))) {
                hero.setStrengthGain(jsonReader.nextDouble());
            } else if (currentTag.equals(Hero.getTagName(HeroTags.STRENGTH_GAIN))) {
                hero.setStrengthGain(jsonReader.nextDouble());
            } else if (currentTag.equals(Hero.getTagName(HeroTags.MIN_DAMAGE))) {
                hero.setMinDamage(jsonReader.nextInt());
            } else if (currentTag.equals(Hero.getTagName(HeroTags.MAX_DAMAGE))) {
                hero.setMaxDamage(jsonReader.nextInt());
            } else if (currentTag.equals(Hero.getTagName(HeroTags.MOVEMENT_SPEED))) {
                hero.setMovementSpeed(jsonReader.nextInt());
            } else if (currentTag.equals(Hero.getTagName(HeroTags.ARMOR))) {
                hero.setArmor(jsonReader.nextDouble());
            } else if (currentTag.equals(Hero.getTagName(HeroTags.SIGHT_RANGE_DAY))) {
                hero.setSightRangeDay(jsonReader.nextInt());
            } else if (currentTag.equals(Hero.getTagName(HeroTags.SIGHT_RANGE_NIGHT))) {
                hero.setSightRangeNight(jsonReader.nextInt());
            } else if (currentTag.equals(Hero.getTagName(HeroTags.ATTACK_RANGE))) {
                hero.setAttackRange(jsonReader.nextInt());
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return hero;
    }

    private List<Integer> parseRoles() throws IOException {
        List<Integer> roles = new ArrayList<>();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            roles.add(jsonReader.nextInt());
        }
        jsonReader.endArray();
        return roles;
    }

    private List<Ability> parseAbilities() throws IOException {
        List<Ability> abilities = new ArrayList<>();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            jsonReader.beginObject();
            Ability ability = new Ability();
            while (jsonReader.hasNext()) {
                String currentTag = jsonReader.nextName();
                if (currentTag.equals(Ability.getTagName(Ability.AbilityTags.NAME))) {
                    ability.setName(jsonReader.nextString());
                } else if (currentTag.equals(Ability.getTagName(Ability.AbilityTags.DESCRIPTION))) {
                    ability.setDescription(jsonReader.nextString());
                } else if (currentTag.equals(Ability.getTagName(Ability.AbilityTags.MANACOST)) &&
                        jsonReader.peek() != JsonToken.NULL) {
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        ability.getManacost().add(jsonReader.nextInt());
                    }
                    jsonReader.endArray();
                } else if (currentTag.equals(Ability.getTagName(Ability.AbilityTags.COOLDOWNS)) &&
                        jsonReader.peek() != JsonToken.NULL) {
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        ability.getCooldowns().add(jsonReader.nextInt());
                    }
                    jsonReader.endArray();
                } else {
                    jsonReader.skipValue();
                }
            }
            jsonReader.endObject();
            abilities.add(ability);
        }
        jsonReader.endArray();
        return abilities;
    }

    public List<Hero> getHeroesList() {
        return heroesList;
    }

    public void setHeroesList(List<Hero> heroesList) {
        this.heroesList = heroesList;
    }


}
