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

package com.teotigraphix.caustic.test;

import com.teotigraphix.caustic.activity.ICausticBackend;
import com.teotigraphix.caustic.activity.ICausticConfiguration;
import com.teotigraphix.caustic.internal.actvity.DefaultCausticBackend;

public class DefaultConfiguration implements ICausticConfiguration {

    @Override
    public ICausticBackend createBackend() {
        return new DefaultCausticBackend() {
            @Override
            public int returnCausticCoreKey() {
                // com.teotigraphix.caustic.test
                return 0xA752E912;
            }
        };
    }

}
