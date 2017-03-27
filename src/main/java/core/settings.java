package core;


import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import utils.STATICS;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * © zekro 2017
 *
 * @author zekro
 */

public class settings {

    private static File sfile = new File("SETTINGS.txt");
    private static TomlWriter tomlw = new TomlWriter();
    private static Toml toml;

    public static class SCONT {
        static final String TOKEN = "TOKEN";
        static final String PREFIX = "CMD_PREFIX";
        static final String CUSTOM_MESSAGE = "CUSTOM_PLAYING_MESSAGE";
        static final String VOICE_LOG_TEXTCHANNEL = "VOICE_LOG_TEXTCHANNEL";
        static final String WARFRAME_ALERTS_TEXTCHANNEL = "WARFRAME_ALERTS_TEXTCHANNEL";
        static final String WARFRAME_ALERTS_REFRESHTIME = "WARFRAME_ALERTS_REFRESHTIME";
        static final String PERMISSION_ROLES = "PERMISSION_ROLES";
        static final String DOCID_WARFRAME_ALERTS_FILTER = "DOCID_WARFRAME_ALERTS_FILTER";
        static final String DOCID_JOKES = "DOCID_JOKES";
        static final String COMMAND_CONSOLE_OUTPUT = "COMMAND_CONSOLE_OUTPUT";
        static final String KICK_VOICE_CHANNEL = "KICK_VOICE_CHANNEL";
    }

    public static boolean testForToken() {
        return (toml.getString(SCONT.TOKEN).length() > 0);
    }

    public static void loadSettings() throws IOException {

        if (!sfile.exists()) {

            Map<String, Object> map = new HashMap<>();

            map.put(SCONT.COMMAND_CONSOLE_OUTPUT, true);
            map.put(SCONT.DOCID_JOKES, "");
            map.put(SCONT.DOCID_WARFRAME_ALERTS_FILTER, "");
            map.put(SCONT.PERMISSION_ROLES, "Admin, Moderator, Owner");
            map.put(SCONT.WARFRAME_ALERTS_REFRESHTIME, 10);
            map.put(SCONT.WARFRAME_ALERTS_TEXTCHANNEL, "");
            map.put(SCONT.VOICE_LOG_TEXTCHANNEL, "");
            map.put(SCONT.CUSTOM_MESSAGE, "ゼクロ");
            map.put(SCONT.PREFIX, "-");
            map.put(SCONT.TOKEN, "");
            map.put(SCONT.KICK_VOICE_CHANNEL, "");

            tomlw.write(map, new File("SETTINGS.txt"));

        } else {

            toml = new Toml().read(sfile);

            STATICS.TOKEN = toml.getString(SCONT.TOKEN);
            STATICS.PREFIX = toml.getString(SCONT.PREFIX);
            STATICS.CUSTOM_MESSAGE = toml.getString(SCONT.CUSTOM_MESSAGE);
            STATICS.warframeAlertsChannel = toml.getString(SCONT.WARFRAME_ALERTS_TEXTCHANNEL);
            STATICS.refreshTime = Math.toIntExact(toml.getLong(SCONT.WARFRAME_ALERTS_REFRESHTIME));
            STATICS.botPermRoles = toml.getString(SCONT.PERMISSION_ROLES).split(", ");
            STATICS.DOCID_warframeAlertsFilter = toml.getString(SCONT.DOCID_WARFRAME_ALERTS_FILTER);
            STATICS.DOCID_jokes = toml.getString(SCONT.DOCID_JOKES);
            STATICS.commandConsoleOutout = toml.getBoolean(SCONT.COMMAND_CONSOLE_OUTPUT);
            STATICS.KICK_VOICE_CHANNEL = toml.getString(SCONT.KICK_VOICE_CHANNEL);
        }

    }





}
