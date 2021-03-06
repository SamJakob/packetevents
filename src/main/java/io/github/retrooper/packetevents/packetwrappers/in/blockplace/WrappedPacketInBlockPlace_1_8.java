/**
 * Copyright (c) 2020 retrooper
 */
package io.github.retrooper.packetevents.packetwrappers.in.blockplace;

import io.github.retrooper.packetevents.packet.PacketTypeClasses;
import io.github.retrooper.packetevents.packetwrappers.api.WrappedPacket;
import io.github.retrooper.packetevents.reflectionutils.Reflection;
import io.github.retrooper.packetevents.utils.NMSUtils;
import io.github.retrooper.packetevents.utils.vector.Vector3i;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.InvocationTargetException;

final class WrappedPacketInBlockPlace_1_8 extends WrappedPacket {
    private static Class<?> blockPlaceClass, blockPositionClass, blockPositionSuperClass;
    private Vector3i blockPosition;
    private ItemStack itemStack;

    WrappedPacketInBlockPlace_1_8(final Object packet) {
        super(packet);
    }

    protected static void load() {
        blockPlaceClass = PacketTypeClasses.Client.BLOCK_PLACE;
        try {
            blockPositionClass = NMSUtils.getNMSClass("BlockPosition");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        blockPositionSuperClass = blockPositionClass.getSuperclass();
    }

    @Override
    protected void setup() {
        try {
            Object nmsBlockPos = Reflection.getField(blockPlaceClass, blockPositionClass, 1).get(packet);
            this.blockPosition = new Vector3i(0, 0, 0);
            this.blockPosition.x = (int) Reflection.getMethod(blockPositionSuperClass, "getX", 0).invoke(nmsBlockPos);
            this.blockPosition.y = (int) Reflection.getMethod(blockPositionSuperClass, "getY", 0).invoke(nmsBlockPos);
            this.blockPosition.z = (int) Reflection.getMethod(blockPositionSuperClass, "getZ", 0).invoke(nmsBlockPos);


            this.itemStack = NMSUtils.toBukkitItemStack(Reflection.getField(blockPlaceClass, NMSUtils.nmsItemStackClass, 0).get(packet));
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    public Vector3i getBlockPosition() {
        return blockPosition;
    }

    public int getBlockPositionX() {
        return blockPosition.x;
    }

    public int getBlockPositionY() {
        return blockPosition.y;
    }

    public int getBlockPositionZ() {
        return blockPosition.z;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
