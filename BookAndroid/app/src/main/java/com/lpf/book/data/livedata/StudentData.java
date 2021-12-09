package com.lpf.book.data.livedata;

import androidx.lifecycle.MutableLiveData;

import com.lpf.book.data.result.Student;

public class StudentData extends MutableLiveData<Student> {
    private static StudentData instance;

    public static StudentData getInstance() {
        if (instance == null) {
            instance = new StudentData();
        }
        return instance;
    }
}
