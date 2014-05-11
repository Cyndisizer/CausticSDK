////////////////////////////////////////////////////////////////////////////////
// Copyright 2014 Michael Schmalle - Teoti Graphix, LLC
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

package com.teotigraphix.caustk.node.master;

import com.teotigraphix.caustk.core.osc.MasterMixerMessage.MasterMixerControl;
import com.teotigraphix.caustk.node.NodeBase;
import com.teotigraphix.caustk.node.effect.EffectsChannel;

/**
 * The master insert of the rack.
 * <p>
 * Holds the master delay, reverb, equalizer, limiter and volume.
 * 
 * @author Michael Schmalle
 * @since 1.0
 */
public class MasterNode extends NodeBase {

    //--------------------------------------------------------------------------
    // Private :: Variables
    //--------------------------------------------------------------------------

    private MasterDelayNode delay;

    private MasterReverbNode reverb;

    private MasterEqualizerNode equalizer;

    private MasterLimiterNode limiter;

    private MasterVolumeNode volume;

    private EffectsChannel effects;

    //--------------------------------------------------------------------------
    // Public Property API
    //--------------------------------------------------------------------------

    //----------------------------------
    // delay
    //----------------------------------

    /**
     * The master delay node in the rack.
     */
    public MasterDelayNode getDelay() {
        return delay;
    }

    //----------------------------------
    // reverb
    //----------------------------------

    /**
     * The master reverb node in the rack.
     */
    public MasterReverbNode getReverb() {
        return reverb;
    }

    //----------------------------------
    // equalizer
    //----------------------------------

    /**
     * The master equalizer node in the rack.
     */
    public MasterEqualizerNode getEqualizer() {
        return equalizer;
    }

    //----------------------------------
    // limiter
    //----------------------------------

    /**
     * The master limiter node in the rack.
     */
    public MasterLimiterNode getLimiter() {
        return limiter;
    }

    //----------------------------------
    // volume
    //----------------------------------

    /**
     * The master volume node in the rack.
     */
    public MasterVolumeNode getVolume() {
        return volume;
    }

    //----------------------------------
    // effects
    //----------------------------------

    /**
     * The master effects node in the rack.
     */
    public EffectsChannel getEffects() {
        return effects;
    }

    //--------------------------------------------------------------------------
    // Constructors
    //--------------------------------------------------------------------------

    /**
     * Serialization
     */
    public MasterNode() {
        delay = new MasterDelayNode();
        reverb = new MasterReverbNode();
        equalizer = new MasterEqualizerNode();
        limiter = new MasterLimiterNode();
        volume = new MasterVolumeNode();
        effects = new EffectsChannel(-1);
    }

    //--------------------------------------------------------------------------
    // Overridden Protected :: Methods
    //--------------------------------------------------------------------------

    @Override
    protected void createComponents() {
    }

    @Override
    protected void destroyComponents() {
    }

    @Override
    protected void updateComponents() {
        delay.update();
        reverb.update();
        equalizer.update();
        limiter.update();
        volume.update();
        effects.update();
    }

    @Override
    protected void restoreComponents() {
        delay.restore();
        reverb.restore();
        equalizer.restore();
        limiter.restore();
        volume.restore();
        effects.restore();
    }

    //--------------------------------------------------------------------------
    // Events
    //--------------------------------------------------------------------------

    /**
     * Base event for the {@link MasterNode} and {@link MasterChildNode}.
     * 
     * @author Michael Schmalle
     * @since 1.0
     */
    public static class MasterNodeEvent extends NodeEvent {

        public MasterNodeEvent(NodeBase target, MasterMixerControl control) {
            super(target, control);
        }
    }

    public static class MasterNodeChangeEvent extends NodeEvent {

        private float value;

        public float getValue() {
            return value;
        }

        public MasterNodeChangeEvent(NodeBase target, MasterMixerControl control, float value) {
            super(target, control);
            this.value = value;
        }
    }
}
