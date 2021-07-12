package me.rew.particletest;

import org.bukkit.event.EventHandler;
import org.bukkit.plugin.java.JavaPlugin;

public class ParticleTest extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("particletask").setExecutor(new ParticleTaskCommand(this));
    }
}
