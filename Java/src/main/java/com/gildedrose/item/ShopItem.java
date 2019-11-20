package com.gildedrose.item;

import com.gildedrose.Item;

public abstract class ShopItem {

    private static final int MAX_QUALITY = 50;
    private Item item;
    private boolean isConjured;

    ShopItem(Item item, boolean isConjured) {
        this.item = item;
        this.isConjured = isConjured;
    }

    int getItemSellDueTime() {
        return this.item.sellIn;
    }

    public void modifyQuality() {
        subtractsQuality();
    }

    public void modifyAmountOfDaysToSellItem() {
        item.sellIn--;
    }

    private void subtractsQuality() {
        int amount = 1;

        if (isConjured) {
            amount = doubleAmount(amount);
        }

        if (isItemSellTimeExpired()) {
            amount = doubleAmount(amount);
        }

        item.quality -= amount;

        if (item.quality < 0) {
            item.quality = 0;
        }
    }

    private int doubleAmount(int amount) {
        amount *= 2;
        return amount;
    }

    void plusQuality(int amount) {
        if (!isMaxQuality()) {

            if (isItemSellTimeExpired()) {
                amount = doubleAmount(amount);
            }

            item.quality += amount;

            if (item.quality > MAX_QUALITY) {
                item.quality = 0;
            }
        }
    }

    private boolean isMaxQuality() {
        return MAX_QUALITY == item.quality;
    }

    private boolean isItemSellTimeExpired() {
        return item.sellIn <= 0;
    }


}
