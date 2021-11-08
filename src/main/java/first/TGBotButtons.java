package first;

import first.user.UserState;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TGBotButtons {
    public static final HashMap<UserState, ArrayList<KeyboardRow>> keyboardByState = new HashMap<>();
    public static final String[][] menuButtonsNames = new String[][]{new String[]{"Виселица", "Быки и коровы"}, new String[]{"Помощь", "Топ"}};
    public static final String[][] gameButtonsNames = new String[][]{new String[]{"Правила", "Перезапустить"}, new String[]{"Выход"}};

    public TGBotButtons() {
        setDefaultButtons();
    }

    public static List<KeyboardRow> getButtons(UserState stateEnum) {
        return keyboardByState.get(stateEnum);
    }

    public static void setDefaultButtons() {
        setButtons(gameButtonsNames, UserState.Playing);
        setButtons(menuButtonsNames, UserState.onMenu);
    }

    private static void setButtons(String[][] names, UserState stateEnum){
        var buttonsKeyboard = new ArrayList<KeyboardRow>();
        for (String[] name : names) {
            var row = new KeyboardRow();
            for (String s : name) {
                row.add(s);
            }
            buttonsKeyboard.add(row);
        }
        keyboardByState.put(stateEnum, buttonsKeyboard);
    }
}
