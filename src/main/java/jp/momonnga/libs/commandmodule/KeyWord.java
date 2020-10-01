package jp.momonnga.libs.commandmodule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * メインコマンドクラス
 *
 * @author ももんが
 * @version 1.0
 */
public abstract class KeyWord extends CommandWord {

    /**
     * オプションコマンドのリスト
     */
    private final List<OptionWord> optionCommandList = new ArrayList<>();

    /**
     * 実行時にオプションが使われたかどうか
     */
    private boolean usedOption = false;

    /**
     * コンストラクタ 頭文字指定あり
     *
     * @param prefix 頭文字
     * @param label  コマンドの文字列
     * @see "(prefix)(label)の形"
     */
    public KeyWord(String prefix, String label) {
        super(prefix, label);
        initVariable();
    }

    /**
     * コンストラクタ 頭文字指定なし
     * /label の形
     *
     * @param label コマンドの文字列
     */
    public KeyWord(String label) {
        this("/", label);
    }

    /**
     * 継承クラスのメンバ初期化用メソッド
     * インスタンスを使いまわすからどうしても必要(改善案考え中)
     */
    protected abstract void initVariable();

    /**
     * コマンドの実行(オプションの適用がメイン)
     *
     * @param args サブコマンドの配列
     */
    public void execute(String[] args) {
        //前回のメンバの初期化
        initVariable();

        //オプションの適用
        Arrays.stream(args).forEach(this::patchOption);

        //最終処理
        execute();

        //オプションの初期化
        usedOption = false;
    }

    /**
     * コマンドの実行(オプション適用後)
     */
    protected abstract void execute();

    /**
     * オプションの適用
     *
     * @param arg オプションの
     */
    private void patchOption(String arg) {
        //オプション使ったら入れる
        usedOption = true;
        //argの長さ
        int length = arg.length();

        //dataPrefixのインデックス番号
        int equalIndex = arg.indexOf(OptionWord.getDataPrefix());

        //ラベルとデータの切り出し
        String labelIncludingPrefix = arg.substring(0, equalIndex);
        String data = arg.substring(equalIndex + 1, length);

        //オプションの呼び出し
        OptionWord optionCommand = getOptionCommand(labelIncludingPrefix);

        //オプションの適用
        optionCommand.patchOption(data);
    }

    /**
     * オプションの取得
     *
     * @param labelIncludingPrefix インデックス込みラベル
     * @return オプション
     */
    private OptionWord getOptionCommand(String labelIncludingPrefix) {
        for (OptionWord optionCommand : optionCommandList) {
            if (optionCommand.getLabelIncludingPrefix().equals(labelIncludingPrefix)) return optionCommand;
        }
        throw new IllegalArgumentException(labelIncludingPrefix + "は不正なコマンドです");
    }

    /**
     * オプションの追加(処理実装済み)
     *
     * @param optionCommand オプションコマンド
     */
    protected void addOptionCommand(OptionWord optionCommand) {
        this.optionCommandList.add(optionCommand);
    }

    /**
     * オプションが実行に使われたか
     *
     * @return true 使われた false 使用していない
     */
    public boolean whetherUsedOption() {
        return usedOption;
    }
}
