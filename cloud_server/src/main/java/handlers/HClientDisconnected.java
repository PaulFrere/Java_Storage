package handlers;

import events.EClientDisconnected;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.ObservesAsync;

@ApplicationScoped
public class HClientDisconnected
{
    private void handleClientDisconnected(@ObservesAsync final EClientDisconnected event)
    {

    }
}