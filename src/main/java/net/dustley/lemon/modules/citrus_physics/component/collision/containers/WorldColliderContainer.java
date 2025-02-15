package net.dustley.lemon.modules.citrus_physics.component.collision.containers;

import net.dustley.lemon.modules.citrus_physics.PhysicsWorld;
import net.dustley.lemon.modules.citrus_physics.component.ActorComponent;

public class WorldColliderContainer extends ColliderContainerComponent {

    public void solve(PhysicsWorld physics, ActorComponent actor, double deltaTime) {
        var ecsWorld = physics.ecsWorld;
//
//        ecsWorld.findEntitiesWith(ActorComponent.class, ColliderContainerComponent.class).stream().forEach(result -> {
//            var otherActor = result.comp1();
//            var container = result.comp2();
//
//            for (Collider shape : shapes) {
//                for (Collider otherShape : container.shapes) {
//                    shape.solve(actor, Optional.of(otherActor), otherShape);
//                }
//            }
//        });
    }

}
