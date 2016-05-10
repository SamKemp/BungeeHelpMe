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
import java.io.InputStream;
import java.nio.file.Files;

public class BungeeHelpMe extends Plugin
{
    @Override
    public void onEnable()
    {
        //Config handling
        loadConfig();

        //Command handling
        getProxy().getPluginManager().registerCommand(this, new HelpMeCommand("helpme"));
        getProxy().getPluginManager().registerCommand(this, new HelpReplyCommand("helpreply"));
    }

    public void loadConfig()
    {
        if (!getDataFolder().exists())
            getDataFolder().mkdir();

        File file = new File(getDataFolder(), "helpme.log");

        if(!file.exists())
        {
            try (InputStream in = getResourceAsStream("helpme.log"))
            {
                Files.copy(getResourceAsStream("helpme.log"), file.toPath());
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        Configuration config = null;

        try
        {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "helpme.log"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        try
        {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, new File(getDataFolder(), "helpme.log"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}