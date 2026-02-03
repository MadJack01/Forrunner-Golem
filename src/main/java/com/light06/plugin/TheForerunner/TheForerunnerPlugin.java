package com.light06.plugin.TheForerunner;

import com.hypixel.hytale.server.core.event.events.player.AddPlayerToWorldEvent;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.plugin.event.PluginSetupEvent;
import com.hypixel.hytale.server.npc.NPCPlugin;
import com.light06.plugin.TheForerunner.CoreComponents.BuilderActionDispatchEventPhase;
import com.light06.plugin.TheForerunner.Events.TriggerPhasedEvent;
import com.light06.plugin.TheForerunner.Handler.TriggerPhasedHandler;
import com.light06.plugin.TheForerunner.Listeners.AddPlayerFromWorldEventListener;
import com.light06.plugin.TheForerunner.Systems.BossTickingSystem;
import javax.annotation.Nonnull;

public class TheForerunnerPlugin extends JavaPlugin {
    public TheForerunnerPlugin(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        super.setup();
        this.getEntityStoreRegistry().registerSystem(new BossTickingSystem());

        this.getEventRegistry().registerGlobal(AddPlayerToWorldEvent.class, AddPlayerFromWorldEventListener::on);
        this.getEventRegistry().registerGlobal(TriggerPhasedEvent.class, new TriggerPhasedHandler());
    }

    @Override
    protected void start() {
        NPCPlugin.get().registerCoreComponentType("DispatchEventPhase", BuilderActionDispatchEventPhase::new);
    }
}
