package com.github.bogieclj.molecule.sql.example1;

import com.iomolecule.config.InputStreamConfigurationSource;
import com.iomolecule.mods.ishell.JLineInteractiveShellModule;
import com.iomolecule.mods.main.SysBuilder;
import com.iomolecule.mods.sqljdbi.SQLJDBIModule;
import com.iomolecule.module.ModuleInfo;
import com.iomolecule.system.LifecycleException;
import com.iomolecule.system.Sys;

import static com.iomolecule.util.CollectionUtils.KV;
import static com.iomolecule.util.CollectionUtils.MAP;

public class InteractiveShellExample {

    public static void main(String[] args) throws LifecycleException {
        Sys sys = new SysBuilder(args)
                .withAttributes(new ModuleInfo("test","1.0","test",MAP(
                        KV("sys.print-lifecycle-time",true)
                )))
                .withModules(new SQLJDBIModule(),
                        new JLineInteractiveShellModule()
                )
                .withEventsSinks(new Main.SampleEventListener())
                .withConfigurations(new InputStreamConfigurationSource(false,
                        true,Main.class.getResourceAsStream("/sql-datasources.json")))
                .build();

        sys.start();

    }
}
