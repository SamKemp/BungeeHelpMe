package pw.untamemadman.plugins.bungee.bungeehelpme;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

/**
 * Created by untamemadman on 12/08/2015.
 */

public class HelpReplyCommand extends Command
{
    public HelpReplyCommand(String name)
    {
        super("helpreply");
    }

    @Override
    public void execute(CommandSender sender, String[] args)
    {
        if(sender instanceof ProxiedPlayer)
        {
            ProxiedPlayer player = (ProxiedPlayer) sender;
            //Sets some strings
            String Prefix = "&6&l[&4&lREPLY&6&l]&r &f";
            String PlayerName = "&6[&5" + ((ProxiedPlayer) sender).getDisplayName().toString() + "&6] &f";
            String Arguments = "";

            if (args.length > 1) //if the player used more then 1 arguments
            {

                //loop through all the arguments and save them to a string
                for(int i = 0; i < args.length; i++)
                {
                    if (i == 0)
                    {
                        //NOOP
                    }
                    else
                    {
                        String arg = args[i] + " ";
                        Arguments = Arguments + arg;
                    }
                }
                String PlayerName2 = "&6[&b" + args[0] + "&6] &f";
                String PlayerName3 = args[0];

                //If the player has the permission "helpme.reply"
                if (sender.hasPermission("helpme.reply"))
                {
                    //Send a copy of the message to the player that typed the command.
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', Prefix + PlayerName + Arguments));

                    //For every player on the BungeeCord
                    for (ProxiedPlayer receivers : ProxyServer.getInstance().getPlayers())
                    {
                        if (receivers.getDisplayName().equalsIgnoreCase(PlayerName3))
                        {
                            //The player will receive a response
                            receivers.sendMessage(ChatColor.translateAlternateColorCodes('&', Prefix + PlayerName + Arguments));
                        }
                        if (receivers.hasPermission("helpme.see")) //Send a copy of the reply to other staff
                        {
                            //They will receive the players cry for help
                            receivers.sendMessage(ChatColor.translateAlternateColorCodes('&', Prefix + PlayerName + PlayerName2 + Arguments));
                        }
                        else //If the player doesn't have permission do nothing
                        {
                            //NOOP
                        }
                    }
                }
                else //If the player doesn't have permission
                {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "You do not have permission to do this."));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "Please contact a server admin if you think this is an error!"));
                }
            }
            else //If they player didn't use arguments
            {
                //Tells the player to use arguments
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "Incorrect usage please use:"));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "/replyme <name> <message>"));
            }
        }
    }
}