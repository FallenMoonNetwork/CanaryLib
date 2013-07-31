package net.canarymod.commandsys.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.canarymod.Canary;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.chat.TextFormat;

public class CanaryModCommand {
    private final static List<String> information;

    static {
        ArrayList<String> temp = new ArrayList<String>();
        temp.add(new StringBuilder(TextFormat.LIGHT_RED).append("---- ").append(TextFormat.ORANGE).append(Canary.getImplementationTitle()).append(" ").append(Canary.getImplementationVersion()).append(TextFormat.LIGHT_RED).append(" ----").toString());
        temp.add(new StringBuilder(TextFormat.ORANGE).append("Project Lead: ").append(TextFormat.WHITE).append("Shadow386").toString());
        temp.add(new StringBuilder(TextFormat.ORANGE).append("Lead Programmers: ").append(TextFormat.WHITE).append("damagefilter, darkdiplomat").toString());
        temp.add(new StringBuilder(TextFormat.ORANGE).append("Programmers: ").append(TextFormat.WHITE).append("14mRh4X0r, somners, gregthegeek, WWOL").toString());
        temp.add(new StringBuilder(TextFormat.ORANGE).append("WebSite: ").append(TextFormat.WHITE).append("http://canarymod.net").toString());
        temp.add(new StringBuilder(TextFormat.ORANGE).append("GitHub: ").append(TextFormat.WHITE).append("http://git.io/GMO-6g").toString());
        temp.add(new StringBuilder(TextFormat.LIGHT_RED).append("  Copyright (c) 2012-2013 ").append(TextFormat.BLUE).append("FallenMoonNetwork").append(TextFormat.LIGHT_RED).append("/").append(TextFormat.ORANGE).append("CanaryMod Team").toString());
        temp.add(new StringBuilder(TextFormat.LIGHT_RED).append("  Licensed under the BSD 3-Clause License ").toString());
        information = Collections.unmodifiableList(temp);
    }

    public void execute(MessageReceiver caller, String[] args) {
        for (String msg : information) {
            caller.message(msg);
        }
    }

}
