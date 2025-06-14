package net.dustley.lemon.modules.camera_effects.screen_shake;

import net.dustley.lemon.modules.math.easing.Easing;
import net.minecraft.client.render.Camera;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3d;
import org.joml.Vector3f;

import static java.lang.Math.clamp;
import static java.lang.Math.max;

public class ScreenShake {

    // Duration
    public int duration = 20;
    public int age = 0;

    // Intensity
    public float intensity = 1f;
    public Easing startCurve = Easing.LINEAR;
    public Easing endCurve = Easing.LINEAR_INV;

    // Position
    public boolean isPositional = false;
    public Vec3d position = Vec3d.ZERO;
    public float maxDistance = 20f;
    public Easing distanceCurve = Easing.LINEAR_INV;

    // Constructors
    public ScreenShake() { }
    public ScreenShake(int duration) { this.duration = duration; }
    public ScreenShake(int duration, float intensity) { this.duration = duration; this.intensity = intensity; }
    public ScreenShake(int duration, float intensity, Easing startCurve, Easing endCurve) { this.duration = duration; this.intensity = intensity; this.startCurve = startCurve; this.endCurve = endCurve; }

    // Positional mode
    public void setupPositional(Vec3d position) { setupPositional(position, maxDistance); }
    public void setupPositional(Vec3d position, float maxDistance) { setupPositional(position, maxDistance, distanceCurve); }
    public void setupPositional(Vec3d position, float maxDistance, Easing curve) {
        isPositional = true;
        this.position = position;
        this.maxDistance = maxDistance;
        this.distanceCurve = curve;
    }

    // Update
    public float update(Camera camera) {
        // Update age
        age++;
        float progress = age / (float) duration;

        // Select curve
        Easing curve;
        if (progress >= 0.5f) {
            curve = endCurve;
            progress = progress - 0.5f;
        } else {
            curve = startCurve;
        }

        // Apply strength
        float strength = MathHelper.lerp(curve.ease(progress, 0, 1, 0.5f), 0f, intensity);
        if(!isPositional) { return strength; }

        float distance = (float) position.distanceTo(camera.getPos());
        if (distance > maxDistance) { return 0; }

        float distanceProgress = clamp(MathHelper.map(distance, 0f, maxDistance, 0f, 1f), 0f, 1f);
        float distanceMultiplier = distanceCurve.ease(distanceProgress, 0, 1, 1); // Inverted

        Vec3d direction = position.subtract(camera.getPos()).normalize();
        float angle = max(0, camera.getHorizontalPlane().dot(direction.toVector3f()));
        return strength * distanceMultiplier * angle;
    }


}
