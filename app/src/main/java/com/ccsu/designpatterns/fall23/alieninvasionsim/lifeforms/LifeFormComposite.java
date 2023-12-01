package com.ccsu.designpatterns.fall23.alieninvasionsim.lifeforms;

import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.Grid;
import com.ccsu.designpatterns.fall23.alieninvasionsim.grid.TerrainTile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LifeFormComposite extends LifeForm {
    protected List<LifeForm> tribeMembers = new ArrayList<LifeForm>();
    private String tribeDescription;
    LifeFormComposite(TerrainTile spawn_tile) {
        super(spawn_tile);
        // VC - Setting the tribe description to be based on its tile
        tribeDescription = "Tribe at Tile - " + spawn_tile.toString();
    }

    /**
     * Return composite interface for this instance
     *
     * @return composite interface for this instance
     * @author Vincent Capra
     * @version 1.0
     * @since 2023-26-10
     */
    public LifeFormComposite composite(){return this;}

    /**
     * Adds the passed in LifeForm to the tribe of LifeForms
     *
     * @param  input_lifeForm LifeForm to be added
     * @author Vincent Capra
     * @version 1.0
     * @since 2023-7-11
     */
    public void add(LifeForm input_lifeForm){
        if(input_lifeForm != null){
            tribeMembers.add((input_lifeForm));
        }
    }

    /**
     * Removes the passed in LifeForm from the tribe of LifeForms
     *
     * @param  remove_lifeForm LifeForm to be removed
     * @author Vincent Capra
     * @version 1.0
     * @since 2023-7-11
     */
    public void removeLifeForm(LifeForm remove_lifeForm){
        tribeMembers.remove(remove_lifeForm);
    }

    /**
     * Removes the passed in LifeForm from the tribe of LifeForms
     *
     * @return Shallow Iterator for our tribe lifeforms
     * @author Vincent Capra
     * @version 1.0
     * @since 2023-7-11
     */
    public Iterator<LifeForm> iterator(){
        return tribeMembers.iterator();
    }

    @Override
    protected void gather() {}

    @Override
    protected void reproduce() {}

    @Override
    protected void move() {}

    @Override
    protected void attack(Grid grid) {}

    @Override
    protected void defend(int damage) {}

    @Override
    public LifeForm clone(TerrainTile residence) {
        return null;
    }

    /**
     * Abstract clone method for the prototype creational pattern.
     * @return null because this class is not to be cloned
     */
    @Override
    public LifeForm clone(TerrainTile residence) {
        return null;
    }
}
