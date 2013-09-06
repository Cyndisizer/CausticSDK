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

package com.teotigraphix.caustk.controller;

import com.teotigraphix.caustk.controller.core.Dispatcher;

/**
 * Bas class for a {@link IControllerComponent}.
 * <p>
 * The {@link IControllerComponent}s get registered with
 * {@link ICaustkController#addComponent(Class, IControllerComponent)}.
 */
public abstract class ControllerComponent implements IControllerComponent {

    //--------------------------------------------------------------------------
    // Property API
    //--------------------------------------------------------------------------

    //----------------------------------
    // controller
    //----------------------------------

    private final ICaustkController controller;

    public final ICaustkController getController() {
        return controller;
    }

    //----------------------------------
    // dispatcher
    //----------------------------------

    private final IDispatcher dispatcher;

    @Override
    public final IDispatcher getDispatcher() {
        return dispatcher;
    }

    //--------------------------------------------------------------------------
    // Constructor
    //--------------------------------------------------------------------------

    public ControllerComponent(ICaustkController controller) {
        this.controller = controller;
        dispatcher = new Dispatcher();
    }

    //--------------------------------------------------------------------------
    // Methods
    //--------------------------------------------------------------------------

    @Override
    public abstract void onRegister();

}
