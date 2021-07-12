package me.rew.particletest;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

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
        double red = Math.max(Float.MIN_VALUE, Color.BLUE.getRed() / 255);
        double green = Color.BLUE.getGreen() / 255;
        double blue = Color.BLUE.getBlue() / 255;
        Location particleMiddle = player.getLocation().clone().add(0.5, 1.25, 0.5);
        List<Location> particleCircle = getCircle(particleMiddle, 4, 50);
        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            particleMiddle.getWorld().spawnParticle(Particle.HEART, particleMiddle, 1, 0, 0, 0, 0);
            particleCircle.forEach(loc -> {
                loc.getWorld().spawnParticle(Particle.REDSTONE, loc, 0, red, green, blue, 1);
            });
        }, 0L, 10L);
        return true;
    }

    public static List<Location> getCircle(Location center, double radius, int amount) {
        double increment = (2 * Math.PI) / amount;
        List<Location> locations = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            double angle = i * increment;
            double x = center.getX() + (radius * Math.cos(angle));
            double z = center.getZ() + (radius * Math.sin(angle));

            locations.add(new Location(center.getWorld(), x, center.getY(), z));
        }

        return locations;
    }
}
