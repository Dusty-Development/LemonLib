package net.dustley.lemon.modules.camera_effects.screen_shake;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.util.math.noise.PerlinNoiseSampler;
import net.minecraft.util.math.random.Random;

import java.util.ArrayList;
import java.util.random.RandomGenerator;

import static java.lang.Math.pow;

// Credit to lodestone for help
public class ScreenShakeManager {

    public static ArrayList<ScreenShake> activeInstances = new ArrayList<>();
    public static PerlinNoiseSampler noise = new PerlinNoiseSampler(Random.create());

    public static float intensity;
    public static float yawOffset;
    public static float pitchOffset;

    public static void cameraTick(Camera camera) {
        if (intensity >= 0.1) {
            yawOffset = randomizeOffset(10);
            pitchOffset = randomizeOffset(-10);
            camera.setRotation(camera.getYaw() + yawOffset, camera.getPitch() + pitchOffset);
        }
    }

    public static void clientTick(Camera camera) {
        float sum = (float) activeInstances.stream().mapToDouble(inst -> inst.update(camera)).sum();

        intensity = (float) pow(sum, 3f);
        activeInstances.removeIf(instance -> instance.age >= instance.duration);
    }

    public static void createScreenShake(ScreenShake instance) {
        activeInstances.add(instance);
    }

    public static float randomizeOffset(int offset) {
        float sampled = 0;
        if (MinecraftClient.getInstance().world != null) {
            sampled = (float) noise.sample((MinecraftClient.getInstance().world.getTime() % 24000 + MinecraftClient.getInstance().getRenderTickCounter().getTickProgress(false))/intensity, offset, 0) * 1.5f;
        }
        return -intensity * 2 >= intensity * 2 ? -intensity * 2 : sampled * intensity * 2;
    }

}
