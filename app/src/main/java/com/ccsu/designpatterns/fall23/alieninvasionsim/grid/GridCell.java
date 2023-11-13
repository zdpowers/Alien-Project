package com.ccsu.designpatterns.fall23.alieninvasionsim.grid;
// GridCell.java
import java.util.HashMap;
import java.util.Map;


/**
 * This GridCell class uses a map to store the buffs and debuffs for each cell/tile.
 *
 *
 * @author Rocky Trinh
 * @version 1.0
 * @since 2023-12-11
 */
public class GridCell {
    private Map<BuffDebuffTypes, Integer> buffsDebuffs;

    public GridCell() {
        this.buffsDebuffs = new HashMap<>();
    }

    public void applyBuffDebuff(BuffDebuffTypes type, int value) {
        buffsDebuffs.put(type, value);
    }

    public void removeBuffDebuff(BuffDebuffTypes type) {
        buffsDebuffs.remove(type);
    }

    public int getBuffDebuffValue(BuffDebuffTypes type) {
        return buffsDebuffs.getOrDefault(type, 0);
    }
}

