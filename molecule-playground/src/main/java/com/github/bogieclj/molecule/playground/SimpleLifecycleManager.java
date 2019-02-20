/*
 * Copyright 2019 Vijayakumar Mohan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.iomolecule.playground;

import com.iomolecule.system.LifecycleException;
import com.iomolecule.system.LifecycleManager;
import com.iomolecule.system.Shell;
import com.iomolecule.system.annotations.SyncEventBus;
import com.iomolecule.system.services.DomainService;
import com.iomolecule.system.services.EventsService;
import com.iomolecule.system.services.FnBus;
import com.iomolecule.system.services.SysLifecycleCallbackService;
import com.google.common.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Named;

import static com.google.common.base.Preconditions.checkArgument;

@Slf4j
public class SimpleLifecycleManager implements LifecycleManager{

    private EventBus eventBus;
    private EventsService eventSinkRegistrationService;
    private SysLifecycleCallbackService sysLifecycleCallbackService;
    private DomainService domainService;
    private Shell interactiveShell;
    private FnBus fnBus;
    boolean started;

    @Inject
    public SimpleLifecycleManager(@SyncEventBus EventBus eventBus,
                                  EventsService eventSinkRegistrationService,
                                  SysLifecycleCallbackService sysLifecycleCallbackService, DomainService domainService,
                                  @Named("shell://interactive/jline") Shell interactiveShell,FnBus fnBus){
        checkArgument(eventBus != null,"EventBus cannot be null!");
        checkArgument(eventSinkRegistrationService != null,"EventSinkRegistration Service cannot be null!");
        this.eventBus = eventBus;
        this.eventSinkRegistrationService = eventSinkRegistrationService;
        this.sysLifecycleCallbackService = sysLifecycleCallbackService;
        this.domainService = domainService;
        this.interactiveShell = interactiveShell;
        this.fnBus = fnBus;
    }



    @Override
    public void start() throws LifecycleException {
        //register the event sinks first
        if(eventSinkRegistrationService.hasAnyEventSinks()){
            for (Object eventSink : eventSinkRegistrationService.getAllEventSinks()) {
                log.debug("Registering event sink {}",eventSink);
            }
            eventSinkRegistrationService.registerEventSinks();
        }

        fnBus.start();

        log.debug("Starting domain service...");
        domainService.start();

        log.debug("Registered Domains..");

        log.debug("Starting lifecycle of services...");



        eventBus.post("STARTING_SYS");


        eventBus.post("STARTED_SYS");

        sysLifecycleCallbackService.invokeAllStartupCallbacks();

        eventBus.post("STARTUP_CALLBACK_COMPLETED");

        started = true;

        log.debug("Starting Interactive Shell...");

        interactiveShell.start(new String[0]);

        log.debug("Done starting interactive Shell...");

    }

    @Override
    public void stop() {

        if(started) {
            log.debug("Stop lifecycle of services...");


            eventBus.post("STOPPING_SYS");

            interactiveShell.stop();

            if (eventSinkRegistrationService.hasAnyEventSinks()) {
                for (Object eventSink : eventSinkRegistrationService.getAllEventSinks()) {
                    log.debug("UnRegistering event sink {}", eventSink);
                }
                eventSinkRegistrationService.unRegisterEventSinks();
            }

            sysLifecycleCallbackService.invokeAllExitCallbacks();

            fnBus.stop();
            log.debug("Sys Stop completed...");
            started = false;
        }
    }
}
