package net.dustley.lemon.modules.citrus_physics.debug;

import net.dustley.lemon.modules.citrus_physics.PhysicsWorld;
import net.dustley.lemon.modules.citrus_physics.component.ActorComponent;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.debug.DebugRenderer;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3d;

import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Math.clamp;

public class CitrusDebugRenderer {

    public static void registerEvents() {
        WorldRenderEvents.BEFORE_DEBUG_RENDER.register((renderer) -> {
            var physicsWorld = PhysicsWorld.getFromWorld(renderer.world());

            if(MinecraftClient.getInstance().getEntityRenderDispatcher().shouldRenderHitboxes()) {
                AtomicInteger renderTokens = new AtomicInteger(5000); // In short how many particles can render at once

                physicsWorld.ecsWorld.findEntitiesWith(ActorComponent.class).stream().forEach(result -> {
                    if(renderTokens.get() > 0) {
                        ActorComponent actor = result.comp();
                        var stack = renderer.matrixStack();
                        var consumers = renderer.consumers();

                        if (stack != null && consumers != null) {
                            stack.push();

                            var cameraPos = renderer.camera().getPos();
                            stack.translate(-cameraPos.x, -cameraPos.y, -cameraPos.z);

                            var position = actor.position;
                            stack.translate(position.x, position.y, position.z);

                            var box = Box.of(Vec3d.ZERO, actor.mass, actor.mass, actor.mass);
                            var vel = actor.position.sub(actor.positionCache, new Vector3d()).mul(50);
                            DebugRenderer.drawBox(stack, consumers, box, (float) clamp(vel.x, 0.0, 1.0), (float) clamp(vel.y, 0.0, 1.0), (float) clamp(vel.z, 0.0, 1.0), 1f);
                            renderTokens.addAndGet(-1);
                            stack.pop();
                        }
                    }
                });
            }
        });
    }

}
