package me.loggits.mountain.bukkit.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundUtils {

    public static void playSound(Player p, org.bukkit.Sound sound) {
        playSound(p, sound, 10, 1);
    }

    public static void playSound(Player p, org.bukkit.Sound sound, float volume, float pitch) {
        playSound(p, sound, volume, pitch, false);
    }

    public static void playSound(Player p, org.bukkit.Sound sound, float volume, float pitch, boolean eyes) {
        p.playSound(eyes ? p.getEyeLocation() : p.getLocation(), sound, volume, pitch);
    }

    public static void playSoundNearby(Player p, org.bukkit.Sound sound, float volume, float pitch) {
        playSoundNearby(p.getLocation(), sound, volume, pitch);
    }

    public static void playSoundNearby(Location loc, org.bukkit.Sound sound, float volume, float pitch) {
        if (loc.getWorld() == null) return;
        loc.getWorld().playSound(loc, sound, volume, pitch);
    }

    public static void playSoundAll(org.bukkit.Sound sound) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            playSound(player, sound);
        }
    }

    public static void playSoundAll(Sound sound, float volume, float pitch) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            playSound(player, sound, volume, pitch);
        }
    }
}
