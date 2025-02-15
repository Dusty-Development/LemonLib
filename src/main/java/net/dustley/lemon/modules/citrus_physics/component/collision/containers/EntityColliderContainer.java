package net.dustley.lemon.modules.citrus_physics.component.collision.containers;

import net.dustley.lemon.modules.citrus_physics.PhysicsWorld;
import net.dustley.lemon.modules.citrus_physics.component.ActorComponent;
import net.dustley.lemon.modules.citrus_physics.component.collision.colliders.BoxCollider;
import net.dustley.lemon.modules.citrus_physics.component.collision.colliders.Collider;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3d;

import java.util.Optional;

public class EntityColliderContainer extends ColliderContainerComponent {

    public void solve(PhysicsWorld physics, ActorComponent actor, double deltaTime) {
        for (Collider shape : shapes) {
            var box = new Box(shape.bounds.minX, shape.bounds.minY, shape.bounds.minZ, shape.bounds.maxX, shape.bounds.maxY, shape.bounds.maxZ).offset(new Vec3d(actor.position.x(), actor.position.y(), actor.position.z()));
            for (Entity entity : physics.world.getOtherEntities(null, box)) {
                var entityShape = new BoxCollider(entity.getBoundingBox());
                var center = entity.getBoundingBox().getCenter();
                shape.solve(actor, Optional.empty(), new Vector3d(center.x, center.y, center.z), entityShape);
            }
        }
    }

}
