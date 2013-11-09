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

package com.teotigraphix.caustk.workstation;

import com.esotericsoftware.kryo.serializers.TaggedFieldSerializer.Tag;
import com.teotigraphix.caustk.controller.ICaustkApplicationContext;
import com.teotigraphix.caustk.core.CausticException;

/**
 * @author Michael Schmalle
 */
public class Part extends CaustkComponent {

    //--------------------------------------------------------------------------
    // Serialized API
    //--------------------------------------------------------------------------

    @Tag(100)
    private Pattern pattern;

    @Tag(101)
    private Machine machine;

    //--------------------------------------------------------------------------
    // Public API :: Properties
    //--------------------------------------------------------------------------

    //----------------------------------
    // defaultName
    //----------------------------------

    @Override
    public String getDefaultName() {
        return machine.getDefaultName();
    }

    //----------------------------------
    // pattern
    //----------------------------------

    /**
     * Returns the owning {@link Pattern}.
     */
    public Pattern getPattern() {
        return pattern;
    }

    //----------------------------------
    // machine
    //----------------------------------

    /**
     * Returns the owning {@link Machine} in the {@link PatternSet}'s
     * {@link RackSet}.
     */
    public Machine getMachine() {
        return machine;
    }

    //----------------------------------
    // patch
    //----------------------------------

    /**
     * Returns the {@link Machine}s {@link Patch}.
     */
    public Patch getPatch() {
        return machine.getPatch();
    }

    //----------------------------------
    // phrase
    //----------------------------------

    /**
     * Returns the {@link Phrase} of the machine at the {@link Pattern}'s bank
     * and pattern index.
     */
    public Phrase getPhrase() {
        return machine.getPhrase(pattern.getBankIndex(), pattern.getPatternIndex());
    }

    //--------------------------------------------------------------------------
    // Constructors
    //--------------------------------------------------------------------------

    /*
     * Serialization.
     */
    Part() {
    }

    Part(ComponentInfo info, Pattern pattern, Machine machine) {
        setInfo(info);
        this.pattern = pattern;
        this.machine = machine;
    }

    @Override
    protected void componentPhaseChange(ICaustkApplicationContext context, ComponentPhase phase)
            throws CausticException {
        switch (phase) {
            case Connect:
                break;
            case Create:
                break;
            case Disconnect:
                break;
            case Load:
                break;
            case Restore:
                break;
            case Update:
                break;
        }
    }
}
