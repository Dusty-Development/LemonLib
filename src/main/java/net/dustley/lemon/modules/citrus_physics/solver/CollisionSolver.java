package net.dustley.lemon.modules.citrus_physics.solver;

import dev.dominion.ecs.api.Dominion;
import net.dustley.lemon.modules.citrus_physics.PhysicsWorld;
import net.dustley.lemon.modules.citrus_physics.component.ActorComponent;
import net.dustley.lemon.modules.citrus_physics.component.collision.containers.ColliderContainerComponent;
import net.dustley.lemon.modules.citrus_physics.component.constraint.ConstraintComponent;

public class CollisionSolver extends Solver {

    public CollisionSolver(PhysicsWorld world, Dominion ecs) { super(world, ecs); }

    public void solve(double deltaTime) {
        for (int i = 0; i < PhysicsWorld.COLLISION_RESOLUTION; i++) {
            ecsWorld.findEntitiesWith(ActorComponent.class, ColliderContainerComponent.class).stream().forEach(result -> result.comp2().solve(result.comp1(), deltaTime / PhysicsWorld.COLLISION_RESOLUTION));
        }
    }

}
