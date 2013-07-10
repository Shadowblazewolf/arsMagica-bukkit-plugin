package me.lionbryce.arsMagica;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class arsMagica extends JavaPlugin
{
	public final Logger logger = Logger.getLogger("minecraft");
	public static arsMagica plugin;
	
	public static String args1;
	public static String args2;
	public static String args3;
	public static String ChatStart = (ChatColor.BLACK + "[" + ChatColor.GOLD + "ArsMagica" + ChatColor.BLACK + "] ");
	
    private ManaManager manaManager = new ManaManager();
	
	@Override
	public void onDisable()
	{
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " has been Diabled");
	}
	@Override
	public void onEnable()
	{
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " version " + pdfFile.getVersion() + " has been Enabled");
		
	}
	public boolean onCommand (CommandSender sender, Command cmd, String Label, String[] args)
	{
		
		Player target = sender.getServer().getPlayer(args[2]);
		Player player = (Player) sender;
		
		if (sender instanceof Player){
			if (cmd.getLabel().equalsIgnoreCase("AM")){
				if (args.length == 1){
					if (args[0].equalsIgnoreCase("D")){
						sender.sendMessage(ChatStart + "the Diminished spell options are...");
						sender.sendMessage(ChatStart + "all spells /AM D all");
						}
					else if (args[0].equalsIgnoreCase("N")){
						sender.sendMessage(ChatStart + "the Normal spell options are...");
						sender.sendMessage(ChatStart + "all spells /AM N all");
						}
					else if (args[0].equalsIgnoreCase("A")){
						sender.sendMessage(ChatStart + "the Augmented spell options are...");
						sender.sendMessage(ChatStart + "all spells /AM A all");	
					}
					else if (args[0].equalsIgnoreCase("B")){
						sender.sendMessage(ChatStart + "these are the basic spells....they will cost no mana");
						sender.sendMessage(ChatStart + "/AM B CheckMana (target)");
						}
					}
				else if (args.length == 2){
					if (args[1].equalsIgnoreCase("heal")){
						if (args[0].equalsIgnoreCase("D")){
							((Player) sender).setHealth(((Player) sender).getHealth() + 3);
						}
						else if (args[0].equalsIgnoreCase("N")){
							((Player) sender).setHealth(((Player) sender).getHealth() + 7);						
						}
						else if (args[0].equalsIgnoreCase("A")){
							((Player) sender).setHealth(20);
						}
					}
					else if (args[0].equalsIgnoreCase("B"))
					{
						if (args[1].equalsIgnoreCase("checkmana")){
							sender.sendMessage(ChatStart + "your current mana is " + ManaManager.getManaRemaining(player));
							sender.sendMessage(ChatStart + "your max mana is " + ManaManager.getPlayerMaxMana(player));
						}
					}
				}
				else if (args.length == 3){
					if (args[1].equalsIgnoreCase("healother"))
					{
						if (target.isOnline()){
							if (args[0].equalsIgnoreCase("D")){
								target.setHealth(target.getHealth() + 3);
							}
							else if (args[0].equalsIgnoreCase("N")){
								target.setHealth(target.getHealth() + 7);
							}
							else if (args[0].equalsIgnoreCase("A")){
								target.setHealth(20);
							}
						}
					}
					else if (args[0].equalsIgnoreCase("B")){
						if (args[1].equalsIgnoreCase("checkmana")){
							if (target.isOnline())
							{
								sender.sendMessage(ChatStart + target.getDisplayName() + "'s current mana is " + ManaManager.getManaRemaining(target));
								sender.sendMessage(ChatStart + target.getDisplayName() + "'s max mana is " + ManaManager.getPlayerMaxMana(target));
							}
						}
					}
				}
				else{
					sender.sendMessage(ChatStart + "this is the main page for the plugin ArsMagica");
					sender.sendMessage(ChatStart + "http://dev.bukkit.org/bukkit-plugins/arsmagica/");
					sender.sendMessage(ChatStart + "to see all the Diminished spell options type /AM D");
					sender.sendMessage(ChatStart + "to see all the Normal spell options type /AM N");
					sender.sendMessage(ChatStart + "to see all the Augmented spell options type /AM A");
					sender.sendMessage(ChatStart + "to see all the basic, you will need these or die, spells type /AM B");
				}
			}
		}
		
		return false;
	}
    public ManaManager getManaManager()
    {
        return manaManager;
    }
}