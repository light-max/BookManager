package com.lpf.book.data.livedata;

import androidx.lifecycle.MutableLiveData;

import com.lpf.book.data.result.Teacher;

public class TeacherData extends MutableLiveData<Teacher> {
    private static TeacherData instance;

    public static TeacherData getInstance() {
        if (instance == null) {
            instance = new TeacherData();
        }
        return instance;
    }
}
