////////////////////////////////////////////////////////////////////////////////
// Copyright 2012 Michael Schmalle - Teoti Graphix, LLC
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

package com.teotigraphix.caustk.sound.effect;

import com.teotigraphix.caustk.controller.IControllerComponent;

public class ChorusEffect extends EffectBase implements IControllerComponent {

    @Override
    public EffectType getType() {
        return EffectType.CHORUS;
    }

    //--------------------------------------------------------------------------
    // API :: Properties
    //--------------------------------------------------------------------------

    //----------------------------------
    // depth
    //----------------------------------

    private float depth = 0.3f;

    public float getDepth() {
        return depth;
    }

    float getDepth(boolean restore) {
        return get(ChorusControl.Depth);
    }

    public void setDepth(float value) {
        if (value == depth)
            return;
        if (value < 0.1f || value > 0.95f)
            throw newRangeException(ChorusControl.Depth, "0.1..0.95", value);
        depth = value;
        set(ChorusControl.Depth, depth);
    }

    //----------------------------------
    // rate
    //----------------------------------

    private float rate = 0.4f;

    public float getRate() {
        return rate;
    }

    float getRate(boolean restore) {
        return get(ChorusControl.Rate);
    }

    public void setRate(float value) {
        if (value == rate)
            return;
        if (value < 0f || value > 1.0f)
            throw newRangeException(ChorusControl.Rate, "0.0..1.0", value);
        rate = value;
        set(ChorusControl.Rate, rate);
    }

    //----------------------------------
    // wet
    //----------------------------------

    private float wet = 0.5f;

    public float getWet() {
        return wet;
    }

    public float getWet(boolean restore) {
        return get(ChorusControl.Wet);
    }

    public void setWet(float value) {
        if (value == wet)
            return;
        if (value < 0f || value > 1f)
            throw newRangeException(ChorusControl.Wet, "0..1", value);
        wet = value;
        set(ChorusControl.Wet, wet);
    }

    public ChorusEffect(int slot, int toneIndex) {
        super(slot, toneIndex);
    }

    @Override
    public void restore() {

    }

    public enum ChorusControl implements IEffectControl {

        /**
         * 0.1..0.95
         */
        Depth("depth"),

        /**
         * 0..1.0
         */
        Rate("rate"),

        /**
         * 0..1.0
         */
        Wet("wet");

        private String control;

        public String getControl() {
            return control;
        }

        private ChorusControl(String control) {
            this.control = control;
        }
    }
}