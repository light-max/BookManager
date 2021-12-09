package com.lpf.book.ui.people.uinfo;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.lpf.book.R;
import com.lpf.book.api.Api;
import com.lpf.book.base.call.Base;
import com.lpf.book.base.mvp.BaseView;
import com.lpf.book.data.result.Student;
import com.lpf.book.data.result.Teacher;

public class UserInfoView extends BaseView<UserInfoActivity> {
    @Override
    public void onCreate(Base base, Bundle savedInstanceState) {
        super.onCreate(base, savedInstanceState);
        click(R.id.back, () -> this.base.finish());
    }

    public void setAdmin() {
        get(R.id.admin).setVisibility(View.VISIBLE);
    }

    public void setTeacher(Teacher teacher) {
        get(R.id.teacher).setVisibility(View.VISIBLE);
        TextView uid = get(R.id.t_uid);
        TextView name = get(R.id.t_name);
        TextView des = get(R.id.t_des);
        uid.setText(teacher.getUid());
        name.setText(teacher.getName());
        des.setText(teacher.getDes());
        click(R.id.t_post, () -> {
            Api.setTeacherInfo(
                    name.getText().toString(),
                    des.getText().toString()
            ).success(() -> {
                base.toast("修改成功");
                base.finish();
            }).run();
        });
    }

    public void setStudent(Student student) {
        get(R.id.student).setVisibility(View.VISIBLE);
        TextView uid = get(R.id.s_uid);
        TextView name = get(R.id.s_name);
        TextView sid = get(R.id.s_sid);
        TextView des = get(R.id.s_des);
        uid.setText(student.getUid());
        name.setText(student.getName());
        sid.setText(student.getSid());
        des.setText(student.getDes());
        RadioButton[] buttons = {get(R.id.sex_0), get(R.id.sex_1), get(R.id.sex_2)};
        buttons[student.getGender()].setChecked(true);
        click(R.id.s_post, () -> {
            Api.setStudentInfo(
                    des.getText().toString(),
                    getGender(buttons)
            ).success(() -> {
                base.toast("修改成功");
                base.finish();
            }).run();
        });
    }

    private int getGender(RadioButton[] buttons) {
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].isChecked()) {
                return i;
            }
        }
        return -1;
    }
}
