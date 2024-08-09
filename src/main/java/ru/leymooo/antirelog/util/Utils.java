package ru.leymooo.antirelog.util;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.platform.bukkit.BukkitComponentSerializer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import ru.leymooo.antirelog.Antirelog;

public class Utils {
    /**
     * Склоняем слова правильно
     *
     * @param ed неизменяемая часть слова, которую нужно просклонять
     * @param a  окончание для слова, в случае если число оканчивается на 1
     * @param b  окончание для слова, в случае если число оканчивается на 2, 3 или 4
     * @param c  окончание для слова, в случае если число оканчивается на 0, 5...9 и 11...19
     * @param n  число, по которому идёт склонение
     * @return правильно просклонённое слово по числу
     */
    public static String formatTimeUnit(String ed, String a, String b, String c, int n) {
        if (n < 0) {
            n = -n;
        }
        int last = n % 100;
        if (last > 10 && last < 21) {
            return ed + c;
        }
        last = n % 10;
        if (last == 0 || last > 4) {
            return ed + c;
        }
        if (last == 1) {
            return ed + a;
        }
        return ed + b;
    }

    public static String color(String message) {
        return BukkitComponentSerializer.legacy().serialize(MiniMessage.miniMessage().deserialize(message));
    }

    public static void sendMessage(Player player, String message) {
        Component formattedMessage = MiniMessage.miniMessage().deserialize(message);
        Audience audience = Antirelog.getPlugin(Antirelog.class).adventure().player(player);
        audience.sendMessage(formattedMessage);
    }

    public static void broadcastMessage(String message) {
        Audience audience = Antirelog.getPlugin(Antirelog.class).adventure().all();
        Component formattedMessage = MiniMessage.miniMessage().deserialize(message);
        audience.sendMessage(formattedMessage);
    }

    public static String replaceTime(String message, int time) {
        return message.replace("%time%", Integer.toString(time)).replace("%formated-sec%",
                formatTimeUnit("секунд", "у", "ы", "", time));
    }

}
