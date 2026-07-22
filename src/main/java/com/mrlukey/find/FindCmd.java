package com.mrlukey.find;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.player.*;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import java.util.concurrent.CompletableFuture;
import java.util.List;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class FindCmd implements SimpleCommand {
    ProxyServer server;

    public FindCmd(ProxyServer server) {
        this.server = server;
    }

    @Override
    public void execute(Invocation invocation) {
        CommandSource source = invocation.source();

        if (source.hasPermission("find.find")) {
            if (invocation.arguments().length != 1) {
                source.sendMessage(Component.text("Incorrect usage. Try: /find <username>"));
            } else {
                String username = invocation.arguments()[0];
                if (server.getPlayer(username).isEmpty()) {
                    source.sendMessage(Component.text("Player not found.", NamedTextColor.RED));
                } else {
                    Player p = server.getPlayer(username).get();
                    RegisteredServer server = p.getCurrentServer().get().getServer();
                    source.sendMessage(Component.text("Player " + username + " is on server: " + server.getServerInfo().getName(), NamedTextColor.GREEN));
                }
            }
        } else {
            source.sendMessage(Component.text("You do not have permission to use this command.", NamedTextColor.RED));
        }
    }
}
