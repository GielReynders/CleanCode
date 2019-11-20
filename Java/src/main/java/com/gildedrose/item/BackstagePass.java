package com.gildedrose.item;

import com.gildedrose.Item;

public class BackstagePass extends ShopItem {


    public BackstagePass(Item item, boolean isConjured) {
        super(item, isConjured);
    }

    @Override
    public void modifyQuality() {
        if (super.getItemSellDueTime() <= 5) {
            plusQuality(3);
        } else if (super.getItemSellDueTime() <= 10) {
            plusQuality(2);
        } else if (super.getItemSellDueTime() > 10) {
            plusQuality(1);
        }
    }

}
