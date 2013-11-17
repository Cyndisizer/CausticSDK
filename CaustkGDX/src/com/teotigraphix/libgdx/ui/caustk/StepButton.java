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

package com.teotigraphix.libgdx.ui.caustk;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.teotigraphix.libgdx.ui.ControlTable;

/**
 * @author Michael Schmalle
 */
public class StepButton extends ControlTable {

    //--------------------------------------------------------------------------
    // Variables
    //--------------------------------------------------------------------------

    private Stack stack;

    private TextButton button;

    private ButtonStateOverlayGroup overlay;

    private ButtonGroup buttonGroup;

    private StepButtonItem item;

    private boolean noEvent = false;

    private boolean noCallBack = false;

    //----------------------------------
    // buttonGroup
    //----------------------------------

    public ButtonGroup getButtonGroup() {
        return buttonGroup;
    }

    public void setButtonGroup(ButtonGroup value) {
        buttonGroup = value;
    }

    public void selectActive(boolean selected) {
        overlay.active(selected);
    }

    public void selectCurrent(boolean selected) {
        overlay.current(selected);
    }

    public int getIndex() {
        return item.getIndex();
    }

    //--------------------------------------------------------------------------
    // Constructor
    //--------------------------------------------------------------------------

    public StepButton(StepButtonItem item, Skin skin) {
        super(skin);
        this.item = item;
    }

    @Override
    protected void createChildren() {
        stack = new Stack();

        button = new TextButton(item.getText(), getSkin(), item.getStyleName());
        if (buttonGroup != null)
            buttonGroup.add(button);

        button.getLabel().setAlignment(Align.top);
        button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (noEvent || !item.isToggle())
                    event.cancel();
                if (!noCallBack) {
                    boolean canceled = onStepButtonListener.onChange(getIndex(), button.isChecked());
                    if (canceled)
                        event.cancel();
                }

            }
        });
        button.addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                boolean canceled = onStepButtonListener.onTouchDown(getIndex());
                if (canceled)
                    event.cancel();
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                onStepButtonListener.onTouchUp(getIndex());
            }
        });
        stack.add(button);
        add(stack).fill().expand().minWidth(10f);

        overlay = new ButtonStateOverlayGroup(getSkin());
        stack.add(overlay);
    }

    public void updateSelected(boolean selected) {
        noCallBack = true;
        button.setChecked(selected);
        noCallBack = false;
    }

    public void select(boolean selected) {
        noEvent = true;
        button.setChecked(selected);
        noEvent = false;
    }

    public void toggle() {
        noEvent = true;
        button.toggle();
        noEvent = false;
    }

    public static class StepButtonItem {

        private String text;

        private String styleName;

        private int index;

        private boolean isToggle;

        public boolean isToggle() {
            return isToggle;
        }

        public int getIndex() {
            return index;
        }

        public String getStyleName() {
            return styleName;
        }

        public String getText() {
            return text;
        }

        public StepButtonItem(int index, String text, boolean isToggle, String styleName) {
            this.index = index;
            this.text = text;
            this.isToggle = isToggle;
            this.styleName = styleName;
        }

    }

    //--------------------------------------------------------------------------
    // Event API
    //--------------------------------------------------------------------------

    private OnStepButtonListener onStepButtonListener;

    public void setOnStepButtonListener(OnStepButtonListener l) {
        onStepButtonListener = l;
    }

    public interface OnStepButtonListener {
        boolean onChange(int index, boolean selected);

        void onTouchUp(int index);

        boolean onTouchDown(int index);
    }

}
