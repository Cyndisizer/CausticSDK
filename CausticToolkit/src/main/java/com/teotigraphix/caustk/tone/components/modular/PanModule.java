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

package com.teotigraphix.caustk.tone.components.modular;

import com.teotigraphix.caustk.controller.ICaustkController;

public class PanModule extends ModularComponentBase {

    //----------------------------------
    // inGain
    //----------------------------------

    private float inGain;

    public float getInGain() {
        return inGain;
    }

    float getInGain(boolean restore) {
        return getValue("in_gain");
    }

    /**
     * @param value (0..1)
     */
    public void setInGain(float value) {
        if (value == inGain)
            return;
        inGain = value;
        if (value < 0f || value > 1f)
            newRangeException("in_gain", "0..1", value);
        setValue("in_gain", value);
    }

    //----------------------------------
    // outAGain
    //----------------------------------

    private float outAGain;

    public float getOutAGain() {
        return outAGain;
    }

    float getOutAGain(boolean restore) {
        return getValue("outa_gain");
    }

    /**
     * @param value (0..1)
     */
    public void setOutAGain(float value) {
        if (value == outAGain)
            return;
        outAGain = value;
        if (value < 0f || value > 1f)
            newRangeException("outa_gain", "0..1", value);
        setValue("outa_gain", value);
    }

    //----------------------------------
    // outBGain
    //----------------------------------

    private float outBGain;

    public float getOutBGain() {
        return outBGain;
    }

    float getOutBGain(boolean restore) {
        return getValue("outb_gain");
    }

    /**
     * @param value (0..1)
     */
    public void setOutBGain(float value) {
        if (value == outBGain)
            return;
        outBGain = value;
        if (value < 0f || value > 1f)
            newRangeException("outb_gain", "0..1", value);
        setValue("outb_gain", value);
    }

    public PanModule() {
    }

    public PanModule(ICaustkController controller, int bay) {
        super(controller, bay);
    }

    @Override
    protected int getNumBays() {
        return 1;
    }

    public enum PanModuleJack implements IModularJack {

        InInput(0),

        InPan(1),

        OutA(0),

        OutB(1);

        private int value;

        @Override
        public final int getValue() {
            return value;
        }

        PanModuleJack(int value) {
            this.value = value;
        }
    }
}