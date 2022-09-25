package org.kayteam.actionapi;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.kayteam.actionapi.actions.*;

import java.util.HashMap;
import java.util.List;

public class ActionManager {

    private final JavaPlugin javaPlugin;
    private Economy economy = null;
    private final HashMap< String , ExternalAction > externalActions = new HashMap<>();

    public ActionManager(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
    }

    public JavaPlugin getJavaPlugin() {
        return javaPlugin;
    }

    public void registerManager() {

        if (getJavaPlugin().getServer().getPluginManager().getPlugin("Vault") != null) {
            RegisteredServiceProvider<Economy> registeredServiceProvider = getJavaPlugin().getServer().getServicesManager().getRegistration(Economy.class);
            if (registeredServiceProvider != null) {
                economy = registeredServiceProvider.getProvider();
            }
        }

    }

    public void reloadManager() {

    }

    public Economy getEconomy() {
        return economy;
    }

    public HashMap<String, ExternalAction> getExternalActions() {
        return externalActions;
    }

    public boolean existExternalAction( String type ) {
        return externalActions.containsKey( type );
    }

    public void addExternalAction( String type , ExternalAction externalAction ) {
        externalActions.put( type , externalAction );
    }

    public void removeExternalAction( String type ) {
        externalActions.remove( type );
    }

    public ExternalAction getExternalAction( String type ) {
        return externalActions.get( type );
    }

    public Action loadAction(String format ) {

        Action action = null;

        String type = getType( format );

        if ( externalActions.containsKey( type ) ) {

            ExternalAction externalAction = externalActions.get( type );

            action = externalAction.generateAction( format );

        } else {

            switch ( type ) {

                case "console": {
                    action = new ConsoleAction(format);
                    break;
                }

                case "player": {
                    action = new PlayerAction(format);
                    break;
                }

                case "message": {
                    action = new MessageAction(format);
                    break;
                }

                case "sound": {
                    action = new SoundAction(format);
                    break;
                }

                case "potion": {
                    action = new PotionAction(format);
                    break;
                }

                case "actionbar": {
                    action = new ActionBarAction(format);
                    break;
                }

                case "removepotion": {
                    action = new RemovePotionAction(format);
                    break;
                }

            }

        }

        return action;

    }

    public Actions loadActions( List<String> formats ) {

        Actions actions = new Actions();

        for ( String format : formats ) {

            Action action = loadAction( format );

            if ( action != null ) {

                action.setActionManager( this );

                actions.addAction(action);

            }

        }

        return actions;

    }

    private String getType( String format ) {

        String type = format;

        if ( format.contains(" ") ) {

            type = format.split( " " )[0];

        }

        type = type.replaceFirst( "\\[" , "");

        type = type.replaceFirst( "]" , "");

        return type;

    }

}