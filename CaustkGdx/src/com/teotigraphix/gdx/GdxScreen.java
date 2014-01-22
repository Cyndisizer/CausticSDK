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

package com.teotigraphix.gdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * The {@link GdxScreen} is the base implementation of the {@link IGdxScreen}
 * API.
 * 
 * @author Michael Schmalle
 * @since 1.0
 */
public class GdxScreen implements IGdxScreen {

    public static final String LOG = GdxScreen.class.getSimpleName();

    //--------------------------------------------------------------------------
    // Private :: Variables
    //--------------------------------------------------------------------------

    private IGdxApplication gdxApplication;

    private Stage stage;

    private Skin skin;

    private TextureAtlas atlas;

    private boolean initialized;

    //--------------------------------------------------------------------------
    // Public API :: Properties
    //--------------------------------------------------------------------------

    //----------------------------------
    // gdxApplication
    //----------------------------------

    @Override
    public IGdxApplication getApplication() {
        return gdxApplication;
    }

    //----------------------------------
    // stage
    //----------------------------------

    @Override
    public Stage getStage() {
        return stage;
    }

    //----------------------------------
    // skin
    //----------------------------------

    @Override
    public Skin getSkin() {
        return skin;
    }

    //----------------------------------
    // initialized
    //----------------------------------

    @Override
    public boolean isInitialized() {
        return initialized;
    }

    //--------------------------------------------------------------------------
    // Protected :: Properties
    //--------------------------------------------------------------------------

    protected final String getName() {
        return getClass().getSimpleName();
    }

    //--------------------------------------------------------------------------
    // Constructors
    //--------------------------------------------------------------------------

    /**
     * Creates a new {@link GdxScreen}.
     */
    public GdxScreen() {
        stage = new Stage();
        atlas = new TextureAtlas(Gdx.files.internal("game.atlas"));
        skin = new Skin(atlas);
    }

    //--------------------------------------------------------------------------
    // IGdxScreen API :: Methods
    //--------------------------------------------------------------------------

    @Override
    public void initialize(IGdxApplication gdxApplication) {
        this.gdxApplication = gdxApplication;
        initialized = true;
    }

    @Override
    public void create() {
        Gdx.app.log(LOG, "Creating screen: " + getName());
    }

    @Override
    public void render(float delta) {
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.log(LOG, "Resizing screen: " + getName() + " to: " + width + " x " + height);
        stage.setViewport(gdxApplication.getWidth(), gdxApplication.getHeight(), true);
        stage.getCamera().translate(-stage.getGutterWidth(), -stage.getGutterHeight(), 0);
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}