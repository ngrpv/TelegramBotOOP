package first;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TGBotButtons {
    public static final HashMap<UserStateEnum, ArrayList<KeyboardRow>> keyboardByState = new HashMap<>();
    public static final String[][] menuButtonsNames = new String[][]{new String[]{"Виселица", "Помощь"}};

    public TGBotButtons() {
        setDefaultButtons();
    }

    public static List<KeyboardRow> getButtons(UserStateEnum stateEnum) {
        return keyboardByState.get(stateEnum);
    }

    public static void setKeyboard(UserStateEnum stateEnum, ArrayList<KeyboardRow> keyboardRows) {
        keyboardByState.put(stateEnum, keyboardRows);
    }

    private void setDefaultButtons() {
       setButtons(menuButtonsNames, UserStateEnum.onMenu);
    }

    private void setButtons(String[][] names, UserStateEnum stateEnum){
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
