package io.github.gcapizzi.lang.model;

import java.util.List;

public interface LangMethod {
    LangObject invoke(LangObject target, List<LangObject> args);
}
