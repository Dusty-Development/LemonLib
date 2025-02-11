package net.dustley.lemon.modules.citrus_physics.solver;

import dev.dominion.ecs.api.Dominion;
import net.dustley.lemon.modules.citrus_physics.PhysicsWorld;

public abstract class Solver {

    Dominion ecsWorld;
    PhysicsWorld world;

    public Solver(PhysicsWorld world, Dominion ecs) {
        this.world = world;
        this.ecsWorld = ecs;
    }

    public abstract void solve(double deltaTime);

}
