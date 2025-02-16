package net.dustley.lemon.modules.citrus_physics.solver;

import dev.dominion.ecs.api.Dominion;
import net.dustley.lemon.modules.citrus_physics.PhysicsWorld;
import net.dustley.lemon.modules.citrus_physics.component.ActorComponent;
import org.joml.Vector3d;

import static net.dustley.lemon.modules.citrus_physics.PhysicsWorld.MAX_SPEED;

public class GenericsSolver extends Solver {

    public GenericsSolver(PhysicsWorld world, Dominion ecs) { super(world, ecs); }

    public void solve(double deltaTime) {

        ecsWorld.findEntitiesWith(ActorComponent.class).stream().forEach(result -> {
            ActorComponent actor = result.comp();

            MAX_SPEED = 50;

            // Moves the particle based on velocity
            Vector3d velocity = actor.position.sub(actor.positionCache, new Vector3d());
            if(velocity.length() > MAX_SPEED) { velocity.normalize(MAX_SPEED); }

            actor.positionCache = new Vector3d(actor.position);
            var accel = actor.acceleration.mul(deltaTime, new Vector3d()).mul(deltaTime);
            actor.position.add(velocity.add(accel, new Vector3d()));
            actor.acceleration = new Vector3d();
        });
    }

}
