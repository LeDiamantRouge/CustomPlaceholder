package fr.lediamantrouge.customplaceholder;

import fr.lediamantrouge.customplaceholder.command.CustomPlaceholderCommand;
import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class CustomPlaceholder extends JavaPlugin {

    private static CustomPlaceholder instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        registerPlaceholder();

        getCommand("customplaceholder").setExecutor(new CustomPlaceholderCommand());
    }

    public void registerPlaceholder() {
        if (getConfig().getConfigurationSection("custom-placeholder") != null) {
            for (String key : getConfig().getConfigurationSection("custom-placeholder").getKeys(false)) {
                new PlaceholderExpansion() {

                    @Override
                    public @NotNull String getIdentifier() {
                        return key;
                    }

                    @Override
                    public @NotNull String getAuthor() {
                        return "CustomPlaceholder";
                    }

                    @Override
                    public @NotNull String getVersion() {
                        return CustomPlaceholder.this.getDescription().getVersion();
                    }

                    @Override
                    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
                        return getConfig().getConfigurationSection("custom-placeholder").getString(key);
                    }
                }.register();
                getLogger().info("Registered custom placeholder " + key);
            }
            getLogger().info("All custom placeholders have been registered");
        } else {
            getLogger().info("No placustom placeholders have been registered !");
        }
    }

    public static CustomPlaceholder getInstance() {
        return instance;
    }
}
