package net.dustley.lemon.modules.citrus_physics.component.collision;

import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3d;

public class AABBColliderComponent extends ColliderComponent {

    Box bounds;

    public AABBColliderComponent(Vector3d size) {
        bounds = Box.of(Vec3d.ZERO, size.x, size.y, size.z);
    }

}
