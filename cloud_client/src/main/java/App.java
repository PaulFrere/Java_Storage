import bootstrap.ClientBootstrap;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import jakarta.enterprise.inject.spi.CDI;

public class App
{
    public static void main(String[] args)
    {
        SeContainerInitializer.newInstance().addPackages(App.class).initialize();
        final ClientBootstrap bootstrap = CDI.current().select(ClientBootstrap.class).get();
        bootstrap.run(args);
    }
}