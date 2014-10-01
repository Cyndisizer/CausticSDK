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

package com.teotigraphix.gdx.app;

import java.io.File;
import java.io.IOException;

import com.teotigraphix.caustk.core.CaustkProject;

public interface IApplicationModel extends IApplicationComponent {

    ApplicationPreferences getApplicationPreferences();

    <T extends CaustkProject> T getProject();

    /**
     * @param project
     * @throws IOException
     * @see ApplicationModelProjectCreateEvent
     * @see ApplicationModelProjectLoadEvent
     */
    <T extends CaustkProject> void setProject(T project) throws IOException;

    void newProject(File file) throws IOException;

    void loadProject(File file) throws IOException;

    File saveProjectAs(String projectName) throws IOException;

    File exportProject(File file, ApplicationExportType exportType) throws IOException;;

    void save();

    void dispose();

    public static enum ApplicationExportType {

        Caustic,

        Project;
    }

}