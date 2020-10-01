package jp.momonnga.libs.commandmodule;

/**
 * オプションコマンドクラス
 *
 * @author ももんが
 * @version 1.0
 */
public abstract class OptionWord extends CommandWord {

    /**
     * データの接頭語
     */
    private static final String dataPrefix = "=";

    /**
     * コンストラクタ
     *
     * @param label オプションのラベル
     */
    public OptionWord(String label) {
        this("-", label);
    }

    /**
     * コンストラクタ
     *
     * @param prefix オプションの接頭語
     * @param label  オプションのラベル
     */
    public OptionWord(String prefix, String label) {
        super(prefix, label);
    }

    /**
     * データの接頭語を取得
     *
     * @return データの接頭語
     */
    public static String getDataPrefix() {
        return dataPrefix;
    }

    /**
     * オプションの適用
     *
     * @param data オプションのデータ
     */
    protected abstract void patchOption(String data);

}
