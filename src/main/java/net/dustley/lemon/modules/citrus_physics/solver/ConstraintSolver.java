package net.dustley.lemon.modules.citrus_physics.solver;

import dev.dominion.ecs.api.Dominion;
import net.dustley.lemon.modules.citrus_physics.PhysicsWorld;
import net.dustley.lemon.modules.citrus_physics.component.ActorComponent;
import net.dustley.lemon.modules.citrus_physics.component.constraint.Constraint;
import org.joml.Vector3d;

import java.awt.*;

public class ConstraintSolver extends Solver {

    public ConstraintSolver(PhysicsWorld world, Dominion ecs) { super(world, ecs); }

    public void solve(double deltaTime) {
        for (var constraintType : world.constraintTypes) {
            ecsWorld.findEntitiesWith(ActorComponent.class, constraintType.getClass()).stream().forEach(result -> {
                ActorComponent actor = result.comp1();
                Constraint constraint = (Constraint) result.comp2();

                constraint.solve(actor);
            });
        }

    }

}
