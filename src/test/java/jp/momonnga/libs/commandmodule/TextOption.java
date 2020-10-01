package jp.momonnga.libs.commandmodule;

import jp.momonnga.libs.commandmodule.option.HasText;

public class TextOption extends OptionWord {

    private HasText hasText;

    public TextOption(HasText hasText){
        super("-","text");
        this.hasText = hasText;
    }

    @Override
    protected void patchOption(String data) {
        hasText.setText(data);
    }
}
