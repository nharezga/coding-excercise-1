package com.myorg.codingexcercise;

/**
 *
 * You are about to build a Refrigerator which has SMALL, MEDIUM, and LARGE sized shelves.
 *
 * Method signature are given below. You need to implement the logic to
 *
 *  1. To keep track of items put in to the Refrigerator (add or remove)
 *  2. Make sure enough space available before putting it in
 *  3. Make sure space is used as efficiently as possible
 *  4. Make sure code runs efficiently
 *
 *
 * Created by kamoorr on 7/14/17.
 */
public class Refrigerator {

    /**
     * Refrigerator Total Cubic Feet (CuFt)
     */
    private int cubicFt;

    /**
     * Large size shelf count and size of one shelf
     */
    private int largeShelfCount;
    private int largeShelfCuFt;

    /**
     * Medium size shelf count and size of one shelf
     */
    private int mediumShelfCount;
    private int mediumShelfCuFt;

    /**
     * Medium size shelf count and size of one shelf
     */
    private int smallShelfCount;
    private int smallShelfCuFt;

    /**
     *
     *  Create a new refrigerator by specifying shelSize and count for SMALL, MEDIUM, LARGE shelves
     * @param largeShelfCount
     * @param largeShelfCuFt
     * @param mediumShelfCount
     * @param mediumShelfCuFt
     * @param smallShelfCount
     * @param smallShelfCuFt
     */
   public Refrigerator(int largeShelfCount, int largeShelfCuFt, int mediumShelfCount, int mediumShelfCuFt, int smallShelfCount, int smallShelfCuFt) {

       /**
        * Calculating total cuft as local variable to improve performance. Assuming no vacant space in the refrigerator
        *
        */
        this.cubicFt = (largeShelfCount * largeShelfCuFt) + (mediumShelfCount * mediumShelfCuFt) + (smallShelfCount* smallShelfCuFt);

        this.largeShelfCount = largeShelfCount;
        this.largeShelfCuFt = largeShelfCuFt;

        this.mediumShelfCount = mediumShelfCount;
        this.mediumShelfCuFt = mediumShelfCuFt;

        this.smallShelfCount = largeShelfCount;
        this.smallShelfCuFt = largeShelfCuFt;


    }

    /**
     * Implement logic to put an item to this refrigerator. Make sure
     *  -- You have enough vacant space in the refrigerator
     *  -- Make this action efficient in a way to increase maximum utilization of the space, re-arrange items when necessary
     *
     * Return
     *      true if put is successful
     *      false if put is not successful, for example, if you dont have enough space any shelf
     *
     *
     * @param item
     */
    public boolean put(Item item) {
        return false;
    }


    /**
     * return item when someone requested for item.
     * Return null when not available
     * @param itemId
     * @return
     */
    public Item get(String itemId) {

        return null;

    }

    /**
     * Return current utilization of the space
     * @return
     */
    public int getUtilizationPercentage() {
        return 0;
    }

    /**
     * Return current utilization in terms of cuft
     * @return
     */
    public int getUsedSpace() {
        return 0;
    }



}
