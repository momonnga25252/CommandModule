package jp.momonnga.libs.commandmodule;

import jp.momonnga.libs.commandmodule.option.HasText;

public class TestCommand extends KeyWord implements HasText {

    private String text;

    public TestCommand(){
        super("/","test");
        addOptionCommand(new TextOption(this));
    }

    @Override
    protected void initVariable() {
        text = "Hello World";
    }

    @Override
    protected void execute() {
        CommandModuleTest.text = text;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
    }
}
