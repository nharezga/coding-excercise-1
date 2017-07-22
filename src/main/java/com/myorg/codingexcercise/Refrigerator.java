package com.myorg.codingexcercise;

import java.util.*;

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

    // Track items in fridge
    private List<Shelf> shelves;

    private TreeMap<Shelf, Integer> treeMap;

    // Total used space
    private int usedCubicFt;

    /**
     *
     *  Create a new refrigerator by specifying shelfSize and count for SMALL, MEDIUM, LARGE shelves
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

        this.smallShelfCount = smallShelfCount;
        this.smallShelfCuFt = smallShelfCuFt;

        shelves = new ArrayList<Shelf>();
        treeMap = new TreeMap<Shelf, Integer>(new FreeSpaceComp());

        // Arrange shelves small -> large to use the smallest possible shelf when inserting if possible
       for(int i = 0; i < largeShelfCount; i++)
           shelves.add(new Shelf(largeShelfCuFt));
//           treeMap.put(new Shelf(largeShelfCuFt), largeShelfCuFt);

        for(int i = 0; i < mediumShelfCount; i++)
//            treeMap.put(new Shelf(mediumShelfCuFt), mediumShelfCuFt);
           shelves.add(new Shelf(mediumShelfCuFt));

       for(int i = 0; i < smallShelfCount; i++)
//           treeMap.put(new Shelf(smallShelfCuFt), smallShelfCuFt);
           shelves.add(new Shelf(smallShelfCuFt));


        usedCubicFt = 0;
    }

    /**
     * Implement logic to put an item to this refrigerator. Make sure
     *  -- You have enough vacant space in the refrigerator
     *  -- Make this action efficient in a way to increase maximum utilization of the space, re-arrange items when necessary
     *
     * Return
     *      true if put is successful
     *      false if put is not successful, for example, if you don't have enough space any shelf, even after re-arranging
     *
     *
     * @param item
     */
    public boolean put(Item item)
    {
        // Check if there is enough room in the fridge for this item
        if(item.getCubicFt() > cubicFt-usedCubicFt)
            return false;   // TODO: Test for this

        Shelf temp = null;
        for(Shelf s : shelves)
        {
            if(item.getCubicFt() == s.getFreeFt())
            {
                s.addItem(item);
                usedCubicFt += item.getCubicFt();
                System.out.println("Found an exact match for " + item.getItemId());
                return true;
            }
            else if(/*temp == null &&*/(item.getCubicFt() < s.getFreeFt()))
                // record the smallest shelf this item will fit on
                temp = s;
        }
        if(temp != null)
        {
            temp.addItem(item);
            usedCubicFt += item.getCubicFt();
            System.out.println("Found an inexact match for " + item.getItemId());
            return true;
        }
        System.out.println("No match for " + item.getItemId() + ", attempting to rearrange");

        return rearrange(item, 0);
    }

    private boolean rearrange(Item item, int listIdx)
    {
        // Iterate over list from large -> small and check if any items can fit on a different shelf
        ListIterator<Shelf> listIterator = shelves.listIterator(listIdx);
        if(listIdx >= shelves.size())
            return false;
        while(listIterator.hasNext())
        {
            Shelf s = listIterator.next();

            // Check if the shelf has an item that can be removed to fit this item
            ListIterator<Item> itemListIterator = s.getItems().listIterator();
            while(itemListIterator.hasNext())
            {
                Item i = itemListIterator.next();
                // Check if there is enough room for the item without rearranging
                if(item.getCubicFt() <= s.getFreeFt())
                {
                    return true;
                }
                else if((s.getFreeFt() + i.getCubicFt() - item.getCubicFt()) <= s.getCubicFt())
                {
                    // this item can replace an item on current shelf
                    if(rearrange(i, listIdx+1))
                    {
                        Item temp = get(i.getItemId());
                        put(item);
                        put(temp);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean put2(Item item) {
        if(item.getCubicFt() > cubicFt-usedCubicFt)
            return false;
        else
        {
            // Attempt to insert into shelf without rearranging
            for(Shelf s : shelves)
            {
                // Item will fit on this shelf without rearranging
                if(s.getFreeFt() >= item.getCubicFt())
                {
                    s.addItem(item);
                    usedCubicFt += item.getCubicFt();
                    return true;
                }
            }

            // Item didn't fit, need to rearrange
            // TODO
        }

        return false;
    }


    /**
     * remove and return the requested item
     * Return null when not available
     * @param itemId
     * @return
     */
    public Item get(String itemId) {
// TODO: Test removing item that isn't there
        Item i;
        for(Shelf s : shelves)
        {
            if((i = s.removeItem(itemId)) != null)
            {
                usedCubicFt -= i.getCubicFt();
                return i;
            }

        }
        return null;

    }

    /**
     * Return current utilization of the space
     * @return
     */
    public float getUtilizationPercentage() {
        return ((float)cubicFt/usedCubicFt)*100;
    }

    /**
     * Return current utilization in terms of cuft
     * @return
     */
    public int getUsedSpace() {
        return usedCubicFt;
    }



}

class FreeSpaceComp implements Comparator<Shelf>
{
    public int compare(Shelf s1, Shelf s2)
    {
        if(s1.getFreeFt() > s2.getFreeFt())
            return 1;
        else
            return -1;
    }
}