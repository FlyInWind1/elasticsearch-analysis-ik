package org.wltea.analyzer.core;

public class IKOtherChar implements ISegmenter {
    private int nStart = -1;
    private int nEnd = -1;

    @Override
    public void analyze(AnalyzeContext context) {
        if (nStart == -1) {
            if (CharacterUtil.CHAR_OTHER_CHAR == context.getCurrentCharType()) {
                nStart = context.getCursor();
            }
        }
        if (nStart != -1) {
            if (CharacterUtil.CHAR_OTHER_CHAR == context.getCurrentCharType()) {
                nEnd = context.getCursor();
            }
            if (CharacterUtil.CHAR_OTHER_CHAR != context.getCurrentCharType() || context.isBufferConsumed()) {
                Lexeme lexeme = new Lexeme(context.getBufferOffset(), nStart, nEnd - nStart + 1, Lexeme.TYPE_UNKNOWN);
                context.addLexeme(lexeme);
                reset();
            }
        }
    }

    @Override
    public void reset() {
        nStart = -1;
        nEnd = -1;
    }

}
