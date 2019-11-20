package com.gildedrose.item.factory;

import com.gildedrose.Item;
import com.gildedrose.item.*;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ItemFactory {

    private static ShopItem getShopItem(Item item) {
        if (StringUtils.containsIgnoreCase(item.name, "Backstage passes")) {
            final boolean conjured = isConjured(item, "conjured");
            return new BackstagePass(item, conjured);

        } else if (item.name.equalsIgnoreCase("Sulfuras, Hand of Ragnaros")) {
            final boolean conjured = isConjured(item, "conjured");
            return new Sulfuras(item, conjured);

        } else if (item.name.equalsIgnoreCase("Aged Brie")) {
            final boolean conjured = isConjured(item, "conjured");
            return new AgedBrie(item, conjured);

        } else {
            final boolean conjured = isConjured(item, "conjured");
            return new NormalItem(item, conjured);

        }
    }

    private static boolean isConjured(Item item, String conjured) {
        return StringUtils.containsIgnoreCase(item.name, conjured);
    }


    public static List<ShopItem> getAListOfShopItem(Item[] items) {
        final List<ShopItem> shopItemsList = new ArrayList<>();

        for (final Item item : items) {
            shopItemsList.add(getShopItem(item));
        }
        return shopItemsList;
    }
}
