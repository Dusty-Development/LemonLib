package net.dustley.lemon.content.test_stick;

import dev.dominion.ecs.api.Entity;
import net.dustley.lemon.modules.citrus_physics.PhysicsWorld;
import net.dustley.lemon.modules.citrus_physics.component.ActorComponent;
import net.dustley.lemon.modules.citrus_physics.component.collision.colliders.SphereCollider;
import net.dustley.lemon.modules.citrus_physics.component.constraint.multi.FixedDistanceConstraint;
import net.dustley.lemon.modules.citrus_physics.component.constraint.single.GravityConstraint;
import net.dustley.lemon.modules.citrus_physics.component.constraint.single.StaticConstraint;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DebugStickItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.joml.Vector2i;
import org.joml.Vector2ic;
import org.joml.Vector3d;
import org.joml.Vector3dc;

public class TestStick extends DebugStickItem {
    public TestStick() { super(new Settings().rarity(Rarity.EPIC).maxCount(1)); }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        var physics = PhysicsWorld.getFromWorld(world);

        Vector2ic size = new Vector2i(32, 32);
        double scale = 0.5;

        Vector3dc leftPos = new Vector3d(user.getX() + 5, user.getY(), user.getZ() + 5);
        Vector3dc rightPos = new Vector3d(user.getX() + (size.x() * scale) + 5, user.getY(), user.getZ() + scale + 5);

        Entity[][] entities = new Entity[size.x()][size.y()];

        for (int x = 0; x < size.x(); x++) {
            for (int y = 0; y < size.y(); y++) {
                Vector3d position = leftPos.add((x * scale), (y * scale), 0.0, new Vector3d());

                entities[x][y] = physics.createEntity().add(new ActorComponent(position, scale));

                physics.addConstraint(entities[x][y], new GravityConstraint(new Vector3d(0.0,-1,0.0)));
//                physics.addParticleCollider(entities[x][y], new SphereCollider(scale * 0.5));
                physics.addEntityCollider(entities[x][y], new SphereCollider(scale * 0.5));
            }
        }

        // Freeze corners
        physics.addConstraint(entities[0][0], new StaticConstraint(new Vector3d(leftPos)));
        physics.addConstraint(entities[size.x()-1][0], new StaticConstraint(new Vector3d(rightPos)));

        for (int x = 0; x < size.x() - 1; x++) {
            for (int y = 0; y < size.y() - 1; y++) {
                var entity = entities[x][y];

                var entityNext = entities[x + 1][y];
                var next = new FixedDistanceConstraint(entityNext, scale);

                var entityUnder = entities[x][y + 1];
                var under = new FixedDistanceConstraint(entityUnder, scale);

                physics.addConstraint(entity, next, under);
            }
        }

        return super.use(world, user, hand);
    }

//    @Override
//    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
//        var physics = PhysicsWorld.getFromWorld(world);
//
//        var eyePos = user.getEyePos();
//        var eyeDir = user.getRotationVector();
//
//        var scale = 0.5;
//        var dist = 0.05;
//        var off = 5;
//        var gravity = -0.98;
//
//        var pos = new Vector3d(eyePos.x + (eyeDir.x * off * scale),eyePos.y + (eyeDir.y * off * scale),eyePos.z + (eyeDir.z * off * scale));
//        var entityA = physics.createEntity().add(new ActorComponent(pos, scale));
//        physics.addConstraint(entityA, new StaticConstraint(new Vector3d(pos)));
//
//        var length = 50;
//        var lastEntity = entityA;
//        for (int i = 0; i < length; i++) {
//            var entity = physics.createEntity().add(new ActorComponent(new Vector3d(eyePos.x + (eyeDir.x * (i + off) * scale), eyePos.y + (eyeDir.y * (i + off) * scale), eyePos.z + (eyeDir.z * (i + off) * scale)), scale));
//            physics.addConstraint(entity, new FixedDistanceConstraint(lastEntity, scale + dist));
//            physics.addConstraint(entity, new GravityConstraint(new Vector3d(0.0,gravity,0.0)));
//
//            var collider = new SphereCollider(scale * 0.5);
////            physics.addParticleCollider(entity, collider);
////            physics.addWorldCollider(entity, collider);
//            physics.addEntityCollider(entity, collider);
//
//            lastEntity = entity;
//        }
//
//        return super.use(world, user, hand);
//    }
}
