package net.dustley.lemon.modules.citrus_physics.component.collision.containers;

import net.dustley.lemon.modules.citrus_physics.PhysicsWorld;
import net.dustley.lemon.modules.citrus_physics.component.ActorComponent;
import net.dustley.lemon.modules.citrus_physics.component.collision.CollisionHelper;
import net.dustley.lemon.modules.citrus_physics.component.collision.colliders.BoxCollider;
import net.dustley.lemon.modules.citrus_physics.component.collision.colliders.Collider;
import net.dustley.lemon.modules.math.conversion.JomlToMC;
import net.dustley.lemon.modules.math.conversion.McToJoml;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3d;

import java.util.Optional;

public class WorldColliderContainer extends ColliderContainerComponent {

    public void solve(PhysicsWorld physics, ActorComponent actor, double deltaTime) {
        var ecsWorld = physics.ecsWorld;

        for (Collider shape : shapes) {
            var world = physics.world;
            var bounds = shape.bounds;

            for (BlockPos blockPos : BlockPos.iterate(bounds.offset(JomlToMC.fromVector3d(actor.position)))) {
                var state = world.getBlockState(blockPos);
                var centerPos = blockPos.toCenterPos();
                var voxelShape = state.getCollisionShape(world, blockPos);

                if(voxelShape.isEmpty()) continue;

                var collisionShape = voxelShape.getBoundingBox();
                var fakeCollider = new BoxCollider(collisionShape);

                var collisionResult = shape.getResult(actor.position, McToJoml.fromVec3d(centerPos), fakeCollider);
                collisionResult.ifPresent(result -> shape.SolveCollisionResult(result, actor, Optional.empty()));
            }
        }
    }

}
