/**
 * Copyright (c) 2020 retrooper
 */
package io.github.retrooper.packetevents.utils.versionlookup;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ProtocolSupportUtils {
    public static boolean isAvailable() {
        return Bukkit.getPluginManager().isPluginEnabled("ProtocolSupport");
    }

    public static int getProtocolVersion(final Player player) {
        return ProtocolSupportAPIAccessor.getProtocolVersion(player);
    }
}
