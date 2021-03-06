package com.github.bogieclj.molecule.sql.example1;

import com.google.common.eventbus.Subscribe;
import com.iomolecule.config.InputStreamConfigurationSource;
import com.iomolecule.mods.main.SysBuilder;
import com.iomolecule.mods.sqlbase.SQLBaseModule;
import com.iomolecule.mods.sqljdbi.SQLJDBIModule;
import com.iomolecule.module.ModuleInfo;
import com.iomolecule.system.Event;
import com.iomolecule.system.LifecycleException;
import com.iomolecule.system.Sys;
import lombok.extern.slf4j.Slf4j;

import static com.iomolecule.util.CollectionUtils.KV;
import static com.iomolecule.util.CollectionUtils.MAP;

public class Main {

    @Slf4j
    static class SampleEventListener{

        @Subscribe
        public void eventListener(Event event){
          log.info("Event {}",event);
        }
    }

    public static void main(String[] args) throws LifecycleException {
        Sys sys = new SysBuilder(args)
                .withAttributes(new ModuleInfo("","","",MAP(
                        KV("sys.print-lifecycle-time",true)
                )))
                .withModules(new SQLJDBIModule())
                .withEventsSinks(new SampleEventListener())
                .withConfigurations(new InputStreamConfigurationSource(false,
                        true,Main.class.getResourceAsStream("/sql-datasources.json")))
                .build();
        sys.start();
    }
}
