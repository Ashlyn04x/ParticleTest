package me.rew.particletest;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ParticleTaskCommand implements CommandExecutor {
    private final ParticleTest plugin;

    public ParticleTaskCommand(ParticleTest plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;
        Player player = (Player) sender;
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            player.getWorld().spawnParticle(Particle.DRIP_LAVA, player.getLocation().clone(), 10, 0.25, 0.25, 0.25, 0);
        }, 1L, 1L);
        return true;
    }
}
