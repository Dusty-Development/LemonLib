package net.dustley.lemon.modules.citrus_physics.component.collision.colliders;

import net.dustley.lemon.modules.citrus_physics.component.ActorComponent;
import org.joml.Vector3d;

import java.util.Optional;

public abstract class Collider {

    public void solve(ActorComponent actor, Optional<ActorComponent> otherActor, Collider otherCollider) {

    }

    public enum ColliderType {
        BOX,
        POINT,
        SPHERE
    }
}
