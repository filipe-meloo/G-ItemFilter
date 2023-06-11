package me.filipe.gitemfilter.commands;

import me.filipe.gitemfilter.GItemFilter;
import me.filipe.gitemfilter.data.PlayerFilter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class filtertext implements CommandExecutor {

    private GItemFilter plugin;
    public filtertext(GItemFilter plugin) {
        this.plugin = plugin;
        plugin.getCommand("filtertext").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("filtertext")) {

            if(!(sender instanceof Player)) {
                sender.sendMessage("Apenas players");
                return true;
            }

            Player player = (Player) sender;

            player.sendMessage(PlayerFilter.getFilter(player).toString());

            return true;
        }

        return false;
    }
}
