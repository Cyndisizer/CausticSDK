
package com.teotigraphix.caustk.machine;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import com.teotigraphix.caustk.rack.IEffect;
import com.teotigraphix.caustk.rack.IRack;
import com.teotigraphix.caustk.rack.effect.EffectType;
import com.teotigraphix.caustk.rack.mixer.MasterDelay;
import com.teotigraphix.caustk.rack.mixer.MasterEqualizer;
import com.teotigraphix.caustk.rack.mixer.MasterLimiter;
import com.teotigraphix.caustk.rack.mixer.MasterMixer;
import com.teotigraphix.caustk.rack.mixer.MasterReverb;

public class CaustkLibraryFactory {

    public enum CaustkComponent {

        /**
         * A {@link CaustkLibrary} holds {@link CaustkScene},
         * {@link CaustkMachine}, {@link CaustkPatch}, {@link CaustkEffect},
         * {@link CaustkPhrase}, {@link CastkMasterMixer} and
         * {@link CaustkMasterSequencer} components.
         */
        Library,

        /**
         * A {@link CaustkScene} holds {@link CaustkMachine} components.
         */
        Scene,

        /**
         * A {@link CaustkMachine} holds one {@link CaustkPatch} and multiple
         * {@link CaustkPhrase} components.
         */
        Machine,

        /**
         * A {@link CaustkPatch} holds one {@link MachinePreset}, one
         * {@link MixerPreset} and up to two {@link CaustkEffect} components.
         */
        Patch,

        /**
         * A {@link CaustkEffect} hold up to 2 live or serialized
         * {@link IEffect} components.
         */
        Effect,

        /**
         * A {@link CaustkPhrase} holds a
         */
        Phrase,

        /**
         * A {@link CastkMasterMixer} holds a live or serialized
         * {@link MasterMixer} which in turn contains a {@link MasterDelay},
         * {@link MasterReverb}, {@link MasterEqualizer} and
         * {@link MasterLimiter} component.
         */
        MasterMixer,

        MasterSequencer;
    }

    private CaustkSceneFactory sceneFactory;

    private CaustkMachineFactory machineFactory;

    private CaustkPatchFactory patchFactory;

    private CaustkEffectFactory effectFactory;

    private CaustkPhraseFactory phraseFactory;

    private CaustkMasterMixerFactory masterMixerFactory;

    private CaustkMasterSequencerFactory masterSequencerFactory;

    private IRack rack;

    public IRack getRack() {
        return rack;
    }

    public void setRack(IRack value) {
        rack = value;

        sceneFactory = new CaustkSceneFactory(rack);
        machineFactory = new CaustkMachineFactory(rack);
        patchFactory = new CaustkPatchFactory(rack);
        effectFactory = new CaustkEffectFactory(rack);
        phraseFactory = new CaustkPhraseFactory(rack);
        masterMixerFactory = new CaustkMasterMixerFactory(rack);
        masterSequencerFactory = new CaustkMasterSequencerFactory(rack);
    }

    public CaustkLibraryFactory(IRack rack) {
        setRack(rack);
    }

    /**
     * Creates an empty {@link CaustkLibrary} with a name.
     * <p>
     * The name is used for the directory name held within the
     * <code>/storageRoot/AppName/libraries</code> directory.
     * 
     * @param name The name of the library, used as the directory name.
     */
    public CaustkLibrary createLibrary(String name) {
        CaustkLibrary caustkLibrary = new CaustkLibrary(UUID.randomUUID(), name);
        return caustkLibrary;
    }

    /**
     * Creates an empty {@link CaustkScene} with name.
     * 
     * @param name The name of the scene.
     */
    public CaustkScene createScene(String name) {
        return sceneFactory.createScene(name);
    }

    /**
     * Creates a {@link CaustkScene} from a <code>.caustic</code> song file
     * import.
     * 
     * @param absoluteCausticFile The absolute location of the
     *            <code>.caustic</code> song file.
     */
    public CaustkScene createScene(File absoluteCausticFile) {
        return sceneFactory.createScene(absoluteCausticFile);
    }

    public CaustkMachine createMachine(MachineType machineType) {
        return machineFactory.createMachine(machineType);
    }

    public CaustkMachine createMachine(MachineType machineType, int index, String machineName) {
        return machineFactory.createMachine(machineType, index, machineName);
    }

    /**
     * Creates a {@link CaustkPatch} with {@link UUID} and {@link MachineType}.
     * 
     * @param toneType The {@link MachineType} of the
     */
    public CaustkPatch createPatch(MachineType machineType) {
        return patchFactory.createPatch(machineType);
    }

    /**
     * Creates a new {@link CaustkPatch}, assigns the {@link CaustkMachine}.
     * 
     * @param machine A {@link CaustkMachine} that does not exist in the native
     *            rack.
     */
    public CaustkPatch createPatch(CaustkMachine machine) {
        return patchFactory.createPatch(machine);
    }

    /**
     * Activates the patch, creating the {@link MachinePreset} and
     * <p>
     * - Creates and assigns the bytes for the {@link MachinePreset}.
     * <p>
     * - Creates and assigns the {@link CaustkPatch} which will then create 0-2
     * {@link CaustkEffect}s. When the {@link CaustkEffect} is created, only the
     * {@link EffectType} is saved and slot index. The {@link IEffect} instance
     * is not restored at this point.
     * 
     * @param livePatch
     * @throws IOException
     */
    public void activatePatch(CaustkPatch caustkPatch) throws IOException {
        patchFactory.activatePatch(caustkPatch);
    }

    public CaustkPhrase createPhrase(MachineType machineType, int bankIndex, int patternIndex) {
        return phraseFactory.createPhrase(machineType, bankIndex, patternIndex);
    }

    public CaustkPhrase createPhrase(CaustkMachine caustkMachine, int bankIndex, int patternIndex) {
        return phraseFactory.createPhrase(caustkMachine, bankIndex, patternIndex);
    }

}