package com.ccsu.designpatterns.fall23.alieninvasionsim.Lifeforms;
/**
 * An interface to define various factories to create different
 * types of lifeforms.
 * In the Sprint 1 Abstract Factory pattern this is
 * the Abstract Fact participant
 *
 * @author Vincent Capra
 */
public interface AbsLifeformFactory {

    public LifeForm makeLifeform();
}
