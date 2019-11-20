package com.gildedrose;

import com.gildedrose.item.ShopItem;
import com.gildedrose.item.factory.ItemFactory;

import java.util.List;

class GildedRose {
    private final List<ShopItem> shopItemsList;

    GildedRose(Item[] items) {
        this.shopItemsList = ItemFactory.getAListOfShopItem(items);
    }


    void updateQuality(boolean useRefactoredSystem) {
        if (useRefactoredSystem) {
            shopItemsList.forEach(shopItem -> {
                shopItem.modifyQuality();
                shopItem.modifyAmountOfDaysToSellItem();
            });
        } else {

        }
    }
}
