package commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import utils.STATICS;

public class Info implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        event.getTextChannel().sendMessage(
                "**zekroBot** JDA Discord Bot - v." + STATICS.VERSION + "\n\n" +
                "Webpage: http://dev.zekro.de\n" +
                "Readme/Changelogs: http://github.zekro.de/DiscordBot/blob/master/README.md\n" +
                "Github Repository: http://github.zekro.de/DiscordBot\n\n" +
                "*© 2017 zekro*"
        ).queue();
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    @Override
    public String help() {
        return null;
    }

    @Override
    public String description() {
        return "Get info about that bot";
    }
}
