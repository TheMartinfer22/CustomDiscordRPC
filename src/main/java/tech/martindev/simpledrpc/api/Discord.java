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
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        handlers.ready = (user) -> {
            System.out.println("-------------------------------------");
            System.out.println("CustomDiscordRPC iniciado com sucesso!");
            System.out.println("Você está logado como: " + user.username);
            System.out.println("-------------------------------------");
        };

        // Inicialização do Discord RPC.
        DiscordRPC lib = DiscordRPC.INSTANCE;
        lib.Discord_Initialize(appId, handlers, true, "");
        lib.Discord_UpdatePresence(new DiscordService().setDiscordConfigurations());

        // Runtime, deixar a Theread trabalhar para atualizar constantemente.
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
