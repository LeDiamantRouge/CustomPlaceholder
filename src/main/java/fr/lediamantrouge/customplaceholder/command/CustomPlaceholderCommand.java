package fr.lediamantrouge.customplaceholder.command;

import fr.lediamantrouge.customplaceholder.CustomPlaceholder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CustomPlaceholderCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {
        if (args.length == 0 || !args[0].equalsIgnoreCase("reload")) {
            s.sendMessage("§cUsage: /" + label + " <reload>");
        }
        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            CustomPlaceholder.getInstance().reloadConfig();
            CustomPlaceholder.getInstance().registerPlaceholder();
            s.sendMessage("§aConfiguration reloaded !");
        }
        return true;
    }
}
