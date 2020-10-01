package jp.momonnga.libs.commandmodule;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;


public class CommandModuleTest {

    public static String text;

    @Test
    public void テストコマンドの実行() throws Exception{
        CommandModule.registerCommand(new TestCommand());
        CommandModule.executeCommand("/test -text=テスト");
        assertThat(text,is("テスト"));
    }

}
