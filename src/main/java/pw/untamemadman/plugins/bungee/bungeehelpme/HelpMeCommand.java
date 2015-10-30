package pw.untamemadman.plugins.bungee.bungeehelpme;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * Created by untamemadman on 11/08/2015.
 */

public class HelpMeCommand extends Command
{
    public HelpMeCommand(String name)
    {
        super("helpme");
    }

    @Override
    public void execute(CommandSender sender, String[] args)
    {
        if(sender instanceof ProxiedPlayer)
        {
            ProxiedPlayer player = (ProxiedPlayer) sender;
            //Sets some strings
            String Prefix = "&6&l[&4&lHELP&6&l]&r &f";
            String serverName = player.getServer().getInfo().getName();
            String Server = "&6[&a" + serverName + "&6] &f";
            String PlayerName = "&6[&5" + ((ProxiedPlayer) sender).getDisplayName().toString() + "&6] &f";
            String Arguments = "";

            if (!(args.length == 0)) //if the player used arguments
            {

                //loop threw all the arguments and save them to a string
                for(int i = 0; i < args.length; i++){
                    String arg = args[i] + " ";
                    Arguments = Arguments + arg;
                }

                //Send a copy of the message to the player that typed the command.
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', Prefix + Server + PlayerName + Arguments));

                //For every player on the BungeeCord
                for (ProxiedPlayer receivers : ProxyServer.getInstance().getPlayers())
                {
                    //If the player has the permission "helpme.see"
                    if (receivers.hasPermission("helpme.see"))
                    {
                        //They will receive the players cry for help
                        receivers.sendMessage(ChatColor.translateAlternateColorCodes('&', Prefix + Server + PlayerName + Arguments));
                    }
                    else //If the player doesn't have permission do nothing
                    {
                        //NOOP
                    }
                }
            }
            else //If they player didn't use arguments
            {
                //Tells the player to use arguments
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "Incorrect usage please use:"));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "/helpme <problem>"));
            }
        }
    }
}
