package me.loggits.mountain.bukkit.sequence;

import me.loggits.mountain.MountainHook;
import me.loggits.mountain.bukkit.utils.SoundUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class Sound {

    private org.bukkit.Sound sound;
    private float volume;
    private float pitch;
    private long delay;
    private Sound other;

    public Sound(org.bukkit.Sound sound, float volume, float pitch, long delay, Sound other) {
        this.sound = sound;
        this.volume = volume;
        this.pitch = pitch;
        this.delay = delay;
        this.other = other;
    }

    public Sound(org.bukkit.Sound sound, float volume, float pitch, long delay) {
        this(sound, volume, pitch, delay, null);
    }

    public Sound(org.bukkit.Sound sound, float volume, float pitch) {
        this(sound, volume, pitch, 0);
    }

    public Sound(org.bukkit.Sound sound) {
        this(sound, 1.0f, 1.0f);
    }

    //EntityChicken
    public static Sound getSequence(List<String> strings) {
        Sound original = null;
        for (String string : strings) {
            if (string.isEmpty()) continue;
            org.bukkit.Sound sound = null;
            Float volume = null;
            Float pitch = null;
            Long delay = null;
            String[] sections = string.split(";");
            try {
                if (sections.length == 2) {
                    delay = Long.parseLong(sections[1]);
                }
                for (String part : sections[0].split(" ")) {
                    if (sound == null) {
                        for (org.bukkit.Sound val : org.bukkit.Sound.values()) {
                            if (val.toString().equalsIgnoreCase(part)) {
                                sound = val;
                            }
                        }
                    } else if (volume == null) {
                        volume = Float.parseFloat(part);
                    } else if (pitch == null) {
                        pitch = Float.parseFloat(part);
                    }
                }
            } catch (NumberFormatException ignored) {
            }
            if (volume == null) volume = 1.0f;
            if (pitch == null) pitch = 1.0f;
            if (delay == null) delay = 0L;
            if (original == null) {
                original = new Sound(sound, volume, pitch, delay);
            } else {
                original.getNoneAttached().attach(new Sound(sound, volume, pitch, delay));
            }
        }
        return original;
    }

    public Sound attach(Sound other) {
        this.other = other;
        return this;
    }

    public Sound getNoneAttached() {
        return this.other == null ? this : this.other.getNoneAttached();
    }

    public void setSound(org.bukkit.Sound sound) {
        this.sound = sound;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public void play() {
        SoundUtils.playSoundAll(this.sound, this.volume, this.pitch);
    }

    public void play(Player player) {
        SoundUtils.playSound(player, this.sound, this.volume, this.pitch);
        Bukkit.getScheduler().runTaskLater(MountainHook.getInstance(), () -> {
            if (this.other != null) {
                this.other.play(player);
            }
        }, this.delay * 20L);
    }

    @Override
    public String toString() {
        return "Sound{" +
               "sound=" + sound +
               ", volume=" + volume +
               ", pitch=" + pitch +
               ", delay=" + delay +
               ", other=" + other +
               '}';
    }
}
