/**
 * Copyright (c) 2020 retrooper
 */
package io.github.retrooper.packetevents.packetwrappers.in.flying;


import io.github.retrooper.packetevents.packet.PacketTypeClasses;
import io.github.retrooper.packetevents.packetwrappers.api.WrappedPacket;
import io.github.retrooper.packetevents.reflectionutils.Reflection;

public class WrappedPacketInFlying extends WrappedPacket {
    private static Class<?> flyingClass;
    protected double x;
    protected double y;
    protected double z;
    protected float yaw;
    protected float pitch;
    protected boolean onGround;
    protected boolean isPosition;
    protected boolean isLook;
    public WrappedPacketInFlying(Object packet) {
        super(packet);
    }

    public static void load() {
        flyingClass = PacketTypeClasses.Client.FLYING;
    }

    @Override
    protected void setup() {
        try {
            //x, y, z, yaw pitch, onGround, isPosition, isLook
            this.x = Reflection.getField(flyingClass, double.class, 0).getDouble(packet);
            this.y = Reflection.getField(flyingClass, double.class, 1).getDouble(packet);
            this.z = Reflection.getField(flyingClass, double.class, 2).getDouble(packet);

            this.yaw = Reflection.getField(flyingClass, float.class, 0).getFloat(packet);
            this.pitch = Reflection.getField(flyingClass, float.class, 1).getFloat(packet);

            this.onGround = Reflection.getField(flyingClass, boolean.class, 0).getBoolean(packet);

            this.isPosition = Reflection.getField(flyingClass, boolean.class, 1).getBoolean(packet);
            this.isLook = Reflection.getField(flyingClass, boolean.class, 2).getBoolean(packet);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public final double getX() {
        return x;
    }

    public final double getY() {
        return y;
    }

    public final double getZ() {
        return z;
    }

    public final float getYaw() {
        return yaw;
    }

    public final float getPitch() {
        return pitch;
    }

    public final boolean isOnGround() {
        return onGround;
    }

    public final boolean isPosition() {
        return isPosition;
    }

    public final boolean isLook() {
        return isLook;
    }

    public static class WrappedPacketInPosition extends WrappedPacketInFlying {

        public WrappedPacketInPosition(Object packet) {
            super(packet);
        }

        @Override
        protected void setup() {
            try {
                this.x = Reflection.getField(flyingClass, double.class, 0).getDouble(packet);
                this.y = Reflection.getField(flyingClass, double.class, 1).getDouble(packet);
                this.z = Reflection.getField(flyingClass, double.class, 2).getDouble(packet);

                this.yaw = Reflection.getField(flyingClass, float.class, 0).getFloat(packet);
                this.pitch = Reflection.getField(flyingClass, float.class, 1).getFloat(packet);

                this.onGround = Reflection.getField(flyingClass, boolean.class, 0).getBoolean(packet);

                this.isPosition = Reflection.getField(flyingClass, boolean.class, 1).getBoolean(packet);
                this.isLook = Reflection.getField(flyingClass, boolean.class, 2).getBoolean(packet);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static class WrappedPacketInPosition_Look extends WrappedPacketInFlying {

        public WrappedPacketInPosition_Look(Object packet) {
            super(packet);
        }

        @Override
        protected void setup() {
            try {
                this.x = Reflection.getField(flyingClass, double.class, 0).getDouble(packet);
                this.y = Reflection.getField(flyingClass, double.class, 1).getDouble(packet);
                this.z = Reflection.getField(flyingClass, double.class, 2).getDouble(packet);

                this.yaw = Reflection.getField(flyingClass, float.class, 0).getFloat(packet);
                this.pitch = Reflection.getField(flyingClass, float.class, 1).getFloat(packet);

                this.onGround = Reflection.getField(flyingClass, boolean.class, 0).getBoolean(packet);

                this.isPosition = Reflection.getField(flyingClass, boolean.class, 1).getBoolean(packet);
                this.isLook = Reflection.getField(flyingClass, boolean.class, 2).getBoolean(packet);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public static class WrappedPacketInLook extends WrappedPacketInFlying {

        public WrappedPacketInLook(Object packet) {
            super(packet);
        }

        @Override
        protected void setup() {
            try {
                this.x = Reflection.getField(flyingClass, double.class, 0).getDouble(packet);
                this.y = Reflection.getField(flyingClass, double.class, 1).getDouble(packet);
                this.z = Reflection.getField(flyingClass, double.class, 2).getDouble(packet);

                this.yaw = Reflection.getField(flyingClass, float.class, 0).getFloat(packet);
                this.pitch = Reflection.getField(flyingClass, float.class, 1).getFloat(packet);

                this.onGround = Reflection.getField(flyingClass, boolean.class, 0).getBoolean(packet);

                this.isPosition = Reflection.getField(flyingClass, boolean.class, 1).getBoolean(packet);
                this.isLook = Reflection.getField(flyingClass, boolean.class, 2).getBoolean(packet);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
