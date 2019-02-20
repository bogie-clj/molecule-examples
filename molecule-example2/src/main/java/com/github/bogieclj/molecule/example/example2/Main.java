package com.iomolecule.example.example2;

import com.iomolecule.mods.main.SysBuilder;
import com.iomolecule.module.ModuleInfo;
import com.iomolecule.system.LifecycleException;
import com.iomolecule.system.OnExit;
import com.iomolecule.system.OnStartup;
import com.iomolecule.system.Sys;
import com.iomolecule.system.annotations.SyncEventBus;
import com.google.common.eventbus.EventBus;

import javax.inject.Inject;

import static com.iomolecule.util.CollectionUtils.MAP;

public class Main {

    public static void main(String[] args) {
        Sys newSys = new SysBuilder(args)
                .withAttributes(ModuleInfo.createModuleInfo("test","1.0","self",MAP()))
                .withOnStartupInst(new OnStartup() {
                    @Override
                    public void onStart(String[] args) {
                        System.out.println("Hello world....");
                    }
                })
                .withOnStartup(SimpleOnStartupExample.class)
                .withOnExitInst(new OnExit() {
                    @Override
                    public void onExit() {
                        System.out.println("Hello world exiting...");
                    }
                })
                .build();
        try {
            newSys.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }
}

class SimpleOnStartupExample implements OnStartup{

    private EventBus eventBus;

    @Inject
    SimpleOnStartupExample(@SyncEventBus EventBus eventBus){
        this.eventBus = eventBus;
    }

    @Override
    public void onStart(String[] args) {
        System.out.println("Publishing event using Sync EventBus");
        eventBus.post("Starting of SimpleOnStartupExample");
        System.out.println("Done publishing event...");
    }
}