package com.gildedrose;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GildedRoseTest {
    private static final String SELL_IN = "sellIn";
    private static final String QUALITY = "Quality";
    private GildedRose app;

    @BeforeEach
    void setUp() {
    }

    @Test
    void sanityTest() {
        assertThat(true).isTrue();
    }

    @Test
    void lowerItemQualityBy1() {
        final int quality = 1;
        final int sellIn = 1;
        final Item[] items = new Item[]{new Item("normal item", sellIn, quality)};
        final Item firstItem = items[0];

        app = new GildedRose(items);
        app.updateQuality(true);


        assertThat(firstItem.quality).describedAs(QUALITY).isEqualTo(0);
    }

    @Test
    void lowerItemToBeSoldDateBy1() {
        final int quality = 1;
        final int sellIn = 1;
        final Item[] items = new Item[]{new Item("normal item", sellIn, quality)};
        final Item firstItem = items[0];

        app = new GildedRose(items);
        app.updateQuality(true);


        assertThat(firstItem.sellIn).describedAs(SELL_IN).isEqualTo(0);
    }

    @Test
    void normalConjuredItem_qualityDecreaseDoubles() {
        final int firstItemQuality = 4;
        final int firstItemSellIn = 3;
        final int secondItemQuality = 10;
        final int secondItemSellIn = 0;
        final Item[] items = new Item[]{
                new Item("First conjured normal item", firstItemSellIn, firstItemQuality),
                new Item("Second conjured normal item", secondItemSellIn, secondItemQuality)
        };
        final Item firstItem = items[0];
        final Item secondItem = items[1];

        app = new GildedRose(items);
        app.updateQuality(true);


        assertThat(firstItem.sellIn).describedAs(SELL_IN).isEqualTo(2);
        assertThat(firstItem.quality).describedAs(QUALITY).isEqualTo(2);
        assertThat(secondItem.quality).describedAs(QUALITY).isEqualTo(6);
        assertThat(secondItem.sellIn).describedAs(SELL_IN).isEqualTo(-1);
    }

    @Test
    void whenAnItemSellByDateIs0_QualityReducesTwiceAsFast() {
        final int quality = 4;
        final int sellIn = 0;
        final Item[] items = new Item[]{new Item("normal item", sellIn, quality)};
        final Item firstItem = items[0];
        app = new GildedRose(items);
        app.updateQuality(true);


        assertThat(firstItem.quality).describedAs(QUALITY).isEqualTo(2);
    }


    @Test
    void itemQualityCanNeverBeLowerThen0() {
        final int quality = 0;
        final int sellIn = 0;
        final Item[] items = new Item[]{new Item("normal item", sellIn, quality)};
        final Item firstItem = items[0];
        app = new GildedRose(items);
        app.updateQuality(true);


        assertThat(firstItem.quality).describedAs(QUALITY).isEqualTo(0);
    }

    @Test
    void AgedBrieQualityIncreasesWhenItGetsOlder_expectIncreaseInQualityByOne() {
        final int quality = 2;
        final Item[] items = new Item[]{new Item("Aged Brie", 4, quality)};
        final Item firstItem = items[0];
        app = new GildedRose(items);
        app.updateQuality(true);


        assertThat(firstItem.quality).describedAs(QUALITY).isEqualTo(3);
    }

    @Test
    void AgedBrieQualityIncreasesWhenItGetsOlderAndIsExpired_expectIncreaseInQualityByOTwo() {
        final int quality = 2;
        final int sellIn = 0;
        final Item[] items = new Item[]{new Item("Aged Brie", sellIn, quality)};
        final Item firstItem = items[0];
        app = new GildedRose(items);
        app.updateQuality(true);


        assertThat(firstItem.quality).describedAs(QUALITY).isEqualTo(4);
    }

    @Test
    void anNormalItemQualityCanNeverExceedAboveFifty() {
        final int quality = 50;
        final int sellIn = 2;
        final Item[] items = new Item[]{new Item("Aged Brie", sellIn, quality)};
        final Item firstItem = items[0];

        app = new GildedRose(items);
        app.updateQuality(true);

        assertThat(firstItem.quality).describedAs(QUALITY).isEqualTo(50);
    }

    @Test
    void sulfuras_CanNeverExpire_TheQualityNeverDecrease_TheQualityIncreases() {
        final int quality = 4;
        final int sellIn = 3;
        final Item[] items = new Item[]{new Item("Sulfuras, Hand of Ragnaros", sellIn, quality)};
        final Item firstItem = items[0];

        app = new GildedRose(items);
        app.updateQuality(true);

        assertThat(firstItem.quality).describedAs(QUALITY).isEqualTo(4);
        assertThat(firstItem.sellIn).describedAs(SELL_IN).isEqualTo(3);
    }

    @Test
    void backstagepass_TheQualityNeverDecreaseItCreasesBy1WhenSellDateIsAboveNine_TheSellDateReducesByOne() {
        final int quality = 4;
        final int sellIn = 11;
        final Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality)};
        final Item firstItem = items[0];

        app = new GildedRose(items);
        app.updateQuality(true);

        assertThat(firstItem.quality).describedAs(QUALITY).isEqualTo(5);
        assertThat(firstItem.sellIn).describedAs(SELL_IN).isEqualTo(10);
    }


    @Test
    void backstagepass_TheQualityNeverDecreaseItInCreasesBy2WhenSellDateIsEqualOrBelow10_TheSellDateReducesByOne() {
        final int quality = 4;
        final int sellIn = 10;
        final Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality)};
        final Item firstItem = items[0];

        app = new GildedRose(items);
        app.updateQuality(true);

        assertThat(firstItem.quality).describedAs(QUALITY).isEqualTo(6);
        assertThat(firstItem.sellIn).describedAs(SELL_IN).isEqualTo(9);
    }

    @Test
    void backstagepass_TheQualityNeverDecreaseItCreasesBy3WhenSellDateIsEqualToFiveOrLess_TheSellDateReducesByOne() {
        final int quality = 4;
        final int sellIn = 5;
        final Item[] items = new Item[]{new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, quality)};
        final Item firstItem = items[0];

        app = new GildedRose(items);
        app.updateQuality(true);

        assertThat(firstItem.quality).describedAs(QUALITY).isEqualTo(7);
        assertThat(firstItem.sellIn).describedAs(SELL_IN).isEqualTo(4);
    }
}
