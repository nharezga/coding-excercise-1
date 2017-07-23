package com.myorg.codingexcercise;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Shelf
{
    private int cubicFt;
    private int usedFt;
    private List<Item> items;

    public Shelf(int cubicFt)
    {
        this.cubicFt = cubicFt;
        this.usedFt = 0;
        this.items = new ArrayList<Item>();
    }

    public int getCubicFt()
    {
        return cubicFt;
    }

    public int getFreeFt()
    {
        return cubicFt - usedFt;
    }

    public List<Item> getItems()
    {
        return items;
    }

    public Item removeItem(String id)
    {
        // Check if item is in this list
        Iterator<Item> itemIterator = items.iterator();
        while(itemIterator.hasNext())
        {
            Item item = itemIterator.next();
            if(item.getItemId().equals(id))
            {
                itemIterator.remove();
                usedFt -= item.getCubicFt();
                return item;
            }
        }
        return null;
    }

    public boolean addItem(Item item)
    {
        // Check if there is enough space to add item
        if(item.getCubicFt() + usedFt <= cubicFt)
        {
            items.add(item);
            usedFt += item.getCubicFt();
            return true;
        }
        return false;
    }
}
