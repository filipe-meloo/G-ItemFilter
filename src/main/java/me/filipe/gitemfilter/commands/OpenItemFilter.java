package me.filipe.gitemfilter.commands;

import me.filipe.gitemfilter.GItemFilter;
import me.filipe.gitemfilter.menus.ItemFilter;
import me.filipe.gitemfilter.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OpenItemFilter implements CommandExecutor {

    private GItemFilter plugin;
    public OpenItemFilter(GItemFilter plugin) {
        this.plugin = plugin;
        plugin.getCommand("itemfilter").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("itemfilter")) {

            if (!(sender instanceof Player)) {
                sender.sendMessage("Apenas players");
                return true;
            }

            Player player = (Player) sender;
            player.openInventory(ItemFilter.getInv(player));
            player.sendMessage(Utils.chat("&aAbriu o menu de filtro de items!"));
            player.playSound(player.getLocation(), "ui.button.click", 1, 1);

            return true;
        }

        return false;
    }
}
