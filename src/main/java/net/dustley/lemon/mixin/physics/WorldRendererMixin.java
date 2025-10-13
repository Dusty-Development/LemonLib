package net.dustley.lemon.mixin.physics;

import net.dustley.lemon.modules.citrus_physics.PhysicsWorld;
import net.dustley.lemon.modules.citrus_physics.component.ActorComponent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.debug.DebugHudEntries;
import net.minecraft.client.render.*;
import net.minecraft.client.render.debug.DebugRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Math.abs;
import static java.lang.Math.clamp;

@Mixin(DebugRenderer.class)
public class WorldRendererMixin {

    @Inject(method = "render", at = @At("TAIL"))
    private void render(MatrixStack matrices, Frustum frustum, VertexConsumerProvider.Immediate vertexConsumers, double cameraX, double cameraY, double cameraZ, boolean lateDebug, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        var physicsWorld = PhysicsWorld.getFromWorld(client.world);

        if(client.debugHudEntryList.isEntryVisible(DebugHudEntries.ENTITY_HITBOXES)) {
            AtomicInteger renderTokens = new AtomicInteger(50000); // In short how many particles can render at once

            physicsWorld.ecsWorld.findEntitiesWith(ActorComponent.class).stream().forEach(result -> {
                if(renderTokens.get() > 0) {
                    ActorComponent actor = result.comp();
                    matrices.push();

                    matrices.translate(-cameraX, -cameraY, -cameraZ);

                    var position = actor.position;
                    matrices.translate(position.x, position.y, position.z);

                    var box = Box.of(Vec3d.ZERO, actor.mass, actor.mass, actor.mass);
                    var vel = actor.position.sub(actor.positionCache, new Vector3d()).mul(20);

                    DebugRenderer.drawBox(matrices, vertexConsumers, box, (float) clamp(abs(vel.x), 0.0, 1.0), (float) clamp(abs(vel.y), 0.0, 1.0), (float) clamp(abs(vel.z), 0.0, 1.0), 1f);

                    renderTokens.addAndGet(-1);
                    matrices.pop();
                }
            });
        }
    }
}
