package com.github.bogieclj.molecule.mongo;

import com.iomolecule.mods.jongo.JongoModule;
import com.iomolecule.mods.main.SysBuilder;
import com.iomolecule.mongodb.services.MongoClientService;
import com.iomolecule.system.LifecycleException;
import com.iomolecule.system.OnStartup;
import com.iomolecule.system.Sys;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import lombok.extern.slf4j.Slf4j;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class AccountsExample {

    public static void main(String[] args) throws LifecycleException {
        Sys sys = new SysBuilder(args)
                .withModules(new JongoModule())
                .withOnStartup(AccountsManager.class)
                .build();

        sys.start();
    }
}


@Slf4j
class AccountsManager implements OnStartup{

    @Inject
    private MongoClientService mongoClientService;


    @Override
    public void onStart(String[] args) {
        log.info("Starting Accounts Manager...");

        log.info("Looking up all databases in the system using client service {}",mongoClientService);


        for (String databaseName : mongoClientService.getDBNames()) {
            log.info(databaseName);
        }

        log.info("All accounts in the system {}",getAllAccounts());

    }


    public List<Account> getAllAccounts(){
        DB iomoleculeDB = mongoClientService.getDB("iomolecule");

        Jongo moleculeJongo = new Jongo(iomoleculeDB);

        MongoCollection accounts = moleculeJongo.getCollection("accounts");

        MongoCursor<Account> accountsCursor = accounts.find().as(Account.class);

        List<Account> accountList = new ArrayList<>();

        for (Account account : accountsCursor) {

            accountList.add(account);

        }

        return accountList;
    }

}