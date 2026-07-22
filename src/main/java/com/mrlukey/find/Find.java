package com.mrlukey.find;

import com.google.inject.Inject;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;

@Plugin(id = "find", name = "Find", version = "BETA0.1", url = "https://www.mrlukey.com", description = "Find players across a Velocity network", authors = {"MrLukey"})
public class Find {

    private final ProxyServer server;
    private final Logger logger;

    @Inject
    public Find(ProxyServer server, Logger logger) {
        this.server = server;
        this.logger = logger;

        logger.info("Hello there! I made my first plugin with Velocity.");
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        server.getCommandManager().register(
                server.getCommandManager().metaBuilder("find").build(),
                new FindCmd(server)
        );

        logger.info("Find plugin has been initialized!");
    }
}
