package net.dustley.lemon.modules.citrus_physics.component.constraint;

import net.dustley.lemon.modules.citrus_physics.component.ActorComponent;

public abstract class Constraint {

    public abstract void solve(ActorComponent actor, double deltaTime);

}
