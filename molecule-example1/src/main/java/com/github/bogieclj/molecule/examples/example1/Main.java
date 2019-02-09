package com.github.bogieclj.molecule.examples.example1;

import com.github.bogieclj.molecule.mods.main.SysBuilder;
import com.github.bogieclj.molecule.system.LifecycleException;
import com.github.bogieclj.molecule.system.OnStartup;
import com.github.bogieclj.molecule.system.Sys;

import static com.github.bogieclj.molecule.module.ModuleInfo.createModuleInfo;
import static com.github.bogieclj.molecule.util.CollectionUtils.MAP;

public class Main {

    static class MoleculeEntry implements OnStartup{

        public void onStart(String[] args) {
            System.out.println("Hello Molecule World!");
        }
    }

    public static void main(String[] args) throws LifecycleException {
        Sys newSystem = new SysBuilder(args)
                .withAttributes(createModuleInfo("molecule-example1","1.0-SNAPSHOT","myself",MAP()))
                .withOnStartup(MoleculeEntry.class)
                .build();

        newSystem.start();
    }
}
