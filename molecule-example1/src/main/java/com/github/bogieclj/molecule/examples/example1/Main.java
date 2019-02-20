package com.iomolecule.examples.example1;

import com.google.common.eventbus.Subscribe;
import com.iomolecule.mods.main.SysBuilder;
import com.iomolecule.system.LifecycleException;
import com.iomolecule.system.OnStartup;
import com.iomolecule.system.Sys;
import com.iomolecule.system.Event;

import static com.iomolecule.module.ModuleInfo.createModuleInfo;
import static com.iomolecule.util.CollectionUtils.MAP;

public class Main {

    static class MoleculeEntry implements OnStartup{

        public void onStart(String[] args) {
            System.out.println("Hello Molecule World!");
        }
    }

    static class SimpleEventListener{

        public @Subscribe void handleEvent(Event event){
            System.out.println("Received Event "+event.getId());
            System.out.println("Event Name : "+event.getName());
            System.out.println("Event time : "+event.getInstant());
        }
    }

    public static void main(String[] args) throws LifecycleException, InterruptedException {
        Sys newSystem = new SysBuilder(args)
                .withAttributes(createModuleInfo("molecule-example1","1.0-SNAPSHOT","myself",MAP()))
                .withOnStartup(MoleculeEntry.class)
                .withEventsSinks(new SimpleEventListener())
                .build();

        newSystem.start();


    }
}
