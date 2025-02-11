package net.dustley.lemon.modules.math.conversion;

import net.minecraft.util.math.Vec3d;
import org.joml.Vector3d;

public class JomlToMC {

    public static Vec3d fromVector3d(Vector3d vec) { return new Vec3d(vec.x, vec.y, vec.z); }

}
