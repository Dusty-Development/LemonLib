package net.dustley.lemon.modules.citrus_physics.component.collision.colliders;

import net.dustley.lemon.modules.citrus_physics.component.collision.CollisionHelper;
import net.dustley.lemon.modules.citrus_physics.component.collision.CollisionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3d;
import org.joml.Vector3dc;

import java.util.Optional;

public class BoxCollider extends Collider {

    public BoxCollider(Vector3d size) {
        bounds = Box.of(Vec3d.ZERO, size.x, size.y, size.z);
    }
    public BoxCollider(Box copy) {
        bounds = Box.of(Vec3d.ZERO, copy.getLengthX(), copy.getLengthY(), copy.getLengthZ());
    }

    public ColliderType getType() { return ColliderType.BOX; }

    public Optional<CollisionResult> getResult(Vector3dc pos, Vector3dc otherPos, Collider otherCollider) {
        switch (otherCollider.getType()) {
            case SPHERE -> { return Optional.ofNullable(CollisionHelper.collideAABBSphere(pos, this, otherPos, (SphereCollider) otherCollider)); }
            case BOX -> { return Optional.ofNullable(CollisionHelper.collideAABBAABB(pos, this, otherPos, (BoxCollider) otherCollider)); }
            case POINT -> { return Optional.ofNullable(CollisionHelper.collideAABBPoint(pos, this, otherPos)); }
        }

        return Optional.empty();
    }
}
