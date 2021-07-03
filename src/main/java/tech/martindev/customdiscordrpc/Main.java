package tech.martindev.customdiscordrpc;

import tech.martindev.customdiscordrpc.api.Discord;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();

        // Todo: Depende seu uso, você precisará alterar isso.
        properties.load(new FileInputStream("src/main/resources/discord.properties"));

        new Discord(properties.getProperty("APPID"));
    }
}