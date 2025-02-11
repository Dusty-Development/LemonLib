package net.dustley.lemon.modules.citrus_physics.component.constraint.single;

import net.dustley.lemon.modules.citrus_physics.component.ActorComponent;
import net.dustley.lemon.modules.citrus_physics.component.constraint.SingleBodyConstraint;
import org.joml.Vector3d;

public class StaticConstraint extends SingleBodyConstraint {

    Vector3d position;

    public StaticConstraint(Vector3d lockPos) {
        position = lockPos;
    }

    public void solve(ActorComponent actor) {
        actor.position = new Vector3d(position);
    }
}
