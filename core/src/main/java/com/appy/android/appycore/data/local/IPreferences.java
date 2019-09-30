package com.appy.android.appycore.data.local;

public interface IPreferences {
    String getName();

    void clear();

    void save();

    void save(Enum key, String value);

    void save(Enum key, int value);

    void save(Enum key, boolean value);

    int getInt(Enum key);

    String getString(Enum key);

    boolean getBoolean(Enum key);
}
