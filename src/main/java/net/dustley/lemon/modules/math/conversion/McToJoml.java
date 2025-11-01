package net.dustley.lemon.modules.math.conversion;

import net.minecraft.util.math.Vec3d;
import org.joml.Vector3d;

public class McToJoml {

    public static Vector3d fromVec3d(Vec3d vec) { return new Vector3d(vec.x, vec.y, vec.z); }

}
