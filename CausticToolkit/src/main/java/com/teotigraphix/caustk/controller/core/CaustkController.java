////////////////////////////////////////////////////////////////////////////////
// Copyright 2013 Michael Schmalle - Teoti Graphix, LLC
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// 
// http://www.apache.org/licenses/LICENSE-2.0 
// 
// Unless required by applicable law or agreed to in writing, software 
// distributed under the License is distributed on an "AS IS" BASIS, 
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and 
// limitations under the License
// 
// Author: Michael Schmalle, Principal Architect
// mschmalle at teotigraphix dot com
////////////////////////////////////////////////////////////////////////////////

package com.teotigraphix.caustk.controller.core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.androidtransfuse.event.EventObserver;

import com.teotigraphix.caustk.controller.ICaustkApplication;
import com.teotigraphix.caustk.controller.ICaustkConfiguration;
import com.teotigraphix.caustk.controller.ICaustkController;
import com.teotigraphix.caustk.controller.IControllerComponent;
import com.teotigraphix.caustk.controller.IDispatcher;
import com.teotigraphix.caustk.controller.command.CommandManager;
import com.teotigraphix.caustk.controller.command.ICommand;
import com.teotigraphix.caustk.controller.command.ICommandManager;
import com.teotigraphix.caustk.controller.command.OSCMessage;
import com.teotigraphix.caustk.core.CtkDebug;
import com.teotigraphix.caustk.library.ILibraryManager;
import com.teotigraphix.caustk.library.LibraryManager;
import com.teotigraphix.caustk.pattern.IPatternManager;
import com.teotigraphix.caustk.pattern.PatternManager;
import com.teotigraphix.caustk.project.IProjectManager;
import com.teotigraphix.caustk.project.ProjectManager;
import com.teotigraphix.caustk.sequencer.IQueueSequencer;
import com.teotigraphix.caustk.sequencer.ISystemSequencer;
import com.teotigraphix.caustk.sequencer.ITrackSequencer;
import com.teotigraphix.caustk.sequencer.queue.QueueSequencer;
import com.teotigraphix.caustk.sequencer.system.SystemSequencer;
import com.teotigraphix.caustk.sequencer.track.TrackSequencer;
import com.teotigraphix.caustk.service.ISerializeService;
import com.teotigraphix.caustk.service.SerializeService;
import com.teotigraphix.caustk.sound.ISoundGenerator;
import com.teotigraphix.caustk.sound.ISoundMixer;
import com.teotigraphix.caustk.sound.ISoundSource;
import com.teotigraphix.caustk.sound.mixer.SoundMixer;
import com.teotigraphix.caustk.sound.source.SoundSource;
import com.teotigraphix.caustk.system.IMemoryManager;
import com.teotigraphix.caustk.system.ISystemState;
import com.teotigraphix.caustk.system.Memory.Type;
import com.teotigraphix.caustk.system.MemoryManager;
import com.teotigraphix.caustk.system.SystemState;

/**
 * @author Michael Schmalle
 */
public class CaustkController implements ICaustkController {

    //--------------------------------------------------------------------------
    // Public Property API
    //--------------------------------------------------------------------------

    private Map<Class<? extends IControllerComponent>, IControllerComponent> api = new HashMap<Class<? extends IControllerComponent>, IControllerComponent>();

    @Override
    public void addComponent(Class<? extends IControllerComponent> clazz,
            IControllerComponent instance) {
        api.put(clazz, instance);
    }

    @Override
    public <T extends IControllerComponent> T getComponent(Class<T> clazz) {
        return clazz.cast(api.get(clazz));
    }

    @Override
    public <T> void register(Class<T> eventType, final EventObserver<T> observer) {
        getDispatcher().register(eventType, observer);
    }

    //----------------------------------
    // application
    //----------------------------------

    private ICaustkApplication application;

    @Override
    public ICaustkApplication getApplication() {
        return application;
    }

    //----------------------------------
    // applicationConfiguration
    //----------------------------------

    @Override
    public ICaustkConfiguration getConfiguration() {
        return application.getConfiguration();
    }

    //----------------------------------
    // dispatcher
    //----------------------------------

    private IDispatcher dispatcher;

    @Override
    public IDispatcher getDispatcher() {
        return dispatcher;
    }

    //----------------------------------
    // projectManager
    //----------------------------------

    private IProjectManager projectManager;

    @Override
    public IProjectManager getProjectManager() {
        return projectManager;
    }

    //----------------------------------
    // serializeService
    //----------------------------------

    private ISerializeService serializeService;

    @Override
    public ISerializeService getSerializeService() {
        return serializeService;
    }

    //----------------------------------
    // trackSequencer
    //----------------------------------

    private ITrackSequencer trackSequencer;

    @Override
    public ITrackSequencer getTrackSequencer() {
        return trackSequencer;
    }

    //----------------------------------
    // queueSequencer
    //----------------------------------

    private IQueueSequencer queueSequencer;

    @Override
    public IQueueSequencer getQueueSequencer() {
        return queueSequencer;
    }

    //----------------------------------
    // libraryManager
    //----------------------------------

    private ILibraryManager libraryManager;

    @Override
    public ILibraryManager getLibraryManager() {
        return libraryManager;
    }

    //----------------------------------
    // commandManager
    //----------------------------------

    private ICommandManager commandManager;

    @Override
    public ICommandManager getCommandManager() {
        return commandManager;
    }

    /**
     * Executes an {@link ICommand} against a registered message.
     * 
     * @param message The message without the controller/applicationId.
     * @param args Arguments to pass to the created {@link OSCMessage} that will
     *            be created.
     * @see #sendOSCCommand(OSCMessage)
     */
    @Override
    public void execute(String message, Object... args) {
        commandManager.execute(message, args);
    }

    @Override
    public void undo() {
        commandManager.undo();
    }

    @Override
    public void redo() {
        commandManager.redo();
    }

    //----------------------------------
    // soundGenerator
    //----------------------------------

    private ISoundGenerator soundGenerator;

    @Override
    public ISoundGenerator getSoundGenerator() {
        return soundGenerator;
    }

    //----------------------------------
    // soundSource
    //----------------------------------

    private ISoundSource soundSource;

    @Override
    public ISoundSource getSoundSource() {
        return soundSource;
    }

    //----------------------------------
    // soundSource
    //----------------------------------

    private ISoundMixer soundMixer;

    @Override
    public ISoundMixer getSoundMixer() {
        return soundMixer;
    }

    //----------------------------------
    // systemSequencer
    //----------------------------------

    private ISystemSequencer systemSequencer;

    @Override
    public ISystemSequencer getSystemSequencer() {
        return systemSequencer;
    }

    //----------------------------------
    // systemSequencer
    //----------------------------------

    private ISystemState systemState;

    @Override
    public ISystemState getSystemState() {
        return systemState;
    }

    //----------------------------------
    // patternManager
    //----------------------------------

    private IPatternManager patternManager;

    @Override
    public IPatternManager getPatternManager() {
        return patternManager;
    }

    //----------------------------------
    // memoryManager
    //----------------------------------

    private IMemoryManager memoryManager;

    @Override
    public IMemoryManager getMemoryManager() {
        return memoryManager;
    }

    //--------------------------------------------------------------------------
    // Constructor
    //--------------------------------------------------------------------------

    /**
     * Constructor, creates the {@link IDeviceFactory}, {@link IDispatcher}.
     * <p>
     * {@link #initialize()} creates all sub components of the
     * {@link ICaustkController}.
     * 
     * @param application The main application.
     */
    public CaustkController(ICaustkApplication application) {
        this.application = application;

        dispatcher = new Dispatcher();
    }

    //--------------------------------------------------------------------------
    // ICausticEngine API
    //--------------------------------------------------------------------------

    // we proxy the actual OSC impl so we can stop, or reroute
    @Override
    public float sendMessage(String message) {
        return soundGenerator.sendMessage(message);
    }

    @Override
    public String queryMessage(String message) {
        return soundGenerator.queryMessage(message);
    }

    //--------------------------------------------------------------------------
    // ISystemController API
    //--------------------------------------------------------------------------

    void initialize() {
        CtkDebug.log("Controller: Create app root dir if not created");
        File applicationRoot = getConfiguration().getApplicationRoot();
        if (!applicationRoot.exists())
            applicationRoot.mkdirs();

        CtkDebug.log("!!! Controller: Create all Sub controllers");
        soundGenerator = getConfiguration().createSoundGenerator(this);
        soundGenerator.initialize();

        // sub composites will add their ICommands in their constructors

        serializeService = new SerializeService(this);
        commandManager = new CommandManager(this);
        projectManager = new ProjectManager(this);
        libraryManager = new LibraryManager(this);

        trackSequencer = new TrackSequencer(this);
        soundSource = new SoundSource(this);
        soundMixer = new SoundMixer(this);
        queueSequencer = new QueueSequencer(this);

        systemSequencer = new SystemSequencer(this);
        systemState = new SystemState(this);
        patternManager = new PatternManager(this);
        memoryManager = new MemoryManager(this);

        components.add(libraryManager);
        components.add(trackSequencer);
        components.add(soundMixer);
        components.add(systemSequencer);
        components.add(patternManager);
        components.add(queueSequencer);

        for (IControllerComponent component : components) {
            component.onRegister();
        }

        memoryManager.setSelectedMemoryType(Type.USER);

        projectManager.initialize();
    }

    private List<IControllerComponent> components = new ArrayList<IControllerComponent>();

    void start() {
    }

    void save() throws IOException {
        CtkDebug.log("Controller.save()");
        projectManager.save();
    }

    void close() {
        CtkDebug.log("Controller.close()");
        soundGenerator.close();
    }

    //--------------------------------------------------------------------------
    // IActivityCycle API
    //--------------------------------------------------------------------------

    @Override
    public void onStart() {
        soundGenerator.onStart();
    }

    @Override
    public void onResume() {
        soundGenerator.onResume();
    }

    @Override
    public void onPause() {
        soundGenerator.onPause();
    }

    @Override
    public void onStop() {
        soundGenerator.onStop();
    }

    @Override
    public void onDestroy() {
        soundGenerator.onDestroy();
    }

    @Override
    public void onRestart() {
        soundGenerator.onRestart();
    }

    @Override
    public void dispose() {
        soundGenerator.dispose();
    }

}