package net.dustley.lemon.content.test_stick;

import net.dustley.lemon.LemonLib;
import net.dustley.lemon.modules.citrus_physics.PhysicsWorld;
import net.dustley.lemon.modules.citrus_physics.component.ActorComponent;
import net.dustley.lemon.modules.citrus_physics.component.collision.SphereColliderComponent;
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
import org.joml.Vector3d;

public class TestStick extends DebugStickItem {
    public TestStick() { super(new Settings().rarity(Rarity.EPIC).maxCount(1)); }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        var physics = PhysicsWorld.getFromWorld(world);

        var eyePos = user.getEyePos();

        var entityA = physics.createEntity()
                .add(new ActorComponent(new Vector3d(eyePos.x,eyePos.y,eyePos.z), 1.0))
                .add(new SphereColliderComponent(0.25))
                .add(new GravityConstraint(new Vector3d(0.0,-1.0,0.0)));

        var entityB = physics.createEntity()
                .add(new ActorComponent(new Vector3d(eyePos.x+0.5,eyePos.y,eyePos.z), 2.0))
                .add(new SphereColliderComponent(0.25))
                .add(new GravityConstraint(new Vector3d(0.0,-1.0,0.0)));

        entityA.add(new StaticConstraint(new Vector3d(eyePos.x,eyePos.y,eyePos.z)));
        entityA.add(new FixedDistanceConstraint(entityB, 1.0));

        entityA.isEnabled();
        entityB.isEnabled();

        LemonLib.LOGGER.info(entityB.toString());

        return super.use(world, user, hand);
    }
}
