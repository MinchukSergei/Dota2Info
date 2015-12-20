package by.famcs.minchuk.dota2info;

import android.util.JsonReader;
import android.util.JsonToken;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 19.12.2015.
 */
public class JsonItemParser {
    private JsonReader jsonReader;
    private List<Item> itemList;

    public JsonItemParser(InputStreamReader reader) {
        this.jsonReader = new JsonReader(reader);
        this.itemList = new ArrayList<>();
    }

    public void parseItemList() throws IOException {
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            itemList.add(parseItem());
        }
        jsonReader.endArray();
    }

    private Item parseItem() throws IOException {
        Item item = new Item();
        jsonReader.beginObject();

        while (jsonReader.hasNext()) {
            String tag = jsonReader.nextName();

            if (tag.equals(Item.ItemTags.ID.getTagName())) {
                item.setId(jsonReader.nextInt());
            } else if (tag.equals(Item.ItemTags.ITEM_NAME.getTagName())) {
                item.setItemName(jsonReader.nextString());
            } else if (tag.equals(Item.ItemTags.IMAGE.getTagName())) {
                item.setImage(jsonReader.nextString());
            } else if (tag.equals(Item.ItemTags.QUALITY.getTagName()) && jsonReader.peek() != JsonToken.NULL) {
                item.setQuality(jsonReader.nextString());
            } else if (tag.equals(Item.ItemTags.COST.getTagName()) && jsonReader.peek() != JsonToken.NULL) {
                item.setCost(jsonReader.nextString());
            } else if (tag.equals(Item.ItemTags.DESCRIPTION.getTagName()) && jsonReader.peek() != JsonToken.NULL) {
                item.setDescription(jsonReader.nextString());
            } else if (tag.equals(Item.ItemTags.MANACOST.getTagName()) && jsonReader.peek() != JsonToken.NULL) {
                item.setManacost(jsonReader.nextString());
            } else if (tag.equals(Item.ItemTags.COOLDOWN.getTagName()) && jsonReader.peek() != JsonToken.NULL) {
                item.setCooldown(jsonReader.nextString());
            } else if (tag.equals(Item.ItemTags.LORE.getTagName()) && jsonReader.peek() != JsonToken.NULL) {
                item.setLore(jsonReader.nextString());
            } else if (tag.equals(Item.ItemTags.CREATED.getTagName()) && jsonReader.peek() != JsonToken.NULL) {
                item.setCreated(jsonReader.nextBoolean());
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return item;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }
}
