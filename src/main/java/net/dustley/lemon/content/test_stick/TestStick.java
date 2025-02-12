package net.dustley.lemon.content.test_stick;

import dev.dominion.ecs.api.Entity;
import net.dustley.lemon.LemonLib;
import net.dustley.lemon.modules.citrus_physics.PhysicsWorld;
import net.dustley.lemon.modules.citrus_physics.component.ActorComponent;
import net.dustley.lemon.modules.citrus_physics.component.collision.SphereColliderComponent;
import net.dustley.lemon.modules.citrus_physics.component.constraint.ConstraintComponent;
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
import org.joml.*;

public class TestStick extends DebugStickItem {
    public TestStick() { super(new Settings().rarity(Rarity.EPIC).maxCount(1)); }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        var physics = PhysicsWorld.getFromWorld(world);

        Vector2ic size = new Vector2i(50, 50);
        double scale = 0.25;

        Vector3dc leftPos = new Vector3d(user.getX(), user.getY(), user.getZ());
        Vector3dc rightPos = new Vector3d(user.getX() + (size.x() * scale), user.getY(), user.getZ());

        Entity[][] entities = new Entity[size.x()][size.y()];

        for (int x = 0; x < size.x(); x++) {
            for (int y = 0; y < size.y(); y++) {
                Vector3d position = leftPos.add((x * scale), (y * scale), 0.0, new Vector3d());

                entities[x][y] = physics.createEntity()
                        .add(new ActorComponent(position, 0.1))
                        .add(new SphereColliderComponent(0.25));

                physics.addConstraint(entities[x][y], new GravityConstraint(new Vector3d(0.0,-1.0,0.0)));
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
}

/*
 * public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
 *        var physics = PhysicsWorld.getFromWorld(world);
 *
 *        var eyePos = user.getEyePos();
 *        var eyeDir = user.getRotationVector();
 *
 *        var entityA = physics.createEntity()
 *                .add(new ActorComponent(new Vector3d(eyePos.x,eyePos.y,eyePos.z), 0.2))
 *                .add(new SphereColliderComponent(0.25))
 *                .add(new GravityConstraint(new Vector3d(0.0,-1.0,0.0)))
 *                .add(new StaticConstraint(new Vector3d(eyePos.x,eyePos.y,eyePos.z)));
 *
 *        var length = 200;
 *        var lastEntity = entityA;
 *        for (int i = 0; i < length; i++) {
 *            var entity = physics.createEntity()
 *                    .add(new ActorComponent(new Vector3d(eyePos.x + eyeDir.x, eyePos.y + eyeDir.y, eyePos.z + eyeDir.z), 0.1))
 *                    .add(new SphereColliderComponent(1))
 *                    .add(new FixedDistanceConstraint(lastEntity, 0.1))
 *                    .add(new GravityConstraint(new Vector3d(0.0,-1.0,0.0)));
 *
 *            lastEntity = entity;
 *        }
 *
 *        return super.use(world, user, hand);
 *    }
 */