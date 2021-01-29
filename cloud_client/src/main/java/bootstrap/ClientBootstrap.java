package bootstrap;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.SneakyThrows;
import command.CLI;
import config.ClientConfig;
import cloud_core.Bootstrap;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@ApplicationScoped
public class ClientBootstrap extends Bootstrap
{
    @Inject
    @Getter(AccessLevel.PROTECTED)
    private ClientConfig config;

    @Inject
    private CLI cli;

    @Override
    @SneakyThrows
    protected void init()
    {
        final Path localStorage = Paths.get(config.getLocalStorage());
        if (!Files.exists(localStorage)) Files.createDirectories(localStorage);
        cli.start();
    }
}
