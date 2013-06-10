package net.canarymod.commandsys.commands;

import net.canarymod.Canary;
import net.canarymod.LineTracer;
import net.canarymod.Translator;
import net.canarymod.api.Server;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.EntityType;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.chat.Colors;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.CommandException;

public class MobspawnCommand {

    public void execute(MessageReceiver caller, String[] parameters) {
        if (caller instanceof Server) {
            console((Server) caller, parameters);
        } else if (caller instanceof Player) {
            player((Player) caller, parameters);
        } else {
            throw new CommandException("Unknown MessageReceiver: " + caller.getClass().getSimpleName());
        }
    }

    private void console(Server caller, String[] args) {
        caller.notice(Translator.translate("mobspawn console"));
    }

    private void player(Player player, String[] args) {
        if (args.length == 2) {
            LineTracer tracer = new LineTracer(player);
            Block b = tracer.getTargetBlock();
            // Spawn a mob with Rider
            if (b != null) {
                b.setY(b.getY() + 1);
                try {
                    Entity mob = Canary.factory().getEntityFactory().newEntity(EntityType.valueOf(args[1].toUpperCase()), b.getLocation());
                    mob.spawn();
                    player.sendMessage(Colors.YELLOW + Translator.translateAndFormat("mobspawn spawned", args[1]));
                } catch (IllegalArgumentException e) {
                    player.notice("mobspawn failed");
                }
            }
            else {
                player.notice("mobspawn failed");
            }
        }

        if (args.length == 3) {
            if (args[2].matches("^[0-9]+$")) {
                // Spawn X amount of entities
                int amount = Integer.parseInt(args[2]);
                LineTracer tracer = new LineTracer(player);
                Block b = tracer.getTargetBlock();
                if (b == null) {
                    player.notice("mobspawn failed");
                    return;
                }
                b.setY(b.getY() + 1);
                boolean spawnSuccess = true;
                for (int i = 0; i < amount; ++i) {
                    try {
                        Entity e = Canary.factory().getEntityFactory().newEntity(EntityType.valueOf(args[1].toUpperCase()), b.getLocation());
                        e.spawn();
                    } catch (IllegalArgumentException e) {
                        player.notice("mobspawn failed");
                    }
                }
                if (spawnSuccess) {
                    player.sendMessage(Colors.YELLOW + Translator.translateAndFormat("mobspawn spawned", args[1]));
                }
                else {
                    player.notice(Translator.translate("mobspawn failed"));
                }
            }
            else {
                LineTracer tracer = new LineTracer(player);
                Block b = tracer.getTargetBlock();
                if (b == null) {
                    player.notice("mobspawn failed");
                    return;
                }
                b.setY(b.getY() + 1);
                // Spawn a mob with Rider
                try {
                    Entity mob = Canary.factory().getEntityFactory().newEntity(EntityType.valueOf(args[1].toUpperCase()), b.getLocation());
                    Entity rider = Canary.factory().getEntityFactory().newEntity(EntityType.valueOf(args[2].toUpperCase()));
                    mob.spawn(rider);
                    player.sendMessage(Colors.YELLOW + Translator.translateAndFormat("mobspawn spawned rider", args[1], args[2]));
                } catch (IllegalArgumentException e) {
                    player.notice("mobspawn failed");
                }
            }
        }
        if (args.length == 4) {
            if (!args[3].matches("^[0-9]+$")) {
                player.notice(Translator.translate("mobspawn failed"));
                Canary.help().getHelp(player, "mobspawn");
                return;
            }
            // Spawn X amount of entities
            int amount = Integer.parseInt(args[3]);
            LineTracer tracer = new LineTracer(player);
            Block b = tracer.getTargetBlock();
            if (b == null) {
                player.notice("mobspawn failed");
                return;
            }
            b.setY(b.getY() + 1);
            boolean spawnSuccess = true;
            // Spawn a mob with Rider
            for (int i = 0; i < amount; ++i) {
                try {
                    Entity mob = Canary.factory().getEntityFactory().newEntity(EntityType.valueOf(args[1].toUpperCase()), b.getLocation());
                    Entity rider = Canary.factory().getEntityFactory().newEntity(EntityType.valueOf(args[2].toUpperCase()));
                    mob.spawn(rider);
                } catch (IllegalArgumentException e) {
                    player.notice("mobspawn failed");
                }
            }
            if (spawnSuccess) {
                player.sendMessage(Colors.YELLOW + Translator.translateAndFormat("mobspawn spawned rider", args[1], args[2]));
            }
            else {
                player.notice(Translator.translate("mobspawn failed"));
            }
        }
    }

}
