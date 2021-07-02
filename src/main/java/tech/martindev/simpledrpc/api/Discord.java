package tech.martindev.simpledrpc.api;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import tech.martindev.simpledrpc.service.DiscordService;

import java.io.IOException;

public class Discord {

    /**
     * Está requisitando o token ID do discord a quem instanciar a classe;
     *
     * @param appId
     */
    public Discord(String appId) throws IOException {
        DiscordRPC lib = DiscordRPC.INSTANCE;
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        handlers.ready = (user) -> System.out.println("Ready!");

        lib.Discord_Initialize(appId, handlers, true, "");
        lib.Discord_UpdatePresence(new DiscordService().setDiscordConfigurations());

        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                lib.Discord_RunCallbacks();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {}
            }
        }, "RPC-Callback-Handler").start();
    }
}
