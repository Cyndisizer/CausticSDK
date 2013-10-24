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

package com.teotigraphix.caustk.rack.tone;

import java.util.ArrayList;
import java.util.List;

import com.teotigraphix.caustk.rack.Rack;

/**
 * @author Michael Schmalle
 */
public class RhythmSet extends Tone {

    private List<RhythmTone> tones = new ArrayList<RhythmTone>();

    public List<RhythmTone> getTones() {
        return tones;
    }

    public RhythmSet() {
    }

    public RhythmSet(Rack rack) {
        super(rack, ToneType.Beatbox);
    }

}