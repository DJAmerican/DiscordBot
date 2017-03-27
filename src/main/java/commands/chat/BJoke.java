package commands.chat;

import commands.Command;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.PermissionException;
import net.dv8tion.jda.core.managers.GuildController;
import utils.STATICS;

import java.util.*;

public class BJoke implements Command {

    public static String HELP = ":warning:  USAGE: ` ~bjoke @user ` OR ` ~bj @user `";

    static boolean called = false;
    static Member victim;


    public boolean called(String[] args, MessageReceivedEvent event) {

        return false;
    }

    int sec = 10;

    public void action(String[] args, final MessageReceivedEvent event) {

        VoiceChannel kickToChannel = event.getGuild().getAfkChannel();
        if (event.getGuild().getVoiceChannelsByName(STATICS.KICK_VOICE_CHANNEL, true).size() > 1)
            kickToChannel = event.getGuild().getVoiceChannelsByName(STATICS.KICK_VOICE_CHANNEL, true).get(0);

        try {
            String a = args[0];
        } catch (Exception e) {
            event.getTextChannel().sendMessage(help()).queue();
            return;
        }

        GuildController gc = new GuildController(event.getGuild());

        try {
            victim = event.getGuild().getMember(event.getMessage().getMentionedUsers().get(0));
        } catch (Exception e) {
            event.getTextChannel().sendMessage(help()).queue();
            return;
        }

        called = true;
        event.getTextChannel().sendMessage(
                event.getMessage().getMentionedUsers().get(0).getAsMention() + " hat einen schlechten Witz gerissen!\n\n" +
                        "Wenn nach 10 Sekunden keiner lacht (wenn doch: '~c') wird er vom Voice Channel gekickt!\n"

        ).queue();

        Timer timer = new Timer();

        VoiceChannel finalKickToChannel = kickToChannel;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!BJokeCancle.canceled && sec >= 0) {
                    event.getMessage().getTextChannel().sendMessage(sec + "...").queue();
                    sec--;
                } else if (!BJokeCancle.canceled) {

                    called = false;
                    event.getMessage().getTextChannel().sendMessage(
                            "Haha, " + event.getMessage().getMentionedUsers().get(0).getAsMention() + ", nieman hat über deinen schlechten Witz gelacht!"
                    ).queue();

                    gc.moveVoiceMember(victim, finalKickToChannel).queue();


                    try {
                        gc.setNickname(victim, victim.getEffectiveName() + " der Unlustige").queue();
                    } catch (PermissionException e) {
                        event.getMessage().getTextChannel().sendMessage("[ERROR] Can't modify a member with higher or equal highest role than the bot!").queue();
                    }
                    timer.cancel();
                    sec = 10;
                } else {
                    timer.cancel();
                    sec = 10;
                }
            }
        }, 0, 1000);


        BJokeCancle.canceled = false;
        sec = 10;


    }

    public void executed(boolean success, MessageReceivedEvent event) {

    }

    public String help() {
        return HELP;
    }

    @Override
    public String description() {
        return "Kick someone out of the voice channel after a bad joke";
    }
}