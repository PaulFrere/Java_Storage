package handlers;

import cloud_core.events.EFileTransferComplete;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.ObservesAsync;
import jakarta.inject.Inject;

@ApplicationScoped
public class HFileTransferComplete
{
    @Inject
    private HFileTransferProgress transferProgress;

    private void handleFileTransferComplete(@ObservesAsync final EFileTransferComplete event)
    {
        System.out.println("Transfer of " + event.getFileName() + " is 100% complete.");
        transferProgress.reset(event.getFileName());
    }
}
