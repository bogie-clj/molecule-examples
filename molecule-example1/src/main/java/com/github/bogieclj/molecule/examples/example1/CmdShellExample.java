package com.github.bogieclj.molecule.examples.example1;

import com.iomolecule.mods.cmdshell.CmdShellModule;
import com.iomolecule.mods.main.SysBuilder;
import com.iomolecule.system.LifecycleException;
import com.iomolecule.system.OnStartup;
import com.iomolecule.system.Shell;
import com.iomolecule.system.Sys;

import javax.inject.Inject;
import javax.inject.Named;

public class CmdShellExample {

    public static void main(String[] args) throws LifecycleException {
        Sys sys = new SysBuilder(args)
                .withModules(new CmdShellModule())
                .withOnStartup(CmdShellStartup.class)
                .build();

        sys.start();
    }


}

class CmdShellStartup implements OnStartup{

    @Inject
    @Named("shell://cmd/default")
    private Shell cmdShell;

    @Override
    public void onStart(String[] args) {
        cmdShell.start(args);
    }
}
