
package com.teotigraphix.caustk.application;

import java.io.File;

import com.teotigraphix.caustk.controller.CaustkController;
import com.teotigraphix.caustk.controller.ICaustkController;
import com.teotigraphix.caustk.core.ICausticEngine;
import com.teotigraphix.caustk.sound.DesktopSoundGenerator;
import com.teotigraphix.caustk.sound.ISoundGenerator;

/**
 * Used in unit tests of the toolkit framework. Need the
 * {@link DesktopSoundGenerator} desktop access for tool kit tests.
 */
public abstract class CaustkConfigurationBase implements ICaustkConfiguration {

    private File applicationRoot;

    private File causticStorage;

    public CaustkConfigurationBase() {
        setCausticStorage(new File(System.getProperty("user.home")));
    }

    @Override
    public abstract String getApplicationId();

    @Override
    public File getApplicationRoot() {
        return applicationRoot;
    }

    @Override
    public void setApplicationRoot(File value) {
        applicationRoot = value;
    }

    @Override
    public File getCausticStorage() {
        return causticStorage;
    }

    @Override
    public void setCausticStorage(File value) {
        causticStorage = value;
    }

    @Override
    public IDeviceFactory createDeviceFactory(ICausticEngine engine) {
        return new DeviceFactory(engine);
    }

    @Override
    public ICaustkController createController(ICaustkApplication application) {
        return new CaustkController(application);
    }

    @Override
    public ISoundGenerator createSoundGenerator(ICaustkController controller) {
        return new DesktopSoundGenerator(controller);
    }

}
