package net.dustley.lemon.modules.citrus_physics.component.collision.colliders;

import net.dustley.lemon.modules.citrus_physics.component.ActorComponent;
import net.dustley.lemon.modules.citrus_physics.component.collision.CollisionResult;
import net.minecraft.util.math.Box;
import org.joml.Vector3d;
import org.joml.Vector3dc;

import java.util.Optional;

public abstract class Collider {

    public Box bounds;

    public abstract ColliderType getType();

    public void solve(ActorComponent actor, Optional<ActorComponent> optionalOtherActor, Vector3dc otherPos, Collider otherCollider) {

        var optionalCollisionResult = getResult(new Vector3d(actor.position), new Vector3d(otherPos), otherCollider);
        if(optionalCollisionResult.isPresent()) {
            CollisionResult collisionResult = optionalCollisionResult.get();

            // TODO: this should be changed to include mass
            var moveAmountA = 0.5;
//            if(optionalOtherActor.isEmpty()) moveAmountA = 1;
            var moveAmountB = 1 - moveAmountA;

            actor.position.add(collisionResult.resolutionVector.mul(moveAmountA, new Vector3d()));

            if(optionalOtherActor.isPresent()) {
                var otherActor = optionalOtherActor.get();
                otherActor.position.sub(collisionResult.resolutionVector.mul(moveAmountB, new Vector3d()));
            }
        }
    }

    public abstract  Optional<CollisionResult> getResult(Vector3dc pos, Vector3dc otherPos, Collider otherCollider);

    public enum ColliderType {
        BOX,
        POINT,
        SPHERE
    }
}
