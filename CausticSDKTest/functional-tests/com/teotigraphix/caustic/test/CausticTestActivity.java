
package com.teotigraphix.caustic.test;

import com.google.inject.Inject;
import com.google.inject.Module;
import com.teotigraphix.caustic.activity.CausticActivity;
import com.teotigraphix.caustic.song.IWorkspace;

public class CausticTestActivity extends CausticActivity {

    @Inject
    private IWorkspace workspace;

    public IWorkspace getWorkspace() {
        return workspace;
    }

    @Override
    protected Module createApplicationModule() {
        return new DefaultCausticModule();
    }

}
