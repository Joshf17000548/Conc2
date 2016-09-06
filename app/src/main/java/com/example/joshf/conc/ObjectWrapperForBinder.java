package com.example.joshf.conc;

import android.os.Binder;

/**
 * Created by joshf on 2016/08/02.
 */
public class ObjectWrapperForBinder extends Binder {

    private final Player mData;

    public ObjectWrapperForBinder(Player data) {
        mData = data;
    }

    public Player getData() {
        return mData;
    }
}
