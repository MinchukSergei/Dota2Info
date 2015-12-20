package by.famcs.minchuk.dota2info;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 18.12.2015.
 */
public class Hero implements Serializable {
    public enum HeroRoles {
        CARRY(0),
        DISABLER(1),
        LANE_SUPPORT(2),
        INITIATOR(3),
        JUNGLER(4),
        SUPPORT(5),
        DURABLE(6),
        PUSHER(7),
        NUKER(8),
        ESCAPE(9),
        ALL(10);

        private int val;
        HeroRoles(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }
    }

    public enum HeroTags {ID, HERO_LOCALIZED_NAME, IMAGE_PATH, BIOGRAPHY, ROLES, ABILITIES,
        BASE_INTELLIGENCE, INTELLIGENCE_GAIN, BASE_AGILITY, AGILITY_GAIN, BASE_STRENGTH,
        STRENGTH_GAIN, MIN_DAMAGE, MAX_DAMAGE, MOVEMENT_SPEED, ARMOR, SIGHT_RANGE_DAY,
        SIGHT_RANGE_NIGHT, ATTACK_RANGE }

    public static String getRole(HeroRoles role) {
        switch (role) {
            case CARRY:
                return "Carry";
            case DISABLER:
                return "Disabler";
            case LANE_SUPPORT:
                return "Lane support";
            case INITIATOR:
                return "Initiator";
            case JUNGLER:
                return "Jungler";
            case SUPPORT:
                return "Support";
            case DURABLE:
                return "Durable";
            case PUSHER:
                return "Pusher";
            case NUKER:
                return "Nuker";
            case ESCAPE:
                return "Escape";
        }
        return null;
    }

    public static String getTagName(HeroTags tag) {
        switch (tag){
            case ID:
                return "Id";
            case HERO_LOCALIZED_NAME:
                return "HeroLocalizedName";
            case IMAGE_PATH:
                return "ImagePath";
            case BIOGRAPHY:
                return "Bio";
            case ROLES:
                return "HeroRoles";
            case ABILITIES:
                return "HeroAbilities";
            case BASE_INTELLIGENCE:
                return "BaseIntelligence";
            case INTELLIGENCE_GAIN:
                return "IntelligenceGain";
            case BASE_AGILITY:
                return "BaseAgility";
            case AGILITY_GAIN:
                return "AgilityGain";
            case BASE_STRENGTH:
                return "BaseStrength";
            case STRENGTH_GAIN:
                return "StrengthGain";
            case MIN_DAMAGE:
                return "MinDamage";
            case MAX_DAMAGE:
                return "MaxDamage";
            case MOVEMENT_SPEED:
                return "MovementSpeed";
            case ARMOR:
                return "Armor";
            case SIGHT_RANGE_DAY:
                return "SightRangeDay";
            case SIGHT_RANGE_NIGHT:
                return "SightRangeNigth";
            case ATTACK_RANGE:
                return "AttackRange";
        }
        return null;
    }

    public Object getValueByTag(HeroTags tag) {
        switch (tag) {
            case ROLES:
                return getRoles();
            case BASE_INTELLIGENCE:
                return getBaseIntelligence();
            case INTELLIGENCE_GAIN:
                return getIntelligenceGain();
            case BASE_AGILITY:
                return getBaseAgility();
            case AGILITY_GAIN:
                return getAgilityGain();
            case BASE_STRENGTH:
                return getBaseStrength();
            case STRENGTH_GAIN:
                return getStrengthGain();
            case MIN_DAMAGE:
                return getMinDamage();
            case MAX_DAMAGE:
                return getMaxDamage();
            case MOVEMENT_SPEED:
                return getMovementSpeed();
            case ARMOR:
                return getArmor();
            case SIGHT_RANGE_DAY:
                return getSightRangeDay();
            case SIGHT_RANGE_NIGHT:
                return getSightRangeNight();
            case ATTACK_RANGE:
                return getAttackRange();
        }
        return null;
    }

    public HeroTags[] getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(HeroTags[] characteristics) {
        this.characteristics = characteristics;
    }

    private HeroTags[] characteristics = {HeroTags.ROLES, HeroTags.BASE_INTELLIGENCE,
    HeroTags.INTELLIGENCE_GAIN, HeroTags.BASE_AGILITY, HeroTags.AGILITY_GAIN,
    HeroTags.BASE_STRENGTH, HeroTags.STRENGTH_GAIN, HeroTags.MIN_DAMAGE, HeroTags.MAX_DAMAGE,
    HeroTags.MOVEMENT_SPEED, HeroTags.ARMOR, HeroTags.SIGHT_RANGE_DAY, HeroTags.SIGHT_RANGE_NIGHT,
    HeroTags.ATTACK_RANGE };

    private int id;
    private String heroLocalizedName;
    private String imagePath;
    private String biography;
    private List<Integer> roles;
    private List<Ability> abilities;
    private int baseIntelligence;
    private double intelligenceGain;
    private int baseAgility;
    private double agilityGain;
    private int baseStrength;
    private double strengthGain;
    private int minDamage;
    private int maxDamage;
    private int movementSpeed;
    private double armor;
    private int sightRangeDay;
    private int sightRangeNight;
    private int attackRange;

    public Hero() {
        this.abilities = new ArrayList<>();
        this.roles = new ArrayList<>();
    }

    public double getAgilityGain() {
        return agilityGain;
    }

    public void setAgilityGain(double agilityGain) {
        this.agilityGain = agilityGain;
    }

    public double getArmor() {
        return armor;
    }

    public void setArmor(double armor) {
        this.armor = armor;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public void setAttackRange(int attackRange) {
        this.attackRange = attackRange;
    }

    public int getBaseAgility() {
        return baseAgility;
    }

    public void setBaseAgility(int baseAgility) {
        this.baseAgility = baseAgility;
    }

    public int getBaseIntelligence() {
        return baseIntelligence;
    }

    public void setBaseIntelligence(int baseIntelligence) {
        this.baseIntelligence = baseIntelligence;
    }

    public int getBaseStrength() {
        return baseStrength;
    }

    public void setBaseStrength(int baseStrength) {
        this.baseStrength = baseStrength;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getHeroLocalizedName() {
        return heroLocalizedName;
    }

    public void setHeroLocalizedName(String heroLocalizedName) {
        this.heroLocalizedName = heroLocalizedName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public double getIntelligenceGain() {
        return intelligenceGain;
    }

    public void setIntelligenceGain(double intelligenceGain) {
        this.intelligenceGain = intelligenceGain;
    }

    public int getMaxDamage() {
        return maxDamage;
    }

    public void setMaxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
    }

    public int getMinDamage() {
        return minDamage;
    }

    public void setMinDamage(int minDamage) {
        this.minDamage = minDamage;
    }

    public int getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(int movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public List<Integer> getRoles() {
        return roles;
    }

    public void setRoles(List<Integer> roles) {
        this.roles = roles;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

    public int getSightRangeDay() {
        return sightRangeDay;
    }

    public void setSightRangeDay(int sightRangeDay) {
        this.sightRangeDay = sightRangeDay;
    }

    public int getSightRangeNight() {
        return sightRangeNight;
    }

    public void setSightRangeNight(int sightRangeNight) {
        this.sightRangeNight = sightRangeNight;
    }

    public double getStrengthGain() {
        return strengthGain;
    }

    public void setStrengthGain(double strengthGain) {
        this.strengthGain = strengthGain;
    }
}
