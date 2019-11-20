package com.gildedrose.item;

import com.gildedrose.Item;

public class AgedBrie extends ShopItem {


    public AgedBrie(Item item, boolean isConjured) {
        super(item, isConjured);
    }

    @Override
    public void modifyQuality() {
        super.plusQuality(1);
    }
}
