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

package com.teotigraphix.caustk.groove.library;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import com.esotericsoftware.kryo.serializers.TaggedFieldSerializer.Tag;
import com.teotigraphix.caustk.core.CausticException;
import com.teotigraphix.caustk.groove.importer.CausticGroup;
import com.teotigraphix.caustk.groove.manifest.LibraryGroupManifest;

public class LibraryGroup extends LibraryProductItem {

    @Tag(20)
    private LibraryGroupManifest manifest;

    @Tag(21)
    private Map<Integer, LibrarySound> sounds = new TreeMap<Integer, LibrarySound>();

    private transient CausticGroup causticGroup;

    public CausticGroup getCausticGroup() {
        return causticGroup;
    }

    public void setCausticGroup(CausticGroup causticGroup) {
        this.causticGroup = causticGroup;
        setReletivePath(causticGroup.getPath());
    }

    @Override
    public LibraryGroupManifest getManifest() {
        return manifest;
    }

    public LibrarySound getSound(int index) {
        return sounds.get(index);
    }

    public Collection<LibrarySound> getSounds() {
        return sounds.values();
    }

    public LibraryGroup(LibraryGroupManifest manifest) {
        this.manifest = manifest;
    }

    public void addSound(int index, LibrarySound sound) throws CausticException {
        if (sounds.containsKey(index))
            throw new CausticException("Slot not empty, use replace ");
        sound.setIndex(index);
        sounds.put(index, sound);
        sound.setGroup(this);
    }
}
