package com.github.bogieclj.molecule.examples.example1;

import com.github.bogieclj.molecule.mods.main.SysBuilder;
import com.github.bogieclj.molecule.system.LifecycleException;

public class SimpleMain {

    public static void main(String[] args) throws LifecycleException {
        new SysBuilder(args).build().start();
    }
}
