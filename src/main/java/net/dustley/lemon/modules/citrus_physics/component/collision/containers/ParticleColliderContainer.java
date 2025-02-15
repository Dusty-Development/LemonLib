package net.dustley.lemon.modules.citrus_physics.component.collision.containers;

import net.dustley.lemon.modules.citrus_physics.PhysicsWorld;
import net.dustley.lemon.modules.citrus_physics.component.ActorComponent;
import net.dustley.lemon.modules.citrus_physics.component.constraint.ConstraintComponent;

public class ParticleColliderContainer extends ColliderContainerComponent {

    public void solve(ActorComponent actor, double deltaTime) {
        // Use a simple n^2 algorithm for now... use a spatial grid later
        for (int i = 0; i < PhysicsWorld.CONSTRAINT_RESOLUTION; i++) {
            ecsWorld.findEntitiesWith(ActorComponent.class, ConstraintComponent.class).stream().forEach(result -> result.comp2().solve(result.comp1(), deltaTime));
        }
    }

}
