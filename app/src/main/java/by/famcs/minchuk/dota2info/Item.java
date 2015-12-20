package by.famcs.minchuk.dota2info;

import java.io.Serializable;

/**
 * Created by USER on 19.12.2015.
 */
public class Item implements Serializable {
    private int id;
    private String itemName;
    private String image;
    private String quality;
    private String cost;
    private String description;
    private String manacost;
    private String cooldown;
    private String lore;
    private boolean created;

    public ItemTags[] getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(ItemTags[] characteristics) {
        this.characteristics = characteristics;
    }

    public String getCooldown() {
        return cooldown;
    }

    public void setCooldown(String cooldown) {
        this.cooldown = cooldown;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public boolean isCreated() {
        return created;
    }

    public void setCreated(boolean created) {
        this.created = created;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getLore() {
        return lore;
    }

    public void setLore(String lore) {
        this.lore = lore;
    }

    public String getManacost() {
        return manacost;
    }

    public void setManacost(String manacost) {
        this.manacost = manacost;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public ItemTags [] characteristics = {ItemTags.QUALITY, ItemTags.COST, ItemTags.DESCRIPTION,
    ItemTags.MANACOST, ItemTags.COOLDOWN, ItemTags.LORE, ItemTags.CREATED};

    public Object getValueByTag(ItemTags tag) {
        switch (tag) {
            case QUALITY:
                return getQuality();
            case COST:
                return getCost();
            case DESCRIPTION:
                return getDescription();
            case MANACOST:
                return getManacost();
            case COOLDOWN:
                return getCooldown();
            case LORE:
                return getLore();
            case CREATED:
                return isCreated();
        }
        return null;
    }

    public enum ItemTags {
        ID("Id"),
        ITEM_NAME("ItemName"),
        IMAGE("Image"),
        QUALITY("Quality"),
        COST("Cost"),
        DESCRIPTION("Description"),
        MANACOST("Manacost"),
        COOLDOWN("Cooldown"),
        LORE("Lore"),
        CREATED("Created");

        private String tagName;
        ItemTags(String name) { this.tagName = name; }

        public String getTagName() {
            return tagName;
        }

        public void setTagName(String tagName) {
            this.tagName = tagName;
        }
    }
}
