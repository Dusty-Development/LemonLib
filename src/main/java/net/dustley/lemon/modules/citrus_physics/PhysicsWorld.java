package net.dustley.lemon.modules.citrus_physics;

import dev.dominion.ecs.api.Dominion;
import dev.dominion.ecs.api.Entity;
import dev.dominion.ecs.api.Scheduler;
import net.dustley.lemon.mixin_duck.PhysicsWorldDuck;
import net.dustley.lemon.modules.citrus_physics.component.constraint.Constraint;
import net.dustley.lemon.modules.citrus_physics.component.constraint.ConstraintComponent;
import net.dustley.lemon.modules.citrus_physics.component.constraint.multi.FixedDistanceConstraint;
import net.dustley.lemon.modules.citrus_physics.component.constraint.single.GravityConstraint;
import net.dustley.lemon.modules.citrus_physics.component.constraint.single.StaticConstraint;
import net.dustley.lemon.modules.citrus_physics.solver.CollisionSolver;
import net.dustley.lemon.modules.citrus_physics.solver.ConstraintSolver;
import net.dustley.lemon.modules.citrus_physics.solver.GenericsSolver;
import net.dustley.lemon.modules.citrus_physics.solver.Solver;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PhysicsWorld {

    public Dominion ecsWorld;
    public Scheduler ecsScheduler;

    ArrayList<Solver> solvers = new ArrayList<>();

    public static int TICK_RATE = 45;
    public static int CONSTRAINT_RESOLUTION = 16;

    // HELPER //
    public static PhysicsWorld getFromWorld(World world) { return ((PhysicsWorldDuck) world).getPhysics(); }

    // RUNTIME //
    public PhysicsWorld() {
        ecsWorld = Dominion.create();
        ecsScheduler = ecsWorld.createScheduler();

        // Register Solvers
        solvers.add(new GenericsSolver(this, ecsWorld));
        solvers.add(new ConstraintSolver(this, ecsWorld));
        solvers.add(new CollisionSolver(this, ecsWorld));

        applySystems();

        ecsScheduler.tickAtFixedRate(TICK_RATE);
    }

    public void tick() {
        ecsScheduler.tick();
    }

    public void destroy() {
        ecsScheduler.shutDown();
    }

    // SPECIFICATIONS //
    public void applySystems() {
        for (Solver solver : solvers) {
            ecsScheduler.schedule(() -> solver.solve(1f / TICK_RATE));
        }
    }

    // LIFECYCLE //

    /**
     * Adds a created body to the lifecycle...
    **/
    public Entity createEntity() {
        return ecsWorld.createEntity(UUID.randomUUID().toString());
    }

    public Entity addConstraint(Entity entity, Constraint... constraints) {
        var constraint = entity.get(ConstraintComponent.class);
        if(constraint == null) {
            entity.add(new ConstraintComponent(constraints));
            return entity;
        }

        constraint.constraints.addAll(List.of(constraints));

        return entity;
    }

}
