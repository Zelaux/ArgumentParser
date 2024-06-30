package argumentparser;

import argumentparser.struct.Bits;

public class ParamPattern {
    public final Param[] params;
    public final int variadicIndex;
    public final int requiredAmount;
    public Bits separators = new Bits(256);

    public ParamPattern(Param[] params) {
        this.params = params;
        {
            int idx = -1;
            for (int i = 0; i < params.length; i++) {
                if (params[i].variadic) {
                    idx = i;
                    break;
                }
            }
            variadicIndex = idx;
        }
        {
            int counter = 0;
            for (Param param : params) {
                if (!param.optional) counter++;
            }
            requiredAmount = counter;
        }
        separators(' ');


    }

    public ParamPattern separators(char... separators) {
        this.separators.clear();
        for (char separator : separators) {
            this.separators.set(separator);
        }
        return this;
    }

    public char[] separators() {
        int size = 0;
        for (int i = 0; i < separators.length(); i++) {
            if (separators.get(i)) size++;
        }
        char[] chars = new char[size];
        int charIdx = 0;
        for (int i = 0; i < separators.length(); i++) {
            if (separators.get(i)) {
                chars[charIdx] = (char) i;
                charIdx++;
            }
        }
        return chars;
    }

    public boolean isSeparator(char c) {
        return separators.get(c);
    }
}
