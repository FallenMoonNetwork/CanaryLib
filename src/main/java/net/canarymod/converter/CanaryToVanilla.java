package net.canarymod.converter;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;

import net.canarymod.Canary;
import net.canarymod.bansystem.Ban;
import net.canarymod.config.*;

/**
 * 
 * @author Jos Kuijpers
 *
 */
public class CanaryToVanilla {

    public CanaryToVanilla() {
        
    }
    
    public boolean convert(String world) {
        
        if(!createFolders(world)) return false;
        if(!downloadMinecraft()) return false;
        if(!createServerProperties(world)) return false;
        if(!createBans()) return false;
        if(!createOps()) return false;
        if(!createWhitelist()) return false;
        
        return true;
    }
    
    private void copyFolder(File src, File dest)
            throws IOException {
        
        if(src.isDirectory()) { // Create directories
            
            // Create the destination if not existend
            if(!dest.exists()) {
                dest.mkdirs();
            }
            
            // List the files/directories
            String contents[] = src.list();
            
            // Do a recursive call
            for(String file : contents) {
                copyFolder(new File(src, file), new File(dest, file));
            }
        }
        else { // Copy files
            
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest);
            
            byte[] buffer = new byte[1024];
            int length;
            
            // Read from one, write to the other
            while((length = in.read(buffer)) > 0) {
                out.write(buffer, 0 , length);
            }
            
            in.close();
            out.close();
        }
        
    }
    
    private boolean createFolders(String world) {
        
        File vanilla = new File("vanilla/");
        vanilla.mkdir();
        
        File canaryWorld = new File("worlds/"+world);
        if(!canaryWorld.isDirectory() || !canaryWorld.exists()) {
            return false;
        }
        
        File dstFolder = new File("vanilla/world/");
        
        try {
            copyFolder(canaryWorld,dstFolder);
        }
        catch(IOException ioe) {
            return false;
        }
        
        return true;
    }

    private boolean downloadMinecraft() {
        URL mc;
        ReadableByteChannel rbc;
        FileOutputStream fos;
        
        try {
            mc = new URL("https://s3.amazonaws.com/MinecraftDownload/launcher/minecraft_server.jar");
            rbc = Channels.newChannel(mc.openStream());
            fos = new FileOutputStream("vanilla/minecraft_server.jar");
            fos.getChannel().transferFrom(rbc, 0, 1 << 24);
        } catch (FileNotFoundException e) {
            return false;
        } catch (MalformedURLException e1) {
            return false;
        } catch (IOException e1) {
            return false;
        }
        
        return true;
    }

    private boolean createBans() {
        Ban[] bans = Canary.bans().getAllBans();
        
        Writer banOutput = null;
        Writer ipBanOutput = null;
        try {
            File banFile = new File("vanilla/banned-players.txt");
            File ipBanFile = new File("vanilla/banned-ips.txt");
            banFile.createNewFile();
            ipBanFile.createNewFile();

            banOutput = new BufferedWriter(new FileWriter(banFile));
            ipBanOutput = new BufferedWriter(new FileWriter(ipBanFile));
            
            for(Ban ban : bans) {
                if(ban.isIpBan()) {
                    ipBanOutput.write(ban.getIp() + "\n");
                }
                else {
                    banOutput.write(ban.getSubject() + "\n");
                }
            }
            
            banOutput.close();
            ipBanOutput.close();
        }
        catch(IOException ioe) {
            try {
                if(banOutput != null) banOutput.close();
                if(ipBanOutput != null) ipBanOutput.close();
            }
            catch(IOException ioe2) {}
            return false;
        }
        
        return true;
    }

    private String[] getUsersWithPermission(String permission) {
        String[] ret = {};
        ArrayList<String> val = new ArrayList<String>();
        
        for(String user : Canary.usersAndGroups().getPlayers()) {
            if(Canary.permissionManager().getPlayerProvider(user).queryPermission(permission)) {
                val.add(user);
            }
        }
        
        return val.toArray(ret);
    }
        
    private boolean createOps() {
        // by all users in all groups, and all users, with permission canary.vanilla.op
        Writer output = null;
        try {
            File opFile = new File("vanilla/ops.txt");
            opFile.createNewFile();
            output = new BufferedWriter(new FileWriter(opFile));
            
            for(String user : getUsersWithPermission("canary.vanilla.op")) {
                output.write(user + "\n");
            }
            
            output.close();
        }
        catch(IOException ioe) {
            if(output != null) {
                try {
                    output.close();
                }
                catch(IOException ioe2) {
                    return false;
                }
            }
            return false;
        }
        
        return true;
    }

    private boolean createServerProperties(String worldname) {
        
        ConfigurationFile props = new ConfigurationFile("vanilla/server.properties");
        props.create();
        
        ServerConfiguration server = Configuration.getServerConfig();
        WorldConfiguration world = Configuration.getWorldConfig(worldname);
        NetworkConfiguration net = Configuration.getNetConfig();
        
        props.setBoolean("allow-flight", world.isFlightAllowed());
        props.setBoolean("allow-nether", world.isNetherAllowed());
        props.setInt("difficulty", world.getDifficulty().getId());
        props.setBoolean("enable-query", net.isQueryEnabled());
        props.setBoolean("enable-rcon", net.isRconEnabled());
        props.setInt("gamemode", world.getGameMode().getId());
        props.setBoolean("generate-structures", world.generatesStructures());
        props.setString("level-name", world.getWorldName());
        props.setString("level-seed", world.getWorldSeed());
        props.setString("level-type", world.getWorldType().toString());
        props.setInt("max-build-height", world.getMaxBuildHeight());
        props.setInt("max-players", net.getMaxPlayers());
        props.setString("motd", server.getMotd());
        props.setBoolean("online-mode", net.isOnlineMode());
        props.setBoolean("pvp", world.isPvpEnabled());
        props.setInt("query.port", net.getQueryPort());
        props.setString("rcon.password", net.getRconPassword());
        props.setInt("rcon.port", net.getRconPort());
        props.setString("server-ip", net.getBindIp());
        props.setInt("server-port", net.getPort());
        props.setBoolean("spawn-animals", world.canSpawnAnimals());
        props.setBoolean("spawn-monsters", world.canSpawnMonsters());
        props.setBoolean("spawn-npcs", world.canSpawnNpcs());
        props.setInt("view-distance", net.getViewDistance());
        props.setBoolean("white-list", false);

        try {
            props.save();
        } catch (IOException e) {
            return false;
        }
        
        return true;
    }
    
    private boolean createWhitelist() {
        
        Writer output = null;
        try {
            File opFile = new File("vanilla/white-list.txt");
            opFile.createNewFile();
            output = new BufferedWriter(new FileWriter(opFile));
            
            //for(String user : getUsersWithPermission("canary.vanilla.op")) {
            //    output.write(user + "\n");
            //}
            
            output.close();
        }
        catch(IOException ioe) {
            if(output != null) {
                try {
                    output.close();
                }
                catch(IOException ioe2) {
                    return false;
                }
            }
            return false;
        }
        
        return true;
    }
        
}
