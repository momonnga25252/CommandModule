package jp.momonnga.libs.commandmodule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * コマンドモジュール
 *
 * @author ももんが
 * @version 1.0
 */
public class CommandModule {

    /**
     * メインコマンドのリスト
     */
    private static final List<KeyWord> keyWordList = new ArrayList<>();

    /**
     * Singleton
     */
    private CommandModule() {
    }

    /**
     * コマンドの実行
     *
     * @param text コマンドの平文
     */
    public static void executeCommand(String text) {

        //スペースで区切る
        String[] args = text.split(" ");

        //サブコマンドを抽出
        List<String> convert = new ArrayList<>(Arrays.asList(args));
        convert.remove(0);
        String[] options = convert.toArray(convert.toArray(new String[convert.size() - 1]));

        //該当するコマンドすべて実行
        for (KeyWord keyWord : getKeyWordList(args[0])) {
            keyWord.execute(options);
        }

    }

    /**
     * コマンドを登録
     *
     * @param keyWord メインコマンド
     */
    public static void registerCommand(KeyWord keyWord) {
        keyWordList.add(keyWord);
    }

    /**
     * コマンドの登録解除
     *
     * @param keyWord メインコマンド
     */
    public static void unregisterCommand(KeyWord keyWord) {
        keyWordList.remove(keyWord);
    }

    /**
     * コマンドの登録解除
     *
     * @param labelIncludePrefix 接頭語付きラベル
     */
    public static void unregisterCommand(String labelIncludePrefix) {
        getKeyWordList(labelIncludePrefix).forEach(CommandModule::unregisterCommand);
    }

    /**
     * 該当のメインコマンドを返す
     *
     * @param labelIncludePrefix 接頭語付きラベル
     * @return メインコマンドのリスト
     */
    private static List<KeyWord> getKeyWordList(String labelIncludePrefix) {
        List<KeyWord> result = new ArrayList<>();
        for (KeyWord keyWord : keyWordList) {
            if (keyWord.getLabelIncludingPrefix().equals(labelIncludePrefix)) result.add(keyWord);
        }
        return result;
    }

}
