package com.github.bogieclj.molecule.sql.example1;

import com.google.common.eventbus.Subscribe;
import com.iomolecule.mods.main.SysBuilder;
import com.iomolecule.mods.sqlbase.SQLBaseModule;
import com.iomolecule.system.Event;
import com.iomolecule.system.LifecycleException;
import com.iomolecule.system.Sys;
import lombok.extern.slf4j.Slf4j;

public class Main {

    @Slf4j
    static class SampleEventListener{

        @Subscribe
        public void eventListener(Event event){
          log.info("Event {}",event);
        }
    }

    public static void main(String[] args) throws LifecycleException {
        Sys sys = new SysBuilder().withModules(new SQLBaseModule())
                .withEventsSinks(new SampleEventListener())
                .build();
        sys.start();
    }
}
