package me.loggits.mountain.common;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.chat.hover.content.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class TextHandler {
    static Pattern rgb = Pattern.compile("(?i)\\{(#[a-f0-9]*)\\}"); // e.g. aaaaa

    public static TextComponent createTextComponent(String message, ClickEvent.Action clickAction, String click, HoverEvent.Action hoverAction, String hover) {
        TextComponent c = new TextComponent(translateString(message));
        c.setClickEvent(new ClickEvent(clickAction, translateString(click)));
        c.setHoverEvent(new HoverEvent(hoverAction, new Text(hover)));
        return c;
    }

    public static TextComponent createCustomText(String text, String hover, String click) {
        TextComponent tc = new TextComponent(translateString(text));
        if (hover != null) tc.setHoverEvent(new HoverEvent(
                HoverEvent.Action.SHOW_TEXT,
                new Text(hover)
        ));
        if (click != null) tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, translateString(click)));
        return tc;
    }

    public static void sendEncapsulatedMessage(CommandSender player, String header, String footer, ArrayList<TextComponent> components) {
        sendMessage(player, header);
        components.forEach(x -> sendMessage(player, x));
        sendMessage(player, footer);
    }

    public static List<String> translateCollection(List<String> l) {
        return l.stream()
                .map(string -> ChatColor.translateAlternateColorCodes('&', string))
                .collect(Collectors.toList());
    }

    public static String translateString(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static String translateString2(String s) {
        String output = translateString(s);
        Matcher matcher = rgb.matcher(output);
        while (matcher.find()) {
            output = output.replace(matcher.group(), ChatColor.of(matcher.group(1)).toString());
        }
        return output;
    }

    public static void sendMessage(CommandSender player, BaseComponent[] component) {
        player.sendMessage(component);
    }

    public static void sendMessage(CommandSender player, TextComponent component) {
        player.sendMessage(component);
    }

    public static void sendMessage(CommandSender player, String component) {
        player.sendMessage(TextComponent.fromLegacyText(component));
    }

    public static void messageSender(CommandSender player, String header, String footer, String... lines) {
        TextHandler.sendMessage(player, header);
        Arrays.stream(lines)
              .forEach(x -> sendMessage(
                      player,
                      TextComponent.fromLegacyText(x.replace("\\n", "\n"))
              ));
        TextHandler.sendMessage(player, footer);
    }

    public static void messageSender(CommandSender player, String header, String footer, List<String> lines) {
        TextHandler.sendMessage(player, header);
        lines.forEach(x -> sendMessage(
                player,
                TextComponent.fromLegacyText(ChatColor.translateAlternateColorCodes('&', x)
                                                      .replace("\\n", "\n"))
        ));
        TextHandler.sendMessage(player, footer);
    }

    public abstract void messageAllTC(TextComponent tc);
}
