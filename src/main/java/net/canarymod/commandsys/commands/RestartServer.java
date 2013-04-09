package net.canarymod.commandsys.commands;



//TODO: This isn't actually working, we could try another attempt or remove the command
public class RestartServer {

//    public RestartServer() {
//        super("*", "Restart the underlying server. The -all parameter will also reload CanaryMod", "Usage: /restart [-all]", 1, 2);
//    }
//
//    protected void execute(MessageReceiver caller, String[] parameters) {
//        if (caller instanceof Server) {
//            caller.notice(caller.getName() + " issued a manual restart!");
//            if (parameters.length == 2 && parameters[1].equalsIgnoreCase("-all")) {
//                ((Server) caller).restart(true);
//            } else {
//                ((Server) caller).restart(false);
//            }
//        } else if (caller instanceof Player) {
//            caller.notice("You cannot restart the server from in-game. Please use the console!");
//        } else {
//            throw new CommandException("Unknown MessageReceiver: " + caller.getClass().getSimpleName());
//        }
//    }

    // private void console(MessageReceiver caller) {
    // caller.notice("Looking down from the great Minecraft Skies!");
    // }

    // private void player(Player player) {
    // double degrees =  (player.getRotation() - 180) % 360;
    // if (degrees < 0) {
    // degrees += 360.0;
    // }
    //
    // player.notice("Compass: " + player.getCardinalDirection().toString() + " (" + (Math.round(degrees * 10) / 10.0) + ")");
    // }

}
