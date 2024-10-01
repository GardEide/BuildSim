package no.uib.inf101.brick.model;

public interface Physics {

    /**
     * Method for applying gravity to cells
     * 
     * @return boolean depending on if a cell has been moved
     */
    boolean applyGravity();

}
