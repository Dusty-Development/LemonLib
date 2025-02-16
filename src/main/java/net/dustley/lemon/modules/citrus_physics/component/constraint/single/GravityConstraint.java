package net.dustley.lemon.modules.citrus_physics.component.constraint.single;

import net.dustley.lemon.modules.citrus_physics.component.ActorComponent;
import net.dustley.lemon.modules.citrus_physics.component.constraint.SingleBodyConstraint;
import org.joml.Vector3d;

public class GravityConstraint extends SingleBodyConstraint {

    public Vector3d gravity;

    public GravityConstraint(Vector3d accel) {
        gravity = accel;
    }

    public void solve(ActorComponent actor, double deltaTime) {
        actor.acceleration.add(gravity);
    }
}
