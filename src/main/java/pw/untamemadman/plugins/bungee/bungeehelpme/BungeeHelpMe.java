package pw.untamemadman.plugins.bungee.bungeehelpme;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class BungeeHelpMe extends Plugin
{
    @Override
    public void onEnable()
    {
        getProxy().getPluginManager().registerCommand(this, new HelpMeCommand("helpme"));
        getProxy().getPluginManager().registerCommand(this, new HelpReplyCommand("helpreply"));
        loadConfig();
    }

    public void loadConfig()
    {
        //Try to get the config file
        try
        {
            Configuration configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
        }
        catch(IOException i) //Catch the error if the config file doesn't exist
        {
        }
    }

}