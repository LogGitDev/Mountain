package me.loggits.mountain.bukkit.nbt;

import net.minecraft.server.v1_16_R3.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class NBTDataHandler {
    public static String getNBTString(NBTTagCompound tag, NamespacedKey path) {
        return tag.getString(path.toString());
    }

    public static Location getNBTLocation(NBTTagCompound tag, NamespacedKey path) {
        String[] data = getNBTString(tag, path).split(":");
        return new Location(
                Bukkit.getWorld(data[0]),
                Double.parseDouble(data[1]),
                Double.parseDouble(data[2]),
                Double.parseDouble(data[3])
        );
    }

    public static byte[] getNBTArray(NBTTagCompound tag, NamespacedKey path) {
        return tag.getByteArray(path.toString());
    }

    public static int getNBTInteger(NBTTagCompound tag, NamespacedKey path) {
        return tag.getInt(path.toString());
    }

    public static byte getNBTByte(NBTTagCompound tag, NamespacedKey path) {
        return tag.getByte(path.toString());
    }

    public static boolean getNBTBoolean(NBTTagCompound tag, NamespacedKey path) {
        return tag.getBoolean(path.toString());
    }

    public static Long getNBTLong(NBTTagCompound tag, NamespacedKey path) {
        return tag.getLong(path.toString());
    }

    public static NBTTagCompound getTag(ItemStack is, boolean create) {
        net.minecraft.server.v1_16_R3.ItemStack item = CraftItemStack.asNMSCopy(is);
        return create ? item.getOrCreateTag() : item.getTag();
    }

    public static ItemStack addNBTData(ItemStack is, NamespacedKey path, String data) {
        net.minecraft.server.v1_16_R3.ItemStack item = CraftItemStack.asNMSCopy(is);
        NBTTagCompound tag = item.getOrCreateTag();
        tag.setString(path.toString(), data);
        item.setTag(tag);
        return CraftItemStack.asBukkitCopy(item);
    }

    public static ItemStack addNBTData(ItemStack is, NamespacedKey path, byte[] array) {
        net.minecraft.server.v1_16_R3.ItemStack item = CraftItemStack.asNMSCopy(is);
        NBTTagCompound tag = item.getOrCreateTag();
        tag.setByteArray(path.toString(), array);
        item.setTag(tag);
        return CraftItemStack.asBukkitCopy(item);
    }

    public static ItemStack addNBTData(ItemStack is, NamespacedKey path, byte b) {
        net.minecraft.server.v1_16_R3.ItemStack item = CraftItemStack.asNMSCopy(is);
        NBTTagCompound tag = item.getOrCreateTag();
        tag.setByte(path.toString(), b);
        item.setTag(tag);
        return CraftItemStack.asBukkitCopy(item);
    }

    public static ItemStack addNBTData(ItemStack is, NamespacedKey path, boolean b) {
        net.minecraft.server.v1_16_R3.ItemStack item = CraftItemStack.asNMSCopy(is);
        NBTTagCompound tag = item.getOrCreateTag();
        tag.setBoolean(path.toString(), b);
        item.setTag(tag);
        return CraftItemStack.asBukkitCopy(item);
    }

    public static ItemStack addNBTData(ItemStack is, NamespacedKey path, Long l) {
        net.minecraft.server.v1_16_R3.ItemStack item = CraftItemStack.asNMSCopy(is);
        NBTTagCompound tag = item.getOrCreateTag();
        tag.setLong(path.toString(), l);
        item.setTag(tag);
        return CraftItemStack.asBukkitCopy(item);
    }

    public static ItemStack addNBTData(ItemStack is, NamespacedKey path, int i) {
        net.minecraft.server.v1_16_R3.ItemStack item = CraftItemStack.asNMSCopy(is);
        NBTTagCompound tag = item.getOrCreateTag();
        tag.setInt(path.toString(), i);
        item.setTag(tag);
        return CraftItemStack.asBukkitCopy(item);
    }

    public static ItemStack addNBTData(ItemStack is, NamespacedKey path, Location location) {
        return addNBTData(
                is,
                path,
                location.getWorld().getName()
                + ":"
                + location.getBlockX()
                + ":"
                + location.getBlockY()
                + ":"
                + location.getBlockZ()
        );
    }

    public static ItemStack addNBTData(ItemStack is, NamespacedKey path, UUID uuid) {
        return addNBTData(is, path, uuid.toString());
    }
}
