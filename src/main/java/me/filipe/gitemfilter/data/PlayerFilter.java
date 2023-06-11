package me.filipe.gitemfilter.data;

import me.filipe.gitemfilter.GItemFilter;
import me.filipe.gitemfilter.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class PlayerFilter {

    private static final Map<UUID, Boolean> enabled = new HashMap<>();
    private static final Map<UUID, List<ItemStack>> filter = new HashMap<>();

    public static Boolean getEnabled(Player p) {
        return enabled.get(p.getUniqueId());
    }

    public static void toggleEnabled(Player p) {
        Boolean a = enabled.get(p.getUniqueId());
        enabled.put(p.getUniqueId(), !a);
    }

    public static List<ItemStack> getFilter(Player p) {
        if (!filter.containsKey(p.getUniqueId())) {
            List<ItemStack> a = new ArrayList<>();
            filter.put(p.getUniqueId(), a);
        }

        return filter.get(p.getUniqueId());
    }

    public static void addFilter(Player p, ItemStack item) {
        List<ItemStack> a = getFilter(p);
        a.add(item);
        filter.put(p.getUniqueId(), a);
    }

    public static void removeFilter(Player p, ItemStack item) {
        List<ItemStack> a = getFilter(p);
        a.remove(item);
        filter.put(p.getUniqueId(), a);
    }

    public static void load(GItemFilter plugin, Player p) {
        if (enabled.containsKey(p.getUniqueId())) return;

        if (plugin.data.getConfig().contains("players." + p.getUniqueId())) {
            enabled.put(p.getUniqueId(), plugin.data.getConfig().getBoolean("players." + p.getUniqueId() + ".enabled"));
            filter.put(p.getUniqueId(), (List<ItemStack>) plugin.data.getConfig().get("players." + p.getUniqueId() + ".filter"));
        } else {
            enabled.put(p.getUniqueId(), true);
            filter.put(p.getUniqueId(), null);
        }
    }

    public static void saveConfig(GItemFilter plugin) {
        for (Map.Entry<UUID, Boolean> entry : enabled.entrySet()) {
            plugin.data.getConfig().set("players." + entry.getKey() + ".enabled", entry.getValue());
        }

        for (Map.Entry<UUID, List<ItemStack>> entry : filter.entrySet()) {
            plugin.data.getConfig().set("players." + entry.getKey() + ".filter", entry.getValue());
        }

        plugin.data.saveConfig();
    }

}
