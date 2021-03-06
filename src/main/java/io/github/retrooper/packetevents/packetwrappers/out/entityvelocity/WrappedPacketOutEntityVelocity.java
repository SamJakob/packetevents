/**
 * Copyright (c) 2020 retrooper
 */
package io.github.retrooper.packetevents.packetwrappers.out.entityvelocity;

import io.github.retrooper.packetevents.enums.ServerVersion;
import io.github.retrooper.packetevents.packet.PacketTypeClasses;
import io.github.retrooper.packetevents.packetwrappers.Sendable;
import io.github.retrooper.packetevents.packetwrappers.api.WrappedPacket;
import io.github.retrooper.packetevents.reflectionutils.Reflection;
import io.github.retrooper.packetevents.utils.NMSUtils;
import org.bukkit.entity.Entity;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public final class WrappedPacketOutEntityVelocity extends WrappedPacket implements Sendable {
    private static Constructor<?> velocityConstructor, vec3dConstructor;
    private static Class<?> velocityClass, vec3dClass;
    private static boolean isVec3dPresent;
    private int entityId;
    private double velocityX, velocityY, velocityZ;
    private Entity entity;
    public WrappedPacketOutEntityVelocity(final Object packet) {
        super(packet);
    }

    public WrappedPacketOutEntityVelocity(final Entity entity, final double velocityX, final double velocityY, final double velocityZ) {
        super();
        this.entityId = entity.getEntityId();
        this.entity = entity;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.velocityZ = velocityZ;
    }

    /**
     * Velocity Constructor parameter types:
     * 1.7.10 -&gt; 1.13.2 use int, double, double, double style,
     * 1.14+ use int, Vec3D style
     */
    public static void load() {
        velocityClass = PacketTypeClasses.Server.ENTITY_VELOCITY;
        if (version.isHigherThan(ServerVersion.v_1_13_2)) {
            try {
                vec3dClass = NMSUtils.getNMSClass("Vec3D");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        try {
            velocityConstructor = velocityClass.getConstructor(int.class, double.class, double.class, double.class);
        } catch (NoSuchMethodException e) {
            //That is fine, just a newer version
            try {
                velocityConstructor = velocityClass.getConstructor(int.class, vec3dClass);
                isVec3dPresent = true;
                //vec3d constructor
                vec3dConstructor = vec3dClass.getConstructor(double.class, double.class, double.class);
            } catch (NoSuchMethodException e2) {
                e2.printStackTrace();
            }

        }
    }

    @Override
    protected void setup() {
        try {
            //ENTITY ID
            this.entityId = Reflection.getField(velocityClass, int.class, 0).getInt(packet);

            int x = Reflection.getField(velocityClass, int.class, 1).getInt(packet);
            int y = Reflection.getField(velocityClass, int.class, 2).getInt(packet);
            int z = Reflection.getField(velocityClass, int.class, 3).getInt(packet);

            //VELOCITY XYZ
            this.velocityX = x / 8000.0D;
            this.velocityY = y / 8000.0D;
            this.velocityZ = z / 8000.0D;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public int getEntityId() {
        return entityId;
    }

    public Entity getEntity() {
        if (entity == null) {
            return NMSUtils.getEntityById(this.entityId);
        } else {
            return entity;
        }
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public double getVelocityZ() {
        return velocityZ;
    }

    @Override
    public Object asNMSPacket() {
        if (!isVec3dPresent) {
            try {
                return velocityConstructor.newInstance(entityId, velocityX, velocityY, velocityZ);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        } else {
            try {
                return velocityConstructor.newInstance(entityId, vec3dConstructor.newInstance(velocityX, velocityY, velocityZ));
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
