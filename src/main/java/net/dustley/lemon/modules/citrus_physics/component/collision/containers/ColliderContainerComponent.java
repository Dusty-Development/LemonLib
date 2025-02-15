package net.dustley.lemon.modules.citrus_physics.component.collision.containers;

import net.dustley.lemon.modules.citrus_physics.component.ActorComponent;
import net.dustley.lemon.modules.citrus_physics.component.collision.colliders.Collider;

import java.util.ArrayList;

public abstract class ColliderContainerComponent {

    public ArrayList<Collider> shapes = new ArrayList<>();

    public abstract void solve(ActorComponent actor, double deltaTime);

}
