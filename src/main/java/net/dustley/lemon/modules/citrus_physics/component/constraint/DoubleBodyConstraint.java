package net.dustley.lemon.modules.citrus_physics.component.constraint;

import dev.dominion.ecs.api.Entity;
import net.dustley.lemon.modules.citrus_physics.component.ActorComponent;

public abstract class DoubleBodyConstraint extends Constraint {

    public Entity other;

    public DoubleBodyConstraint(Entity other) {this.other = other;}

}
