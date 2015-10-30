package pw.untamemadman.plugins.bungee.bungeehelpme;

import net.md_5.bungee.api.plugin.Plugin;

public class BungeeHelpMe extends Plugin
{
    @Override
    public void onEnable()
    {
        getProxy().getPluginManager().registerCommand(this, new HelpMeCommand("helpme"));
        getProxy().getPluginManager().registerCommand(this, new HelpReplyCommand("helpreply"));
    }
}