package jp.momonnga.libs.commandmodule;

/**
 * コマンドクラス
 *
 * @author ももんが
 * @version 1.0
 */
public abstract class CommandWord {

    /**
     * コマンドのラベル
     */
    private final String label;

    /**
     * コマンドの頭文字
     */
    private final String prefix;

    /**
     * コンストラクタ
     *
     * @param prefix 接頭語
     * @param label  ラベル
     */
    public CommandWord(String prefix, String label) {
        this.prefix = prefix;
        this.label = label;
    }

    /**
     * 接頭語の取得
     *
     * @return 接頭語
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * ラベルの取得
     *
     * @return ラベル
     */
    public String getLabel() {
        return label;
    }

    /**
     * 接頭語付きラベルの取得
     *
     * @return 接頭語付きラベル
     */
    public String getLabelIncludingPrefix() {
        return prefix + label;
    }

}
