package tech.martindev.simpledrpc.service;

import club.minnced.discord.rpc.DiscordRichPresence;

import java.io.*;
import java.util.Properties;

public class DiscordService {
    /**
     * Seta as configurações do presence juntamente pega as informações do properties.
     *
     * @return
     * @throws IOException
     */
    public DiscordRichPresence setDiscordConfigurations() throws IOException {

        DiscordRichPresence presence = new DiscordRichPresence();
        Properties properties = new Properties();

        // Todo: Depende seu uso, você precisará alterar isso.
        properties.load(new FileInputStream("src/main/resources/discord.properties"));

        // Propriedades
        presence.startTimestamp = System.currentTimeMillis() / 1000;
        presence.largeImageKey = properties.getProperty("BIG_IMAGE");
        presence.largeImageText = properties.getProperty("BIG_IMAGE_TEXT");
        presence.smallImageKey = properties.getProperty("SMALL_IMAGE");
        presence.smallImageText = properties.getProperty("SMALL_IMAGE_TEXT");
        presence.details = properties.getProperty("DETAILS");
        presence.state = properties.getProperty("STATE");
        return presence;
    }
}
