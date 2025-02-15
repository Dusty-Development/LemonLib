package net.dustley.lemon.modules.citrus_physics.solver;

import dev.dominion.ecs.api.Dominion;
import net.dustley.lemon.modules.citrus_physics.PhysicsWorld;
import net.dustley.lemon.modules.citrus_physics.component.ActorComponent;
import net.dustley.lemon.modules.citrus_physics.component.constraint.Constraint;
import net.dustley.lemon.modules.citrus_physics.component.constraint.ConstraintComponent;
import net.dustley.lemon.modules.citrus_physics.component.constraint.multi.FixedDistanceConstraint;
import net.dustley.lemon.modules.citrus_physics.component.constraint.single.GravityConstraint;
import net.dustley.lemon.modules.citrus_physics.component.constraint.single.StaticConstraint;
import org.joml.Vector3d;

import java.awt.*;

public class ConstraintSolver extends Solver {

    public ConstraintSolver(PhysicsWorld world, Dominion ecs) { super(world, ecs); }

    public void solve(double deltaTime) {
        for (int i = 0; i < PhysicsWorld.CONSTRAINT_RESOLUTION; i++) {
            ecsWorld.findEntitiesWith(ActorComponent.class, ConstraintComponent.class).stream().forEach(result -> result.comp2().solve(result.comp1(), deltaTime / PhysicsWorld.CONSTRAINT_RESOLUTION));
        }
    }

}
