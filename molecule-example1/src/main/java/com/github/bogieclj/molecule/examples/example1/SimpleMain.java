package com.github.bogieclj.molecule.examples.example1;

import com.iomolecule.mods.main.SysBuilder;
import com.iomolecule.system.LifecycleException;

public class SimpleMain {

    public static void main(String[] args) throws LifecycleException {
        new SysBuilder(args).build().start();
    }
}
