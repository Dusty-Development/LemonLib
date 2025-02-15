package net.dustley.lemon.modules.citrus_physics.component.collision.colliders;

import net.dustley.lemon.modules.citrus_physics.component.collision.CollisionHelper;
import net.dustley.lemon.modules.citrus_physics.component.collision.CollisionResult;
import net.minecraft.util.math.Box;
import org.joml.Vector3dc;

import java.util.Optional;

public class SphereCollider extends Collider {

    public double radius;

    public SphereCollider(double radius) {
        this.radius = radius;
        this.bounds = new Box(-radius, -radius, -radius, radius, radius, radius);
    }

    public ColliderType getType() { return ColliderType.SPHERE; }

    public Optional<CollisionResult> getResult(Vector3dc pos, Vector3dc otherPos, Collider otherCollider) {
        switch (otherCollider.getType()) {
            case SPHERE -> { return Optional.ofNullable(CollisionHelper.collideSphereSphere(pos, this, otherPos, (SphereCollider) otherCollider)); }
            case BOX -> { return Optional.of(CollisionHelper.collideSphereAABB(pos, this, otherPos, (BoxCollider) otherCollider)); }
            case POINT -> { return Optional.ofNullable(CollisionHelper.collideSpherePoint(pos, this, otherPos)); }
        }

        return Optional.empty();
    }
}
