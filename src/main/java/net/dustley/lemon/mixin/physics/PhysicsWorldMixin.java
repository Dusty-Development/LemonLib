package net.dustley.lemon.mixin.physics;

import net.dustley.lemon.mixin_duck.PhysicsWorldDuck;
import net.dustley.lemon.modules.citrus_physics.PhysicsWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(World.class)
public class PhysicsWorldMixin implements PhysicsWorldDuck {

	@Override public PhysicsWorld lemonLib$getPhysics() { return physicsWorld; }
	@Unique PhysicsWorld physicsWorld;

	@Inject(at = @At("TAIL"), method = "<init>")
	private void init(CallbackInfo info) {
		physicsWorld = new PhysicsWorld((World)(Object)this);
	}

//	@Inject(at = @At("HEAD"), method = "tick")
//	private void tick(CallbackInfo info) {
//		physicsWorld.world = (World)(Object)this;
//	}

	@Inject(at = @At("TAIL"), method = "close")
	private void destroy(CallbackInfo info) {
		physicsWorld.destroy();
	}


}