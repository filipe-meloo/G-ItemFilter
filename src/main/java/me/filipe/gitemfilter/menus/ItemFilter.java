package me.filipe.gitemfilter.menus;

import me.filipe.gitemfilter.data.PlayerFilter;
import me.filipe.gitemfilter.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemFilter implements InventoryHolder {

    public static Map<String, ItemStack> getItens() {
        Map<String, ItemStack> itens = new HashMap<>();

        List<String> lore = new ArrayList<>();
        lore.add(""); lore.add("&7Clica para &c&l[✗] Desativar&7."); lore.add("");
        itens.put("enabled", Utils.createItem(Material.LIME_STAINED_GLASS_PANE, 1, "&a&l[✔] Ativado", lore));

        lore.clear(); lore.add(""); lore.add("&7Clica para &a&l[✔] Ativar&7."); lore.add("");
        itens.put("disabled", Utils.createItem(Material.RED_STAINED_GLASS_PANE, 1, "&c&l[X] Desativado", lore));

        lore.clear(); lore.add(""); lore.add("&7Clica para &c&l[✗] Não Apanhar&7."); lore.add("");
        itens.put("reverse", Utils.createItem(Material.LIME_STAINED_GLASS_PANE, 1, "&a&l[✔] Apanhar", lore));

        lore.clear(); lore.add(""); lore.add("&7Clica para &a&l[✔] Apanhar&7."); lore.add("");
        itens.put("notreverse", Utils.createItem(Material.RED_STAINED_GLASS_PANE, 1, "&c&l[X] Não Apanhar", lore));

        lore.clear(); lore.add(""); lore.add("&7Clica para &cFechar."); lore.add("");
        itens.put("close", Utils.createItem(Material.BARRIER, 1, "&c&lFechar", lore));

        itens.put("filler", Utils.createItem(Material.BLACK_STAINED_GLASS_PANE, 1, "", new ArrayList<String>()));

        return itens;
    }

    public static Inventory getInv(Player p) {
        Inventory inv = Bukkit.createInventory(new ItemFilter(), 9*3, "Item Filter");

        for (int i = 18; i < 27; i++) {
            inv.setItem(i, getItens().get("filler"));
        }

        if (PlayerFilter.getEnabled(p)) {
            inv.setItem(26, getItens().get("enabled"));
        } else {
            inv.setItem(26, getItens().get("disabled"));
        }

        if (PlayerFilter.getReverse(p)) {
            inv.setItem(18, getItens().get("reverse"));
        } else {
            inv.setItem(18, getItens().get("notreverse"));
        }

        inv.setItem(22, getItens().get("close"));

        List<ItemStack> filter = PlayerFilter.getFilter(p);
        if (filter != null) {
            int j = 0;
            for (ItemStack itemStack : filter) {
                if (itemStack != null) {
                    inv.setItem(j, itemStack);
                    j++;
                }
            }
        }

        return inv;
    }

    @Override
    public Inventory getInventory() {
        return null;
    }
}
