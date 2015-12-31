package ee.ttu.foodgame.controller;

import static ee.ttu.foodgame.App.UNIT;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;

import ee.ttu.foodgame.App;

public abstract class Controller {

    protected Table createTableWithButtons(TextButton... buttons) {
        // Table that fills the screen
        Table table = new Table();
        table.setFillParent(true);
        table.setPosition(0, App.GAME_HEIGHT * 0.05f);

        // VerticalGroup that holds all buttons
        VerticalGroup verticalGroup = new VerticalGroup().space(UNIT).pad(UNIT).fill();

        // Buttons
        for (int i = 0; i < buttons.length; i++) {
            verticalGroup.addActor(buttons[i]);
        }

        // VerticalGroup expands to fill the table in the X-dimension
        table.add(verticalGroup).expandX().fillX();

        // Table is bottom-aligned
        table.bottom();

        return table;
    }

    protected TextButton createButton(FileHandle atlasFile, FileHandle fontFile, String upDrawableName,
            String downDrawableName, String buttonText) {
        Skin skin = new Skin();
        TextureAtlas atlas = new TextureAtlas(atlasFile);
        skin.addRegions(atlas);
        TextButtonStyle style = new TextButtonStyle();
        style.up = skin.getDrawable(upDrawableName);
        style.down = skin.getDrawable(downDrawableName);
        style.font = new BitmapFont(fontFile, false);
        return new TextButton(buttonText, style);
    }

}
