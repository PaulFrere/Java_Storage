package handlers;

import cloud_core.events.EFileTransferFailed;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.ObservesAsync;
import jakarta.inject.Inject;

@ApplicationScoped
public class HFileTransferFailed
{
    @Inject
    private HFileTransferProgress transferProgress;

    private void handleFileTransferFailed(@ObservesAsync final EFileTransferFailed event)
    {
        System.out.println("Transfer of " + event.getFileName() + " failed.");
        transferProgress.reset(event.getFileName());
    }
}
