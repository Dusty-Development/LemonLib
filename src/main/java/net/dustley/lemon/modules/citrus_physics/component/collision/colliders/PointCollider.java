package net.dustley.lemon.modules.citrus_physics.component.collision.colliders;

import net.dustley.lemon.modules.citrus_physics.component.collision.CollisionHelper;
import net.dustley.lemon.modules.citrus_physics.component.collision.CollisionResult;
import org.joml.Vector3dc;

import java.util.Optional;

public class PointCollider extends Collider {

    public ColliderType getType() { return ColliderType.POINT; }

    public Optional<CollisionResult> getResult(Vector3dc pos, Vector3dc otherPos, Collider otherCollider) {
        switch (otherCollider.getType()) {
            case SPHERE -> { return Optional.of(CollisionHelper.collidePointSphere(pos, otherPos, (SphereCollider) otherCollider)); }
            case BOX -> { return Optional.of(CollisionHelper.collidePointAABB(pos, otherPos, (BoxCollider) otherCollider)); }
        }

        return Optional.empty();
    }
}