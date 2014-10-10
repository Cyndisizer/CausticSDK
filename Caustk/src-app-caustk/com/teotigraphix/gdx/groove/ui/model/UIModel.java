
package com.teotigraphix.gdx.groove.ui.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import com.badlogic.gdx.utils.Array;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.teotigraphix.gdx.app.ApplicationComponent;
import com.teotigraphix.gdx.controller.ViewBase;
import com.teotigraphix.gdx.groove.ui.behavior.MainTemplateBehavior;
import com.teotigraphix.gdx.groove.ui.components.ViewStackData;
import com.teotigraphix.gdx.groove.ui.factory.UIFactory;
import com.teotigraphix.gdx.scene2d.ui.ButtonBar.ButtonBarItem;

/**
 * Holds state for the {@link MainTemplateBehavior} ui.
 */
@Singleton
public abstract class UIModel extends ApplicationComponent implements IUIModel {

    @Inject
    private UIFactory uiFactory;

    //--------------------------------------------------------------------------
    // Private :: Variables
    //--------------------------------------------------------------------------

    private UIState state;

    private Array<ViewStackData> views;

    private Array<ButtonBarItem> buttons;

    private Array<ButtonBarItem> viewButtons;

    @Override
    protected String getPreferenceId() {
        return getApplication().getApplicationId() + "/UIModel";
    }

    public UIState getState() {
        return state;
    }

    //----------------------------------
    // uiFactory
    //----------------------------------

    @Override
    public UIFactory getUIFactory() {
        return uiFactory;
    }

    //--------------------------------------------------------------------------
    // Public Property :: API
    //--------------------------------------------------------------------------

    @Override
    public Collection<ViewBase> getViews() {
        ArrayList<ViewBase> values = new ArrayList<ViewBase>(state.getViews().values());
        Collections.sort(values, new Comparator<ViewBase>() {
            @Override
            public int compare(ViewBase lhs, ViewBase rhs) {
                return lhs.getIndex() > rhs.getIndex() ? 1 : -1;
            }
        });
        return values;
    }

    //----------------------------------
    // selectedView
    //----------------------------------

    @Override
    public ViewBase getSelectedView() {
        return getState().getSelectedView();
    }

    @Override
    public void setSelectedViewId(int viewId) {
        if (getState().getSelectedViewId() == viewId)
            return;
        getState().setSelectedViewId(viewId);
        getEventBus().post(new UIModelEvent(UIModelEventKind.ViewChange, this));
    }

    //----------------------------------
    // viewIndex
    //----------------------------------

    @Override
    public int getViewIndex() {
        return getSelectedView().getIndex();
    }

    /**
     * @param viewIndex
     * @see UIModelEventKind#ViewChange
     */
    @Override
    public void setViewIndex(int viewIndex) {
        if (getViewIndex() == viewIndex)
            return;
        state.setSelectedViewId(getViewIdByIndex(viewIndex));
        getEventBus().post(new UIModelEvent(UIModelEventKind.ViewChange, this));
    }

    @Override
    public ViewBase getViewByIndex(int viewIndex) {
        for (ViewBase view : state.getViews().values()) {
            if (view.getIndex() == viewIndex)
                return view;
        }
        return null;
    }

    private int getViewIdByIndex(int viewIndex) {
        for (ViewBase view : state.getViews().values()) {
            if (view.getIndex() == viewIndex)
                return view.getId();
        }
        return -1;
    }

    //----------------------------------
    // views
    //----------------------------------

    @Override
    public void setSceneViewIndex(int viewIndex) {
        if (state.getSceneViewIndex() == viewIndex)
            return;
        state.setSceneViewIndex(viewIndex);
        getEventBus().post(new UIModelEvent(UIModelEventKind.SceneViewChange, this));
    }

    @Override
    public int getSceneViewIndex() {
        return state.getSceneViewIndex();
    }

    @Override
    public Array<ViewStackData> getSceneViews() {
        return views;
    }

    @Override
    public void setSceneViews(Array<ViewStackData> views) {
        this.views = views;
    }

    //----------------------------------
    // buttons
    //----------------------------------

    @Override
    public Array<ButtonBarItem> getSceneButtons() {
        if (buttons != null)
            return buttons;

        buttons = new Array<ButtonBarItem>();
        for (ViewStackData data : views) {
            buttons.add(data.toButtonBarItem());
        }

        return buttons;
    }

    @Override
    public void setSceneButtons(Array<ButtonBarItem> buttons) {
        this.buttons = buttons;
    }

    //----------------------------------
    // viewButtons
    //----------------------------------

    @Override
    public Array<ButtonBarItem> getViewButtons() {
        return viewButtons;
    }

    @Override
    public void setViewButtons(Array<ButtonBarItem> viewButtons) {
        this.viewButtons = viewButtons;
    }

    //--------------------------------------------------------------------------
    // Constructor
    //--------------------------------------------------------------------------

    public UIModel() {
    }

    @Override
    public void restore(UIState state) {
        this.state = state;

        getEventBus().post(new UIModelEvent(UIModelEventKind.SceneViewChange, this));
        getEventBus().post(new UIModelEvent(UIModelEventKind.ViewChange, this));
        getEventBus().post(new UIModelEvent(UIModelEventKind.PrefsViewIndexChange, this));
    }

    //--------------------------------------------------------------------------
    // Event
    //--------------------------------------------------------------------------

    public enum UIModelEventKind {
        SceneViewChange, ViewChange, PrefsViewIndexChange
    }

    public static class UIModelEvent {

        private UIModelEventKind kind;

        private UIModel model;

        public UIModelEventKind getKind() {
            return kind;
        }

        public UIModel getModel() {
            return model;
        }

        public UIModelEvent(UIModelEventKind kind, UIModel model) {
            this.kind = kind;
            this.model = model;
        }
    }
}
