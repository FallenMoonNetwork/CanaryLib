package net.canarymod.motd;

import net.canarymod.chat.MessageReceiver;

/**
 * Message Of The Day parser<p/>
 * Calls the logic of the key to be replaced</p>
 * *INTERNAL USE*
 *
 * @author Jason (darkdiplomat)
 */
abstract class MOTDParser {
    private final String key;
    private final MOTDOwner owner;

    MOTDParser(String key, MOTDOwner owner) {
        this.key = key;
        this.owner = owner;
    }

    final String key() {
        return key;
    }

    final MOTDOwner getOwner() {
        return owner;
    }

    abstract String parse(MessageReceiver receiver) throws Exception;
}
