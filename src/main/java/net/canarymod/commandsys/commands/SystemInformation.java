package net.canarymod.commandsys.commands;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.text.MessageFormat;
import java.util.TimeZone;

import net.canarymod.Canary;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.chat.TextFormat;
import net.canarymod.commandsys.NativeCommand;
import net.visualillusionsent.utils.DateUtils;
import net.visualillusionsent.utils.SystemUtils;

/**
 * System Information read-out command
 *
 * @author Jason (darkdiplomat)
 */
public class SystemInformation implements NativeCommand {

    @Override
    public void execute(MessageReceiver caller, String[] parameters) {

        Runtime rtime = Runtime.getRuntime();
        RuntimeMXBean rBean = ManagementFactory.getRuntimeMXBean();
        float freeMem = (rtime.freeMemory() / 1024.0F) / 1024.0F; // Pull up to MegaBytes
        float alloMem = (rtime.totalMemory() / 1024.0F) / 1024.0F; // Pull up to MegaBytes
        float maxMem = (rtime.maxMemory() / 1024.0F) / 1024.0F; // Pull up to MegaBytes
        long uptime = rBean.getUptime() / 1000; // RuntimeMXBean returns millis and getTimeUntil needs seconds
        int rawOff = TimeZone.getDefault().getRawOffset();
        int gmtH = rawOff / (60 * 60 * 1000); // GMT offset Hours
        int gmtM = (rawOff / (60 * 1000)) % 60; // GMT offset Minutes
        caller.message(TextFormat.ORANGE + "   *** " + TextFormat.LIGHT_RED + "SYSTEM INFO" + TextFormat.ORANGE + " ***");
        caller.message(TextFormat.ORANGE + "OS Name: " + TextFormat.WHITE + SystemUtils.SYSTEM_OS);
        caller.message(TextFormat.ORANGE + "OS Version: " + TextFormat.WHITE + SystemUtils.SYSTEM_VERSION);
        caller.message(TextFormat.ORANGE + "OS Architecture: " + TextFormat.WHITE + SystemUtils.SYSTEM_ARCH);
        caller.message(TextFormat.ORANGE + "Java Vendor: " + TextFormat.WHITE + System.getProperty("java.vendor")); // VIUtils 1.1.1 will have a constant for this later
        caller.message(TextFormat.ORANGE + "Java Version: " + TextFormat.WHITE + SystemUtils.JAVA_VERSION);
        caller.message(TextFormat.ORANGE + "Avalible Processors: " + TextFormat.WHITE + rtime.availableProcessors());
        caller.message(TextFormat.ORANGE + String.format("RAM: \u00A7F%.2fMb Free \u00A76| \u00A7F%.2fMb Allocated \u00A76| \u00A7F%.2fMb Max", freeMem, alloMem, maxMem));
        caller.message(TextFormat.ORANGE + "Server Start: " + TextFormat.WHITE + DateUtils.longToDateTime(rBean.getStartTime()) + MessageFormat.format(" GMT{0,number,00}:{1,number,00}", gmtH, gmtM));
        caller.message(TextFormat.ORANGE + "Time Alive: " + TextFormat.WHITE + DateUtils.getTimeUntil(uptime));
        caller.message(String.format(TextFormat.ORANGE + "Ticks Per Second (TPS):\u00A7F %.2f", Canary.getServer().getTicksPerSecond()));
    }
}
